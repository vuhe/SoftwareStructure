package top.vuhe.model;

public class Formula {
    private final int a;
    private final OperatorEnum op;
    private final int b;

    /**
     * 初始化算式
     * 为避免不必要的问题，算式在初始化后禁止修改
     *
     * @param a  第一个数
     * @param op 运算符
     * @param b  第二个数
     */
    public Formula(int a, OperatorEnum op, int b) {
        this.a = a;
        this.op = op;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public OperatorEnum getOp() {
        return op;
    }

    public int getB() {
        return b;
    }

    @Override
    public String toString() {
        return a + " " + op + " " + b + " = ";
    }
}
