package top.vuhe.view.function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.vuhe.controller.observer.RebuildQuestionSubject;
import top.vuhe.controller.observer.intf.Observer;
import top.vuhe.view.problem.ProblemPanel;

import javax.swing.*;
import java.awt.*;

/**
 * 界面下方的布局
 *
 * @author vuhe
 */
public class FunctionPanel extends JPanel implements Observer {
    private static final Logger logger = LoggerFactory.getLogger(FunctionPanel.class);
    private static final FunctionPanel INSTANCE = new FunctionPanel();
    private final JButton showAns = new JButton("显示答案");

    private FunctionPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));

        // 目前仅有一个显示答案的按钮
        // 此处用于将显示方法提交到Swing UI 线程操作
        // 由于在其它线程操作因此可能会有延迟
        // 在显示答案之后会将按钮禁用
        showAns.addActionListener((e) -> SwingUtilities.invokeLater(() -> {
            ProblemPanel panel = ProblemPanel.instance();
            panel.showAns();
            showAns.setEnabled(false);
            logger.info("push button ans disable it.");
        }));

        // 添加
        add(showAns);

        logger.info("build FunctionPanel.");
    }

    public static FunctionPanel instance() {
        return INSTANCE;
    }

    /**
     * 用于接受来自菜单栏的信息通知
     *
     * @param message     信息
     * @param subjectName 订阅名
     */
    @Override
    public void update(String message, String subjectName) {
        if (RebuildQuestionSubject.SUBJECT_NAME.equals(subjectName)) {
            showAns.setEnabled(true);
        }
    }
}
