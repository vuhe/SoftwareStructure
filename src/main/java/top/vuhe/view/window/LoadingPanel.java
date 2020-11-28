package top.vuhe.view.window;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

/**
 * 加载中页面
 *
 * @author vuhe
 */
public class LoadingPanel extends JPanel {
    private static final Logger logger = LoggerFactory.getLogger(LoadingPanel.class);
    private static final JPanel INSTANCE = new LoadingPanel();

    private LoadingPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(new JLabel("加载中……"));
    }

    public static JPanel instance() {
        logger.info("获取加载页面");
        return INSTANCE;
    }
}
