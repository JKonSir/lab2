package functions;

/**
 * Created by gijoe on 6/26/2015.
 */
public interface TabulatedFunctionFactory {

    TabulatedFunction createTabulatedFunction(double leftX, double rightX, int pointsCount);
    TabulatedFunction createTabulatedFunction(double leftX, double rightX, double[] values);
    TabulatedFunction createTabulatedFunction(FunctionPoint[] points);
}
