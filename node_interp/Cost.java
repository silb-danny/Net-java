package node_interp;

public abstract class Cost{
    public abstract double forward(double[] input, int CorrectIndex);
    public abstract double[] backward();
    public abstract Cost construct();
}
