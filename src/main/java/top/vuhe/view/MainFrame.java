package top.vuhe.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.vuhe.controller.observer.CreateQuestionSubject;
import top.vuhe.controller.observer.RefreshUiSubject;
import top.vuhe.controller.observer.intf.Observer;
import top.vuhe.view.bottom.FunctionPanel;
import top.vuhe.view.center.LoadingPanel;
import top.vuhe.view.menu.MainMenuBar;
import top.vuhe.view.center.QuestionPanel;

import javax.swing.*;
import java.awt.*;

/**
 * @author vuhe
 */
public class MainFrame extends JFrame implements Observer {
    private static final Logger logger = LoggerFactory.getLogger(MainFrame.class);
    private static final MainFrame INSTANCE = new MainFrame();

    /**
     * 用静态函数返回（单例模式）
     * 以便之后可能的扩展
     */
    public static MainFrame create() {
        logger.info("创建主窗口");
        return INSTANCE;
    }

    private MainFrame() {
        super("加减法口算练习系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 400);
        setResizable(false);

        // 设置菜单
        setJMenuBar(MainMenuBar.instance());

        // 默认为BorderLayout布局
        // 设置题目在中心
        add(QuestionPanel.instance(), BorderLayout.CENTER);
        // 设置按钮操作在下方
        add(FunctionPanel.instance(), BorderLayout.SOUTH);

        // 准备好后再显示，减少空白等待时间
        setVisible(true);
    }

    @Override
    public void update(String message, String subjectName) {
        if (CreateQuestionSubject.NAME.equals(subjectName)) {
            startLoading();
        } else if (RefreshUiSubject.NAME.equals(subjectName)) {
            endLoading();
        }
    }

    private void startLoading() {
        remove(QuestionPanel.instance());
        add(LoadingPanel.instance(), BorderLayout.CENTER);
    }

    private void endLoading() {
        remove(LoadingPanel.instance());
        add(QuestionPanel.instance(), BorderLayout.CENTER);
    }
}
