package top.vuhe.controller.formula;

import top.vuhe.model.Formula;
import top.vuhe.model.Operator;

import java.util.*;

public class FormulaFactory {
    // 随机数生产器
    private static final Random random = new Random(47);
    // 算式统计器
    private static final Set<Formula> set = new HashSet<>();
    // 运算符统计器
    private static final Map<Operator, Integer> map = new EnumMap<>(Operator.class);

    // 初始化运算符统计器
    static {
        map.put(Operator.plus, 0);
        map.put(Operator.minus, 0);
    }

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
        // 记录生成的算式和运算符
        set.add(formula);
        map.put(formula.getOp(), map.get(formula.getOp()) + 1);
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
        Operator op = formula.getOp();
        int ans = op.calculate(a, b);
        // 答案超出范围
        if (ans < 0 || 100 < ans) {
            return false;
        }
        // 算式存在
        if (set.contains(formula)) {
            return false;
        }
        // 运算符是否平均
        return map.get(op) <= 25;
    }
}
