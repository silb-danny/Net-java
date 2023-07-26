package node_interp;

public class HingeLoss extends Cost {
    private boolean[] max;
    @Override
    public HingeLoss construct() {
        return new HingeLoss();
    }

    @Override
    public double forward(double input[], int CorrectIndex) {
        max = new boolean[input.length];
        double out = 0;
        for (int i = 0; i < max.length; i++) {
            double in = input[i] - input[CorrectIndex] + 1;
            max[i] = in >= 0;
            if (max[i]) { // if input bigger = than 0
                out += in;
            }
        }
        return out;
    }

    @Override
    public double[] backward() {
        double[] out = new double[max.length];
        for (int i = 0; i < max.length; i++) {
            if (max[i]) { // if input bigger = than 0
                out[i] = 1;
            } else {
                out[i] = 0; // technically default value
            }
        }
        return out;
    }
}
