package ru.ssau.lr7;

import ru.ssau.lr7.functions.ArrayTabulatedFunction;
import ru.ssau.lr7.functions.TabulatedFunction;
import ru.ssau.lr7.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.lr7.functions.factory.TabulatedFunctionFactory;
import ru.ssau.lr7.operations.TabulatedFunctionOperationService;

public class Operations {
    private String operation;
    private TabulatedFunctionOperationService CombinedTabulatedFunction;
    TabulatedFunction operand1;
    TabulatedFunction operand2;

    public TabulatedFunctionOperationService getCombinedTabulatedFunction() {
        return CombinedTabulatedFunction;
    }

    public void setCombinedTabulatedFunction(TabulatedFunctionOperationService combinedTabulatedFunction) {
        CombinedTabulatedFunction = combinedTabulatedFunction;
    }


    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}