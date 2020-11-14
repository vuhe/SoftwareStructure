package top.vuhe;

import top.vuhe.view.MainFrame;

import javax.swing.*;

public class MainApplication {
    /**
     * 使用Swing的线程操作UI
     *
     * @param args 主函数参数
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::getMainFrame);
    }
}
