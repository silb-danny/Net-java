package mat_interp;

public class OutputLayer {
    private CostFunction costFunction;
    private Matrix inputs;
    public int indexMax;

    // initializing using one output neuron and a cost function
    public OutputLayer(CostFunction costFunction) {
        this.costFunction = costFunction;
    }
    public double forward(Matrix inputs, Matrix expected) {
        this.inputs = inputs;
        indexMax = costFunction.forward(inputs, expected);
        double out = costFunction.getExpected().get(indexMax,0);
        return out;
    }
    public Matrix backward() {
        return costFunction.backward();
    }

    public Matrix getInputs() {
        return inputs;
    }
}
