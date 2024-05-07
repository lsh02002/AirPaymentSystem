package com.github.supercodingspring.service;

import com.github.supercodingspring.config.customExceptionHandler.CustomException;
import com.github.supercodingspring.config.customExceptionHandler.ExceptionStatus;
import com.github.supercodingspring.repository.items.ElectronicStoreItemRepository;
import com.github.supercodingspring.repository.items.ItemEntity;
import com.github.supercodingspring.repository.storeSales.StoreSales;
import com.github.supercodingspring.repository.storeSales.StoreSalesRepository;
import com.github.supercodingspring.web.dto.items.BuyOrder;
import com.github.supercodingspring.web.dto.items.Item;
import com.github.supercodingspring.web.dto.items.ItemBody;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ElectronicStoreItemService {
    private ElectronicStoreItemRepository electronicStoreItemRepository;
    private StoreSalesRepository storeSalesRepository;

    public List<Item> findAllItem() throws CustomException {
        List<ItemEntity> itemEntities = electronicStoreItemRepository.findAllItems().orElseThrow(()->new CustomException(ExceptionStatus.POST_IS_EMPTY));
        return itemEntities.stream().map(Item::new).collect(Collectors.toList());
    }

    public Integer savaItem(ItemBody itemBody) throws CustomException {
        ItemEntity itemEntity = new ItemEntity(null, itemBody.getName(), itemBody.getType(),
                                               itemBody.getPrice(), itemBody.getSpec().getCpu(), itemBody.getSpec().getCapacity());
        return electronicStoreItemRepository.saveItem(itemEntity).orElseThrow(()->new CustomException(ExceptionStatus.POST_IS_EMPTY));
    }

    public Item findItemById(String id) throws CustomException {
        Integer idInt = Integer.parseInt(id);
        ItemEntity itemEntity = electronicStoreItemRepository.findItemById(idInt).orElseThrow(()->new CustomException(ExceptionStatus.POST_IS_EMPTY));

        Item item = new Item(itemEntity);
        return item;
    }

    public List<Item> findItemsByIds(List<String> ids) throws CustomException{
        List<ItemEntity> itemEntities = electronicStoreItemRepository.findAllItems().orElseThrow(()->new CustomException(ExceptionStatus.POST_IS_EMPTY));;
        return itemEntities.stream()
                                       .map(Item::new)
                                       .filter((item -> ids.contains(item.getId())))
                                       .collect(Collectors.toList());
    }

    public void deleteItem(String id) throws CustomException{
        Integer idInt = Integer.parseInt(id);
        electronicStoreItemRepository.deleteItem(idInt);
    }

    public Item updateItem(String id, ItemBody itemBody) throws CustomException{
        Integer idInt = Integer.valueOf(id);
        ItemEntity itemEntity = new ItemEntity(idInt, itemBody.getName(),
                                               itemBody.getType(), itemBody.getPrice(),
                                               itemBody.getSpec().getCpu(), itemBody.getSpec().getCapacity());

        ItemEntity itemEntityUpdated = electronicStoreItemRepository.updateItemEntity(idInt, itemEntity).orElseThrow(()->new CustomException(ExceptionStatus.POST_IS_EMPTY));;

        return new Item(itemEntityUpdated);
    }

    @Transactional(transactionManager = "tm1")
    public Integer buyItems(BuyOrder buyOrder) throws CustomException{
        // 1. BuyOrder 에서 상품 ID와 수량을 얻어낸다.
        // 2. 상품을 조회하여 수량이 얼마나 있는 지 확인한다.
        // 3. 상품의 수량과 가격을 가지고 계산하여 총 가격을 구한다.
        // 4. 상품의 재고에 기존 계산한 재고를 구매하는 수량을 뺸다.
        // 5. 상품 구매하는 수량 * 가격 만큼 가계 매상으로 올린다.
        // (단, 재고가 아예 없거나 매장을 찾을 수 없으면 살 수 없다. )

        Integer itemId = buyOrder.getItemId();
        Integer itemNums = buyOrder.getItemNums();

        System.out.println("itemId: " + itemId);
        ItemEntity itemEntity = electronicStoreItemRepository.findItemById(itemId).orElseThrow(()->new CustomException(ExceptionStatus.POST_IS_EMPTY));

        if (itemEntity.getStoreId() == null ) throw new CustomException(ExceptionStatus.POST_IS_EMPTY);
        if (itemEntity.getStock() <= 0) throw new CustomException(ExceptionStatus.POST_IS_EMPTY);

        Integer successBuyItemNums;
        if ( itemNums >= itemEntity.getStock() ) successBuyItemNums = itemEntity.getStock();
        else successBuyItemNums = itemNums;

        Integer totalPrice = successBuyItemNums * itemEntity.getPrice();

        // Item 재고 감소
        electronicStoreItemRepository.updateItemStock(itemId, itemEntity.getStock() - successBuyItemNums);

        if (successBuyItemNums == 4) throw new CustomException(ExceptionStatus.BAD_REQUEST);

        // 매장 매상 추가
        StoreSales storeSales = storeSalesRepository.findStoreSalesById(itemEntity.getStoreId()).orElseThrow(()->new CustomException(ExceptionStatus.USER_IS_NOT_EXIST));
        storeSalesRepository.updateSalesAmount(itemEntity.getStoreId(), storeSales.getAmount() + totalPrice);

        return successBuyItemNums;
    }
}
