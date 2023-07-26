package node_interp;

import java.util.Random;

public class OutputLayer extends Layer{
    private Cost costFunction;

    // initializing using one output neuron and a cost function
    public OutputLayer(Cost costFunction) {
        this.costFunction = costFunction;
    }

    @Override
    public void init(int inputsLength, Random rnd, double learningRate) {}
    @Override
    public double[] forward(double[] inputs) {return new double[0];}
    @Override
    public double[] backward(double[] inputs) {return new double[0];}
    @Override
    public int getLengthOfOutputs() {return 0;}

    public double[] backward() {
        return costFunction.backward();
    }
    public double forward(double[] inputs, int correctIndex) {
        double out = costFunction.forward(inputs, correctIndex);
        return out;
    }



}
