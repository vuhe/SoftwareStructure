package top.vuhe.controller.factory;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
@Slf4j
class QuestionTest {
    /**
     * 默认检验问题数
     */
    private static final int N = 10_000;

    @BeforeAll
    static void beforeAll() {
        log.info("「问题」单元测试");
    }

    @Test
    @DisplayName("检查重复算式")
    void checkRepeatedFormula() {
        log.info("开始运算式生成不重复测试");

        // 创建并行问题流
        Stream<Question> questionStream = Stream.generate(
                new QuestionFactory(QuestionEnum.HalfHalf)::produce);
        questionStream.parallel()
                .unordered()
                // 长度限制
                .limit(N)
                // 对于每一个问题执行
                .forEach(this::checkEveryQuestionRepeatedFormula);

        log.info("算式检测完成，无重复");
    }

    @Test
    @DisplayName("检查运算符数量")
    void checkNumberOfOperators() {
        log.info("开始运算符在指定范围测试");

        // 创建并行问题流
        Stream<Question> questionStream = Stream.generate(
                new QuestionFactory(QuestionEnum.HalfHalf)::produce);
        questionStream.parallel()
                .unordered()
                // 长度限制
                .limit(N)
                // 对于每一个问题执行
                .forEach(this::checkEveryQuestionNumberOfOperators);

        log.info("运算符检查完成，符合要求");
    }

    private void checkEveryQuestionRepeatedFormula(Question question) {
        // 检查一个问题中是否有重复
        Set<Formula> set = new HashSet<>();
        // 获取迭代器
        question.iterator()
                // 检查每一个算式
                .forEachRemaining(node ->
                        // 断言是否存在
                        Assertions.assertTrue(set.add(node.getFormula())));
    }

    private void checkEveryQuestionNumberOfOperators(Question question) {
        // 初始化映射
        Map<Operator, Integer> map = new EnumMap<>(Operator.class);
        map.put(Operator.Minus, 0);
        map.put(Operator.Plus, 0);

        // 获取迭代器
        question.iterator()
                // 添加每一个运算符
                .forEachRemaining(node ->
                        map.put(node.getFormula().getOp(), map.get(node.getFormula().getOp()) + 1));

        // 断言加法数量一致
        Assertions.assertSame(25, map.get(Operator.Plus));
        // 断言减法数量一致
        Assertions.assertSame(25, map.get(Operator.Minus));
    }
}
