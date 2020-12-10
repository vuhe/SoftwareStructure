package top.vuhe.view.window;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;

/**
 * 加载中页面
 *
 * @author vuhe
 */
@Slf4j
public class LoadingPanel extends JPanel {
    private static final JPanel INSTANCE = new LoadingPanel();

    private LoadingPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(new JLabel("加载中……"));
    }

    public static JPanel instance() {
        log.info("获取加载页面");
        return INSTANCE;
    }
}
