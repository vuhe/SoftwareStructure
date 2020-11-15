package top.vuhe.view.function;

import top.vuhe.view.problem.ProblemPanel;

import javax.swing.*;
import java.awt.*;

public class FunctionPanel extends JPanel {
    public FunctionPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        var button = new JButton("显示答案");
        button.addActionListener((e) -> SwingUtilities.invokeLater(() -> {
            ProblemPanel panel = ProblemPanel.getProblemPanel();
            panel.showAns();
            button.setEnabled(false);
        }));
        add(button);
    }
}
