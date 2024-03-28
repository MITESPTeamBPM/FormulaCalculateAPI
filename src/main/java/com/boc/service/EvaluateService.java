package com.boc.service;

import org.apache.poi.ss.usermodel.CellValue;

public interface EvaluateService {
    public String testService();

    public String solveFormula(String formula);

    public String evaluateFormula(String formula);

    public String getCellValueAsString(CellValue cellValue);
}
