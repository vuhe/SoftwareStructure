package top.vuhe.view.problem;

import top.vuhe.model.Formula;

import javax.swing.*;

/**
 * @author vuhe
 */
public class ProblemLabel extends JLabel {
    private final Formula formula;
    private boolean showAns = false;

    public ProblemLabel(Formula formula) {
        this.formula = formula;
        setSize(100, 10);
        setText(formula.toString());
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
}
