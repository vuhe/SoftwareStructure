package top.vuhe.model;

import lombok.extern.slf4j.Slf4j;
import top.vuhe.model.entity.Question;

/**
 * 此应用上下文
 *
 * @author vuhe
 */
@Slf4j
public final class Context {
    /**
     * 算式数量
     * <br>
     * <b>不变量</b>
     */
    public static final int FORMULA_NUM = 50;

    public static final String FILE_PATH = "./exercise";

    private static String file = null;

    /**
     * 当前习题
     */
    private static Question question;

    private Context() {
        throw new UnsupportedOperationException("This class CAN'T be instantiated.");
    }

    public synchronized static Question getQuestion() {
        return question;
    }

    public synchronized static void setQuestion(Question question) {
        Context.question = question;
    }

    public synchronized static String getFile() {
        return file;
    }

    public synchronized static void setFile(String file) {
        Context.file = file;
    }
}
