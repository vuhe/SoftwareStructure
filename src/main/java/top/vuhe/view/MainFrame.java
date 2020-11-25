package top.vuhe.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.vuhe.view.function.FunctionPanel;
import top.vuhe.view.problem.ProblemPanel;

import javax.swing.*;
import java.awt.*;

/**
 * @author vuhe
 */
public class MainFrame extends JFrame {
    private static final Logger logger = LoggerFactory.getLogger(MainFrame.class);

    /**
     * 用静态函数返回（工厂模式）
     * 以便之后可能的扩展
     */
    public static void create() {
        new MainFrame();
        logger.info("build MainFrame.");
    }

    private MainFrame() {
        super("加减法口算练习系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 400);
        setResizable(false);
        setVisible(true);

        // 默认为BorderLayout布局
        // 设置题目在中心
        add(ProblemPanel.getProblemPanel(), BorderLayout.CENTER);
        // 设置按钮操作在下方
        add(new FunctionPanel(), BorderLayout.SOUTH);
    }
}
