package top.vuhe.view.window.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

/**
 * 界面下方的布局
 *
 * @author vuhe
 */
public class FunctionPanel extends JPanel {
    private static final Logger logger = LoggerFactory.getLogger(FunctionPanel.class);
    private static final FunctionPanel INSTANCE = new FunctionPanel();
    private final JButton showAns = new JButton("显示答案");

    private FunctionPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));

        // 目前仅有一个显示答案的按钮
        // 在显示答案之后会将按钮禁用
        showAns.addActionListener(e -> {
            FormulasPanel panel = FormulasPanel.instance();
            panel.showAns();
            showAns.setEnabled(false);
            logger.info("显示答案（button）");
        });

        // 添加
        add(showAns);

        logger.info("获取功能按钮面板");
    }

    public static FunctionPanel instance() {
        return INSTANCE;
    }

    /**
     * 用于接受来自UI刷新的通知
     */
    public void update() {
        showAns.setEnabled(true);
    }
}
