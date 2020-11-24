package top.vuhe.controller.problem;

import static top.vuhe.model.Context.FORMULA_NUM;
import top.vuhe.model.Problem;
import top.vuhe.controller.formula.FormulaFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vuhe
 */
public class ProblemFactory {
    private static final List<Problem> QUESTION = new ArrayList<>(51);

    public static List<Problem> getTestProblem() {
        if (QUESTION.size() < FORMULA_NUM) {
            for (int i = 0; i < FORMULA_NUM; i++) {
                // 用算式方法工厂创建一个算式
                // 用刚刚创建的算式，生成一个问题
                // 加入问题集合
                QUESTION.add(new Problem(FormulaFactory.getFormula()));
            }
        }
        return QUESTION;
    }
}
