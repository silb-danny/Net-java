package mat_interp;

public class ReLu extends ActivationFunction{
    // rederector
    private boolean[] max;
    @Override
    public Matrix forward(Matrix z) {
        max = new boolean[z.rows];
        Matrix out = new Matrix(z.rows, z.columns);
        out.zero(); // zeroing out all values
        for (int i = 0; i < z.rows; i++) {
            double value = z.get(i,0);
            max[i] = value >= 0 ; // max 0
            if(max[i]) // if maximum
                out.set(i,0,value);
        }
        return out;
    }
    @Override
    public Matrix backward(Matrix dz) {
        Matrix out = new Matrix(max.length, 1);
        out.zero();
        for (int i = 0; i < out.rows; i++) {
            if(max[i])
                out.set(i,0,dz.get(i,0)); // if was max then set value else set zero
        }
        return out;
    }
}
