package functions.meta;

import functions.Function;

/**
 * Created by gijoe on 6/21/2015.
 */
public class Scale implements Function {

    private Function f1;
    private double k1;
    private double k2;

    public Scale(Function f1, double k1, double k2) {
        this.f1 = f1;
        this.k1 = k1;
        this.k2 = k2;
    }

    public double getLeftDomainBorder() {
        if (k1 > 0) {
            return f1.getLeftDomainBorder() * k1;
        }
        return f1.getRightDomainBorder() * k1;
    }

    public double getRightDomainBorder() {
        if (k1 > 0) {
            return f1.getRightDomainBorder() * k1;
        }
        return f1.getLeftDomainBorder() * k1;
    }

    public double getFunctionValue(double x) {
        return f1.getFunctionValue(x * k1) * k2;
    }
}
