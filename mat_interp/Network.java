package mat_interp;

import java.util.Random;

public class Network {
    private Matrix[] inputs;
    private Matrix[] expected;
    private int[] randomSeq;
    private int ind = 0;

    private Layer[] hiddenL;
    private OutputLayer outputL;

    private double learningRate;

    private Random rnd;
    private double initialRandom;

    private Model model;


    public Network(Matrix[] inputs, Matrix[] expected, Layer[] hiddenL, OutputLayer outputL, double learningRate, double scaleR) {
        this.inputs = inputs;
        this.hiddenL = hiddenL;
        this.outputL = outputL;
        this.learningRate = learningRate;
        this.rnd = new Random();
        this.expected = expected;
        this.initialRandom = scaleR;
    }
    public Network(Layer[] hiddenL, OutputLayer outputL, double learningRate, double scaleR) {
        this.hiddenL = hiddenL;
        this.outputL = outputL;
        this.learningRate = learningRate;
        this.initialRandom = scaleR;
        this.rnd = new Random();
    }

    public void setInputs(Matrix[] inputs, Matrix[] expected) {
        this.inputs = inputs;
        this.expected = expected;
    }
    public void setInputs(Matrix[] inputs, int[] expected) { // expected are indices of final layer -> classification
        this.inputs = inputs;
        Matrix[] temp = new Matrix[expected.length];
        for (int i = 0; i < expected.length; i++) {
            temp[i] = new Matrix(inputs[i].rows,1);
            temp[i].zero();
            temp[i].set(expected[i],0,1);
        }
        this.expected = temp;
    }

    public void init() { // initializing model
        if(inputs != null) {
            randomSeq = inputRandom();
            model = new Model(inputs[randomSeq[ind]], expected[randomSeq[ind]], rnd, hiddenL, outputL, learningRate, initialRandom);
            ind ++;
        } else {
            System.err.println("error: inputs missing");
        }
    }

    private int[] inputRandom() { // randomly generating random input sequence
        int[] indx = new int[inputs.length];
        for (int i = 0; i < indx.length; i++) {
            indx[i] = -1;
        }
        for (int i = 0; i < indx.length; i++) {
            int randomIndx = rnd.nextInt(indx.length);
            while (inArray(indx,randomIndx)) {
                randomIndx = rnd.nextInt(indx.length);
            }
            indx[i] = randomIndx;
        }
        return indx;
    }
    private boolean inArray(int[] arry, int value) { // checks if index already in array
        for (int i = 0; i < arry.length; i++) {
            if(arry[i] == value)
                return true;
        }
        return false;
    }

    public Model train(int epoch, boolean print) {
        double loss = 0;
        model.forwardPass();
        model.backwardPass();
        for (int i = 0; i < epoch; i++) {
            for (int j = ind; j < inputs.length; j++) {
                model.setInputs(inputs[randomSeq[j]],expected[randomSeq[j]]);
                double out = model.forwardPass();
//                System.out.println(out);
                model.backwardPass();
//                if(i == epoch-1) // if last iteration
                loss += out;
            }
            ind = 0;
            randomSeq = inputRandom(); // randomizing matrix sequence of inputs
            if(print) {
                loss /= inputs.length; // mean error
                System.out.println("loss " + loss);
            }
            loss = 0;
        }
        return this.model;
    }

    public Model returnModel() {
        return this.model;
    }

    public Matrix getWeights (int layer) {
        return hiddenL[layer].getW();
    }
    public Matrix getBias (int layer) {
        return hiddenL[layer].getB();
    }

}


