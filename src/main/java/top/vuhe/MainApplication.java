package top.vuhe;

import top.vuhe.entity.Problem;
import top.vuhe.problem.ProblemFactory;

import java.util.List;

public class MainApplication {
    public static void main(String[] args) {
        List<Problem> problems = ProblemFactory.getTestProblem();
        System.out.println("问题：");
        for (int i = 0; i < 50; i++) {
            if (i != 0 && i % 5 == 0) {
                System.out.println();
            }
            System.out.print(problems.get(i).getFormula() + "\t");
        }
        System.out.println("\n答案：");
        for (int i = 0; i < 50; i++) {
            if (i != 0 && i % 5 == 0) {
                System.out.println();
            }
            Problem problem = problems.get(i);
            System.out.print(problem.getFormula());
            System.out.print(problem.getAns() + "\t");

        }
    }

}
