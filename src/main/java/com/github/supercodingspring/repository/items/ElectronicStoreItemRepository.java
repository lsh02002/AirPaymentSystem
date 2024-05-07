package com.github.supercodingspring.repository.items;

import com.github.supercodingspring.config.customExceptionHandler.CustomException;

import java.util.List;
import java.util.Optional;

public interface ElectronicStoreItemRepository {

    List<ItemEntity> findAllItems();

    Optional<Integer> saveItem(ItemEntity itemEntity);

    Optional<ItemEntity> updateItemEntity(Integer idInt, ItemEntity itemEntity);

    void deleteItem(int parseInt);

    Optional<ItemEntity> findItemById(Integer idInt) throws CustomException;
    void updateItemStock(Integer itemId, Integer i);
}
