package top.vuhe.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.vuhe.controller.factory.QuestionFactory;
import top.vuhe.controller.observer.CreateQuestionSubject;
import top.vuhe.controller.observer.RefreshUiSubject;
import top.vuhe.controller.observer.intf.Observer;
import top.vuhe.model.Context;
import top.vuhe.model.entity.Question;

import java.util.concurrent.*;

/**
 * @author vuhe
 */
public class ControllerExecutor implements Observer {
    private static final Logger logger = LoggerFactory.getLogger(ControllerExecutor.class);
    private static final ControllerExecutor INSTANCE = new ControllerExecutor();
    private final ExecutorService pool;

    private ControllerExecutor() {
        // CachedThreadPool
        pool = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<>(),
                r -> new Thread(r, "ControllerThread"));
    }

    public static ControllerExecutor instance() {
        return INSTANCE;
    }

    @Override
    public void update(String message, String subjectName) {
        // 收到创建新题目请求
        if (CreateQuestionSubject.NAME.equals(subjectName)) {
            createQuestion();
        }
    }

    public void createQuestion() {
        pool.submit(() -> {
            logger.info("创建线程更新习题");
            Question question = QuestionFactory.of().create();
            Context.setQuestion(question);
            RefreshUiSubject.instance().notifyObservers("创建完成");
        });
    }
}
