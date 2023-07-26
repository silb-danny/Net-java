import mat_interp.Matrix;
import mat_interp.*;
// import node_interp.*;

public class Main {
    public static void main(String[] args) {
        /* node implimentation */
//        double[] inputs = {1,2,3,1,5,2,3,4,7,1,0,7};
//        Layer[] hiddenLayers = {new HiddenLayer(4, new ReLU()), new HiddenLayer(2, new ReLU()), new HiddenLayer(3, new ReLU())};
//        Network network = new Network(new InputLayer(inputs, 1), hiddenLayers, new OutputLayer(new HingeLoss()), 0.01);
//        for (int i = 0; i < 100; i++) {
//            double loss = network.forwardPass();
//            System.out.println(loss);
//            network.backwardPass();
//            System.out.println("done");
//        }
        /* matrix implimentation*/
        Matrix[] input = new Matrix[1000];
        Matrix[] expected = new Matrix[1000];
        int neurons = 1;
        for (int i = 0; i < input.length; i++) {
            input[i] = new Matrix(1,1,i/50.0);
            expected[i] = new Matrix(1,1,i/50.0);
        }
//        for (int i = 0; i < input.length; i++) {
//            System.out.println("input "+input[i]);
//            System.out.println("expected "+expected[i]);
//        }
        Layer[] hiddenLayers = {new HiddenLayer(1, new Pass())};
        OutputLayer out = new OutputLayer(new LossE());
        Network network = new Network(hiddenLayers,out,0.000002,0.9);
        network.setInputs(input,expected);
        network.init();
        Model m = network.train(10000,false);
//        System.out.println("w"+network.getWeights(0));
//        System.out.println("b"+network.getBias(0));
//        System.out.println("w"+network.getWeights(1));
//        System.out.println("b"+network.getBias(1));
        m.setInputs(new Matrix(1,1,-5),new Matrix(1,1,16));
        Matrix ou = m.testR();
        System.out.println(ou);
        m.setInputs(new Matrix(1,1,4),new Matrix(1,1,16));
        ou = m.testR();
        System.out.println(ou);
        m.setInputs(new Matrix(1,1,1),new Matrix(1,1,1));
        ou = m.testR();
        System.out.println(ou);
    }
}
