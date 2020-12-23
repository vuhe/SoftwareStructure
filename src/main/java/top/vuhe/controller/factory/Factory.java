package top.vuhe.controller.factory;

import top.vuhe.model.entity.Question;

/**
 * @author vuhe
 */
public abstract class Factory<T> {

    public static Factory<Question> getQuestionFactory(QuestionEnum type) {
        return new QuestionFactory(type);
    }

    /**
     * 生产方法
     * @return 生产的对象
     */
    abstract public T produce();

}
