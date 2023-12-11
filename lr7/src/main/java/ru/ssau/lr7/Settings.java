package ru.ssau.lr7;

public class Settings {
    String TabulatedFunctionFactory;

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
