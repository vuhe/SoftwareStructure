package top.vuhe.view.window.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class FormulasPanel extends JPanel {
    private static final Logger logger = LoggerFactory.getLogger(FormulasPanel.class);
    private static final FormulasPanel INSTANCE = new FormulasPanel();
    private final List<FormulaComponent> labels = new LinkedList<>();

    /**
     * 采用单例模式，禁止外部调用
     * 依据情况未来可能会换用实现
     */
    private FormulasPanel() {
        setLayout(new GridLayout(10, 5, 5, 5));
        for (int i = 0; i < Context.FORMULA_NUM; i++) {
            FormulaComponent formulaComponent = FormulaComponent.instance();
            add(formulaComponent);
            labels.add(formulaComponent);
        }
    }

    /**
     * 单例模式
     *
     * @return 此Panel的单例
     */
    public static FormulasPanel instance() {
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
     * 用于接受来自UI刷新的信息通知
     */
    public void update() {
        Question question = Context.getQuestion();
        // 算式 和 算式标签迭代器
        var itProblem = question.iterator();
        var itLabel = labels.iterator();
        // 循环设置
        while (itLabel.hasNext() && itProblem.hasNext()) {
            itLabel.next().setFormula(itProblem.next());
        }
    }
}

class FormulaComponent extends JPanel {
    private Formula formula;
    private final JLabel formulaText = new JLabel();
    private final JLabel ansText = new JLabel();

    private FormulaComponent() {
        setSize(100, 10);
        add(formulaText);
        add(ansText);
        // 设置题目加载
        formulaText.setText("加载中……");
        // 默认不显示答案
        ansText.setVisible(false);
    }

    static FormulaComponent instance() {
        return new FormulaComponent();
    }

    /**
     * 对一个标签中信息进行替换，显示答案
     */
    public void showAns() {
        ansText.setVisible(true);
    }

    public void setFormula(Formula formula) {
        // 设置问题文字
        formulaText.setText(formula.toString());

        // 设置答案文字
        ansText.setText(String.valueOf(formula.getAns()));
        // 默认不显示答案
        ansText.setVisible(false);

        // 记录算式
        this.formula = formula;
    }
}