package com.github.supercodingspring.repository.storeSales;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class StoreSales {
    private Integer id;
    private String storeName;
    private Integer amount;
}
