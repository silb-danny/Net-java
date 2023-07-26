package node_interp;

import java.util.Random;

public abstract class Layer {
    abstract public void init(int inputsLength, Random rnd, double learningRate);
    abstract public double[] forward(double[] inputs);
    abstract public double[] backward(double[] inputs);
    abstract public int getLengthOfOutputs();
}
