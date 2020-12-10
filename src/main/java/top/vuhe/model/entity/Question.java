package top.vuhe.model.entity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 问题实体
 * <p>
 * 一组算式的集合及其它的附加信息
 *
 * @author vuhe
 */
public class Question implements Iterable<Question.Node> {
    private final List<Node> questions;

    private Question(List<Formula> formulas) {
        questions = new ArrayList<>(formulas.size() + 1);
        for (var f : formulas) {
            questions.add(new Node(f));
        }
    }

    public static Question from(){
        return new Question(new ArrayList<>());
    }

    public static Question from(Formula formula) {
        if (formula != null) {
            List<Formula> list = new ArrayList<>();
            list.add(formula);
            return new Question(list);
        } else {
            return from();
        }
    }

    public static Question from(List<Formula> formulas) {
        if (formulas != null) {
            return new Question(formulas);
        } else {
            return from();
        }
    }

    public enum State {
        // 未做
        NotDo,
        // 错误
        Wrong,
        // 正确
        Correct
    }

    public static class Node {
        private final String formula;
        private final int ans;
        private State state = State.NotDo;
        private Integer userAns = null;

        private Node(Formula f) {
            formula = f.toString();
            ans = f.getAns();
        }

        public String getFormula() {
            return formula;
        }

        public int getAns() {
            return ans;
        }

        public State getState() {
            return state;
        }

        public Integer getUserAns() {
            return userAns;
        }

        public void setState(State state) {
            this.state = state;
        }

        public void setUserAns(Integer userAns) {
            this.userAns = userAns;
        }
    }

    @NotNull
    @Override
    public Iterator<Node> iterator() {
        return questions.iterator();
    }
}
