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

    private QuestionPanel() {
        setLayout(new BorderLayout(5,5));
        add(FormulasPanel.instance(), BorderLayout.CENTER);
        add(FunctionPanel.instance(), BorderLayout.SOUTH);
    }

    public static JPanel instance() {
        return INSTANCE;
    }
}
