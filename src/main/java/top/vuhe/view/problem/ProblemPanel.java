package top.vuhe.view.problem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.vuhe.controller.observer.RebuildQuestionSubject;
import top.vuhe.controller.observer.intf.Observer;
import top.vuhe.controller.problem.ProblemFactory;
import top.vuhe.model.Problem;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author vuhe
 */
public class ProblemPanel extends JPanel implements Observer {
    private static final Logger logger = LoggerFactory.getLogger(ProblemPanel.class);
    private static final ProblemPanel INSTANCE = new ProblemPanel();
    private List<ProblemLabel> labels = new LinkedList<>();

    /**
     * 采用单例模式，禁止外部调用
     * 注意：依据情况未来可能会换用enum实现
     */
    private ProblemPanel() {
        setLayout(new GridLayout(10, 5, 5, 5));
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
    public static ProblemPanel instance() {
        logger.info("get ONE ProblemPanel.");
        return INSTANCE;
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

    /**
     * 用于接受来自菜单栏的信息通知
     *
     * @param message     信息
     * @param subjectName 订阅名
     */
    @Override
    public void update(String message, String subjectName) {
        if (RebuildQuestionSubject.SUBJECT_NAME.equals(subjectName)) {
            Problem problem = ProblemFactory.of().create();
            // 算式 和 算式标签迭代器
            var itProblem = problem.iterator();
            var itLabel = labels.iterator();
            // 循环设置
            while (itLabel.hasNext() && itProblem.hasNext()) {
                itLabel.next().rebuild(itProblem.next());
            }
        }
    }
}
