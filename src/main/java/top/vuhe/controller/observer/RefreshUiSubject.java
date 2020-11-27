package top.vuhe.controller.observer;

import top.vuhe.controller.observer.intf.AbstractSubject;
import top.vuhe.controller.observer.intf.Observer;
import top.vuhe.view.MainFrame;
import top.vuhe.view.bottom.FunctionPanel;
import top.vuhe.view.center.QuestionPanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vuhe
 */
public class RefreshUiSubject extends AbstractSubject {
    public static final String NAME = "刷新UI";
    private final List<Observer> list = new ArrayList<>();
    private static final AbstractSubject INSTANCE = new RefreshUiSubject();

    private RefreshUiSubject() {
        // 先通知更新题目
        addObserver(QuestionPanel.instance());
        addObserver(FunctionPanel.instance());
        // 再通知更新面板
        addObserver(MainFrame.create());
    }

    public static AbstractSubject instance() {
        return INSTANCE;
    }

    @Override
    public void addObserver(Observer o) {
        list.add(o);
    }

    @Override
    public void deleteObserver(Observer o) {

    }

    @Override
    public void notifyObservers(String message) {
        // 此处使用 Swing 线程处理 UI
        SwingUtilities.invokeLater(() -> {
            for (var o : list) {
                o.update(message, NAME);
            }
        });
    }
}
