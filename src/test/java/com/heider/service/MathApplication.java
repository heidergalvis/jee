package com.heider.service;

public class MathApplication {

    private ICalculatorService calculatorService;

    public int doubleOf(int number) {

        return this.calculatorService.doubleOf(number);
    }

    public void setCalculatorService(ICalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    public ICalculatorService getCalculatorService() {
        return this.calculatorService;
    }
}
