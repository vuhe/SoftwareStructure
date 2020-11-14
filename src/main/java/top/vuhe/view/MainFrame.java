package top.vuhe.view;

import top.vuhe.view.problem.ProblemPanel;

import javax.swing.*;

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
        setSize(500, 300);
        setResizable(false);
        setVisible(true);
        add(new ProblemPanel());
    }
}
