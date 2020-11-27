package top.vuhe.controller.observer;

import top.vuhe.controller.ControllerExecutor;
import top.vuhe.controller.observer.intf.AbstractSubject;
import top.vuhe.controller.observer.intf.Observer;
import top.vuhe.view.bottom.FunctionPanel;
import top.vuhe.view.center.QuestionPanel;

import java.util.ArrayList;
import java.util.List;

/**
 * 习题比例变化订阅
 * <p>
 * 用于菜单改变习题变化时通知其它需要更改的组件
 *
 * @author vuhe
 */
public class CreateQuestionSubject extends AbstractSubject {
    public static final String NAME = "生成习题";
    private final List<Observer> list = new ArrayList<>();
    private static final AbstractSubject INSTANCE = new CreateQuestionSubject();

    private CreateQuestionSubject() {
        // 通知线程创建习题
        addObserver(ControllerExecutor.instance());
    }

    public static AbstractSubject instance() {
        return INSTANCE;
    }

    @Override
    public void addObserver(Observer o) {
        list.add(o);
    }

    @Override
    public void deleteObserver(Observer o) {
        list.remove(o);
    }

    @Override
    public void notifyObservers(String message) {
        for (var o : list) {
            o.update(message, NAME);
        }
    }
}
