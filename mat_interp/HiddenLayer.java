package mat_interp;

import java.util.Random;

public class HiddenLayer extends Layer{
    private Matrix inputs;
    private Matrix w; // weights
    private Matrix b; // biases

    private LinearSum linearSum;
    private ActivationFunction actvFunction;

    // initialization
    public HiddenLayer(int numberOfNeurons, ActivationFunction actvFunction) {
        this.actvFunction = actvFunction;
        this.rows = numberOfNeurons;
        linearSum = new LinearSum();
    }
    @Override
    public void init(int inputsLength, Random rnd, double scale) {
        this.w = new Matrix(this.rows, inputsLength);
        this.b = new Matrix(this.rows,1);
        this.w.randomM(rnd,scale);
        this.b.randomM(rnd,scale);
//        this.w.setAll(0.2);
//        this.b.setAll(0.2);
    }
    @Override
    public Matrix forward(Matrix inputs) {
        Matrix z = linearSum.forward(inputs, w, b);
        return actvFunction.forward(z);
    }

    @Override
    public Matrix backward(Matrix inputs, double learningRate) {
        Matrix dz = actvFunction.backward(inputs);
        return linearSum.backward(dz, learningRate);
    }

    public Matrix getW() {
        return w;
    }

    public Matrix getB() {
        return b;
    }
}
