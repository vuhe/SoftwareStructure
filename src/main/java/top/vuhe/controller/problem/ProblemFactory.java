package top.vuhe.controller.problem;

import static top.vuhe.model.Context.FORMULA_NUM;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.vuhe.model.Formula;
import top.vuhe.model.Problem;
import top.vuhe.controller.formula.FormulaFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 问题生成器
 * <p>
 * 职责：用于一套习题的生成
 *
 * @author vuhe
 */
public class ProblemFactory {
    private static final Logger logger = LoggerFactory.getLogger(ProblemFactory.class);
    private static Problem PROBLEM = null;

    public static Problem getTestProblem() {
        List<Formula> formulas = new ArrayList<>(51);
        if (PROBLEM == null) {
            for (int i = 0; i < FORMULA_NUM; i++) {
                // 用算式方法工厂创建一个算式
                // 用刚刚创建的算式，生成一个问题
                // 加入问题集合
                formulas.add(FormulaFactory.getFormula());
            }
            PROBLEM = Problem.from(formulas);

            logger.info("build new Problem.");

        }

        logger.info("return a Problem.");

        return PROBLEM;
    }
}
