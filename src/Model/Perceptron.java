package Model;

import java.util.ArrayList;
import java.util.List;

public class Perceptron {
   private List<Double> vectorW;
   private double thetaThreshold;
   private double alpha;

    public Perceptron(int vectorSize, double alpha) {
        this.alpha = alpha;
        this.vectorW = new ArrayList<>();
        for (int i = 0; i < vectorSize ; i++) // from -5 to 5 as starting values
            this.vectorW.add((Math.random()*10)-5);

        this.thetaThreshold = Math.random()*10-5;
    }

    public List<Double> getVectorW() {
        return vectorW;
    }

    public void setVectorW(List<Double> vectorW) {
        this.vectorW = vectorW;
    }

    public double getThetaThreshold() {
        return thetaThreshold;
    }

    public void setThetaThreshold(double thetaThreshold) {
        this.thetaThreshold = thetaThreshold;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public void learn(Node node, int correctAnswer){

        double net = 0;
        double scalarProduct = 0;
        for (int i = 0; i < node.getAttributesColumn().size() ; i++) // Calculate X * W
            scalarProduct += node.getAttributesColumn().get(i) * this.vectorW.get(i);

        net = scalarProduct - thetaThreshold;
        int y = (scalarProduct>=this.thetaThreshold?1:0);

        if (y != correctAnswer) { //Do learn
            //System.out.println("LEARN!");
            List<Double> vectorWPrime = new ArrayList<>(this.vectorW);
            for (int i = 0; i < node.getAttributesColumn().size(); i++) // W' = W + (Correct-Y) * Alpha * X
                vectorWPrime.set(i, (this.vectorW.get(i) + ((correctAnswer - y) * alpha * node.getAttributesColumn().get(i))));

            this.vectorW = vectorWPrime;
            this.thetaThreshold = thetaThreshold + (correctAnswer - y) * alpha * -1;
        }
    }

    public double evaluate(Node node){
        double net = 0;
        double scalarProduct = 0;
        for (int i = 0; i < node.getAttributesColumn().size() ; i++) // Calculate X * W
            scalarProduct += node.getAttributesColumn().get(i) * this.vectorW.get(i);

        net = scalarProduct - thetaThreshold;
        //return (scalarProduct>=this.thetaThreshold?1:0);
        return net;
    }

    public void normalizePerceptron(){
        double sumOfSquares=0;

        for (double d : this.vectorW)
            sumOfSquares=+Math.pow(d,2);

        double length = sumOfSquares;
        length = Math.sqrt(length);

        List<Double>  newVectorW = new ArrayList<>();

        for (int i = 0; i < this.vectorW.size() ; i++)
            newVectorW.set(i,(this.vectorW.get(i)/length));

        this.thetaThreshold = thetaThreshold/length;
        this.vectorW = newVectorW;
    }

    @Override
    public String toString() {
        return "Perceptron{" +
                "vectorW=" + vectorW +
                ", thetaThreshold=" + thetaThreshold +
                '}';
    }
}


