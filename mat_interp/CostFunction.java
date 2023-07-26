package mat_interp;

public abstract class CostFunction {
    public abstract int forward(Matrix z, Matrix expected);
    public abstract Matrix backward();
    public abstract Matrix getExpected();
}
