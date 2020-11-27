package top.vuhe.view.center;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.vuhe.controller.observer.RefreshUiSubject;
import top.vuhe.controller.observer.intf.Observer;
import top.vuhe.model.Context;
import top.vuhe.model.entity.Formula;
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
        for (int i = 0; i < Context.FORMULA_NUM; i++) {
            QuestionLabel questionLabel = new QuestionLabel();
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
        if (RefreshUiSubject.NAME.equals(subjectName)) {
            Question question = Context.getQuestion();
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

class QuestionLabel extends JLabel {
    private Formula formula;
    private boolean showAns = false;

    QuestionLabel() {
        setSize(100, 10);
        setText("加载中……");
    }

    /**
     * 对一个标签中信息进行替换，显示答案
     */
    public void showAns() {
        if (!showAns) {
            setText(formula + "" + formula.getAns());
            showAns = true;
        }
    }

    /**
     * 重设算式
     *
     * @param formula 新算式
     */
    public void rebuild(Formula formula) {
        build(formula);
    }

    /**
     * 构建标签
     *
     * @param formula 算式
     */
    private void build(Formula formula) {
        this.formula = formula;
        setText(formula.toString());
        showAns = false;
    }
}