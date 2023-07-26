package mat_interp;

import java.util.Arrays;
import java.util.Random;

public class Matrix {

    public int rows;
    public int columns;
    public double[][] mat;
    // constructors
    public Matrix(Matrix m){
        this.rows = this.rows;
        this.columns = this.columns;
        this.mat = new double[m.rows][m.columns];
        this.setAll(m);
    }
    public Matrix(double[][] m){
        this.rows = m.length;
        this.columns = m[0].length;
        this.mat = new double[this.rows][this.columns];
        this.zero();
        setAll(m);
    }
    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.mat = new double[rows][columns];
    }
    public Matrix(int rows, int columns, double value) {
        this.rows = rows;
        this.columns = columns;
        this.mat = new double[rows][columns];
        setAll(value);
    }
    public Matrix(int size) {
        this.rows = size;
        this.columns = size;
        this.mat = new double[size][size];
    }
    public Matrix(int size, double value) {
        this.rows = size;
        this.columns = size;
        this.mat = new double[size][size];
        setAll(value);
    }
    // matrix manipulation
    // resize matrix
    public void resize(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.mat = new double[rows][columns];
    }
    public void resize(Matrix matr) {
        this.rows = matr.rows;
        this.columns = matr.columns;
        this.mat = new double[rows][columns];
        this.setAll(matr);
    }
    public void transpose() { // flips matrix columns and rows : 2*3 matrix => 3*2 matrix
        Matrix temp = new Matrix(columns, rows);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                temp.mat[j][i] = mat[i][j];
            }
        }
        resize(temp);
    }
    // values setting manipulation
    public int[] maxInd() {
        int[] maxInd = {0,0};
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                if (mat[maxInd[0]][maxInd[1]] < mat[i][j]) {
                    maxInd[0] = i;
                    maxInd[1] = j;
                }
            }
        }
        return maxInd;
    }
    public int[] minInd() {
        int[] minInd = {0,0};
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                if(mat[minInd[0]][minInd[1]] > mat[i][j]) {
                    minInd[0] = i;
                    minInd[1] = j;
                }
            }
        }
        return minInd;
    }
    public void randomM(Random rnd, double scale) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                mat[i][j] = rnd.nextDouble()*scale;
            }
        }
    }

    public double get(int i, int j) {
        return mat[i][j];
    }
    public double get(int[] ind) {
        return mat[ind[0]][ind[1]];
    }
    public void set(int i, int j, double value) {
        mat[i][j] = value;
    }
    public void set(int[] ind, double value) {
        mat[ind[0]][ind[1]] = value;
    }
    public void setAll(double value) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                mat[i][j] = value;
            }
        }
    }
    public void setAll(Matrix values) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                mat[i][j] = values.mat[i][j];
            }
        }
    }
    public void setAll(double[][] values) {
        for (int i = 0; i < this.rows; i++) { // cloning values
            for (int j = 0; j < this.columns; j++) {
                this.mat[i][j] = values[i][j];
            }
        }
    }
    // global values
    public void zero() {
        setAll(0);
    }
    public void one() {
        setAll(1);
    }
    public double identity() {
        double identity  = 0;
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                identity += mat[i][j];
            }
        }
        return identity;
    }
    public void norm() {
        double ident = identity();
        div(ident);
    }
    public Matrix rNorm() {
        Matrix tempM = new Matrix(mat);
        tempM.norm();
        return tempM;
    }
    public void normMax() {
        int[] maxI = maxInd();
        double max = get(maxI);
        div(max);
    }
    public Matrix rNormMax() {
        Matrix tempM = new Matrix(mat);
        tempM.normMax();
        return tempM;
    }
    // opperators
    // addition
    public void add(double scalar) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                mat[i][j] += scalar;
            }
        }
    }
    public void add(Matrix matr) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                mat[i][j] += matr.mat[i][j];
            }
        }
    }
    public Matrix rAdd(double scalar) {
        Matrix tempM = new Matrix(mat);
        tempM.add(scalar);
        return tempM;
    }
    public Matrix rAdd(Matrix matr) {
        Matrix tempM = new Matrix(mat);
        tempM.add(matr);
        return tempM;
    }
    // subtraction
    public void sub(double scalar) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                mat[i][j] -= scalar;
            }
        }
    }
    public void sub(Matrix matr) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                mat[i][j] -= matr.mat[i][j];
            }
        }
    }
    public Matrix rSub(double scalar) {
        Matrix tempM = new Matrix(mat);
        tempM.sub(scalar);
        return tempM;
    }
    public Matrix rSub(Matrix matr) {
        Matrix tempM = new Matrix(mat);
        tempM.sub(matr);
        return tempM;
    }
    // multiplication
    public void mul(double scalar) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                mat[i][j] *= scalar;
            }
        }
    }
    public void mul(Matrix matr) {
        Matrix temp = rMul(matr);
        setAll(temp);
    }
    public Matrix rMul(double scalar) {
        Matrix tempM = new Matrix(mat);
        tempM.mul(scalar);
        return tempM;
    }
    public Matrix rMul(Matrix matr) {
        Matrix tempM = new Matrix(rows, matr.columns);
        tempM.zero();
        if(this.columns != matr.rows)
            return tempM;
        for (int i = 0; i < tempM.rows; i++) {
            for (int j = 0; j < tempM.columns; j++) {
                double sum = 0;
                for (int k = 0; k < this.columns; k++) {
                    sum += this.mat[i][k]*matr.mat[k][j];
                }
                tempM.mat[i][j] = sum;
            }
        }
        return tempM;
    }
    // division
    public void div(double scalar) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                mat[i][j] /= scalar;
            }
        }
    }
    public Matrix rDiv(double scalar) {
        Matrix tempM = new Matrix(mat);
        tempM.div(scalar);
        return tempM;
    }

    // override opperators
    public boolean equals(Matrix o) {
        boolean equal = true;
        for (int i = 0; i < this.rows; i++) {
            equal = equal && Arrays.equals(mat[i],o.mat[i]);
        }
        return equal;
    }
    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                str += " "+mat[i][j];
            }
            str += "\n";
        }
        return str;
    }
}
