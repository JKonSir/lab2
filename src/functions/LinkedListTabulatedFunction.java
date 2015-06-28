package functions;

import java.io.Serializable;
import java.util.Formatter;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by gijoe on 6/16/2015.
 */
public class LinkedListTabulatedFunction implements TabulatedFunction, Serializable {

    private FunctionNode head = new FunctionNode();
    private int lastUseIndex = 0; // индекс элемента, к которому было последнее обращение
    private int nodeCount; // кол-во элементов в списке
    private FunctionNode lastUseNode; // ссылка на элемент, к которому было последнее обращение

    {
        head.next = head;
        head.previous = head;
        lastUseIndex = 0;
        lastUseNode = head;
    }

    public static class LinkedListTabulatedFunctionFactory implements TabulatedFunctionFactory{

        @Override
        public TabulatedFunction createTabulatedFunction(double leftX, double rightX, int pointsCount) {
            return new LinkedListTabulatedFunction(leftX, rightX, pointsCount);
        }

        @Override
        public TabulatedFunction createTabulatedFunction(double leftX, double rightX, double[] values) {
            return new LinkedListTabulatedFunction(leftX, rightX, values);
        }

        @Override
        public TabulatedFunction createTabulatedFunction(FunctionPoint[] points) {
            return new LinkedListTabulatedFunction(points);
        }
    }

    private class FunctionNode {
        private FunctionPoint point;
        private FunctionNode next;
        private FunctionNode previous;

        public FunctionNode() {
            this.point = null;
            this.next = null;
            this.previous = null;
        }
    }

    private FunctionNode addNodeToTail() {

        FunctionNode node = new FunctionNode();
        this.head.previous.next = node;
        node.next = this.head;
        node.previous = this.head.previous;
        this.head.previous = node;

        return node;
    }

    private FunctionNode addNodeByIndex(int index) {

        FunctionNode node = new FunctionNode();

        FunctionNode tmp = head;
        for (int i = 0; i != index + 1; i++) {
            tmp = tmp.next;
        }
        tmp.previous.next = node;
        node.previous = tmp.previous;
        tmp.previous = node;
        node.next = tmp;

        return node;
    }

    private FunctionNode deleteNodeByIndex(int index) {

        FunctionNode tmp = head;
        for (int i = 0; i != index + 1; i++) {
            tmp = tmp.next;
        }
        tmp.previous.next = tmp.next;
        tmp.next.previous = tmp.previous;

        return tmp;
    }

    private FunctionNode getNodeByIndex(int index) {

        FunctionNode tmp = lastUseNode;
        int differenceIndex = Math.abs(index - lastUseIndex);
        if (index == 0) {
            lastUseNode = head.next;
            return head.next;
        }
        if (index == lastUseIndex) {
            return tmp;
        } else {
            if (index > lastUseIndex && differenceIndex <= nodeCount / 2) {
                for (int i = 0; i < differenceIndex; i++) {
                    tmp = tmp.next;
                }
                lastUseIndex = index;
                lastUseNode = tmp;
                return tmp;
            } else {
                if (index > lastUseIndex && differenceIndex > nodeCount / 2) {
                    int tmpMaxIteration = nodeCount - index + lastUseIndex;
                    for (int i = 0; i < tmpMaxIteration; i++) {
                        tmp = tmp.previous;
                    }
                    lastUseIndex = index;
                    lastUseNode = tmp;
                    return tmp;
                } else {
                    if (index < lastUseIndex && differenceIndex <= nodeCount / 2) {
                        for (int i = 0; i < differenceIndex; i++) {
                            tmp = tmp.previous;
                        }
                        lastUseIndex = index;
                        lastUseNode = tmp;
                        return tmp;
                    } else {
                        if (index < lastUseIndex && differenceIndex > nodeCount / 2) {
                            int tmpMaxIteration = nodeCount - lastUseIndex + index;
                            for (int i = 0; i <= tmpMaxIteration; i++) {
                                tmp = tmp.next;
                            }
                            lastUseIndex = index;
                            lastUseNode = tmp;
                            return tmp;
                        }
                    }
                }
            }
        }
        return null;
    }

    public LinkedListTabulatedFunction(double leftX, double rightX, int nodeCount) {
        try {
            if (leftX >= rightX || nodeCount <= 2) {
                throw new IllegalArgumentException();
            }
            this.nodeCount = nodeCount;
            double stepX = (rightX - leftX) / (nodeCount - 1);
            for (int i = 0; i < nodeCount; i++) {
                addNodeToTail().point = new FunctionPoint(leftX + i * stepX, 0);
            }
        } catch (IllegalArgumentException ex) {
            System.out.println(ex);
        }
    }

    public LinkedListTabulatedFunction(double leftX, double rightX, double[] values) {
        try {
            if (leftX >= rightX || values.length <= 2) {
                throw new IllegalArgumentException();
            }
            this.nodeCount = values.length;
            double stepX = (rightX - leftX) / (nodeCount - 1);
            for (int i = 0; i < nodeCount; i++) {
                addNodeToTail().point = new FunctionPoint(leftX + i * stepX, values[i]);
            }
        } catch (IllegalArgumentException ex) {
            System.out.println(ex);
        }
    }

    public LinkedListTabulatedFunction(FunctionPoint[] points) {
        try {
            if (points[0].getPointValueX() >= points[points.length - 1].getPointValueX() || points.length <= 2) {
                throw new IllegalArgumentException();
            }
            this.nodeCount = points.length;
            for (int i = 0; i < nodeCount; i++) {
                addNodeToTail().point = points[i];
            }
        } catch (IllegalArgumentException ex) {
            System.out.println(ex);
        }
    }

    public double getLeftDomainBorder() {
        return this.head.next.point.getPointValueX();
    }

    public double getRightDomainBorder() {
        return this.head.previous.point.getPointValueX();
    }

    public double getFunctionValue(double x) {
        if (x >= getLeftDomainBorder() && x <= getRightDomainBorder()) {
            FunctionNode tmp = this.head;
            for (int i = 0; i < nodeCount - 1; i++) {
                tmp = tmp.next;
                if (x >= tmp.point.getPointValueX() && x <= tmp.next.point.getPointValueX()) {
                    return (tmp.next.point.getPointValueY() - tmp.point.getPointValueY()) / (tmp.next.point.getPointValueX() - tmp.point.getPointValueX()) * (x - tmp.point.getPointValueX()) + tmp.point.getPointValueY();
                }
            }
        }
        return Double.NaN;
    }

    public int getPointsCount() {
        return nodeCount;
    }

    public FunctionPoint getPoint(int index) {
        try {
            if (index > nodeCount - 1) {
                throw new FunctionPointIndexOutOfBoundsException();
            }
            return this.getNodeByIndex(index).point;
        } catch (FunctionPointIndexOutOfBoundsException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public void setPoint(int index, FunctionPoint point) {
        try {
            if (index > nodeCount - 1) {
                throw new FunctionPointIndexOutOfBoundsException();
            }
            FunctionNode tmp = getNodeByIndex(index);
            if (point.getPointValueX() <= tmp.previous.point.getPointValueX() || point.getPointValueX() >= tmp.next.point.getPointValueX()) {
                throw new InappropriateFunctionPointException();
            }
            if (index > 0 && index < (nodeCount - 1) && point.getPointValueX() > tmp.previous.point.getPointValueX() && point.getPointValueX() < tmp.next.point.getPointValueX()) {
                tmp.point = point;
            }
            if (index == 0 && point.getPointValueX() < tmp.next.point.getPointValueX()) {
                tmp.point = point;
            }
            if (index == nodeCount - 1 && point.getPointValueX() > tmp.previous.point.getPointValueX()) {
                tmp.point = point;
            }
        } catch (FunctionPointIndexOutOfBoundsException ex) {
            System.out.println(ex);
        } catch (InappropriateFunctionPointException ex) {
            System.out.println(ex);
        }
    }

    public double getPointX(int index) {
        try {
            if (index > nodeCount - 1) {
                throw new FunctionPointIndexOutOfBoundsException();
            }
            return getNodeByIndex(index).point.getPointValueX();
        } catch (FunctionPointIndexOutOfBoundsException ex) {
            System.out.println(ex);
        }
        return Double.NaN;
    }

    public void setPointX(int index, double x) {
        try {
            if (index > nodeCount - 1) {
                throw new FunctionPointIndexOutOfBoundsException();
            }
            FunctionNode tmp = getNodeByIndex(index);
            if (x <= tmp.previous.point.getPointValueX() || x >= tmp.next.point.getPointValueX()) {
                throw new InappropriateFunctionPointException();
            }
            if (index > 0 && index < (nodeCount - 1) && x > tmp.previous.point.getPointValueX() && x < tmp.next.point.getPointValueX()) {
                tmp.point.setPointValueX(x);
            }
            if (index == 0 && x < tmp.next.point.getPointValueX()) {
                tmp.point.setPointValueX(x);
            }
            if (index == nodeCount - 1 && x > tmp.previous.point.getPointValueX()) {
                tmp.point.setPointValueX(x);
            }
        } catch (FunctionPointIndexOutOfBoundsException ex) {
            System.out.println(ex);
        } catch (InappropriateFunctionPointException ex) {
            System.out.println(ex);
        }
    }

    public double getPointY(int index) {
        try {
            if (index > nodeCount - 1) {
                throw new FunctionPointIndexOutOfBoundsException();
            }
            return getNodeByIndex(index).point.getPointValueY();
        } catch (FunctionPointIndexOutOfBoundsException ex) {
            System.out.println(ex);
        }
        return Double.NaN;
    }

    public void setPointY(int index, double y) {
        try {
            if (index > nodeCount - 1) {
                throw new FunctionPointIndexOutOfBoundsException();
            }
            getNodeByIndex(index).point.setPointValueY(y);
        } catch (FunctionPointIndexOutOfBoundsException ex) {
            System.out.println(ex);
        }
    }

    public void deletePoint(int index) {
        try {
            if (index > nodeCount - 1) {
                throw new FunctionPointIndexOutOfBoundsException();
            }
            if (nodeCount < 3) {
                throw new IllegalStateException();
            }
            deleteNodeByIndex(index);
            --nodeCount;
        } catch (FunctionPointIndexOutOfBoundsException ex) {
            System.out.println(ex);
        } catch (IllegalStateException ex) {
            System.out.println(ex);
        }
    }

    public void addPoint(FunctionPoint point) {
        try {
            FunctionNode tmp = head;
            for (int i = 0; i < nodeCount; i++) {
                tmp = tmp.next;
                if (tmp.point.getPointValueX() == point.getPointValueX()) {
                    throw new InappropriateFunctionPointException();
                }
            }
            if (getNodeByIndex(nodeCount - 1).point.getPointValueX() < point.getPointValueX()) {
                addNodeToTail().point = point;
                ++nodeCount;
            } else {
                if (getNodeByIndex(0).point.getPointValueX() > point.getPointValueX()) {
                    addNodeByIndex(0).point = point;
                    ++nodeCount;
                } else {
                    tmp = head.next;
                    int i;
                    for (i = 1; i < nodeCount - 1; i++) {
                        tmp = tmp.next;
                        if (point.getPointValueX() > tmp.previous.point.getPointValueX() && point.getPointValueX() < tmp.next.point.getPointValueX()) {
                            break;
                        }
                    }
                    addNodeByIndex(i).point = point;
                    ++nodeCount;
                }
            }
        } catch (InappropriateFunctionPointException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public String toString() {
        Formatter fmt = new Formatter();
        FunctionNode tmp = this.head.next;
        fmt.format("{");
        fmt.format("(%f; %f)", tmp.point.getPointValueX(), tmp.point.getPointValueY());
        for (int i = 1; i < this.nodeCount; i++) {
            tmp = tmp.next;
            fmt.format(", ");
            fmt.format("(%f; %f)", tmp.point.getPointValueX(), tmp.point.getPointValueY());
        }
        fmt.format("}");

        return fmt.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TabulatedFunction)) {
            return false;
        }
        if (o instanceof LinkedListTabulatedFunction) {
            FunctionNode tmp = head;
            for (int i = 0; i < nodeCount; i++) {
                tmp = tmp.next;
                if (tmp.point.getPointValueX() != ((TabulatedFunction) o).getPointX(i) || tmp.point.getPointValueY() != ((TabulatedFunction) o).getPointY(i)) {
                    return false;
                }
            }
        } else {
            FunctionNode tmp = head;
            for (int i = 0; i < nodeCount; i++) {
                tmp = tmp.next;
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
        FunctionNode tmp = this.head;

        for (int i = 0; i < nodeCount; i++) {
            tmp = tmp.next;
            Long lowBitX = new Long(Long.MAX_VALUE & Double.doubleToLongBits(tmp.point.getPointValueX()));
            Integer lbx = lowBitX.intValue();
            Long highBitX = new Long(Double.doubleToLongBits(tmp.point.getPointValueX()) >>> 32);
            Integer hbx = highBitX.intValue();
            Long lowBitY = new Long(Long.MAX_VALUE & Double.doubleToLongBits(tmp.point.getPointValueY()));
            Integer lby = lowBitY.intValue();
            Long highBitY = new Long(Double.doubleToLongBits(tmp.point.getPointValueY()) >>> 32);
            Integer hby = highBitY.intValue();

            hashCode = hashCode | lbx | hbx | lby | hby;
        }

        return hashCode | nodeCount;
    }

    @Override
    public Object clone() {

        LinkedListTabulatedFunction tmpL = new LinkedListTabulatedFunction(this.getLeftDomainBorder(), this.getRightDomainBorder(), this.getPointsCount());
        FunctionNode tmp1 = tmpL.head;
        FunctionNode tmp2 = this.head;

        for (int i = 0; i < tmpL.nodeCount; i++) {
            tmp1 = tmp1.next;
            tmp2 = tmp2.next;
            tmp1.point = (FunctionPoint) tmp2.point.clone();
            tmp1.point.setPointValueY(tmp2.point.getPointValueY());
        }

        return tmpL;
    }

    @Override
    public Iterator<FunctionPoint> iterator() {

        return new Iterator() {
            FunctionNode tmp = head;

            @Override
            public boolean hasNext() {
                if (tmp.next.point != null) {
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
                tmp = tmp.next;
                return tmp.point;
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
}
