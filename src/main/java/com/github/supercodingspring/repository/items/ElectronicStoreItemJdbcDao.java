package com.github.supercodingspring.repository.items;

import com.github.supercodingspring.config.customExceptionHandler.CustomException;
import com.github.supercodingspring.config.customExceptionHandler.ExceptionStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class ElectronicStoreItemJdbcDao implements ElectronicStoreItemRepository {

    private final JdbcTemplate jdbcTemplate;

    static RowMapper<ItemEntity> itemEntityRowMapper = (((rs, rowNum) ->
                new ItemEntity(
                        rs.getInt("id"),
                        rs.getNString("name"),
                        rs.getNString("type"),
                        rs.getInt("price"),
                        rs.getNString("cpu"),
                        rs.getNString("capacity"))));

    public ElectronicStoreItemJdbcDao(@Qualifier("jdbcTemplate1") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ItemEntity> findAllItems() {
        return jdbcTemplate.query("SELECT * FROM item", itemEntityRowMapper);
    }

    @Override
    public Optional<Integer> saveItem(ItemEntity itemEntity) {
        jdbcTemplate.update("INSERT INTO item(name, type, price, cpu, capacity) VALUES (?, ?, ?, ?, ?)",
                            itemEntity.getName(), itemEntity.getType(), itemEntity.getPrice(),
                            itemEntity.getCpu(), itemEntity.getCapacity());

        ItemEntity itemEntityFound = jdbcTemplate.queryForObject("SELECT * FROM item WHERE name = ?", itemEntityRowMapper, itemEntity.getName());
        return Optional.ofNullable(itemEntityFound.getId());
    }

    @Override
    public Optional<ItemEntity> updateItemEntity(Integer idInt, ItemEntity itemEntity) {
        jdbcTemplate.update("UPDATE item " +
                            "SET name = ?, type = ?, price = ?, cpu =?, capacity = ?" +
                            "WHERE id = ?",
                            itemEntity.getName(), itemEntity.getType(), itemEntity.getPrice(),
                            itemEntity.getCpu(), itemEntity.getCapacity(), idInt);

        ItemEntity itemEntitySelected = jdbcTemplate.queryForObject("SELECT * FROM item WHERE id = ?", itemEntityRowMapper, idInt);
        return Optional.ofNullable(itemEntitySelected);
    }

    @Override
    public void deleteItem(int idInt) {
        jdbcTemplate.update("DELETE FROM item WHERE id = ?", idInt);
    }

    @Override
    public Optional<ItemEntity> findItemById(Integer idInt) throws CustomException {
        try {
            ItemEntity itemEntity = jdbcTemplate.queryForObject("SELECT * FROM item WHERE id = ?", itemEntityRowMapper, idInt);
            return Optional.ofNullable(itemEntity);
        }catch (EmptyResultDataAccessException e){
            throw new CustomException(ExceptionStatus.POST_IS_EMPTY);
        }
    }

    @Override
    public void updateItemStock(Integer itemId, Integer stock) {
        jdbcTemplate.update("UPDATE item " +
                            " SET stock = ? " +
                            " WHERE id = ? ", stock, itemId);
    }
}
