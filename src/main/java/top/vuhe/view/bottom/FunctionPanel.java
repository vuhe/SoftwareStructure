package top.vuhe.view.bottom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.vuhe.controller.observer.RefreshUiSubject;
import top.vuhe.controller.observer.intf.Observer;
import top.vuhe.view.center.QuestionPanel;

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
        showAns.addActionListener(e -> SwingUtilities.invokeLater(() -> {
            QuestionPanel panel = QuestionPanel.instance();
            panel.showAns();
            showAns.setEnabled(false);
            logger.info("显示答案（button）");
        }));

        // 添加
        add(showAns);

        logger.info("创建功能面板");
    }

    public static FunctionPanel instance() {
        return INSTANCE;
    }

    /**
     * 用于接受来自UI刷新的通知
     *
     * @param message     信息
     * @param subjectName 订阅名
     */
    @Override
    public void update(String message, String subjectName) {
        if (RefreshUiSubject.NAME.equals(subjectName)) {
            showAns.setEnabled(true);
        }
    }
}
