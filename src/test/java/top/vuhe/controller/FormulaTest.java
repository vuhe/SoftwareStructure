package top.vuhe.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.vuhe.controller.formula.FormulaFactory;
import top.vuhe.model.Formula;

/**
 * 算式类单元测试
 *
 * @author vuhe
 */
public class FormulaTest {
    private static final Logger logger = LoggerFactory.getLogger(FormulaTest.class);
    /**
     * 默认检验算式数
     */
    private static final int N = 10_000;

    @BeforeAll
    public static void beforeAll() {
        logger.info("「算式」单元测试");
    }

    @Test
    @DisplayName("运算数测试")
    public void checkCalculatedValue() {
        logger.info("开始运算数区间 (0, 100) 测试");
        for (int i = 0; i < N; i++) {
            Formula formula = FormulaFactory.getFormula();
            int a = formula.getA();
            int b = formula.getB();
            Assertions.assertTrue(0 < a && a < 100);
            Assertions.assertTrue(0 < b && b < 100);
        }
        logger.info("运算数检测完成，符合要求");
    }

    @Test
    @DisplayName("运算结果测试")
    public void checkFormulaAns() {
        logger.info("开始运算结果区间 [0, 100] 测试");
        for (int i = 0; i < N; i++) {
            Formula formula = FormulaFactory.getFormula();
            int ans = formula.getAns();
            Assertions.assertTrue(0 <= ans && ans <= 100);
        }
        logger.info("运算结果检测完成，符合要求");
    }
}
