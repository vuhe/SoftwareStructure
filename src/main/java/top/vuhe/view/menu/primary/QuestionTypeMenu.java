package top.vuhe.view.menu.primary;

import lombok.extern.slf4j.Slf4j;
import top.vuhe.model.Context;
import top.vuhe.view.MainFrame;

import javax.swing.*;

/**
 * 习题模式菜单
 *
 * @author vuhe
 */
@Slf4j
public class QuestionTypeMenu extends JMenu {
    private static final JMenu INSTANCE = new QuestionTypeMenu();

    private QuestionTypeMenu() {
        setText("习题模式");

        // 按钮组
        ButtonGroup questionType = new ButtonGroup();

        // 默认选中 50% 50% 混合模式
        JRadioButtonMenuItem mix = TypeRadioButton.getMixType(50, 50);
        mix.setSelected(true);
        questionType.add(mix);
        add(mix);

        // 全加法模式
        JRadioButtonMenuItem plus = TypeRadioButton.getAllPlusType();
        questionType.add(plus);
        add(plus);

        // 全减法模式
        JRadioButtonMenuItem minus = TypeRadioButton.getAllMinusType();
        questionType.add(minus);
        add(minus);

        log.info("创建「习题模式」菜单");
    }

    public static JMenu instance() {
        return INSTANCE;
    }
}

@Slf4j
class TypeRadioButton extends JRadioButtonMenuItem {
    private static final JRadioButtonMenuItem PLUS = new TypeRadioButton("全加法题目", 100, 0);
    private static final JRadioButtonMenuItem MINUS = new TypeRadioButton("全减法题目", 0, 100);

    private TypeRadioButton(String name, int plus, int minus) {
        setText(name);
        addActionListener(e -> {
            // 更改全局比例
            Context.setProportionNumber(plus, minus);

            // 通知主视图更新信息
            MainFrame.instance().refresh();
        });
    }

    static JRadioButtonMenuItem getMixType(int plus, int minus) {
        log.info("创建「混合题目」按钮");
        return new TypeRadioButton("混合题目", plus, minus);
    }

    static JRadioButtonMenuItem getAllPlusType() {
        log.info("创建「全加法题目」按钮");

        return PLUS;
    }

    static JRadioButtonMenuItem getAllMinusType() {
        log.info("创建「全减法题目」按钮");
        return MINUS;
    }
}
