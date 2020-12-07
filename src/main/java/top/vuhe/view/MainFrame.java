package top.vuhe.view;

import lombok.extern.slf4j.Slf4j;
import top.vuhe.controller.ControllerExecutor;
import top.vuhe.view.window.QuestionPanel;
import top.vuhe.view.window.LoadingPanel;
import top.vuhe.view.menu.MainMenuBar;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author vuhe
 */
@Slf4j
public class MainFrame extends JFrame {
    private static final MainFrame INSTANCE = new MainFrame();
    private final CardLayout CARD_LAYOUT = new CardLayout();

    /**
     * 用静态函数返回（单例模式）
     * 以便之后可能的扩展
     */
    public static MainFrame instance() {
        log.info("获取主窗口");
        return INSTANCE;
    }

    private MainFrame() {
        super("加减法口算练习系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 400);
        setResizable(false);
        setLayout(CARD_LAYOUT);

        // 设置菜单
        setJMenuBar(MainMenuBar.instance());
        // 设置两个切换页面
        add(LoadingPanel.instance(), "loading");
        add(QuestionPanel.instance(), "question");

        // 默认显示加载中
        CARD_LAYOUT.show(getContentPane(), "loading");

        // 准备好后再显示，减少空白等待时间
        setVisible(true);

        refresh();
    }

    /**
     * 刷新主页面
     */
    public void refresh() {
        log.info("刷新主页面");
        startLoading();

        // 等待题目生成完毕
        Future<?> result = ControllerExecutor.buildQuestion();
        try {
            result.get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("题目生成线程出现问题", e);
        }

        endLoading();
        log.info("主页面刷新完成");
    }

    /**
     * 开始加载
     */
    private void startLoading() {
        // 显示加载
        CARD_LAYOUT.show(getContentPane(), "loading");
    }

    /**
     * 完成加载
     */
    private void endLoading() {
        // 刷新面板信息
        QuestionPanel.instance().refresh();

        // 显示题目
        CARD_LAYOUT.show(getContentPane(), "question");
    }
}
