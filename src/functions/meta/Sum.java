package functions.meta;

import functions.Function;

/**
 * Created by gijoe on 6/21/2015.
 */
public class Sum implements Function {

    private Function f1;
    private Function f2;

    public Sum(Function f1, Function f2) {
        this.f1 = f1;
        this.f2 = f2;
    }

    public double getLeftDomainBorder() {
        if (f1.getLeftDomainBorder() >= f2.getLeftDomainBorder()) {
            return f1.getLeftDomainBorder();
        }
        return f2.getLeftDomainBorder();
    }

    public double getRightDomainBorder() {
        if (f1.getRightDomainBorder() <= f2.getRightDomainBorder()) {
            return f1.getRightDomainBorder();
        }
        return f2.getRightDomainBorder();
    }

    public double getFunctionValue(double x) {
        return f1.getFunctionValue(x) + f2.getFunctionValue(x);
    }
}
