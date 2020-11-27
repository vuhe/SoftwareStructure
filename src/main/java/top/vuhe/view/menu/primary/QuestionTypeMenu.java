package top.vuhe.view.menu.primary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.vuhe.controller.observer.RebuildQuestionSubject;
import top.vuhe.controller.observer.intf.AbstractSubject;
import top.vuhe.model.Context;

import javax.swing.*;

/**
 * @author vuhe
 */
public class QuestionTypeMenu extends JMenu {
    private static final Logger logger = LoggerFactory.getLogger(QuestionTypeMenu.class);
    private static final JMenu INSTANCE = new QuestionTypeMenu();

    private QuestionTypeMenu() {
        setText("习题模式");

        // 按钮组
        ButtonGroup questionType = new ButtonGroup();

        // 默认选中 50% 50% 混合模式
        JRadioButtonMenuItem mix = MixTypeRadioButton.instance();
        mix.setSelected(true);
        questionType.add(mix);
        add(mix);

        // 全加法模式
        JRadioButtonMenuItem plus = AllPlusTypeRadioButton.instance();
        questionType.add(plus);
        add(plus);

        // 全减法模式
        JRadioButtonMenuItem minus = AllMinusTypeRadioButton.instance();
        questionType.add(minus);
        add(minus);

        logger.info("创建「习题模式」菜单");
    }

    public static JMenu instance() {
        return INSTANCE;
    }
}

class AllPlusTypeRadioButton {
    private static final Logger logger = LoggerFactory.getLogger(AllPlusTypeRadioButton.class);
    private static final JRadioButtonMenuItem INSTANCE = MixTypeRadioButton.instance(100, 0);

    private AllPlusTypeRadioButton() {
    }

    public static JRadioButtonMenuItem instance() {
        INSTANCE.setText("全加法题目");
        logger.info("创建「全加法题目」按钮");
        return INSTANCE;
    }
}

class AllMinusTypeRadioButton {
    private static final Logger logger = LoggerFactory.getLogger(AllMinusTypeRadioButton.class);
    private static final JRadioButtonMenuItem INSTANCE = MixTypeRadioButton.instance(0, 100);

    private AllMinusTypeRadioButton() {
    }

    public static JRadioButtonMenuItem instance() {
        INSTANCE.setText("全减法题目");
        logger.info("创建「全减法题目」按钮");
        return INSTANCE;
    }
}

class MixTypeRadioButton extends JRadioButtonMenuItem {
    private static final Logger logger = LoggerFactory.getLogger(MixTypeRadioButton.class);
    private static final JRadioButtonMenuItem HALF_HALF = new MixTypeRadioButton(50, 50);

    private MixTypeRadioButton(int plus, int minus) {
        setText("混合题目");
        addActionListener(e -> {
            // 更改全局比例
            Context.setProportionNumber(plus, minus);

            // 通知订阅者更新视图
            AbstractSubject subject = RebuildQuestionSubject.instance();
            subject.notifyObservers("更新问题");
        });
    }

    public static JRadioButtonMenuItem instance() {
        logger.info("创建「混合题目」按钮");
        return new MixTypeRadioButton(50, 50);
    }

    public static JRadioButtonMenuItem instance(int plus, int minus) {
        return new MixTypeRadioButton(plus, minus);
    }
}
