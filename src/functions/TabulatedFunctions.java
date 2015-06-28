package functions;

import java.io.*;
import java.lang.reflect.Constructor;

/**
 * Created by gijoe on 6/21/2015.
 */
public class TabulatedFunctions {

    private static TabulatedFunctionFactory tabulatedFunctionFactory = new ArrayTabulatedFunction.ArrayTabulatedFunctionFactory();

    private TabulatedFunctions() {
    }

    public static void setTabulatedFunctionFactory(TabulatedFunctionFactory tabulatedFunctionFactory) {
        TabulatedFunctions.tabulatedFunctionFactory = tabulatedFunctionFactory;
    }

//    public static TabulatedFunction createTabulatedFunction(Class c1, FunctionPoint[] points) {
//        boolean flag = false;
//        for (Class c : c1.getInterfaces()) {
//            if (c.equals(TabulatedFunction.class)) {
//                flag = true;
//            }
//        }
//        if (!flag) {
//            throw new IllegalArgumentException("Class is not implements interface TabulatedFunction");
//        }
//        try {
//            return (TabulatedFunction) c1.getConstructor(new Class[] {FunctionPoint[].class}).newInstance(points);
//        } catch (Exception ex) {
//            throw new IllegalArgumentException(ex);
//        }
//    }

    public static TabulatedFunction createTabulatedFunction(Class c1, FunctionPoint[] points) {
        boolean flag = false;
        for (Class c : c1.getInterfaces()) {
            if (c.equals(TabulatedFunction.class)) {
                flag = true;
            }
        }
        if (!flag) {
            throw new IllegalArgumentException("����� �� ��������� ��������� TabulatedFunction.");
        }
        try {

            Constructor constructors = c1.getConstructor(FunctionPoint[].class);
            return (TabulatedFunction) constructors.newInstance(new Object[]{points});
        } catch (Throwable e) //��� ������ �����
        {
            throw new IllegalArgumentException(e);
        }
    }

    public static TabulatedFunction createTabulatedFunction(double leftX, double rightX, int pointsCount) {
        return tabulatedFunctionFactory.createTabulatedFunction(leftX, rightX, pointsCount);
    }

    public static TabulatedFunction createTabulatedFunction(double leftX, double rightX, double[] values) {
        return tabulatedFunctionFactory.createTabulatedFunction(leftX, rightX, values);
    }

    public static TabulatedFunction createTabulatedFunction(FunctionPoint[] points) {
        return tabulatedFunctionFactory.createTabulatedFunction(points);
    }

    public static TabulatedFunction tabulate(Function f, double leftX, double rightX, int pointsCount) {
        try {
            if (leftX <= f.getLeftDomainBorder() || rightX >= f.getRightDomainBorder()) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException ex) {
            System.out.println(ex);
        }

        FunctionPoint[] points = new FunctionPoint[pointsCount];
        double tmpX = 0;
        double stepX = (rightX - leftX) / (pointsCount - 1);
        for (int i = 0; i < pointsCount; i++) {
            tmpX = leftX + i * stepX;
            points[i] = new FunctionPoint(tmpX, f.getFunctionValue(tmpX));
        }
        TabulatedFunction tmp = tabulatedFunctionFactory.createTabulatedFunction(points);
        return tmp;
    }

    public static void outputTabulatedFunction(TabulatedFunction function, OutputStream out) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
        objectOutputStream.writeObject(function);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    public static TabulatedFunction inputTabulatedFunction(InputStream in) throws IOException {
        ObjectInputStream oin = new ObjectInputStream(in);
        TabulatedFunction function = null;
        try {
            function = (TabulatedFunction) oin.readObject();
        } catch (ClassNotFoundException ex) {
            oin.close();
            return function;
        } finally {
            oin.close();
        }
        return function;
    }
}
