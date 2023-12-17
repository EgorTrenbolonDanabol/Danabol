package ru.ssau.lr7;

public class Settings {
    String TabulatedFunctionFactory;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    private String operation;

    public Settings(String tabulatedFunctionFactory) {
        TabulatedFunctionFactory = tabulatedFunctionFactory;
    }


    public String getTabulatedFunctionFactory() {
        return TabulatedFunctionFactory;
    }

    public void setTabulatedFunctionFactory(String tabulatedFunctionFactory) {
        TabulatedFunctionFactory = tabulatedFunctionFactory;
    }


}
