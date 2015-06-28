package functions;

import java.io.Serializable;

/**
 * Created by gijoe on 6/21/2015.
 */
public interface TabulatedFunction extends Function, Serializable, Iterable<FunctionPoint> {

    @Override
    double getLeftDomainBorder();
    @Override
    double getRightDomainBorder();
    int getPointsCount();
    FunctionPoint getPoint(int index);
    void setPoint(int index, FunctionPoint point);
    double getPointX(int index);
    void setPointX(int index, double x);
    double getPointY(int index);
    void setPointY(int index, double y);
    void deletePoint(int index);
    void addPoint(FunctionPoint point);
    Object clone();
}
