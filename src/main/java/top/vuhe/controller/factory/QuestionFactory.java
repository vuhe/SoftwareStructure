package top.vuhe.controller.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.vuhe.model.Context;
import top.vuhe.model.entity.Formula;
import top.vuhe.model.entity.Operator;
import top.vuhe.model.entity.Question;

import java.util.*;

import static top.vuhe.model.Context.*;

/**
 * 问题生成器
 * <p>
 * 职责：用于一套习题的生成
 * 检查：一套题中的重复和算式出现概率
 *
 * @author vuhe
 */
public class QuestionFactory {
    private static final Logger logger = LoggerFactory.getLogger(QuestionFactory.class);
    /**
     * 算式统计器
     */
    private final Set<Formula> formulaSet = new HashSet<>();
    /**
     * 生成的问题
     */
    private Question question;

    /**
     * 构造方法
     * <p>
     * 默认不开放，以便将来扩展使用
     */
    private QuestionFactory() {}

    /**
     * 默认构造
     * <p>
     * 比例由应用上文确定
     *
     * @return 构造工厂
     */
    public static QuestionFactory of() {
        return new QuestionFactory();
    }

    /**
     * 生成一套习题
     *
     * @return 习题
     */
    public Question create() {
        if (question == null) {
            buildProblem();
        }
        logger.info("返回生成的一套习题");
        return question;
    }

    /**
     * 构建一套习题
     * <p>
     * 习题中加法减法比例已在初始化化时确定
     * 按顺序生成后打乱顺序
     */
    private void buildProblem() {
        List<Formula> formulas = new ArrayList<>(FORMULA_NUM + 1);
        // 加法
        Operator op = Operator.plus;
        for (int i = 0; i < Context.getPlusNum(); i++) {
            // 用刚刚创建的算式，生成一个加法算式
            // 加入问题集合
            formulas.add(checkAndBuildFormula(op));
        }
        // 减法
        op = Operator.minus;
        for (int i = 0; i < Context.getMinusNum(); i++) {
            // 用刚刚创建的算式，生成一个减法算式
            // 加入问题集合
            formulas.add(checkAndBuildFormula(op));
        }

        // 打乱
        Collections.shuffle(formulas);
        logger.info("创建一套习题");

        this.question = Question.from(formulas);
    }

    /**
     * 检查算式是否存在
     *
     * @param op 运算符
     * @return 检查过的算式
     */
    private Formula checkAndBuildFormula(Operator op) {
        Formula formula;
        do {
            formula = FormulaFactory.getFormula(op);
        } while (!formulaSet.add(formula));
        return formula;
    }
}
