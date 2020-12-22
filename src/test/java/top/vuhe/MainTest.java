package top.vuhe;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import top.vuhe.controller.factory.Factory;
import top.vuhe.controller.factory.QuestionEnum;
import top.vuhe.model.entity.Question;

/**
 * 面向过程测试
 *
 * @author vuhe
 */
@Slf4j
public class MainTest {
    /**
     * 版本 v0.x 测试
     * <p>
     * 测试算式的产生和运算
     */
    @Test
    @DisplayName("面向过程测试")
    public void test() {
        log.info("v0.x 面向过程测试");
        Factory<Question> questionFactory = Factory.getQuestionFactory(QuestionEnum.HalfHalf);
        Question question = questionFactory.produce();
        int i = 0;
        for (var formula : question) {
            if (i != 0 && i % 5 == 0) {
                System.out.println();
            }
            System.out.print(formula);
            System.out.printf("%3d   ", formula.getAns());
            i++;
        }
        System.out.println("\n");
    }
}
