package node_interp;

import java.util.Random;

public class Neuron {
    private double[] weights;
    private double bias;
    private LinearSum linearSum;
    private Activation actvFunction;
    private double learningRate;

    public Neuron(int amountOfWeights, Activation actvFunction, Random rnd, double learningRate) {
        this.actvFunction = actvFunction;
        this.linearSum = new LinearSum();
        this.weights = new double[amountOfWeights];
        this.learningRate = learningRate;
        this.init(rnd);
    }

    // initializing weights and bias randomly
    private void init(Random rnd) {
        for (int i = 0; i < weights.length; i++) {
            weights[i] = rnd.nextDouble();
        }
        bias = rnd.nextDouble();
    }
    public double forward(double[] inputs) {
        double z = linearSum.forward(inputs,this);
        return actvFunction.forward(z);
    }

    public double[] backward(double dirZ) {
        double dz = actvFunction.backward(dirZ);
        return linearSum.backward(dz);
    }
    public double getBias() {
        return bias;
    }
    public double[] getWeights() {
        return weights;
    }
    public void addBias(double bias) {
        this.bias += bias;
    }
    public void addWeights(double[] weights, double dz) {
        for (int i = 0; i < weights.length; i++) {
            this.weights[i] -= learningRate*weights[i]*dz;
        }
    }

}
