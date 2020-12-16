package top.vuhe.controller.factory;

import static top.vuhe.model.Context.ANS_MAX;

import lombok.extern.slf4j.Slf4j;
import top.vuhe.model.entity.Formula;
import top.vuhe.model.entity.Formula.Builder;
import top.vuhe.model.entity.Operator;

import java.util.*;
import java.util.stream.Stream;

/**
 * 算式生成器
 * <p>
 * 职责：用于生成符合要求的算式
 * 检查：运算数和结果的值域
 *
 * @author vuhe
 */
@Slf4j
abstract class FormulaFactory extends Factory<Formula> {
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
        // 创建并行生产流
        Stream<Builder> builderStream = Stream.generate(this::build);
        Optional<Builder> builderOp = builderStream.parallel()
                // 检查答案
                .filter(this::checkFormula)
                .limit(1)
                // 获取一个
                .findFirst();

        Builder builder;
        if (builderOp.isPresent()) {
            builder = builderOp.get();
        } else {
            log.error("生产错误");
            throw new NoSuchElementException("生产错误");
        }

        log.trace("生产一个算式");
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

    private Builder build() {
        return Formula.builder()
                // 两个数数范围：1 ～ 99
                .a(RANDOM_NUM.nextInt(99) + 1)
                .b(RANDOM_NUM.nextInt(99) + 1)
                // 子类获取运算符
                .op(getOp());
    }

    /**
     * 符合答案要求返回 true
     * <p>
     * 符合答案标准：(0 <= ans <= 100)
     *
     * @param builder 算式构建者
     * @return 是否符合要求
     */
    private boolean checkFormula(Builder builder) {
        int ans = builder.ans();
        // 答案是否超出范围
        return 0 <= ans && ans <= ANS_MAX;
    }
}

class AddFormulaFactory extends FormulaFactory {
    @Override
    protected Operator getOp() {
        return Operator.Plus;
    }
}

class SubFormulaFactory extends FormulaFactory {
    @Override
    protected Operator getOp() {
        return Operator.Minus;
    }
}
