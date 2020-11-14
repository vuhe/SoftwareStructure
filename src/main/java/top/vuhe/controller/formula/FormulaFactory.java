package top.vuhe.controller.formula;

import top.vuhe.model.Formula;
import top.vuhe.model.Operator;

import java.util.Random;

public class FormulaFactory {
    // 随机数生产器
    private static final Random random = new Random(47);

    public static Formula getFormula() {
        int a = random.nextInt(101);
        Operator op = randomGetOperator();
        int b = random.nextInt(101);
        // 不符合答案重新生产数字
        while (!checkAns(a, op, b)) {
            a = random.nextInt(101);
            b = random.nextInt(101);
        }
        return new Formula(a, op, b);
    }

    private static Operator randomGetOperator() {
        if (random.nextInt(2) == 1) {
            return Operator.plus;
        } else {
            return Operator.minus;
        }
    }

    /**
     * 符合答案(0 <= ans <= 100)要求返回 true
     *
     * @param a  第一个数
     * @param op 运算符
     * @param b  第二个数
     * @return 是否符合要求
     */
    private static boolean checkAns(int a, Operator op, int b) {
        int ans = op.calculate(a, b);
        return 0 <= ans && ans <= 100;
    }
}
