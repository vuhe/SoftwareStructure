package top.vuhe.view.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.vuhe.view.menu.primary.QuestionTypeMenu;

import javax.swing.*;

/**
 * @author vuhe
 */
public class MainMenuBar extends JMenuBar {
    private static final Logger logger = LoggerFactory.getLogger(MainMenuBar.class);
    private static final JMenuBar INSTANCE = new MainMenuBar();

    private MainMenuBar() {
        add(QuestionTypeMenu.instance());
    }

    public static JMenuBar instance() {
        logger.info("build menu bar");
        return INSTANCE;
    }
}
