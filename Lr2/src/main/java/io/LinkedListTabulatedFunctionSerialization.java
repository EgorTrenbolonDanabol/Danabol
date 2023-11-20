package io;

import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;
import operations.TabulatedDifferentialOperator;

import java.io.*;

public class LinkedListTabulatedFunctionSerialization {
    public static void main(String[] args) {
        try(BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream("output/serialized_linked_list_functions.bin"))) {
            double[] xValues = {1, 2, 3, 4, 5};
            double[] yValues = {1, 4, 9, 16, 25};
            TabulatedFunction originalFunction = new LinkedListTabulatedFunction(xValues, yValues);
            TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator();
            TabulatedFunction firstDerivative = operator.derive(originalFunction);
            TabulatedFunction secondDerivative = operator.derive(firstDerivative);
            FunctionsIO.serialize(outputStream, originalFunction);
            FunctionsIO.serialize(outputStream, firstDerivative);
            FunctionsIO.serialize(outputStream, secondDerivative);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("output/serialized_linked_list_functions.bin"))) {
            System.out.println(FunctionsIO.deserialize(inputStream));
            System.out.println(FunctionsIO.deserialize(inputStream));
            System.out.println(FunctionsIO.deserialize(inputStream));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}