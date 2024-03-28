package com.boc.ws;

import com.boc.dto.CalculateRequest;
import com.boc.service.impl.EvaluateServiceImpl;
import com.boc.util.StandardResponse;
import org.springframework.http.ResponseEntity;

public class Main {
    public static void main(String[] args) {
        String response = new EvaluateServiceImpl().evaluateFormula("IF(OR(ISERROR(43/46)-1,43=0),\"WWW\",(43/46)-1)");
        System.out.println(response);
    }
}