package mat_interp;

public class Pass extends ActivationFunction{
    @Override
    public Matrix forward(Matrix z) {
        return z;
    }

    @Override
    public Matrix backward(Matrix dz) {
        return dz;
    }
}
