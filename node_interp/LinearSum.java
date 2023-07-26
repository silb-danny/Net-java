package node_interp;

public class LinearSum{
    private double[] x; // inputs
    private Neuron n1;
    public double forward(double[] inputs, Neuron n) {
        this.x = inputs;
        n1 = n;
        double out = 0; // output
        double[] w = n1.getWeights();
        for (int i = 0; i < x.length; i++) {
            out = w[i]*x[i];
        }
        out += n1.getBias();
        return out;
    }
    public double[] backward(double dz) {

        n1.addBias(dz);
        double[] w = n1.getWeights();
        double[] z = new double[w.length];
        for (int i = 0; i < x.length; i++) {
            z[i] = w[i]*dz;
        }
        n1.addWeights(x,dz);
        return z;
    }

}
