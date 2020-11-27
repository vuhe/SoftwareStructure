package top.vuhe.controller.observer.intf;

/**
 * @author vuhe
 */
public interface Observer {
    /**
     * 订阅更新
     *
     * @param message     信息
     * @param subjectName 订阅名
     */
    void update(String message, String subjectName);
}
