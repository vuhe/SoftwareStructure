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
public class Question implements Iterable<Formula> {
    private final List<Formula> formulas;

    private Question(List<Formula> formulas) {
        this.formulas = formulas;
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

    @NotNull
    @Override
    public Iterator<Formula> iterator() {
        return formulas.iterator();
    }
}
