package functions;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.TypeVariable;
import java.sql.Types;
import java.util.Formatter;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by gijoe on 6/13/2015.
 */
public class ArrayTabulatedFunction implements TabulatedFunction, Serializable {

    private FunctionPoint[] points;
    private int pointsCount;

    public ArrayTabulatedFunction(double leftX, double rightX, int pointsCount) {
        try {
            if (leftX >= rightX || pointsCount <= 2) {
                throw new IllegalArgumentException();
            }
            this.points = new FunctionPoint[pointsCount];
            this.pointsCount = pointsCount;
            double stepX = (rightX - leftX) / (pointsCount - 1);
            for (int i = 0; i < pointsCount; i++) {
                this.points[i] = new FunctionPoint(leftX + i * stepX, 0);
            }
        } catch (IllegalArgumentException ex) {
            System.out.println(ex);
        }
    }

    public ArrayTabulatedFunction(double leftX, double rightX, double[] values) {
        try {
            if (leftX >= rightX || values.length <= 2) {
                throw new IllegalArgumentException();
            }
            this.points = new FunctionPoint[values.length];
            this.pointsCount = values.length;
            double stepX = (rightX - leftX) / (values.length - 1);
            for (int i = 0; i < values.length; i++) {
                this.points[i] = new FunctionPoint(i * stepX, values[i]);
            }
        } catch (IllegalArgumentException ex) {
            System.out.println(ex);
        }
    }

    public ArrayTabulatedFunction(FunctionPoint[] points) {
        try {
            if (points[0].getPointValueX() >= points[points.length - 1].getPointValueX() || points.length <= 2) {
                throw new IllegalArgumentException();
            }
            this.pointsCount = points.length;
            this.points = points;
        } catch (IllegalArgumentException ex) {
            System.out.println(ex);
        }
    }

    public static ArrayTabulatedFunction testReflection(Class c1, FunctionPoint[] points) {
        try {
            Constructor[] constructors = c1.getConstructors();
            TypeVariable[] myClass = c1.getTypeParameters();
            Class[] arument = {FunctionPoint[].class};
            return (ArrayTabulatedFunction) c1.getDeclaredConstructor(FunctionPoint[].class).newInstance(new Object[]{points});
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    public double getLeftDomainBorder() {
        return points[0].getPointValueX();
    }

    public double getRightDomainBorder() {
        return points[pointsCount - 1].getPointValueX();
    }

    public double getFunctionValue(double x) {
        if (x >= getLeftDomainBorder() && x <= getRightDomainBorder()) {
            for (int i = 0; i < pointsCount - 1; i++) {
                if (x >= this.points[i].getPointValueX() && x <= this.points[i + 1].getPointValueX()) {
                    return (this.points[i + 1].getPointValueY() - this.points[i].getPointValueY()) / (this.points[i + 1].getPointValueX() - this.points[i].getPointValueX()) * (x - this.points[i].getPointValueX()) + this.points[i].getPointValueY();
                }
            }
        }
        return Double.NaN;
    }

    public int getPointsCount() {
        return pointsCount;
    }

    public FunctionPoint getPoint(int index) {
        try {
            if (index > getPointsCount() - 1) {
                throw new FunctionPointIndexOutOfBoundsException();
            }
            return this.points[index];
        } catch (FunctionPointIndexOutOfBoundsException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public void setPoint(int index, FunctionPoint point) {
        try {
            if (index > getPointsCount() - 1) {
                throw new FunctionPointIndexOutOfBoundsException();
            }
            if (point.getPointValueX() <= this.points[index - 1].getPointValueX() || point.getPointValueX() >= this.points[index + 1].getPointValueX()) {
                throw new InappropriateFunctionPointException();
            }
            if (index > 0 && index < (pointsCount - 1) && point.getPointValueX() >= this.points[index - 1].getPointValueX() && point.getPointValueX() <= this.points[index + 1].getPointValueX()) {
                this.points[index] = point;
            }
            if (index == 0 && point.getPointValueX() < this.points[index + 1].getPointValueX()) {
                this.points[index] = point;
            }
            if (index == pointsCount - 1 && point.getPointValueX() > this.points[index - 1].getPointValueX()) {
                this.points[index] = point;
            }
        } catch (FunctionPointIndexOutOfBoundsException ex) {
            System.out.println(ex);
        } catch (InappropriateFunctionPointException ex) {
            System.out.println(ex);
        }
    }

    public double getPointX(int index) {
        try {
            if (index > getPointsCount() - 1) {
                throw new FunctionPointIndexOutOfBoundsException();
            }
            return this.points[index].getPointValueX();
        } catch (FunctionPointIndexOutOfBoundsException ex) {
            System.out.println(ex);
        }
        return Double.NaN;
    }

    public void setPointX(int index, double x) {
        try {
            if (index > getPointsCount() - 1) {
                throw new FunctionPointIndexOutOfBoundsException();
            }
            if (index > 0 && index < (pointsCount - 1) && x >= this.points[index - 1].getPointValueX() && x <= this.points[index + 1].getPointValueX()) {
                if (x <= this.points[index - 1].getPointValueX() || x >= this.points[index + 1].getPointValueX()) {
                    throw new InappropriateFunctionPointException();
                }
                this.points[index].setPointValueX(x);
                this.points[index].setPointValueY(x);
            } else {
                if (index == 0 && x < this.points[index + 1].getPointValueX()) {
                    this.points[index].setPointValueX(x);
                    this.points[index].setPointValueY(x);
                } else {
                    if (index == pointsCount - 1 && x > this.points[index - 1].getPointValueX()) {
                        this.points[index].setPointValueX(x);
                        this.points[index].setPointValueY(x);
                    }
                }
            }
        } catch (FunctionPointIndexOutOfBoundsException ex) {
            System.out.println(ex);
        } catch (InappropriateFunctionPointException ex) {
            System.out.println(ex);
        }
    }

    public double getPointY(int index) {
        try {
            if (index > getPointsCount() - 1) {
                throw new FunctionPointIndexOutOfBoundsException();
            }
            return this.points[index].getPointValueY();
        } catch (FunctionPointIndexOutOfBoundsException ex) {
            System.out.println(ex);
        }
        return Double.NaN;
    }

    public void setPointY(int index, double y) {
        try {
            if (index > getPointsCount() - 1) {
                throw new FunctionPointIndexOutOfBoundsException();
            }
            this.points[index].setPointValueY(y);
        } catch (FunctionPointIndexOutOfBoundsException ex) {
            System.out.println(ex);
        }
    }

    public void deletePoint(int index) {
        try {
            if (index > getPointsCount() - 1) {
                throw new FunctionPointIndexOutOfBoundsException();
            }
            if (getPointsCount() < 3) {
                throw new IllegalStateException();
            }
            if (index == (this.points.length - 1)) {
                --pointsCount;
            } else {
                System.arraycopy(this.points, index + 1, this.points, index, this.points.length - index - 2);
                --pointsCount;
            }
        } catch (FunctionPointIndexOutOfBoundsException ex) {
            System.out.println(ex);
        } catch (IllegalStateException ex) {
            System.out.println(ex);
        }
    }

    public void addPoint(FunctionPoint point) {
        try {
            for (int i = 0; i < pointsCount; i++) {
                if (points[i].getPointValueX() == point.getPointValueX()) {
                    throw new InappropriateFunctionPointException();
                }
            }
            int count = 0;
            ++pointsCount;
            if (pointsCount - 1 == this.points.length) {
                FunctionPoint[] tmpPoints = new FunctionPoint[this.points.length + this.points.length / 2];
                for (int i = getPointsCount(); i < tmpPoints.length; i++) {
                    tmpPoints[i] = new FunctionPoint();
                }
                System.arraycopy(this.points, 0, tmpPoints, 0, this.points.length);
                this.points = tmpPoints;
            }
            if (point.getPointValueX() < getLeftDomainBorder()) {
                System.arraycopy(this.points, 0, this.points, 1, getPointsCount());
                this.points[0] = point;
            } else {
                if (point.getPointValueX() > this.points[0].getPointValueX() && point.getPointValueX() < this.points[pointsCount - 2].getPointValueX()) {
                    for (int i = 1; i < pointsCount - 1; i++) {
                        if (point.getPointValueX() > this.points[i - 1].getPointValueX() && point.getPointValueX() < this.points[i].getPointValueX()) {
                            count = i;
                            break;
                        }
                    }
                    System.arraycopy(this.points, count, this.points, count + 1, pointsCount - count - 1);
                    this.points[count] = point;
                } else {
                    if (point.getPointValueX() > this.points[getPointsCount() - 1].getPointValueX()) {
                        this.points[getPointsCount() - 1] = point;
                    }
                }
            }
        } catch (InappropriateFunctionPointException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public String toString() {
        Formatter fmt = new Formatter();
        fmt.format("{");
        fmt.format("(%f; %f)", this.points[0].getPointValueX(), this.points[0].getPointValueY());
        for (int i = 1; i < pointsCount; i++) {
            fmt.format(", ");
            fmt.format("(%f; %f)", this.points[i].getPointValueX(), this.points[i].getPointValueY());
        }
        fmt.format("}");

        return fmt.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TabulatedFunction)) {
            return false;
        }
        if (o instanceof ArrayTabulatedFunction) {
            for (int i = 0; i < pointsCount; i++) {
                if (this.points[i].getPointValueX() != ((TabulatedFunction) o).getPointX(i) || this.points[i].getPointValueY() != ((TabulatedFunction) o).getPointY(i)) {
                    return false;
                }
            }
        } else {
            for (int i = 0; i < pointsCount; i++) {
                if (this.getPointX(i) != ((TabulatedFunction) o).getPointX(i) || this.getPointY(i) != ((TabulatedFunction) o).getPointY(i)) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public int hashCode() {

        int hashCode = 0;
        for (int i = 0; i < pointsCount; i++) {
            Long lowBitX = new Long(Long.MAX_VALUE & Double.doubleToLongBits(this.points[i].getPointValueX()));
            Integer lbx = lowBitX.intValue();
            Long highBitX = new Long(Double.doubleToLongBits(this.points[i].getPointValueX()) >>> 32);
            Integer hbx = highBitX.intValue();
            Long lowBitY = new Long(Long.MAX_VALUE & Double.doubleToLongBits(this.points[i].getPointValueY()));
            Integer lby = lowBitY.intValue();
            Long highBitY = new Long(Double.doubleToLongBits(this.points[i].getPointValueY()) >>> 32);
            Integer hby = highBitY.intValue();

            hashCode = hashCode | lbx | hbx | lby | hby;
        }

        return hashCode | pointsCount;
    }

    @Override
    public Object clone() {

        return new ArrayTabulatedFunction(this.points);
    }

    public Iterator<FunctionPoint> iterator() {

        return new Iterator() {

            int index;

            @Override
            public boolean hasNext() {
                if (index < pointsCount) {
                    return true;
                }
                return false;
            }

            @Override
            public FunctionPoint next() {
                if (!hasNext()) {
                    try {
                        throw new NoSuchElementException();
                    } catch (NoSuchElementException ex) {
                        System.out.println(ex);
                    }
                }
                return points[index++];
            }

            @Override
            public void remove() {
                try {
                    throw new UnsupportedOperationException();
                } catch (UnsupportedOperationException ex) {
                    System.out.println(ex);
                }
            }
        };
    }

    public static class ArrayTabulatedFunctionFactory implements TabulatedFunctionFactory {

        @Override
        public TabulatedFunction createTabulatedFunction(double leftX, double rightX, int pointsCount) {
            return new ArrayTabulatedFunction(leftX, rightX, pointsCount);
        }

        @Override
        public TabulatedFunction createTabulatedFunction(double leftX, double rightX, double[] values) {
            return new ArrayTabulatedFunction(leftX, rightX, values);
        }

        @Override
        public TabulatedFunction createTabulatedFunction(FunctionPoint[] points) {
            return new ArrayTabulatedFunction(points);
        }
    }
}