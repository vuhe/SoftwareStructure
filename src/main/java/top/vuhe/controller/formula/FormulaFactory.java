package top.vuhe.controller.formula;

import static top.vuhe.model.Context.ANS_MAX;
import top.vuhe.model.Formula;
import top.vuhe.model.Operator;

import java.util.*;

/**
 * @author vuhe
 */
public class FormulaFactory {
    /**
     * 随机数生产器
     */
    private static final Random RANDOM_NUM = new Random(47);
    /**
     * 算式统计器
     */
    private static final Set<Formula> FORMULA_SET = new HashSet<>();
    /**
     * 运算符统计器
     */
    private static final Map<Operator, Integer> OP_MAP = new EnumMap<>(Operator.class);

    // 初始化运算符统计器
    static {
        OP_MAP.put(Operator.plus, 0);
        OP_MAP.put(Operator.minus, 0);
    }

    public static Formula getFormula() {
        Formula formula;
        do {
            // 1 ～ 99
            int a = RANDOM_NUM.nextInt(99) + 1;
            // 随机运算符
            Operator op = randomGetOperator();
            // 1 ～ 99
            int b = RANDOM_NUM.nextInt(99) + 1;
            formula = new Formula(a, op, b);
            // 不符合答案重新生产算式
        } while (!checkFormula(formula));
        // 记录生成的算式和运算符
        FORMULA_SET.add(formula);
        OP_MAP.put(formula.getOp(), OP_MAP.get(formula.getOp()) + 1);
        return formula;
    }

    /**
     * 获取随机运算符
     *
     * @return 运算符
     */
    private static Operator randomGetOperator() {
        if (RANDOM_NUM.nextInt(2) == 1) {
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
        if (ans < 0 || ANS_MAX < ans) {
            return false;
        }
        // 算式存在
        if (FORMULA_SET.contains(formula)) {
            return false;
        }
        // 运算符是否平均
        return OP_MAP.get(op) <= 25;
    }
}
