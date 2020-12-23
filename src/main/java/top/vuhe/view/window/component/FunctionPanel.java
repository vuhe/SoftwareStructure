package top.vuhe.view.window.component;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;

/**
 * 界面下方的布局
 *
 * @author vuhe
 */
@Slf4j
public class FunctionPanel extends JPanel {
    private static final FunctionPanel INSTANCE = new FunctionPanel();
    private final JButton showAns = new JButton("检查答案");
    // TODO-重置问题
    private final JButton reset = new JButton("重置");
    // TODO-保存状态，写入文件
    private final JButton save = new JButton("保存");

    private FunctionPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));

        // 在显示答案之后会将按钮禁用
        showAns.addActionListener(e -> {
            FormulasPanel panel = FormulasPanel.instance();
            if (panel.checkAns()) {
                showAns.setEnabled(false);
                log.info("显示答案（button）");
            }
        });

        // 添加
        add(showAns);
        add(reset);
        add(save);

        log.info("获取功能按钮面板");
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
