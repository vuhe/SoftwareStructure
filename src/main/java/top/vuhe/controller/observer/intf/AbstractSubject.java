package top.vuhe.controller.observer.intf;

/**
 * 抽象主题订阅角色
 *
 * @author vuhe
 */
public abstract class AbstractSubject {
    /**
     * 添加观察者
     * @param o 观察者
     */
    abstract public void addObserver(Observer o);

    /**
     * 删除观察者
     * @param o 观察者
     */
    abstract public void deleteObserver(Observer o);

    /**
     * 通知所有的观察者
     * @param message 通知信息
     */
    abstract public void notifyObservers(String message);
}
