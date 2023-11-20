package io;

import java.io.*;
import functions.*;
import operations.TabulatedDifferentialOperator;
public class ArrayTabulatedFunctionSerialization {
    public static void main(String[] args) {
        String filePath = "output/serialized_array_functions.bin";

        try (FileOutputStream fos = new FileOutputStream(filePath);
             BufferedOutputStream bfos = new BufferedOutputStream(fos)) {

            // Create and serialize the ArrayTabulatedFunction and its derivatives
            ArrayTabulatedFunction a = new ArrayTabulatedFunction(new SqrFunction(), 0, 10, 101);
            TabulatedDifferentialOperator op = new TabulatedDifferentialOperator();
            TabulatedFunction a1 = op.derive(a);
            TabulatedFunction a2 = op.derive(a1);

            FunctionsIO.serialize(bfos, a);
            FunctionsIO.serialize(bfos, a1);
            FunctionsIO.serialize(bfos, a2);

        } catch (IOException er) {
            er.printStackTrace();
        }

        try (FileInputStream fis = new FileInputStream(filePath);
             BufferedInputStream bfis = new BufferedInputStream(fis)) {

            // Deserialize and print the functions
            ArrayTabulatedFunction deserializedA = (ArrayTabulatedFunction) FunctionsIO.deserialize(bfis);
            ArrayTabulatedFunction deserializedA1 = (ArrayTabulatedFunction) FunctionsIO.deserialize(bfis);
            ArrayTabulatedFunction deserializedA2 = (ArrayTabulatedFunction) FunctionsIO.deserialize(bfis);

            System.out.println(deserializedA.toString());
            System.out.println(deserializedA1.toString());
            System.out.println(deserializedA2.toString());

        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}