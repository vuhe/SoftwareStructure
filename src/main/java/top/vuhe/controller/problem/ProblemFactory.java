package top.vuhe.controller.problem;

import top.vuhe.model.Problem;
import top.vuhe.controller.formula.FormulaFactory;

import java.util.ArrayList;
import java.util.List;

public class ProblemFactory {
    private static final List<Problem> question = new ArrayList<>(51);

    public static List<Problem> getTestProblem() {
        if (question.size() < 50) {
            for (int i = 0; i < 50; i++) {
                // 用算式方法工厂创建一个算式
                // 用刚刚创建的算式，生成一个问题
                // 加入问题集合
                question.add(new Problem(FormulaFactory.getFormula()));
            }
        }
        return question;
    }
}
