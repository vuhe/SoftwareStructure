package top.vuhe.controller.factory;

import lombok.AllArgsConstructor;

/**
 * @author vuhe
 */
@AllArgsConstructor
public enum QuestionEnum {
    /**
     * 混合模式
     */
    HalfHalf(25, 25),
    /**
     * 全加法模式
     */
    AllPlus(50, 0),
    /**
     * 全减法模式
     */
    AllMinus(0, 50);

    final int plus;
    final int minus;
}
