package top.vuhe.view.window.component;

import lombok.extern.slf4j.Slf4j;
import top.vuhe.model.Context;
import top.vuhe.model.entity.Question;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author vuhe
 */
@Slf4j
public class FormulasPanel extends JPanel {
    private static final FormulasPanel INSTANCE = new FormulasPanel();
    private final List<FormulaComponent> labels = new LinkedList<>();

    /**
     * 采用单例模式，禁止外部调用
     * 依据情况未来可能会换用实现
     */
    private FormulasPanel() {
        setLayout(new GridLayout(10, 5, 5, 5));
    }

    /**
     * 单例模式
     *
     * @return 此Panel的单例
     */
    public static FormulasPanel instance() {
        log.info("获取算式面板");
        return INSTANCE;
    }

    /**
     * 循环调用标签中的显示方法
     */
    public boolean checkAns() {
        log.info("显示所有算式答案");
        var isAllDone = true;
        for (var i : labels) {
            isAllDone = isAllDone && i.hasUserAns();
        }
        if (isAllDone) {
            for (var i : labels) {
                i.checkAns();
            }
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "未做完前不能提交",
                    "警告",
                    JOptionPane.WARNING_MESSAGE);
        }
        return isAllDone;
    }

    /**
     * 用于接受来自UI刷新的信息通知
     */
    public void update() {
        Question question = Context.getQuestion();
        var isDone = true;
        for (var q : question) {
            var f = FormulaComponent.instance(q);
            labels.add(f);
            add(f);
            isDone = isDone && (q.getState() == Question.State.Correct ||
                    q.getState() == Question.State.Wrong);
        }
        if (isDone) {
            FunctionPanel.instance().isDone();
        }
    }

    public void reset() {
        for (var l : labels) {
            l.reset();
        }
    }

    public void save() {
        for (var l : labels) {
            l.save();
        }
    }
}

@Slf4j
class FormulaComponent extends JPanel {
    private final Question.Node node;
    private final JLabel formulaText = new JLabel();
    private final JTextField userAns = new JTextField(2);
    private final JLabel ansText = new JLabel();

    private FormulaComponent(Question.Node node) {
        this.node = node;

        // TODO-对状态进行复原
        // 设置问题文字
        formulaText.setText(node.getFormula().toString());

        // 设置答案文字
        ansText.setText(String.valueOf(node.getFormula().getAns()));
        // 默认不显示答案
        ansText.setVisible(false);

        userAns.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String key = "0123456789" + (char) 8;
                if (key.indexOf(e.getKeyChar()) < 0) {
                    //如果不是数字则取消
                    e.consume();
                }
            }
        });

        // 习题复原
        if (node.getState() != Question.State.NotDo) {
            userAns.setText(node.getUserAns().toString());
            if (node.getState() != Question.State.Done) {
                checkAns();
            }
        }

        setSize(100, 10);
        add(formulaText);
        add(userAns);
        add(ansText);
    }

    static FormulaComponent instance(Question.Node node) {
        return new FormulaComponent(node);
    }

    /**
     * 对一个标签中信息进行替换，显示答案
     */
    public void checkAns() {
        // 禁止再编辑
        userAns.setEditable(false);
        var userInput = userAns.getText();
        // 检查结果，设置状态
        if (Objects.equals(userInput, ansText.getText())) {
            ansText.setForeground(Color.GREEN);
            node.setState(Question.State.Correct);
        } else {
            ansText.setForeground(Color.RED);
            node.setState(Question.State.Wrong);
        }
        ansText.setVisible(true);
    }

    public boolean hasUserAns() {
        return !"".equals(userAns.getText());
    }

    public void reset() {
        userAns.setText("");
        ansText.setVisible(false);
        node.setState(Question.State.NotDo);
        node.setUserAns(null);
    }

    public void save() {
        // 设置状态
        if (node.getState() == Question.State.NotDo
                && !"".equals(userAns.getText())) {
            node.setState(Question.State.Done);
        }
        if (node.getState() != Question.State.NotDo) {
            node.setUserAns(Integer.parseInt(userAns.getText()));
        }
    }
}