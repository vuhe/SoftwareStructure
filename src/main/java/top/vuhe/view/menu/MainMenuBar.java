package top.vuhe.view.menu;

import lombok.extern.slf4j.Slf4j;
import top.vuhe.view.menu.primary.QuestionTypeMenu;

import javax.swing.*;

/**
 * @author vuhe
 */
@Slf4j
public class MainMenuBar extends JMenuBar {
    private static final JMenuBar INSTANCE = new MainMenuBar();

    private MainMenuBar() {
        add(QuestionTypeMenu.instance());
    }

    public static JMenuBar instance() {
        log.info("获取菜单栏");
        return INSTANCE;
    }
}
