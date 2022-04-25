import java.io.*;
import java.util.*;

public class Knn {
    public static void main(String[] args) throws IOException {

        List<Vect> TrainingList;
        List<Vect> TestList;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input the value of k:");
        int k = Integer.parseInt(bufferedReader.readLine());
        TrainingList = getVectList("Z:\\Knn\\iris_training.txt");
        TestList = getVectList("Z:\\Knn\\iris_test.txt");
        KNNAlgorithm(TestList,TrainingList,k,true);

        System.out.println("Write your own sample");
        boolean sample = true;
            while (sample) {
                System.out.println("Type the values of your sample (separate with \";\" in between the values) ");

                String line = bufferedReader.readLine();
                line += ";[]";
                List<Vect> vectSet = new ArrayList<>();
                String[] tmp = line.split(";");
                List<Double> attributesColumn = new ArrayList<>();

                for (int i = 0; i < tmp.length - 1; i++)
                    attributesColumn.add(Double.parseDouble(tmp[i]));

                vectSet.add(new Vect(attributesColumn, tmp[tmp.length - 1]));
                TestList = vectSet;
                KNNAlgorithm(TestList,TrainingList,k,false);
                System.out.println("Do you want to write another sample? (Y/N)");
                String lines = bufferedReader.readLine();
                if(lines.equals("N")){
                    sample=false;
                }else {
                    sample=true;
                }
            }

    }

    public static List<Vect> getVectList(String fileAddress) throws IOException {
        String line;
        List<Vect> vectSet = new ArrayList<>();

        FileReader fileReader = new FileReader(fileAddress);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        while ((line = bufferedReader.readLine())!=null && (!line.equals(""))){
            String [] tmp = line.split(" ");

            List<Double> attributesColumn = new ArrayList<>();
            for (int i = 0; i < tmp.length-1 ; i++)
                attributesColumn.add(Double.parseDouble(tmp[i]));

            vectSet.add(new Vect(attributesColumn,tmp[tmp.length-1]));
        }
        return vectSet;
    }

    public static double getDist(Vect vect1, Vect vect2){
        double dist=0;
        for (int i = 0; i < vect1.getAttributes().size() ; i++) {
            dist += Math.pow(vect1.getAttributes().get(i)-vect2.getAttributes().get(i),2);
        }
        return dist;
    }

    public static String KNNAlgorithm(List<Vect> TestList, List<Vect> TrainingList, int k, boolean stats){

        String iris_class="";
        double correctAnswer=0;
        System.out.println("k = " + k);
        for (Vect testVect : TestList) {
              int ind = TestList.indexOf(testVect)+1;
            List<Dist> distance = new ArrayList<>();

            for (Vect trainingVect : TrainingList)
                distance.add(new Dist(testVect, trainingVect, getDist(testVect, trainingVect)));

            Collections.sort(distance);

            double correctVectors = 0;
            List<String> stringList = new ArrayList<>();
            Set<String> stringSet = new HashSet<>();

            for (int j = 0; j < k; j++) {
                stringList.add(distance.get(j).getVectTrain().getVectClassName());
                stringSet.add(distance.get(j).getVectTrain().getVectClassName());

                if (distance.get(j).getVectTrain().getVectClassName().equals(testVect.getVectClassName()))
                    correctVectors++;
            }

            int max =0;

            for (String string :stringSet) {
                if (Collections.frequency(stringList,string)>max) {
                    max = Collections.frequency(stringList, string);
                    iris_class = string;
                }
            }
            System.out.println("Sample No: "+ind);
            if (iris_class.equals(testVect.getVectClassName()))
                correctAnswer++;
            System.out.println("Algorithm perfomed classification: " + iris_class);

            if (stats) {
                System.out.println("Compatability with the training set: " + ((correctVectors / k) * 100) + "%");
                System.out.println("The correct classification: " + testVect.getVectClassName());

            }
            System.out.println("❀❀❀❀❀❀❀❀❀❀❀❀❀❀❀❀❀❀❀❀❀❀❀❀❀❀❀❀");
        }
        if (stats)
            System.out.println("The accuracy of the algorithm:  "+ (correctAnswer/TestList.size())*100+"%\n❀❀❀❀❀❀❀❀❀❀❀❀❀❀❀❀❀❀❀❀❀❀❀❀❀❀❀❀");

        return iris_class;
    }
}
