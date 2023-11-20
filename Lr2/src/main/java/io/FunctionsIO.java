package io;

import java.io.*;
import functions.*;
import functions.factory.*;
import java.text.NumberFormat;
import java.util.Locale;
import java.text.ParseException;
public final class FunctionsIO {
    // Приватный конструктор
    private FunctionsIO() {
        throw new UnsupportedOperationException();
    }
    public static void writeTabulatedFunction(BufferedWriter writer, TabulatedFunction function) throws IOException {
        PrintWriter printwriter = new PrintWriter(writer);
        printwriter.println(function.getCount());
        for (Point point : function) {
            printwriter.printf("%f %f\n", point.x, point.y);
        }
        writer.flush();
    }
    public static TabulatedFunction readTabulatedFunction(BufferedReader reader, TabulatedFunctionFactory factory) throws IOException {
        int count = 0;

        try {
            count = Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            // Можно добавить логгирование ошибки или другие меры обработки ошибки ввода.
        }

        double[] xValues = new double[count];
        double[] yValues = new double[count];
        NumberFormat nf = NumberFormat.getInstance(Locale.forLanguageTag("ru"));

        for (int i = 0; i < count; i++) {
            try {
                String xy = reader.readLine();
                String[] xay = xy.split(" ");

                xValues[i] = nf.parse(xay[0]).doubleValue();
                yValues[i] = nf.parse(xay[1]).doubleValue();
            } catch (IOException | ParseException e) {
                e.printStackTrace();
                // Можно добавить логгирование ошибки или другие меры обработки ошибки ввода.
            }
        }
        return factory.create(xValues, yValues);
    }
    public static void serialize(BufferedOutputStream stream, TabulatedFunction function) throws IOException {
        ObjectOutputStream objstream = new ObjectOutputStream(stream);
        objstream.writeObject(function);
        stream.flush();
    }
    public static void writeTabulatedFunction(BufferedOutputStream outputStream, TabulatedFunction function){
        try{
            DataOutputStream dos = new DataOutputStream(outputStream);
            dos.writeInt(function.getCount());
            for (Point point : function) {
                dos.writeDouble(point.x);
                dos.writeDouble(point.y);
            }
            dos.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static TabulatedFunction readTabulatedFunction(BufferedInputStream inputStream, TabulatedFunctionFactory factory) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        int size = dataInputStream.readInt();
        double[] xValues = new double[size];
        double[] yValues = new double[size];

        for (int i = 0; i < size; i++) {
            xValues[i] = dataInputStream.readDouble();
            yValues[i] = dataInputStream.readDouble();
        }

        return factory.create(xValues, yValues);
    }

    public static TabulatedFunction deserialize(BufferedInputStream stream) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(stream);
        return (TabulatedFunction) objectInputStream.readObject();
    }

}