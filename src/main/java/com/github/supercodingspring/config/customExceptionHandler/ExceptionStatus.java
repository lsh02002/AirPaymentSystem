package com.github.supercodingspring.config.customExceptionHandler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionStatus {

    //PASSWORDS_DO_NOT_MATCH(401,"비밀번호가 일치 하지 않습니다."),
    //INVALID_TOKEN(401,"유효하지 않은 토큰입니다."),
    //AUTHORIZATION_EXCEPTION(403,"접근할 수 있는 권한이 없습니다."),
    POST_IS_EMPTY(404,"해당 품목이 존재 하지 않습니다."),
    //COMMENT_IS_EMPTY(404,"해당 댓글이 존재 하지 않습니다."),
    USER_IS_NOT_EXIST(404,"사용자가 존재 하지 않습니다."),
    BAD_REQUEST(400, "요청하신 사용자와 티켓의 갯수가 맛지 않습니다."),
    INVALID_RESPONSE(409, "예약 수가 2개이상입니다.");
    //REQUEST_IS_EMPTY(404,"요청이 존재하지 않습니다."),
    //PAGE_IS_NOT_EXIST(404,"요청하신 페이지 내역이 존재하지 않습니다."),
    //USERNAME_IS_EXIST(409,"이미 등록된 username입니다.");

    private final int statusCode;
    private final String message;
}
