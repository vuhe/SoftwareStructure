package top.vuhe.view.function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.vuhe.view.problem.ProblemPanel;

import javax.swing.*;
import java.awt.*;

/**
 * 界面下方的布局
 *
 * @author vuhe
 */
public class FunctionPanel extends JPanel {
    private static final Logger logger = LoggerFactory.getLogger(FunctionPanel.class);

    public FunctionPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));

        // 目前仅有一个显示答案的按钮
        var button = new JButton("显示答案");
        // 此处用于将显示方法提交到Swing UI 线程操作
        // 由于在其它线程操作因此可能会有延迟
        // 在显示答案之后会将按钮禁用
        button.addActionListener((e) -> SwingUtilities.invokeLater(() -> {
            ProblemPanel panel = ProblemPanel.getProblemPanel();
            panel.showAns();
            button.setEnabled(false);
            logger.info("push button ans disable it.");
        }));

        // 添加
        add(button);

        logger.info("build FunctionPanel.");
    }
}
