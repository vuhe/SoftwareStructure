package top.vuhe.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.vuhe.controller.factory.QuestionFactory;
import top.vuhe.model.entity.Formula;
import top.vuhe.model.entity.Operator;
import top.vuhe.model.entity.Question;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 问题类单元测试
 *
 * @author vuhe
 */
public class QuestionTest {
    private static final Logger logger = LoggerFactory.getLogger(QuestionTest.class);
    /**
     * 默认检验问题数
     */
    private static final int N = 100;

    @BeforeAll
    public static void beforeAll() {
        logger.info("「问题」单元测试");
    }

    @Test
    @DisplayName("检查重复算式")
    public void checkRepeatedFormula() {
        logger.info("开始运算式生成不重复测试");
        for (int i = 0; i < N; i++) {
            Question question = QuestionFactory.of().create();
            Set<Formula> set = new HashSet<>();
            for (var formula : question) {
                Assertions.assertTrue(set.add(formula));
            }
        }
        logger.info("算式检测完成，无重复");
    }

    @Test
    @DisplayName("检查运算符数量")
    public void checkNumberOfOperators() {
        logger.info("开始运算符在指定范围测试");
        for (int i = 0; i < N; i++) {
            Question question = QuestionFactory.of().create();
            Map<Operator, Integer> map = new EnumMap<>(Operator.class);
            map.put(Operator.minus, 0);
            map.put(Operator.plus, 0);
            for (var formula : question) {
                map.put(formula.getOp(), map.get(formula.getOp()) + 1);
            }
            Assertions.assertSame(map.get(Operator.minus), map.get(Operator.plus));
        }
        logger.info("运算符检查完成，符合要求");
    }
}
