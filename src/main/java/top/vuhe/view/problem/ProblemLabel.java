package top.vuhe.view.problem;

import top.vuhe.model.Problem;

import javax.swing.*;

public class ProblemLabel extends JLabel {
    private final Problem problem;
    private boolean showAns = false;

    public ProblemLabel(Problem problem) {
        this.problem = problem;
        setSize(100, 10);
        setText(problem.getFormula().toString());
    }

    public void showAns() {
        if (!showAns) {
            setText(problem.getFormula() + "" + problem.getAns());
            showAns = true;
        }
    }
}
