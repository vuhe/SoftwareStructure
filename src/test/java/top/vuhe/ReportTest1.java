package top.vuhe;

import org.junit.Test;
import top.vuhe.controller.formula.FormulaFactory;
import top.vuhe.controller.problem.ProblemFactory;
import top.vuhe.model.Formula;
import top.vuhe.model.Operator;
import top.vuhe.model.Problem;

import java.util.*;

public class ReportTest1 {
    /**
     * 版本 v0.1 测试
     *
     * 测试算式的产生和运算
     */
    @Test
    public void testV1() {
        System.out.println("v0.1 算式：");
        List<Problem> problems = ProblemFactory.getTestProblem();
        for (int i = 0; i < 50; i++) {
            if (i != 0 && i % 5 == 0) {
                System.out.println();
            }
            Problem problem = problems.get(i);
            Formula formula = problem.getFormula();
            System.out.print(formula);
            System.out.printf("%3d   ", problem.getAns());
        }
        System.out.println("\n");
    }

    /**
     * 版本 v0.2 测试
     *
     * 测试算式的运算值是否符合 (0, 100)
     */
    @Test
    public void testV2() {
        System.out.println("v0.2:");
        // 测试生成的算式
        List<Problem> problems = ProblemFactory.getTestProblem();
        for (var p : problems) {
            Formula formula = p.getFormula();
            int a = formula.getA();
            int b = formula.getB();
            if (a <= 0 || 100 <= a) {
                throw new RuntimeException("随机数不符合 (0, 100)");
            }
            if (b <= 0 || 100 <= b) {
                throw new RuntimeException("随机数不符合 (0, 100)");
            }
        }
        System.out.println("运算值符合 (0, 100)\n");
    }

    /**
     * 版本 v0.3 测试
     *
     * 测试算式的运算结果是否符合 [0, 100]
     */
    @Test
    public void testV3() {
        System.out.println("v0.3:");
        // 测试生成的算式
        List<Problem> problems = ProblemFactory.getTestProblem();
        for (var p : problems) {
            Formula formula = p.getFormula();
            Problem problem = new Problem(formula);
            int ans = problem.getAns();
            if (ans < 0 || 100 < ans) {
                throw new RuntimeException("结果不符合 (0, 100)");
            }
        }
        System.out.println("运算结果符合 [0, 100]\n");
    }

    /**
     * 版本 v0.4 测试
     *
     * 测试「算式是否重复」以及「运算符的平均度」
     */
    @Test
    public void testV4() {
        System.out.println("v0.4:");
        Set<Formula> set = new HashSet<>();
        Map<Operator, Integer> map = new EnumMap<>(Operator.class);
        map.put(Operator.minus, 0);
        map.put(Operator.plus, 0);
        List<Problem> problems = ProblemFactory.getTestProblem();
        for (var p : problems) {
            Formula formula = p.getFormula();
            if (set.contains(formula)) {
                throw new RuntimeException("算式重复");
            }
            if (map.get(formula.getOp()) > 25) {
                throw new RuntimeException("运算符不平均");
            }
            set.add(formula);
            map.put(formula.getOp(), map.get(formula.getOp()) + 1);
        }
        System.out.println("算式没有重复，运算符平均生成\n");
    }
}
