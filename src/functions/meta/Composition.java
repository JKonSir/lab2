package functions.meta;

import functions.Function;

/**
 * Created by gijoe on 6/21/2015.
 */
public class Composition implements Function {

    private Function f1;
    private Function f2;

    public Composition(Function f1, Function f2) {
        this.f1 = f1;
        this.f2 = f2;
    }

    public double getLeftDomainBorder() {
        return f2.getLeftDomainBorder();
    }

    public double getRightDomainBorder() {
        return f2.getRightDomainBorder();
    }

    public double getFunctionValue(double x) {
        return f2.getFunctionValue(f1.getFunctionValue(x));
    }
}
