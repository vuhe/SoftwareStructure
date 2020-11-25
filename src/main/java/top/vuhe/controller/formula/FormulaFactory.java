package top.vuhe.controller.formula;

import static top.vuhe.model.Context.ANS_MAX;

import top.vuhe.model.Formula;
import top.vuhe.model.Operator;

import java.util.*;

/**
 * 算式生成器
 * <p>
 * 职责：用于生成符合要求的算式
 *
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
        Formula.Builder builder = new Formula.Builder();

        do {
            // 两个数数范围：1 ～ 99
            // 随机运算符
            builder.setA(RANDOM_NUM.nextInt(99) + 1)
                    .setOp(randomGetOperator())
                    .setB(RANDOM_NUM.nextInt(99) + 1);
            // 不符合答案重新生产算式
        } while (!checkFormula(builder));

        Formula formula = builder.build();
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
     * @param builder 算式构建者
     * @return 是否符合要求
     */
    private static boolean checkFormula(Formula.Builder builder) {
        int ans = builder.getAns();
        // 答案超出范围
        if (ans < 0 || ANS_MAX < ans) {
            return false;
        }
        // 算式存在
        if (FORMULA_SET.contains(builder.build())) {
            return false;
        }
        // 运算符是否平均
        return OP_MAP.get(builder.getOp()) <= 25;
    }
}
