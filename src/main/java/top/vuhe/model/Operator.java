package top.vuhe.model;

public enum Operator {
    // 加法
    plus() {
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
    minus() {
        @Override
        public int calculate(int a, int b) {
            return a - b;
        }

        @Override
        public String toString() {
            return "-";
        }
    };

    // 运算方法
    public abstract int calculate(int a, int b);
}
