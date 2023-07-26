package node_interp;

import java.util.Random;

public class HiddenLayer extends Layer {
    private double[] inputs;
    public Neuron[] neurons;
    private Activation actvFunction;
    // initializing using cost function and n neurons
    public HiddenLayer(int numberOfNeurons, Activation actvFunction) {
        this.actvFunction = actvFunction;
        this.neurons = new Neuron[numberOfNeurons];
    }

    // initializing using one output neuron and a cost function
    public HiddenLayer(Activation actvFunction) {
        this.actvFunction = actvFunction;
        this.neurons = new Neuron[1];
    }

    @Override
    public void init(int inputsLength, Random rnd, double learningRate) {
        this.inputs = new double[inputsLength];
        for (int i = 0; i < neurons.length; i++) {
            neurons[i] = new Neuron(inputsLength, actvFunction.construct(), rnd, learningRate);
        }
    }

    @Override
    public double[] forward(double[] inputs) {
        double[] out = new double[neurons.length];
        for (int i = 0; i < neurons.length; i++) { // setting output
            out[i] = neurons[i].forward(inputs);
        }
        return out;
    }

    @Override
    public double[] backward(double[] inputs) {
        double[] out = new double[this.inputs.length];
        for (int i = 0; i < neurons.length; i++) { // setting output
            double[] temp = neurons[i].backward(inputs[i]);
            for (int j = 0; j < out.length; j++) { // adding up all outputs from each neuron
                out[j] += temp[j];
            }
        }
        return out;
    }

    @Override
    public int getLengthOfOutputs() {
        return neurons.length;
    }
}
