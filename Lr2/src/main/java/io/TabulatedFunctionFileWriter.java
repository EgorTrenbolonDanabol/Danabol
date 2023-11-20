package io;
import java.io.*;
import functions.*;
public class TabulatedFunctionFileWriter {
    public static void main(String[] args) {
        String arrayFilePath = "output/array_function.txt";
        String linkedListFilePath = "output/linked_list_function.txt";

        double[] x = {1, 2, 3, 4};
        double[] y = {2, 3, 4, 5};

        try (BufferedWriter bfw_a = new BufferedWriter(new FileWriter(arrayFilePath));
             BufferedWriter bfw_ll = new BufferedWriter(new FileWriter(linkedListFilePath))) {

            ArrayTabulatedFunction a = new ArrayTabulatedFunction(x, y);
            LinkedListTabulatedFunction ll = new LinkedListTabulatedFunction(x, y);

            FunctionsIO.writeTabulatedFunction(bfw_a, a);
            FunctionsIO.writeTabulatedFunction(bfw_ll, ll);

        } catch (IOException er) {
            er.printStackTrace();
        }
    }
}