package com.boc.service.impl;

import com.boc.service.EvaluateService;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

@Service
public class EvaluateServiceImpl implements EvaluateService {

    public static Logger log = Logger.getLogger("com.boc.service.impl.EvaluateServiceImpl");

    @Override
    public String testService() {
        log.info("testService: " + new Date());
        return "Service is working";
    }

    @Override
    public String solveFormula(String formula) {
        log.info("solveFormula: " + formula + " " + new Date());

        return evaluateFormula(formula);
    }

    @Override
    public String evaluateFormula(String formula) {
        log.info("evaluateFormula: " + formula + " " + new Date());
        try {
            Workbook workbook = new XSSFWorkbook();
            log.debug("evaluateFormula - workbook: " + new Date());
            Sheet sheet = workbook.createSheet();
            log.debug("evaluateFormula - sheet: " + new Date());
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            log.debug("evaluateFormula - evaluator: " + new Date());
            Row row = sheet.createRow(0);
            log.debug("evaluateFormula - row: " + new Date());
            int i = new Random().nextInt(100);
            log.debug("evaluateFormula - i: " + i + " " + new Date());
            Cell cell = row.createCell(i);
            log.debug("evaluateFormula - formula: " + formula + " " + new Date());
            cell.setCellFormula(formula);
            log.debug("evaluateFormula - cell: " + new Date());

            evaluator.evaluateFormulaCell(cell);
            log.debug("evaluateFormula - evaluateFormulaCell: " + new Date());
            
            CellValue cellValue = evaluator.evaluate(cell);
            log.info("evaluateFormula - cellValue: " + new Date());

            return getCellValueAsString(cellValue);
        } catch (Exception e) {
            log.error(e.getMessage());
            return "#ERROR - " + e.getMessage();
        } finally {
            log.info("evaluateFormula - finally: " + new Date());
        }
    }

    @Override
    public String getCellValueAsString(CellValue cellValue) {
        log.info("getCellValueAsString: " + cellValue + " " + new Date());
        switch (cellValue.getCellType()) {
            case STRING:
                return cellValue.getStringValue();
            case NUMERIC:
                return Double.toString(cellValue.getNumberValue());
            case BOOLEAN:
                return Boolean.toString(cellValue.getBooleanValue());
            case ERROR:
                return FormulaError.forInt(cellValue.getErrorValue()).getString();
            default:
                return "Unknown Type";
        }
    }
}
