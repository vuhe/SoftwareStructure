package top.vuhe.view.problem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.vuhe.controller.problem.ProblemFactory;
import top.vuhe.model.Problem;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author vuhe
 */
public class ProblemPanel extends JPanel {
    private static final Logger logger = LoggerFactory.getLogger(ProblemPanel.class);
    private static ProblemPanel PANEL = new ProblemPanel();
    private List<ProblemLabel> labels = new LinkedList<>();

    /**
     * 采用单例模式，禁止外部调用
     * 注意：依据情况未来可能会换用enum实现
     */
    private ProblemPanel() {
        setLayout(new GridLayout(10,5, 5, 5));
        Problem problem = ProblemFactory.of().create();
        for (var i : problem) {
            ProblemLabel problemLabel = new ProblemLabel(i);
            add(problemLabel);
            labels.add(problemLabel);
        }
    }

    /**
     * 单例模式
     *
     * @return 此Panel的单例
     */
    public static ProblemPanel getProblemPanel() {
        logger.info("get ONE ProblemPanel.");
        return PANEL;
    }

    /**
     * 循环调用标签中的显示方法
     */
    public void showAns() {
        logger.info("show formulas ans.");
        for (var i : labels) {
            i.showAns();
        }
    }
}
