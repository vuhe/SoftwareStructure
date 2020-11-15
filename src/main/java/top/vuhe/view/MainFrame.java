package top.vuhe.view;

import top.vuhe.view.function.FunctionPanel;
import top.vuhe.view.problem.ProblemPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    /**
     * 用静态函数返回（工厂模式）
     * 以便之后可能的扩展
     */
    public static void getMainFrame() {
        new MainFrame();
    }

    private MainFrame() {
        super("加减法口算练习系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 400);
        setResizable(false);
        setVisible(true);
        add(ProblemPanel.getProblemPanel(), BorderLayout.CENTER);
        add(new FunctionPanel(), BorderLayout.SOUTH);
    }
}
