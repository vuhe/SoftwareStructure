package top.vuhe;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.vuhe.controller.problem.ProblemFactory;
import top.vuhe.model.Problem;

/**
 * 面向过程测试
 *
 * @author vuhe
 */
public class MainTest {
    private static final Logger logger = LoggerFactory.getLogger(MainTest.class);

    /**
     * 版本 v0.x 测试
     * <p>
     * 测试算式的产生和运算
     */
    @Test
    @DisplayName("面向过程测试")
    public void test() {
        logger.info("v0.x 面向过程测试");
        Problem problem = ProblemFactory.getTestProblem();
        int i = 0;
        for (var formula : problem) {
            if (i != 0 && i % 5 == 0) {
                System.out.println();
            }
            System.out.print(formula);
            System.out.printf("%3d   ", formula.getAns());
            i++;
        }
        System.out.println("\n");
    }
}