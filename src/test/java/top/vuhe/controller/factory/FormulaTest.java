package top.vuhe.controller.factory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.vuhe.model.entity.Formula;

import java.util.stream.Stream;

/**
 * 算式类单元测试
 *
 * @author vuhe
 */
class FormulaTest {
    private static final Logger logger = LoggerFactory.getLogger(FormulaTest.class);
    /**
     * 默认检验算式数
     */
    private static final int N = 100_000;
    /**
     * 加法算式工厂
     */
    private static AddFormulaFactory addFactory;
    /**
     * 减法算式工厂
     */
    private static SubFormulaFactory subFactory;

    @BeforeAll
    static void beforeAll() {
        logger.info("「算式」单元测试");
        addFactory = new AddFormulaFactory();
        subFactory = new SubFormulaFactory();
    }

    /**
     * 提供测试用方法工厂
     *
     * @return 算式工厂流
     */
    static Stream<Factory<Formula>> factoryStream() {
        return Stream.of(addFactory, subFactory);
    }

    @ParameterizedTest
    @DisplayName("运算数测试")
    @MethodSource("factoryStream")
    void checkCalculatedValue(Factory<Formula> factory) {
        logger.info("开始运算数区间 (0, 100) 测试");

        Stream<Formula> stream = Stream.generate(factory::produce);
        stream.parallel()
                .limit(N)
                .unordered()
                .forEach(formula -> {
                    int a = formula.getA();
                    int b = formula.getB();
                    Assertions.assertTrue(0 < a && a < 100);
                    Assertions.assertTrue(0 < b && b < 100);
                });

        logger.info("运算数检测完成，符合要求");
    }

    @ParameterizedTest
    @DisplayName("运算结果测试")
    @MethodSource("factoryStream")
    void checkFormulaAns(Factory<Formula> factory) {
        logger.info("开始运算结果区间 [0, 100] 测试");

        Stream<Formula> stream = Stream.generate(factory::produce);
        stream.parallel()
                .limit(N)
                .unordered()
                .forEach(formula -> {
                    int ans = formula.getAns();
                    Assertions.assertTrue(0 <= ans && ans <= 100);
                });

        logger.info("运算结果检测完成，符合要求");
    }
}
