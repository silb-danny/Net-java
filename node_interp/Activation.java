package node_interp;

public abstract class Activation{
    public abstract Activation construct();
    public abstract double forward(double input);
    public abstract double backward(double dz);
}
