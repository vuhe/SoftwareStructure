package top.vuhe.view.center;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.vuhe.controller.observer.RebuildQuestionSubject;
import top.vuhe.controller.observer.intf.Observer;
import top.vuhe.controller.factory.QuestionFactory;
import top.vuhe.model.entity.Question;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author vuhe
 */
public class QuestionPanel extends JPanel implements Observer {
    private static final Logger logger = LoggerFactory.getLogger(QuestionPanel.class);
    private static final QuestionPanel INSTANCE = new QuestionPanel();
    private List<QuestionLabel> labels = new LinkedList<>();

    /**
     * 采用单例模式，禁止外部调用
     * 注意：依据情况未来可能会换用enum实现
     */
    private QuestionPanel() {
        setLayout(new GridLayout(10, 5, 5, 5));
        Question question = QuestionFactory.of().create();
        for (var i : question) {
            QuestionLabel questionLabel = new QuestionLabel(i);
            add(questionLabel);
            labels.add(questionLabel);
        }
    }

    /**
     * 单例模式
     *
     * @return 此Panel的单例
     */
    public static QuestionPanel instance() {
        logger.info("获取问题面板单例");
        return INSTANCE;
    }

    /**
     * 循环调用标签中的显示方法
     */
    public void showAns() {
        logger.info("显示问题面板所有答案");
        for (var i : labels) {
            i.showAns();
        }
    }

    /**
     * 用于接受来自菜单栏的信息通知
     *
     * @param message     信息
     * @param subjectName 订阅名
     */
    @Override
    public void update(String message, String subjectName) {
        if (RebuildQuestionSubject.SUBJECT_NAME.equals(subjectName)) {
            Question question = QuestionFactory.of().create();
            // 算式 和 算式标签迭代器
            var itProblem = question.iterator();
            var itLabel = labels.iterator();
            // 循环设置
            while (itLabel.hasNext() && itProblem.hasNext()) {
                itLabel.next().rebuild(itProblem.next());
            }
        }
    }
}
