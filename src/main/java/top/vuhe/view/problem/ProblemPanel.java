package top.vuhe.view.problem;

import top.vuhe.controller.problem.ProblemFactory;
import top.vuhe.model.Problem;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class ProblemPanel extends JPanel {
    private static ProblemPanel PANEL = new ProblemPanel();
    private List<ProblemLabel> labels = new LinkedList<>();

    private ProblemPanel() {
        setLayout(new GridLayout(10,5, 5, 5));
        List<Problem> problems = ProblemFactory.getTestProblem();
        for (var i : problems) {
            ProblemLabel problemLabel = new ProblemLabel(i);
            add(problemLabel);
            labels.add(problemLabel);
        }
    }

    public static ProblemPanel getProblemPanel() {
        return PANEL;
    }

    public void showAns() {
        for (var i : labels) {
            i.showAns();
        }
    }
}
