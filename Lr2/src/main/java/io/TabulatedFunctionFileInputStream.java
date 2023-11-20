package io;

import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.LinkedListTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;
import operations.TabulatedDifferentialOperator;

import java.io.*;
public class TabulatedFunctionFileInputStream {
    public static void main(String[] args) {
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("input/binary function.bin"))) {
            TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
            TabulatedFunction function = FunctionsIO.readTabulatedFunction(inputStream, factory);
            System.out.println(function.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("Введите размер и значения функции");
            LinkedListTabulatedFunctionFactory linkedListFactory = new LinkedListTabulatedFunctionFactory();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            TabulatedFunction linkedListFunction = FunctionsIO.readTabulatedFunction(reader, linkedListFactory);
            TabulatedDifferentialOperator differentialOperator = new TabulatedDifferentialOperator();
            System.out.println(differentialOperator.derive(linkedListFunction).toString());
        } catch (IOException er) {
            er.printStackTrace();
        }
    }
}
