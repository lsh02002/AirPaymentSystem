package com.github.supercodingspring.repository.storeSales;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class StoreSalesJdbcTemplateDao implements StoreSalesRepository {

    private final JdbcTemplate jdbcTemplate;

    public StoreSalesJdbcTemplateDao(@Qualifier("jdbcTemplate1") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    static RowMapper<StoreSales> storeSalesRowMapper = (((rs, rowNum) ->
                new StoreSales(
                        rs.getInt("id"),
                        rs.getNString("store_name"),
                        rs.getInt("amount")
                )
            ));

    @Override
    public Optional<StoreSales> findStoreSalesById(Integer storeId) {
        StoreSales storeSales = jdbcTemplate.queryForObject("SELECT * from store_sales WHERE id = ?", storeSalesRowMapper, storeId);
        return Optional.ofNullable(storeSales);
    }

    @Override
    public void updateSalesAmount(Integer storeId, Integer amount) {
        jdbcTemplate.update("UPDATE store_sales " +
                            " SET amount = ? " +
                            " WHERE id = ? ", amount, storeId);
    }
}
