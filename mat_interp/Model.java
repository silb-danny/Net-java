package mat_interp;

import java.util.Random;

public class Model {
    private Matrix inputL;
    private Layer[] hiddenL;
    private OutputLayer outputL;
    private double learningRate;
    private Random rnd;
    private Matrix correctInd;

    public Model(Matrix inputL, Matrix expected, Random rnd, Layer[] hiddenL, OutputLayer outputL, double learningRate, double scale) {
        this.inputL = inputL;
        this.hiddenL = hiddenL;
        this.outputL = outputL;
        this.learningRate = learningRate;
        this.rnd = rnd;
        this.correctInd = expected;
        this.init(scale);
    }

    // initializer setting length of inputs for each layer
    protected void init(double scale) {
        int numberOfInputs = inputL.rows;
        for (int i = 0; i < hiddenL.length; i ++) {
            hiddenL[i].init(numberOfInputs, rnd, scale);
            numberOfInputs = hiddenL[i].rows;
        }
    }
    public void setInputs(Matrix inputs, Matrix expected) {
        inputL.setAll(inputs);
        this.correctInd = expected;
    }
    // forward pass
    public double forwardPass() {
        Matrix inputs = this.inputL;
        for (int i = 0; i < hiddenL.length; i++) {
            inputs = hiddenL[i].forward(inputs);
        }
        double loss = outputL.forward(inputs, correctInd);
        return loss;
    }

    // backward pass (back-prop)
    public void backwardPass() {
        Matrix inputs = outputL.backward();
        for (int i = hiddenL.length-1; i >= 0 ; i--) {
//            System.out.println("outputs"+inputs);
            inputs = hiddenL[i].backward(inputs, learningRate);

        }
    }
    public int testC() { // test classification
        forwardPass();
        return outputL.indexMax;
    }
    public Matrix testR() { // test regression
        forwardPass();
        Matrix m = outputL.getInputs();
        return m;
    }
}

