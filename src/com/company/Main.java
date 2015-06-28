package com.company;

import functions.*;
import functions.basic.Cos;
import functions.basic.Sin;
import gui.FunctionParameters;
import gui.PrimaryWindowProgram;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {

//        Double d = new Double(10.0 / 3);
//        Long ll = new Long(Long.MAX_VALUE);
//        Long low = new Long(Long.MAX_VALUE);
//        Long height = new Long(Long.MAX_VALUE);
//        Integer i = new Integer(Integer.MAX_VALUE);
//        System.out.println(d);
//        low = (i & Double.doubleToLongBits(d));
//        height = Double.doubleToLongBits(d) >>> 32;
//        System.out.println(Integer.MAX_VALUE);
//        System.out.println(ll.intValue());
//        System.out.println(ll >>> 32);
//        System.out.println(low.intValue());
//        System.out.println(height.intValue());
//        System.out.println(Double.doubleToLongBits(d));

//        LinkedListTabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(-1, 55, 10);
//        FunctionPoint tmp;
//        for(int i = 0; i < linkedListTabulatedFunction.getPointsCount(); i++){
//            tmp = linkedListTabulatedFunction.getPoint(i);
//            System.out.println("Элемент списка: " + tmp.getPointValueX() + "  " + tmp.getPointValueY());
//        }
//        tmp = linkedListTabulatedFunction.getPoint(1);
//        System.out.println("Элемент списка: " + tmp.getPointValueX() + "  " + tmp.getPointValueY());
//        tmp = linkedListTabulatedFunction.getPoint(0);
//        System.out.println("Элемент списка: " + tmp.getPointValueX() + "  " + tmp.getPointValueY());
//        System.out.println(linkedListTabulatedFunction.getPoint(9).equals(linkedListTabulatedFunction.getPoint(9)));
//        System.out.println(linkedListTabulatedFunction.getPoint(9).hashCode());
//        System.out.println(linkedListTabulatedFunction.getPoint(8).hashCode());
//        System.out.println(linkedListTabulatedFunction.toString());
//        LinkedListTabulatedFunction tmpLL = (LinkedListTabulatedFunction) linkedListTabulatedFunction.clone();
//        System.out.println(tmpLL.toString());
//        tmpLL.deletePoint(5);
//        System.out.println(tmpLL.toString());
//        //System.out.println("Элемент списка: " + tmp.getPointValueX() + "  " + tmp.getPointValueY());
//        System.out.println("\n");
//
//        ArrayTabulatedFunction tabulatedFunction = new ArrayTabulatedFunction(-1, 55, 10);
//        for(int i = 0; i < tabulatedFunction.getPointsCount(); i++){
//            System.out.println(tabulatedFunction.getPointX(i) + "  " + tabulatedFunction.getPointY(i));
//        }
//        System.out.println("\n");
//        System.out.println(tabulatedFunction.getPoint(9).equals(linkedListTabulatedFunction.getPoint(9)));
//        System.out.println(tabulatedFunction.toString());
//        ArrayTabulatedFunction tmpAA = (ArrayTabulatedFunction) tabulatedFunction.clone();
//        tabulatedFunction.deletePoint(9);
//        tabulatedFunction.deletePoint(5);
//        tabulatedFunction.deletePoint(5);
//        tabulatedFunction.deletePoint(0);
//        tabulatedFunction.deletePoint(5);
//        tabulatedFunction.deletePoint(16);
//        System.out.println(tabulatedFunction.toString());
//        System.out.println(tmpAA.toString());
//        for(int i = 0; i < tabulatedFunction.getPointsCount(); i++){
//            System.out.println(tabulatedFunction.getPointX(i) + "  " + tabulatedFunction.getPointY(i));
//        }
//        System.out.println("\n");
//        tabulatedFunction.addPoint(new FunctionPoint(0, 0));
//        tabulatedFunction.addPoint(new FunctionPoint(10, 0));
//        tabulatedFunction.addPoint(new FunctionPoint(5, 0));
//        tabulatedFunction.addPoint(new FunctionPoint(50, 0));
//        tabulatedFunction.addPoint(new FunctionPoint(60, 0));
//        tabulatedFunction.addPoint(new FunctionPoint(70, 0));
//        tabulatedFunction.addPoint(new FunctionPoint(0, 0));
//        for(int i = 0; i < tabulatedFunction.getPointsCount(); i++){
//            System.out.println(tabulatedFunction.getPointX(i) + "  " + tabulatedFunction.getPointY(i));
//        }
//        System.out.println(tabulatedFunction.getPointsCount());
//        System.out.println("\n");

//        FunctionParameters dialog = new FunctionParameters();
//        System.out.println(dialog.showDialog());

//        DocumentTabulatedFunction documentTabulatedFunction = new DocumentTabulatedFunction();
//        try {
//            documentTabulatedFunction.newFunction(-5, 15, 9);
//            documentTabulatedFunction.saveFunctionAs("E:\\test.txt");
//            documentTabulatedFunction.newFunction(0, 15, 9);
//            documentTabulatedFunction.saveFunction();
//            documentTabulatedFunction.loadFunction("E:\\test.txt");
//        } catch (IOException ex) {
//            System.out.println(ex);
//        }
//        ArrayTabulatedFunction arrayTabulatedFunction = (ArrayTabulatedFunction) DocumentTabulatedFunction.getTabulatedFunction();
//        System.out.println(arrayTabulatedFunction.toString());

//        FunctionParameters functionParameters = new FunctionParameters();
//        functionParameters.showDialog();
//        System.out.println(functionParameters.getPointsCount());

//        PrimaryWindowProgram primaryWindowProgram = new PrimaryWindowProgram();

//        JTable jTable = new JTable(new TableForTabulatedFunction(new DocumentTabulatedFunction().getTabulatedFunction(), new PrimaryWindowProgram()));

//        DocumentTabulatedFunction documentTabulatedFunction = new DocumentTabulatedFunction();
//        documentTabulatedFunction.newFunction(-10, 10, 20);
//        TableForTabulatedFunction tableForTabulatedFunction = new TableForTabulatedFunction(documentTabulatedFunction.getTabulatedFunction(), new JDialog());
//        System.out.println(tableForTabulatedFunction.getRowCount());

//        DocumentTabulatedFunction documentTabulatedFunction = new DocumentTabulatedFunction();
//        documentTabulatedFunction.newFunction(-10, 10, 20);
//        System.out.println(documentTabulatedFunction.getTabulatedFunction().toString());
//        documentTabulatedFunction.addPoint(new FunctionPoint(0, 0));
//        System.out.println(documentTabulatedFunction.getTabulatedFunction().toString());

//        ArrayTabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(-10, 10, 20);
//        System.out.println(arrayTabulatedFunction.toString());
//        arrayTabulatedFunction.addPoint(new FunctionPoint(0, 0));
//        System.out.println(arrayTabulatedFunction.toString());

//        LinkedListTabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(-10, 10, 20);
//        System.out.println(linkedListTabulatedFunction.toString());
//        linkedListTabulatedFunction.addPoint(new FunctionPoint(0, 0));
//        System.out.println(linkedListTabulatedFunction.toString());

//        TabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(-10, 10, 20);
//        TabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(-10, 10, 20);
//        for (FunctionPoint p : linkedListTabulatedFunction) {
//            System.out.println(p.toString());
//        }

//        Function f = new Cos();
//        TabulatedFunction tf;
//        tf = TabulatedFunctions.tabulate(f, 0, Math.PI, 11);
//        System.out.println(tf.getClass());
//        TabulatedFunctions.setTabulatedFunctionFactory(new
//                LinkedListTabulatedFunction.LinkedListTabulatedFunctionFactory());
//        tf = TabulatedFunctions.tabulate(f, 0, Math.PI, 11);
//        System.out.println(tf.getClass());
//        TabulatedFunctions.setTabulatedFunctionFactory(new
//                ArrayTabulatedFunction.ArrayTabulatedFunctionFactory());
//        tf = TabulatedFunctions.tabulate(f, 0, Math.PI, 11);
//        System.out.println(tf.getClass());

        TabulatedFunction f;
        FunctionPoint[] functionPoints = new FunctionPoint[]{
                new FunctionPoint(0, 0),
                new FunctionPoint(10, 10),
                new FunctionPoint(11, 11),
                new FunctionPoint(12, 12),
                new FunctionPoint(13, 10)
        };
        for (FunctionPoint p : functionPoints) {
            System.out.println(p.toString());
        }

//        FunctionPoint functionPoint = FunctionPoint.testReflection(FunctionPoint.class, new FunctionPoint(10, 10));
//        System.out.println(functionPoint.getClass());
//        System.out.println(functionPoint);

        f = TabulatedFunctions.createTabulatedFunction(LinkedListTabulatedFunction.class, functionPoints);
        System.out.println(f.getClass());
        System.out.println(f);

        System.out.println("Hello, World!!!");

//        TabulatedFunction tabulatedFunction;
//        FunctionPoint[] functionPoint = new FunctionPoint[]{
//                new FunctionPoint(0, 0),
//                new FunctionPoint(1, 1),
//                new FunctionPoint(2, 2)
//        };
//
//        Integer[] integers = new Integer[]{1, 2, 3, 4};
//
//        System.out.println(integers.getClass());
//
//        double[] d = new double[]{1.0, 2.0, 3.0};
//        tabulatedFunction = ArrayTabulatedFunction.testReflection(ArrayTabulatedFunction.class, functionPoint);
//        System.out.println(tabulatedFunction.getClass());
//        System.out.println(tabulatedFunction);
    }
}