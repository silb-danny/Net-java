package mat_interp;

public abstract class ActivationFunction {
    public abstract Matrix forward(Matrix z);
    public abstract Matrix backward(Matrix dz);
}
