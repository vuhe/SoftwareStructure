package top.vuhe.controller.formula;

import top.vuhe.model.Formula;
import top.vuhe.model.Operator;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class FormulaFactory {
    // 随机数生产器
    private static final Random random = new Random(47);
    // 算式统计器
    private static final Set<Formula> set = new HashSet<>();

    public static Formula getFormula() {
        Formula formula;
        do {
            // 1 ～ 99
            int a = random.nextInt(99) + 1;
            // 随机运算符
            Operator op = randomGetOperator();
            // 1 ～ 99
            int b = random.nextInt(99) + 1;
            formula = new Formula(a, op, b);
            // 不符合答案重新生产算式
        } while (!checkFormula(formula));
        // 记录生成的算式
        set.add(formula);
        return formula;
    }

    /**
     * 获取随机运算符
     *
     * @return 运算符
     */
    private static Operator randomGetOperator() {
        if (random.nextInt(2) == 1) {
            return Operator.plus;
        } else {
            return Operator.minus;
        }
    }

    /**
     * 符合答案要求返回 true
     * <p>
     * 符合答案标准：(0 <= ans <= 100) 且 算式不重复
     *
     * @param formula 算式
     * @return 是否符合要求
     */
    private static boolean checkFormula(Formula formula) {
        int a = formula.getA();
        int b = formula.getB();
        int ans = formula.getOp().calculate(a, b);
        return 0 <= ans && ans <= 100 && !set.contains(formula);
    }
}
