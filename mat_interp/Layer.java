package mat_interp;

import java.util.Random;
public abstract class Layer {
    public int rows;

    abstract public void init(int inputsLength, Random rnd, double scale);
    abstract public Matrix forward(Matrix inputs);
    abstract public Matrix backward(Matrix inputs, double learningRate);

    public abstract Matrix getW();

    public abstract Matrix getB();
}
