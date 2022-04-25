public class Dist implements Comparable<Dist> {

    @Override
    public int compareTo(Dist anotherNode) {
        return Double.compare(this.distance, anotherNode.distance);
    }

    private final Vect vectTest;
    private final Vect vectTrain;
    private final double distance;

    public Dist(Vect nodeTest, Vect nodeTrain, double distance) {
        this.vectTest = nodeTest;
        this.vectTrain = nodeTrain;
        this.distance = distance;
    }

    public Vect getVectTrain() {
        return vectTrain;
    }
}

