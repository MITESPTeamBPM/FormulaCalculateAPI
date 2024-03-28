package com.boc.ws;

import com.boc.dto.CalculateRequest;
import com.boc.service.EvaluateService;
import com.boc.service.impl.EvaluateServiceImpl;
import com.boc.util.StandardResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class Calculator {
    public static Logger log = Logger.getLogger("com.boc.ws.Calculator");

    @Autowired
    private final EvaluateService evaluateService = new EvaluateServiceImpl();

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<StandardResponse> testService() {
        try {
            log.info("Testing App is working");
            String output = evaluateService.testService();
            return new ResponseEntity<StandardResponse>(new StandardResponse("200", output, null), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error in testService", e);
            return new ResponseEntity<StandardResponse>(new StandardResponse("500", "Error in testService", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/run", method = RequestMethod.POST)
    public ResponseEntity<StandardResponse> calService(@RequestBody CalculateRequest request) {
        try {
            log.info("Request received for formula: " + request.getFormula() + " " + new Date());
            String output = evaluateService.solveFormula(request.getFormula());
            log.info("Response: " + output + " " + new Date());
            return new ResponseEntity<StandardResponse>(new StandardResponse("200", output, null), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error in calService", e);
            return new ResponseEntity<StandardResponse>(new StandardResponse("500", e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
