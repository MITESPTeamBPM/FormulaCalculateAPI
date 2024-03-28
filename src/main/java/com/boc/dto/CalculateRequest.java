package com.boc.dto;

public class CalculateRequest {
    private String formulaName;
    private String formula;

    public CalculateRequest() {
    }

    public CalculateRequest(String formulaName, String formula) {
        this.setFormulaName(formulaName);
        this.setFormula(formula);
    }

    public String getFormulaName() {
        return formulaName;
    }

    public void setFormulaName(String formulaName) {
        this.formulaName = formulaName;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    @Override
    public String toString() {
        return "CalculateRequest{" +
                "formulaName='" + formulaName + '\'' +
                ", formula='" + formula + '\'' +
                '}';
    }
}
