package com.github.supercodingspring.web.controller;

import com.github.supercodingspring.config.customExceptionHandler.CustomException;
import com.github.supercodingspring.service.AirPaymentService;
import com.github.supercodingspring.web.dto.airline.PaymentRequest;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/air-reservation")
@AllArgsConstructor
@Slf4j
public class AirPaymentController {

    private AirPaymentService airPaymentService;

    @ApiOperation(value = "결제 지불", notes = "결제한 내용을 지불합니다.")
    @PostMapping("/payments")
    public String makePayment(@RequestBody PaymentRequest request) throws CustomException {

        log.info("결제를 시작합니다");

        Integer payNum = airPaymentService.makePayment(request);

        return "요청하신 결제 중 " + payNum + "건 진행완료 되었습니다.";
    }
}
