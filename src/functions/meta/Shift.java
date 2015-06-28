package functions.meta;

import functions.Function;

/**
 * Created by gijoe on 6/21/2015.
 */
public class Shift implements Function {

    private Function f1;
    private double offsetX;
    private double offsetY;

    public Shift(Function f1, double offsetX, double offsetY) {
        this.f1 = f1;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public double getLeftDomainBorder() {
        return f1.getRightDomainBorder() + offsetX;
    }

    public double getRightDomainBorder() {
        return f1.getLeftDomainBorder() + offsetX;
    }

    public double getFunctionValue(double x) {
        return f1.getFunctionValue(x + offsetX) + offsetY;
    }
}
