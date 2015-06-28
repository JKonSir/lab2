package functions.basic;

import functions.Function;

/**
 * Created by gijoe on 6/21/2015.
 */
public class Log implements Function{

    @Override
    public double getLeftDomainBorder() {
        return Double.MIN_VALUE;
    }

    @Override
    public double getRightDomainBorder() {
        return Double.MAX_VALUE;
    }

    @Override
    public double getFunctionValue(double x) {
        return Math.log(x);
    }
}
