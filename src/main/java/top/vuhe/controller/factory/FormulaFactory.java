package top.vuhe.controller.factory;

import static top.vuhe.model.Context.ANS_MAX;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.vuhe.model.entity.Formula;
import top.vuhe.model.entity.Operator;

import java.util.*;

/**
 * 算式生成器
 * <p>
 * 职责：用于生成符合要求的算式
 * 检查：运算数和结果的值域
 *
 * @author vuhe
 */
abstract class FormulaFactory extends Factory<Formula> {
    private static final Logger logger = LoggerFactory.getLogger(FormulaFactory.class);
    /**
     * 随机数生产器
     */
    private static final Random RANDOM_NUM = new Random(47);

    /**
     * 获取一个算式
     * <p>
     * 此算式已检查过数值符合要求
     *
     * @return 算式
     */
    @Override
    public Formula produce() {
        Formula.Builder builder = new Formula.Builder();

        do {
            // 两个数数范围：1 ～ 99
            builder.setA(RANDOM_NUM.nextInt(99) + 1)
                    .setOp(getOp())
                    .setB(RANDOM_NUM.nextInt(99) + 1);
            // 不符合答案重新生产算式
        } while (!checkFormula(builder));

        return builder.build();
    }

    /**
     * 获取一个运算符
     * <p>
     * 此方法行为由实现的子类控制
     *
     * @return 运算符
     */
    abstract protected Operator getOp();

    /**
     * 符合答案要求返回 true
     * <p>
     * 符合答案标准：(0 <= ans <= 100)
     *
     * @param builder 算式构建者
     * @return 是否符合要求
     */
    private boolean checkFormula(Formula.Builder builder) {
        int ans = builder.getAns();
        // 答案是否超出范围
        return 0 <= ans && ans <= ANS_MAX;
    }
}

class AddFormulaFactory extends FormulaFactory {
    @Override
    protected Operator getOp() {
        return Operator.plus;
    }
}

class SubFormulaFactory extends FormulaFactory {
    @Override
    protected Operator getOp() {
        return Operator.minus;
    }
}
