package top.vuhe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.vuhe.view.MainFrame;

import javax.swing.*;

/**
 * @author vuhe
 */
public class MainApplication {
    private static final Logger logger = LoggerFactory.getLogger(MainApplication.class);

    /**
     * 使用Swing的线程操作UI
     *
     * @param args 主函数参数
     */
    public static void main(String[] args) {
        logger.info("start system, welcome use this system!");
        SwingUtilities.invokeLater(MainFrame::create);
    }
}
