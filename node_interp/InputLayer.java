package node_interp;

import java.util.Random;

public class InputLayer extends Layer{
    private double[] inputs;
    private boolean inputsInserted = false;
    private int correctIndex;
    public InputLayer(int numberOfInputs) { // initializing using only the size of the inputs
        inputs = new double[numberOfInputs];
    }

    public InputLayer(double[] inputs, int correctIndex) { // initializing using existing inputs
        this.inputs = inputs;
        this.inputsInserted = true;
        this.correctIndex = correctIndex;
    }

    public void setData(double[] inputs, int correctIndex) {
        this.inputs = inputs; // reference ***
        this.correctIndex = correctIndex;
    }

    public double[] getInputs() {
        return inputs;
    }

    public int getCorrectIndex() {
        return correctIndex;
    }

    @Override
    public void init(int inputsLength, Random rnd, double learningRate) {}
    @Override
    public double[] forward(double[] inputs) {
        if(inputsInserted)
            return this.inputs;
        // no inputs inserted
        this.inputs = inputs;
        return inputs;
    }
    @Override
    public double[] backward(double[] inputs) {
        return new double[0];
    }

    @Override
    public int getLengthOfOutputs() {
        return inputs.length;
    }
}
