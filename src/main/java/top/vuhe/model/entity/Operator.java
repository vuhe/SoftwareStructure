package top.vuhe.model.entity;

/**
 * @author vuhe
 */
public enum Operator {
    // 加法
    Plus() {
        @Override
        public int calculate(int a, int b) {
            return a + b;
        }

        @Override
        public String toString() {
            return "+";
        }
    },
    // 减法
    Minus() {
        @Override
        public int calculate(int a, int b) {
            return a - b;
        }

        @Override
        public String toString() {
            return "-";
        }
    };

    /**
     * 运算方法
     *
     * @param a 第一个运算数
     * @param b 第二个运算数
     * @return 运算结果
     */
    public abstract int calculate(int a, int b);
}
