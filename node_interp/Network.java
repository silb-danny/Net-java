package node_interp;

import java.util.Random;

public class Network {
    private InputLayer inputL;
    private Layer[] hiddenL;
    private OutputLayer outputL;
    private double learningRate;
    private Random rnd;
    public Network(InputLayer inputL, Layer[] hiddenL, OutputLayer outputL, double learningRate) {
        this.inputL = inputL;
        this.hiddenL = hiddenL;
        this.outputL = outputL;
        this.learningRate = learningRate;
        rnd = new Random();
        this.init();
    }

    // initializer setting length of inputs for each layer
    protected void init() {
        int numberOfInputs = inputL.getLengthOfOutputs();
        for (int i = 0; i < hiddenL.length; i ++) {
            hiddenL[i].init(numberOfInputs, rnd, learningRate);
            numberOfInputs = hiddenL[i].getLengthOfOutputs();
        }
        outputL.init(numberOfInputs, rnd, learningRate);
    }
    public void setInputs(double[] inputs, int correctIndex) {
        inputL.setData(inputs, correctIndex);
    }
    // forward pass
    public double forwardPass() {
        double[] inputs = inputL.getInputs();
        for (int i = 0; i < hiddenL.length; i++) {
            inputs = hiddenL[i].forward(inputs);
        }
        double cost = outputL.forward(inputs, inputL.getCorrectIndex());
        return cost;
    }

    // backward pass (back-prop)
    public void backwardPass() {
        double[] inputs = outputL.backward();
        for (int i = hiddenL.length-1; i >= 0 ; i--) {
            inputs = hiddenL[i].backward(inputs);
        }
    }
}
