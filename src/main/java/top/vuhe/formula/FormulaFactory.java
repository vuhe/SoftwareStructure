package top.vuhe.formula;

import top.vuhe.entity.Formula;
import top.vuhe.entity.OperatorEnum;

import java.util.Random;

public class FormulaFactory {
    // 随机数生产器
    private static final Random random = new Random(47);

    public static Formula getFormula() {
        int a = random.nextInt(101);
        OperatorEnum op = randomGetOperator();
        int b = random.nextInt(101);
        return new Formula(a, op, b);
    }


    private static OperatorEnum randomGetOperator() {
        if (random.nextInt(2) == 1) {
            return OperatorEnum.plus;
        } else {
            return OperatorEnum.minus;
        }
    }
}
