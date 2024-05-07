package com.github.supercodingspring.web.controller;

import com.github.supercodingspring.config.customExceptionHandler.CustomException;
import com.github.supercodingspring.service.ElectronicStoreItemService;
import com.github.supercodingspring.web.dto.items.BuyOrder;
import com.github.supercodingspring.web.dto.items.Item;
import com.github.supercodingspring.web.dto.items.ItemBody;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Slf4j
public class ElectronicStoreController {

    private final ElectronicStoreItemService electronicStoreItemService;

    @ApiOperation(value = "전체 물품 검색", notes = "전체 물품을 검색합니다.")
    @GetMapping("/items")
    public List<Item> findAllItem() throws CustomException{

        log.info("전체 물품 검색을 시작합니다");

        return electronicStoreItemService.findAllItem();
    }

    @ApiOperation(value = "물품 입력", notes = "물품을 입력합니다.")
    @PostMapping("/items")
    public String registerItem(@RequestBody ItemBody itemBody) throws CustomException{

        log.info("물품 입력을 시작합니다");

        Integer itemId = electronicStoreItemService.savaItem(itemBody);
        return "ID: " +  itemId;
    }

    @ApiOperation(value = "아이디 인자로 검색", notes = "아이디 인자(path-variable)로 물품을 검색합니다.")
    @GetMapping("/items/{id}")
    public Item findItemByPathId(@PathVariable String id) throws CustomException {

        log.info("아이디 인자로 검색을 시작합니다");

        return electronicStoreItemService.findItemById(id);
    }

    @ApiOperation(value = "아이디로 질의 검색", notes = "아이디로 물품을 질의 검색합니다.")
    @GetMapping("/items-query")
    public Item findItemByQueryId(@RequestParam("id") String id) throws CustomException {

        log.info("아이디로 질의 검색을 시작합니다");

        return electronicStoreItemService.findItemById(id);
    }

    @ApiOperation(value = "여러 아이디로 질의 검색", notes = "여러 아이디들로 물품을 질의 검색합니다.")
    @GetMapping("/items-queries")
    public List<Item> findItemByQueryIds(@RequestParam("id") List<String> ids) throws CustomException {

        log.info("여러 아이디로 검색을 시작합니다");

        return electronicStoreItemService.findItemsByIds(ids);
    }

    @ApiOperation(value = "물품 삭제", notes = "물품을 삭제합니다.")
    @DeleteMapping("/items/{id}")
    public String deleteItemByPathId(@PathVariable String id) throws CustomException {

        log.info("삭제를 하겠습니다");

        electronicStoreItemService.deleteItem(id);
        return "Object with id =" + id + "has been deleted";
    }

    @ApiOperation(value = "물품 수정", notes = "물품을 수정합니다.")
    @PutMapping("/items/{id}")
    public Item updateItem(@PathVariable String id, @RequestBody ItemBody itemBody) throws CustomException {

        log.info("물품 수정을 시작합니다");

        return electronicStoreItemService.updateItem(id, itemBody);
    }

    @ApiOperation(value = "물품 구매", notes = "물품을 구매합니다.")
    @PostMapping("/items/buy")
    public String buyItem(@RequestBody BuyOrder buyOrder) throws CustomException{

        log.info("물품 구매를 시작합니다");

        Integer orderItemNums = electronicStoreItemService.buyItems(buyOrder);
        return "요청하신 Item 중 " + orderItemNums + "개를 구매 하였습니다.";
    }
}
