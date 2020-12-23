package top.vuhe.controller.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.vuhe.model.entity.Formula;
import top.vuhe.model.entity.Question;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 问题生成器
 * <p>
 * 职责：用于一套习题的生成
 * 检查：一套题中的重复和算式出现概率
 *
 * @author vuhe
 */
class QuestionFactory extends Factory<Question> {
    private static final Logger logger = LoggerFactory.getLogger(QuestionFactory.class);
    /**
     * 生成的问题
     */
    private Question question;
    private final QuestionEnum type;

    /**
     * 构造方法
     * <p>
     * 默认不对包外开放，以便将来扩展使用
     */
    QuestionFactory(QuestionEnum type) {
        this.type = type;
    }

    /**
     * 生成一套习题
     *
     * @return 习题
     */
    @Override
    public Question produce() {
        if (question == null) {
            buildProblem();
        }
        logger.debug("返回生成的一套习题");
        return question;
    }

    /**
     * 构建一套习题
     * <p>
     * 习题中加法减法比例已在初始化化时确定
     * 按顺序生成后打乱顺序
     */
    private void buildProblem() {
        // 加法
        Factory<Formula> addFormula = new AddFormulaFactory();
        Stream<Formula> addStream = Stream.generate(addFormula::produce);
        // 并行化加法算式流
        addStream = addStream.parallel()
                // 忽略顺序
                .unordered()
                // 去重
                .distinct()
                // 取一定的加法算式
                .limit(type.plus);

        // 减法
        Factory<Formula> subFormula = new SubFormulaFactory();
        Stream<Formula> subStream = Stream.generate(subFormula::produce);
        // 并行化减法算式
        subStream = subStream.parallel()
                // 忽略顺序
                .unordered()
                // 去重
                .distinct()
                // 取一定的减法算式
                .limit(type.minus);

        // 合并流并收集
        List<Formula> formulas = Stream.concat(addStream, subStream)
                .unordered().parallel().collect(Collectors.toList());
        // 打乱
        Collections.shuffle(formulas);
        logger.debug("创建一套习题");

        this.question = Question.from(formulas);
    }
}
