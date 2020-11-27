package top.vuhe.view.center;

import top.vuhe.model.entity.Formula;

import javax.swing.*;

/**
 * @author vuhe
 */
public class QuestionLabel extends JLabel {
    private Formula formula;
    private boolean showAns = false;

    public QuestionLabel(Formula formula) {
        setSize(100, 10);
        setText("加载中……");
        build(formula);
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
