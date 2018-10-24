package fr.enssat.caronnantel;

import com.github.habernal.confusionmatrix.ConfusionMatrix;
import fr.enssat.caronnantel.algorithm.DistanceType;
import fr.enssat.caronnantel.algorithm.KNNAlgorithm;
import fr.enssat.caronnantel.algorithm.PredictionResult;
import fr.enssat.caronnantel.model.Flower;
import fr.enssat.caronnantel.model.IrisClass;
import fr.enssat.caronnantel.utilities.DataImporter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Launcher {

    /**
     * The best choice of K depends upon the data.
     * Generally, larger values of K reduce the effect of noise on the classification
     */
    private static final int K = 2; // Pour k très grand, on s'attends à avoir 33% de précision

    private static final DistanceType distanceType = DistanceType.MANHATTAN;

    public static void main(String[] args) throws IOException {
        KNNAlgorithm algorithm = new KNNAlgorithm(K);

        DataImporter importer = new DataImporter();
        Map<Flower, IrisClass> trainingSet = importer.getTrainingSet();
        Map<Flower, IrisClass> testSet = importer.getTestSet();

        algorithm.setTrainingSet(trainingSet);

        System.out.println("Executing the " + K + "-Nearest Neighbors algorithm...");
        int goodPredictions = 0;
        List<PredictionResult> predictionsResults = new ArrayList<>();
        for (Map.Entry<Flower, IrisClass> entry : testSet.entrySet()) {
            IrisClass prediction = algorithm.predict(entry.getKey(), distanceType);

            PredictionResult result = new PredictionResult(entry.getValue(), prediction);
            predictionsResults.add(result);

            if (prediction == entry.getValue()) {
                goodPredictions++;
            }
            //System.out.println("prediction = " + prediction + " | expected = " + entry.getValue());
        }

        System.out.println("-----");
        System.out.println("For K=" + K + " and DistanceType " + distanceType + " results are:");
        System.out.println("     - Total predictions = " + testSet.size() + " | good = " + goodPredictions + " | bad = " + (testSet.size() - goodPredictions));
        double accuracy = goodPredictions * 1.0 / testSet.size();
        System.out.println("     - Accuracy = " + accuracy * 100 + "%");

        createAndDisplayConfusionMatrix(predictionsResults);
    }

    private static void createAndDisplayConfusionMatrix(List<PredictionResult> results){
        ConfusionMatrix cm = new ConfusionMatrix();
        for (PredictionResult result : results) {
            cm.increaseValue(result.getExpected().name(), result.getPredicted().name(), 1);
        }
        System.out.println("\n" + cm);
    }
}
