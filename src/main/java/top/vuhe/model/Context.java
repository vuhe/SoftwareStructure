package top.vuhe.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.vuhe.model.entity.Question;

/**
 * 此应用上下文
 *
 * @author vuhe
 */
public final class Context {
    private static final Logger logger = LoggerFactory.getLogger(Context.class);
    /**
     * 算式数量
     * <br>
     * <b>不变量</b>
     */
    public static final int FORMULA_NUM = 50;
    /**
     * 算式结果最大值
     * <br>
     * <b>不变量</b>
     */
    public static final int ANS_MAX = 100;
    /**
     * 概率和
     * <br>
     * <b>不变量</b>
     */
    private static final int PROBABILITY_SUM = 100;

    /**
     * 加法算式数量
     * <br>
     * <b>变量</b>
     */
    private static int PLUS_NUM = 25;
    /**
     * 减法算式数量
     * <br>
     * <b>变量</b>
     */
    private static int MINUS_NUM = 25;
    /**
     * 当前习题
     */
    private static Question question;

    private Context() {
        throw new UnsupportedOperationException("This class CAN'T be instantiated.");
    }

    public synchronized static int getMinusNum() {
        return MINUS_NUM;
    }

    public synchronized static int getPlusNum() {
        return PLUS_NUM;
    }

    public synchronized static void setProportionNumber(int plus, int minus) {
        if (plus + minus != PROBABILITY_SUM) {
            logger.error("概率和不为 100% !");
            throw new IllegalArgumentException("The calculation formula accounts for not 100%.");
        }
        if (plus < 0) {
            logger.error("加法算式比例小于 0 !");
            throw new IllegalArgumentException("The ratio of addition formula cannot be less than zero.");
        }
        if (minus < 0) {
            logger.error("减法算式比例小于 0 !");
            throw new IllegalArgumentException("The ratio of subtraction formula cannot be less than zero.");
        }
        // 百分比转换为数量
        PLUS_NUM = (int) (plus * 0.01 * FORMULA_NUM);
        MINUS_NUM = FORMULA_NUM - PLUS_NUM;
    }

    public synchronized static Question getQuestion() {
        return question;
    }

    public synchronized static void setQuestion(Question question) {
        Context.question = question;
    }
}
