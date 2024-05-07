package com.github.supercodingspring.repository.users;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class UserEntity {
    private Integer userId;
    private String userName;
    private String likeTravelPlace;
    private String phoneNum;
}