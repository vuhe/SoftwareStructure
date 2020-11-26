package top.vuhe.model;

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
public class Problem implements Iterable<Formula> {
    private final List<Formula> formulas;
    private final int size;

    private Problem(List<Formula> formulas, int size) {
        this.formulas = formulas;
        this.size = size;
    }

    public static Problem from(){
        return new Problem(new ArrayList<>(), 0);
    }

    public static Problem from(Formula formula) {
        if (formula != null) {
            List<Formula> list = new ArrayList<>();
            list.add(formula);
            return new Problem(list, 1);
        } else {
            return from();
        }
    }

    public static Problem from(List<Formula> formulas) {
        if (formulas != null) {
            return new Problem(formulas, formulas.size());
        } else {
            return from();
        }
    }

    @NotNull
    @Override
    public Iterator<Formula> iterator() {
        return formulas.iterator();
    }

    public int getSize() {
        return size;
    }
}
