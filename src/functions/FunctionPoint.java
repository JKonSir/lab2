package functions;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.Formatter;

/**
 * Created by gijoe on 6/13/2015.
 */
public class FunctionPoint implements Serializable {

    private double x;
    private double y;

    public FunctionPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public FunctionPoint(FunctionPoint point) {
        this.x = point.x;
        this.y = point.y;
    }

    public FunctionPoint() {
        this.x = 0;
        this.y = 0;
    }

    public static FunctionPoint testReflection(Class c1, FunctionPoint point) {
        try {
            Constructor constructor = c1.getConstructor(FunctionPoint.class);
            return (FunctionPoint) constructor.newInstance(point);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    public double getPointValueX() {
        return this.x;
    }

    public void setPointValueX(double x) {
        this.x = x;
    }

    public double getPointValueY() {
        return this.y;
    }

    public void setPointValueY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        Formatter fmt = new Formatter();
        fmt.format("(%f; %f)", this.x, this.y);

        return fmt.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof FunctionPoint)) {
            return false;
        }
        if (((FunctionPoint) o).x == this.x && ((FunctionPoint) o).y == this.y) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        Long lowBitX = new Long(Long.MAX_VALUE & Double.doubleToLongBits(this.x));
        Integer lbx = lowBitX.intValue();
        Long highBitX = new Long(Double.doubleToLongBits(this.x) >>> 32);
        Integer hbx = highBitX.intValue();
        Long lowBitY = new Long(Long.MAX_VALUE & Double.doubleToLongBits(this.y));
        Integer lby = lowBitY.intValue();
        Long highBitY = new Long(Double.doubleToLongBits(this.y) >>> 32);
        Integer hby = highBitY.intValue();

        return lbx | hbx | lby | hby;
    }

    @Override
    public Object clone() {

        return new FunctionPoint(this.x, this.y);
    }
}
