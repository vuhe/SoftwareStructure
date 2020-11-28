package top.vuhe.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.vuhe.controller.factory.QuestionFactory;
import top.vuhe.model.Context;
import top.vuhe.model.entity.Question;

import java.util.concurrent.*;

/**
 * Controller 线程池
 * <p>
 * 此类用于处理和后台处理相关的操作
 * 使用线程防止连接、读取、生成等操作阻塞线程
 *
 * @author vuhe
 */
public class ControllerExecutor {
    private static final Logger logger = LoggerFactory.getLogger(ControllerExecutor.class);
    private static final ControllerExecutor INSTANCE = new ControllerExecutor();
    /**
     * 线程池
     */
    private final ExecutorService pool;

    private ControllerExecutor() {
        // CachedThreadPool 线程池
        pool = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<>(),
                r -> new Thread(r, "ControllerThread"));
    }

    public static ControllerExecutor instance() {
        return INSTANCE;
    }

    public static Future<?> invokeLater(Runnable task) {
        return INSTANCE.pool.submit(task);
    }

    public static <T> Future<T> invokeLater(Callable<T> task) {
        return INSTANCE.pool.submit(task);
    }

    /**
     * 创建新习题任务
     */
    public static Future<?> buildQuestion() {
        return invokeLater(() -> {
            logger.info("创建线程更新习题");

            Question question = QuestionFactory.of().create();
            Context.setQuestion(question);

            logger.info("创建完成");
        });
    }
}