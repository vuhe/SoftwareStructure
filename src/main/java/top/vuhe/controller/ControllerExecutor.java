package top.vuhe.controller;

import lombok.extern.slf4j.Slf4j;
import top.vuhe.controller.factory.Factory;
import top.vuhe.controller.factory.QuestionEnum;
import top.vuhe.model.Context;
import top.vuhe.model.entity.Question;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.concurrent.*;

/**
 * Controller 线程池
 * <p>
 * 此类用于处理和后台处理相关的操作
 * 使用线程防止连接、读取、生成等操作阻塞线程
 *
 * @author vuhe
 */
@Slf4j
public class ControllerExecutor {
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
     * 生成习题文件
     */
    public static Future<?> buildQuestionToFile() {
        return invokeLater(() -> {
            var date = LocalDateTime.now();
            var prefix = Context.FILE_PATH +
                    "/" + date.getYear() +
                    "_" + date.getMonthValue() +
                    "_" + date.getDayOfMonth() + "_";

            // 混合生成
            writeQuestion(
                    Factory.getQuestionFactory(QuestionEnum.HalfHalf).produce(),
                    prefix + "混合算式"
            );

            // 全加法生成
            writeQuestion(
                    Factory.getQuestionFactory(QuestionEnum.AllPlus).produce(),
                    prefix + "全加法"
            );

            // 全减法生成
            writeQuestion(
                    Factory.getQuestionFactory(QuestionEnum.AllMinus).produce(),
                    prefix + "全减法"
            );
        });
    }

    /**
     * 从文件读取习题数据
     *
     * @param file 文件
     */
    public static Future<?> readQuestionFromFile(String file) {
        return invokeLater(() -> {
            log.info("读取文件");
            var json = readQuestion(file);
            Context.setQuestion(JsonUnit.fromJson(json, Question.class));
            log.info("读取完成");
        });
    }

    public static Future<?> writeQuestionToFile(Question question) {
        return invokeLater(() -> {
            var path = Context.FILE_PATH + "/" + Context.getFile();
            writeQuestion(question, path);
        });
    }

    private static void writeQuestion(Question question, String file) {
        var json = JsonUnit.toJson(question);
        try (var out = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)) {
            out.write(json);
        } catch (IOException e) {
            log.error("文件写入错误", e);
        }
    }

    private static String readQuestion(String file) {
        StringBuilder sb = new StringBuilder();
        try (var in = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line).append("\r\n");
            }
        } catch (IOException e) {
            log.error("文件读入错误", e);
        }
        return sb.toString();
    }
}
