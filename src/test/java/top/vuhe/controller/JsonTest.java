package top.vuhe.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import top.vuhe.controller.factory.Factory;
import top.vuhe.controller.factory.QuestionEnum;
import top.vuhe.model.entity.Question;

@Slf4j
public class JsonTest {
    @Test
    @DisplayName("Json 转换测试")
    void checkRepeatedFormula() {
        log.info("测试转换");
        var question = Factory.getQuestionFactory(QuestionEnum.HalfHalf).produce();

        var json = JsonUnit.toJson(question);
        System.out.print(json);

        System.out.println();

        var testQuestion = JsonUnit.fromJson(json, Question.class);
        for (var f : testQuestion) {
            System.out.println(f);
        }
    }
}
