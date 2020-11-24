package top.vuhe.model;

/**
 * @author vuhe
 */
public class Problem {
    private final Formula formula;
    private final int ans;

    public Problem(Formula formula) {
        this.formula = formula;
        Operator op = formula.getOp();
        this.ans = op.calculate(formula.getA(), formula.getB());
    }

    public Formula getFormula() {
        return formula;
    }

    public int getAns() {
        return ans;
    }
}
