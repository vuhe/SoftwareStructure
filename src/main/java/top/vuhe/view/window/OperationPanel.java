package top.vuhe.view.window;

import lombok.extern.slf4j.Slf4j;
import top.vuhe.controller.ControllerExecutor;
import top.vuhe.model.Context;
import top.vuhe.view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

/**
 * @author vuhe
 */
@Slf4j
public class OperationPanel extends JPanel {
    private static final JPanel INSTANCE = new OperationPanel();
    private final JButton buildQuestion= new JButton("创建习题集");
    private final JComboBox<String> selectedExercise = new JComboBox<>();
    private final JButton startExercise = new JButton("开始练习");

    private OperationPanel() {
        setBorder(BorderFactory.createEmptyBorder(50, 100, 150, 100));
        // 初始化 创建习题按钮
        buildQuestion.addActionListener(e -> {
            var ans = ControllerExecutor.buildQuestionToFile();
            try {
                ans.get();
            } catch (ExecutionException | InterruptedException ex) {
                log.error("问题生成错误", ex);
            }
            refreshExerciseList();
        });

        // 初始化 习题选择框
        refreshExerciseList();

        // 初始化 开始练习按钮
        startExercise.addActionListener(e -> {
            if (selectedExercise.getSelectedIndex() <= 0) {
                JOptionPane.showMessageDialog(
                        null,
                        "请先选择一套习题",
                        "警告",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                Context.setFile((String) selectedExercise.getSelectedItem());
                MainFrame.instance().loading();
            }
        });

        // 主要页面 初始化
        setLayout(new GridLayout(4, 1, 20, 20));

        add(new JLabel("提示：若无习题，请先生成"));
        add(buildQuestion);
        add(selectedExercise);
        add(startExercise);
    }

    public static JPanel instance() {
        return INSTANCE;
    }

    private void refreshExerciseList() {
        selectedExercise.removeAllItems();
        selectedExercise.addItem("请选择一套习题");
        var filesName = new LinkedList<String>();

        // 文件IO
        var path = new File(Context.FILE_PATH);
        if (!path.exists()) {
            path.mkdirs();
        }
        var files = path.listFiles();
        if (files != null) {
            for (var f : files) {
                filesName.add(f.getName());
            }
        }

        for (var name : filesName) {
            selectedExercise.addItem(name);
        }
    }
}
