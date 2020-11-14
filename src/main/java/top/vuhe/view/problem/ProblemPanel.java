package top.vuhe.view.problem;

import top.vuhe.controller.problem.ProblemFactory;
import top.vuhe.model.Problem;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ProblemPanel extends JPanel {
    public ProblemPanel() {
        setLayout(new GridLayout(10,5,5,5));
        List<Problem> problems = ProblemFactory.getTestProblem();
        for (var i : problems) {
            add(new JLabel(i.getFormula().toString()));
        }
    }
}
