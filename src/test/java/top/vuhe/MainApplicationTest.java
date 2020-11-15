package top.vuhe;

import org.junit.Test;
import top.vuhe.controller.formula.FormulaFactory;
import top.vuhe.controller.problem.ProblemFactory;
import top.vuhe.model.Formula;
import top.vuhe.model.Problem;

import java.util.List;

public class MainApplicationTest {
    /**
     * 版本 v0.1 测试
     *
     * 测试算式的产生和运算
     */
    @Test
    public void testV1() {
        List<Problem> problems = ProblemFactory.getTestProblem();
        System.out.println("算式：");
        for (int i = 0; i < 50; i++) {
            if (i != 0 && i % 5 == 0) {
                System.out.println();
            }
            Problem problem = problems.get(i);
            Formula formula = problem.getFormula();
            System.out.print(formula);
            System.out.printf("%3d   ", problem.getAns());
        }
    }

    /**
     * 版本 v0.2 测试
     *
     * 测试算式的运算值是否符合 (0, 100)
     */
    @Test
    public void testV2() {
        // 测试 1000 次
        for (int i = 0; i < 1000; i++) {
            Formula formula = FormulaFactory.getFormula();
            int a = formula.getA();
            int b = formula.getB();
            if (a <= 0 || 100 <= a) {
                throw new RuntimeException("随机数不符合 (0, 100)");
            }
            if (b <= 0 || 100 <= b) {
                throw new RuntimeException("随机数不符合 (0, 100)");
            }
        }
        System.out.println("\n\n运算值符合 (0, 100)");
    }

    /**
     * 版本 v0.3 测试
     *
     * 测试算式的运算结果是否符合 [0, 100]
     */
    @Test
    public void testV3() {
        // 测试 1000 次
        for (int i = 0; i < 1000; i++) {
            Formula formula = FormulaFactory.getFormula();
            Problem problem = new Problem(formula);
            int ans = problem.getAns();
            if (ans < 0 || 100 < ans) {
                throw new RuntimeException("结果不符合 (0, 100)");
            }
        }
        System.out.println("\n运算结果符合 [0, 100]");
    }
}
