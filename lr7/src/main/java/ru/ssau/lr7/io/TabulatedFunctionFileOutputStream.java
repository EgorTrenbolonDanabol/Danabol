package ru.ssau.lr7.io;

import ru.ssau.lr7.functions.ArrayTabulatedFunction;
import ru.ssau.lr7.functions.LinkedListTabulatedFunction;
import ru.ssau.lr7.functions.TabulatedFunction;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TabulatedFunctionFileOutputStream {
    public static void main(String[] args) {
        try (BufferedOutputStream arrayBufferedOutputStream = new BufferedOutputStream(new FileOutputStream("output/array function.bin"));
             BufferedOutputStream linkedListBufferedOutputStream = new BufferedOutputStream(new FileOutputStream("output/linked list function.bin"))) {
            double[] xValues = {0.0, 0.5, 1.0, 1.5, 2.0};
            double[] yValues = {0.0, 0.25, 1.0, 2.25, 4.0};
            TabulatedFunction arrayFunction = new ArrayTabulatedFunction(xValues, yValues);
            TabulatedFunction listFunction = new LinkedListTabulatedFunction(xValues, yValues);

            FunctionsIO.writeTabulatedFunction(arrayBufferedOutputStream, arrayFunction);
            FunctionsIO.writeTabulatedFunction(linkedListBufferedOutputStream, listFunction);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}