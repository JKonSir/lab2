package functions;

import java.io.*;
import java.util.Iterator;

/**
 * Created by gijoe on 6/24/2015.
 */
public class DocumentTabulatedFunction implements TabulatedFunction {

    private TabulatedFunction tabulatedFunction;
    private String fileName;
    private boolean modified = false;
    private boolean fileNameAssigned = false;

    public boolean isModified() {
        return modified;
    }

    public void setTabulatedFunction(TabulatedFunction tabulatedFunction) {
        this.tabulatedFunction = tabulatedFunction;
    }

    public boolean isFileNameAssigned() {
        return fileNameAssigned;
    }

    public TabulatedFunction getTabulatedFunction() {
        return this.tabulatedFunction;
    }

    public void newFunction(double leftX, double rightX, int pointsCount) {
        this.tabulatedFunction = new ArrayTabulatedFunction(leftX, rightX, pointsCount);
        this.modified = true;
    }

    public void saveFunction() throws IOException {
        File file = new File(this.fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file.getName());
            TabulatedFunctions.outputTabulatedFunction(tabulatedFunction, fileOutputStream);
            this.fileNameAssigned = true;
            this.modified = false;
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
            fileOutputStream.close();
            System.out.println(ex);
        } finally {
            fileOutputStream.close();
        }
    }

    public void saveFunctionAs(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file.getName());
            TabulatedFunctions.outputTabulatedFunction(tabulatedFunction, fileOutputStream);
            this.fileName = fileName;
            this.fileNameAssigned = true;
            this.modified = false;
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
            fileOutputStream.close();
        } finally {
            fileOutputStream.close();
        }
    }

    public void loadFunction(String fileName) throws IOException {
        File file = new File(fileName);
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file.getName());
            this.tabulatedFunction = (ArrayTabulatedFunction) TabulatedFunctions.inputTabulatedFunction(fileInputStream);
            this.modified = true;
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
            fileInputStream.close();
        } finally {
            fileInputStream.close();
        }
    }

    public void tabulatedFunction(Function function, double leftX, double rightX, int pointsCount) {
        this.tabulatedFunction = TabulatedFunctions.tabulate(function, leftX, rightX, pointsCount);
        this.modified = true;
    }

    public double getLeftDomainBorder(){
        return tabulatedFunction.getLeftDomainBorder();
    }

    public double getRightDomainBorder(){
        return tabulatedFunction.getRightDomainBorder();
    }

    public int getPointsCount(){
        return tabulatedFunction.getPointsCount();
    }

    public double getFunctionValue(double x){
        return tabulatedFunction.getFunctionValue(x);
    }

    public FunctionPoint getPoint(int index){
        return tabulatedFunction.getPoint(index);
    }

    public void setPoint(int index, FunctionPoint point){
        tabulatedFunction.setPoint(index, point);
        this.modified = true;
    }

    public double getPointX(int index){
        return tabulatedFunction.getPointX(index);
    }

    public void setPointX(int index, double x){
        tabulatedFunction.setPointX(index, x);
        this.modified = true;
    }

    public double getPointY(int index){
        return tabulatedFunction.getPointY(index);
    }

    public void setPointY(int index, double y){
        tabulatedFunction.setPointY(index, y);
        this.modified = true;
    }

    public void deletePoint(int index){
        tabulatedFunction.deletePoint(index);
        this.modified = true;
    }

    public void addPoint(FunctionPoint point){
        tabulatedFunction.addPoint(point);
        this.modified = true;
    }

    @Override
    public Object clone(){
        return tabulatedFunction.clone();
    }

    @Override
    public Iterator<FunctionPoint> iterator(){
        return tabulatedFunction.iterator();
    }
}
