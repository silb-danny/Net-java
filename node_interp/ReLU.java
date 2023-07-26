package node_interp;

public class ReLU extends Activation{
    private boolean max;
    @Override
    public ReLU construct() {
        return new ReLU();
    }

    @Override
    public double forward(double input) {
        this.max = input >= 0;
        if(max) { // if input bigger = than 0
            return input;
        }
        return 0;
    }

    @Override
    public double backward(double dz) {
        if(max) { // if input bigger = than 0
            return dz;
        }
        return 0;
    }
}
