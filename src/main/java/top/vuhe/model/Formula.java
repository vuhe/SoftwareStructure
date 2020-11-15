package top.vuhe.model;

import java.util.Objects;

public class Formula {
    private final int a;
    private final Operator op;
    private final int b;

    /**
     * 初始化算式
     * 为避免不必要的问题，算式在初始化后禁止修改
     *
     * @param a  第一个数
     * @param op 运算符
     * @param b  第二个数
     */
    public Formula(int a, Operator op, int b) {
        this.a = a;
        this.op = op;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public Operator getOp() {
        return op;
    }

    public int getB() {
        return b;
    }

    @Override
    public String toString() {
        return String.format("%2d", a) +
                " " +
                op +
                " " +
                String.format("%2d", b) +
                " = ";
    }

    /**
     * 重写函数用以支持 Map 和 Set
     *
     * @param o 其它对象
     * @return 是否相同
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Formula formula = (Formula) o;
        return a == formula.a &&
                b == formula.b &&
                op == formula.op;
    }

    /**
     * 重写函数用以支持 Map 和 Set
     *
     * @return hashCode
     */
    @Override
    public int hashCode() {
        return Objects.hash(a, op, b);
    }
}
