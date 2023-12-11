package ru.ssau.lr7.concurrent;

import ru.ssau.lr7.functions.LinkedListTabulatedFunction;
import ru.ssau.lr7.functions.UnitFunction;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
public class MultiplyingTaskExecutor {
    public static void main(String[] args) {
        LinkedListTabulatedFunction ll = new LinkedListTabulatedFunction(new UnitFunction(), 1, 1000, 1000);
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Thread(new MultiplyingTask(ll)));
        }
        for (Thread thread : list) {
            thread.start();
        }
        Vector<Thread> activeThreads = new Vector<>(list);
        while (!activeThreads.isEmpty()) {
            for (int i = 0; i < activeThreads.size(); i++) {
                if (!activeThreads.get(i).isAlive())
                    activeThreads.remove(i--);
            }
        }
        System.out.println(ll);
    }
}