package com.github.supercodingspring.web.dto.items;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class BuyOrder {
    private Integer itemId;
    private Integer itemNums;
}
