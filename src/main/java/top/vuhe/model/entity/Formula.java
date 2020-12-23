package top.vuhe.model.entity;

import lombok.Data;

/**
 * 算式实体
 * <p>
 * 一个算式的所有信息
 *
 * @author vuhe
 */
@Data
public class Formula {
    private final int a;
    private final Operator op;
    private final int b;
    private final int ans;

    /**
     * 初始化算式
     * 为避免不必要的问题，算式在初始化后禁止修改
     *
     * @param a  第一个数
     * @param op 运算符
     * @param b  第二个数
     */
    private Formula(int a, Operator op, int b) {
        this.a = a;
        this.op = op;
        this.b = b;
        ans = op.calculate(a, b);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * 构建者模式
     * 此模式是用于对对象生成做封装，并提供默认值
     * 为之后的可能承载更多的信息做准备
     */
    public static class Builder {
        private int a = 1;
        private Operator op = Operator.Plus;
        private int b = 1;

        public Builder a(int a) {
            this.a = a;
            return this;
        }
        public Builder op(Operator op) {
            this.op = op;
            return this;
        }

        public Builder b(int b) {
            this.b = b;
            return this;
        }

        public int ans() {
            return op.calculate(a, b);
        }

        public Formula build() {
            return new Formula(a, op, b);
        }
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
}
