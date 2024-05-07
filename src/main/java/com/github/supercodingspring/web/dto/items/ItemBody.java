package com.github.supercodingspring.web.dto.items;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class ItemBody {

    private String name;
    private String type;
    private Integer price;
    private Spec spec;

}
