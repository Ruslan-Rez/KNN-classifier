import java.util.List;

public class Vect {
    private final List<Double> attributes;
    private final String vectClassName;


    public Vect(List<Double> attributesColumn, String irisClassName) {
        this.attributes = attributesColumn;
        this.vectClassName = irisClassName;
    }

    public List<Double> getAttributes() {
        return attributes;
    }

    public String getVectClassName() {
        return vectClassName;
    }

}
