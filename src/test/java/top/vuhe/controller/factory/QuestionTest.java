package top.vuhe.controller.factory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.vuhe.model.Context;
import top.vuhe.model.entity.Formula;
import top.vuhe.model.entity.Operator;
import top.vuhe.model.entity.Question;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/**
 * 问题类单元测试
 *
 * @author vuhe
 */
class QuestionTest {
    private static final Logger logger = LoggerFactory.getLogger(QuestionTest.class);
    /**
     * 默认检验问题数
     */
    private static final int N = 10_000;

    @BeforeAll
    static void beforeAll() {
        logger.info("「问题」单元测试");
    }

    @Test
    @DisplayName("检查重复算式")
    void checkRepeatedFormula() {
        logger.info("开始运算式生成不重复测试");

        // 创建并行问题流
        Stream<Question> questionStream = Stream.generate(new QuestionFactory()::produce);
        questionStream.parallel()
                .unordered()
                // 长度限制
                .limit(N)
                // 对于每一个问题执行
                .forEach(this::checkEveryQuestionRepeatedFormula);

        logger.info("算式检测完成，无重复");
    }

    @Test
    @DisplayName("检查运算符数量")
    void checkNumberOfOperators() {
        logger.info("开始运算符在指定范围测试");

        // 创建并行问题流
        Stream<Question> questionStream = Stream.generate(new QuestionFactory()::produce);
        questionStream.parallel()
                .unordered()
                // 长度限制
                .limit(N)
                // 对于每一个问题执行
                .forEach(this::checkEveryQuestionNumberOfOperators);

        logger.info("运算符检查完成，符合要求");
    }

    private void checkEveryQuestionRepeatedFormula(Question question) {
        // 检查一个问题中是否有重复
        Set<Formula> set = new HashSet<>();
        // 获取迭代器
        question.iterator()
                // 检查每一个算式
                .forEachRemaining(formula ->
                        // 断言是否存在
                        Assertions.assertTrue(set.add(formula)));
    }

    private void checkEveryQuestionNumberOfOperators(Question question) {
        // 初始化映射
        Map<Operator, Integer> map = new EnumMap<>(Operator.class);
        map.put(Operator.minus, 0);
        map.put(Operator.plus, 0);

        // 获取迭代器
        question.iterator()
                // 添加每一个运算符
                .forEachRemaining(formula ->
                        map.put(formula.getOp(), map.get(formula.getOp()) + 1));

        // 断言加法数量一致
        Assertions.assertSame(Context.getPlusNum(), map.get(Operator.plus));
        // 断言减法数量一致
        Assertions.assertSame(Context.getMinusNum(), map.get(Operator.minus));
    }
}
