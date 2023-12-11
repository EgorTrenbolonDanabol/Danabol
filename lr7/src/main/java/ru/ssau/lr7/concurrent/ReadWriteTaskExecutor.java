package ru.ssau.lr7.concurrent;

import ru.ssau.lr7.functions.ConstantFunction;
import ru.ssau.lr7.functions.LinkedListTabulatedFunction;
import ru.ssau.lr7.functions.TabulatedFunction;


public class ReadWriteTaskExecutor {
    public static void main(String[] args) {
        ConstantFunction source = new ConstantFunction(-1);
        TabulatedFunction tabulatedFunction = new LinkedListTabulatedFunction(source, 1, 1000, 1000);
        Thread threadRead = new Thread(new ReadTask(tabulatedFunction));
        Thread threadWrite = new Thread(new WriteTask(tabulatedFunction, 0.5));

        threadRead.start();
        threadWrite.start();



    }
}
