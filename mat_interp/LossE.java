package mat_interp;

public class LossE extends CostFunction{
    private Matrix expected;
    @Override
    public int forward(Matrix z, Matrix expected) {
        this.expected = z.rSub(expected);
        return z.maxInd()[0];
    }

    @Override
    public Matrix backward() {
//        System.out.println(expected);
        return expected;
    }

    public Matrix getExpected() {
        return expected;
    }
}
