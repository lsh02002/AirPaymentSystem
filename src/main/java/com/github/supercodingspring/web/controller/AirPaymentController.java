package com.github.supercodingspring.web.controller;

import com.github.supercodingspring.service.AirPaymentService;
import com.github.supercodingspring.web.dto.airline.PaymentRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/air-reservation")
@AllArgsConstructor
public class AirPaymentController {

    private AirPaymentService airPaymentService;

    @PostMapping("/payments")
    public String makePayment(@RequestBody PaymentRequest request){
        Integer payNum = airPaymentService.makePayment(request);

        return "요청하신 결제 중 " + payNum + "건 진행완료 되었습니다.";
    }
}
