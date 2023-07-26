package mat_interp;

public class LinearSum {
    private Matrix x;
    private Matrix w;
    private Matrix b;

    public Matrix forward(Matrix x, Matrix w, Matrix b) {
        this.x = x;
        this.w = w;
        this.b = b;
        Matrix z = w.rMul(x); // multiplying x and w
        z.add(b);
//        System.out.println("w"+w);
//        System.out.println("b"+b);
//        System.out.println("x"+x);
//        System.out.println("z"+z);
        return z;
    }
    public Matrix backward(Matrix dz, double learningRate) {
        this.b.sub(dz.rMul(learningRate)); // 2*1 - 2*1 * learn rate -> 2*1
        this.x.transpose(); // 3*1 := 1*3
        Matrix temp = dz.rMul(this.x);
        this.x.transpose();

        this.w.transpose(); // 2*3 := 3*2
        Matrix temp2 = this.w.rMul(dz); // 3*2 by 2*1 -> 3*1
        this.w.transpose(); // 3*2 := 2*3

        temp.mul(learningRate);
        this.w.sub(temp); // 2*3 - learn rate*(2*1 by 1*3 -> 2*3) -> 2*3
        return temp2;
    }
}
