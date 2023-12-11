package ru.ssau.lr7.io;


import ru.ssau.lr7.functions.TabulatedFunction;
import ru.ssau.lr7.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.lr7.functions.factory.LinkedListTabulatedFunctionFactory;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
public class TabulatedFunctionFileReader {
    public static void main(String[] args) {
        String filePath = "input/function.txt";

        try (FileReader fr_a = new FileReader(filePath);
             FileReader fr_ll = new FileReader(filePath)) {

            BufferedReader bfr_a = new BufferedReader(fr_a);
            BufferedReader bfr_ll = new BufferedReader(fr_ll);

            ArrayTabulatedFunctionFactory atff = new ArrayTabulatedFunctionFactory();
            LinkedListTabulatedFunctionFactory lltff = new LinkedListTabulatedFunctionFactory();

            TabulatedFunction atf = FunctionsIO.readTabulatedFunction(bfr_a, atff);
            TabulatedFunction lltf = FunctionsIO.readTabulatedFunction(bfr_ll, lltff);

            System.out.println(atf.toString());
            System.out.println(lltf.toString());
        } catch (IOException er) {
            er.printStackTrace();
        }
    }
}