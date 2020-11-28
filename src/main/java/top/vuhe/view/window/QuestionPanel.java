package top.vuhe.view.window;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.vuhe.view.window.component.FormulasPanel;
import top.vuhe.view.window.component.FunctionPanel;

import javax.swing.*;
import java.awt.*;

/**
 * @author vuhe
 */
public class QuestionPanel extends JPanel {
    private static final Logger logger = LoggerFactory.getLogger(QuestionPanel.class);
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
        logger.info("获取问题面板");
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
