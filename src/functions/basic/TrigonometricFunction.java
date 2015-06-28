package functions.basic;

import functions.Function;

/**
 * Created by gijoe on 6/21/2015.
 */
public abstract class TrigonometricFunction implements Function {

    @Override
    public double getLeftDomainBorder() {
        return Double.NEGATIVE_INFINITY;
    }

    @Override
    public double getRightDomainBorder() {
        return Double.POSITIVE_INFINITY;
    }

    @Override
    public abstract double getFunctionValue(double x);
}
