package top.vuhe.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.vuhe.controller.ControllerExecutor;
import top.vuhe.view.bottom.FunctionPanel;
import top.vuhe.view.center.LoadingPanel;
import top.vuhe.view.menu.MainMenuBar;
import top.vuhe.view.center.QuestionPanel;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author vuhe
 */
public class MainFrame extends JFrame {
    private static final Logger logger = LoggerFactory.getLogger(MainFrame.class);
    private static final MainFrame INSTANCE = new MainFrame();

    /**
     * 用静态函数返回（单例模式）
     * 以便之后可能的扩展
     */
    public static MainFrame instance() {
        logger.info("获取主窗口");
        return INSTANCE;
    }

    private MainFrame() {
        super("加减法口算练习系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 400);
        setResizable(false);
        setLayout(new BorderLayout(5,5));

        // 设置菜单
        setJMenuBar(MainMenuBar.instance());

        // 默认为BorderLayout布局
        // 准备好后再显示，减少空白等待时间
        setVisible(true);

        refresh();
    }

    public void refresh() {
        startLoading();

        // 等待题目生成完毕
        Future<?> result = ControllerExecutor.buildQuestion();
        try {
            result.get();
        } catch (InterruptedException | ExecutionException e) {
            logger.error("题目生成线程出现问题", e);
        }

        endLoading();
    }

    /**
     * 开始加载
     */
    private void startLoading() {
        getContentPane().removeAll();
        getContentPane().add(LoadingPanel.instance(), BorderLayout.CENTER);
    }

    /**
     * 完成加载
     */
    private void endLoading() {
        QuestionPanel.instance().update();
        FunctionPanel.instance().update();

        getContentPane().removeAll();
        getContentPane().add(QuestionPanel.instance(), BorderLayout.CENTER);
        getContentPane().add(FunctionPanel.instance(), BorderLayout.SOUTH);
    }
}
