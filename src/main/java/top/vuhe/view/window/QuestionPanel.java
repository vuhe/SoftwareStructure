package top.vuhe.view.window;

import lombok.extern.slf4j.Slf4j;
import top.vuhe.view.window.component.FormulasPanel;
import top.vuhe.view.window.component.FunctionPanel;

import javax.swing.*;
import java.awt.*;

/**
 * @author vuhe
 */
@Slf4j
public class QuestionPanel extends JPanel {
    private static final QuestionPanel INSTANCE = new QuestionPanel();
    private final FormulasPanel formulasPanel = FormulasPanel.instance();
    private final FunctionPanel buttonPanel = FunctionPanel.instance();

    private QuestionPanel() {
        setLayout(new BorderLayout(5, 5));
        add(formulasPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * 问题面板
     *
     * @return 问题面板
     */
    public static QuestionPanel instance() {
        log.info("获取问题面板");
        return INSTANCE;
    }

    /**
     * 刷新
     */
    public void refresh() {
        formulasPanel.update();
        buttonPanel.update();
    }
}
