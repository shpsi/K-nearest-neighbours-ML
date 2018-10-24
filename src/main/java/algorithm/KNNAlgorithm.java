package fr.enssat.caronnantel.algorithm;

import fr.enssat.caronnantel.model.Flower;
import fr.enssat.caronnantel.model.InvalidDistanceType;
import fr.enssat.caronnantel.model.IrisClass;
import fr.enssat.caronnantel.utilities.ListUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class KNNAlgorithm {

    private int k; // k-nearest neighbor to consider
    private Map<Flower, IrisClass> trainingSet;

    public KNNAlgorithm(int k) {
        this.k = k;
    }

    public void setTrainingSet(Map<Flower, IrisClass> trainingSet) {
        this.trainingSet = trainingSet;
    }

    public IrisClass predict(Flower testInstance, DistanceType distanceType) {
        List<Result> result = new ArrayList<>();
        for (Map.Entry<Flower, IrisClass> entry : trainingSet.entrySet()) {
            result.add(new Result(calculDistance(distanceType, entry.getKey(), testInstance), entry.getValue()));
        }
        Collections.sort(result, new DistanceComparator());

        List<IrisClass> neighborsToConsider = new ArrayList<>();
        for (int i = 0; i <= k; i++) {
            if (i >= result.size()) { // Avoid too big value of K
                break;
            }
            neighborsToConsider.add(result.get(i).getIrisClass());
        }

        return ListUtils.mostCommonItem(neighborsToConsider);
    }

    // ====================
    // DISTANCE CALCULATION
    // ====================

    private double calculDistance(DistanceType type, Flower from, Flower to) {
        switch (type) {
            case EUCLIDIENNE:
                return computeEuclidianDistance(from,to);
            case MANHATTAN:
                return  computeManhattanDistance(from,to);
            case MINKOWSKI:
                return computeMinkowskiDistance(from,to, 4); // 4 because we have 4 features for each flower
        }
        throw new InvalidDistanceType();
    }

    /**
     * Calcul de la distance Euclidienne entre les deux entitiées
     * Formule de https://fr.wikipedia.org/wiki/Distance_(math%C3%A9matiques)
     */
    private double computeEuclidianDistance(Flower from, Flower to) {
        double distance = 0.0;
        distance += Math.pow(from.getSepalLength() - to.getSepalLength(), 2);
        distance += Math.pow(from.getSepalWidth() - to.getSepalWidth(), 2);
        distance += Math.pow(from.getPetalLength() - to.getPetalLength(), 2);
        distance += Math.pow(from.getPetalWidth() - to.getPetalWidth(), 2);
        return Math.sqrt(distance);
    }

    private double computeManhattanDistance(Flower from, Flower to) {
        double distance = 0.0;
        distance += Math.abs(from.getSepalLength() - to.getSepalLength());
        distance += Math.abs(from.getSepalWidth() - to.getSepalWidth());
        distance += Math.abs(from.getPetalLength() - to.getPetalLength());
        distance += Math.abs(from.getPetalWidth() - to.getPetalWidth());
        return distance;
    }

    private double computeMinkowskiDistance(Flower from, Flower to, int p) { // p-distance
        assert p != 0 : "p mut be > 0 !";
        double distance = 0;
        distance += Math.pow(from.getSepalLength() - to.getSepalLength(), p);
        distance += Math.pow(from.getSepalWidth() - to.getSepalWidth(), p);
        distance += Math.pow(from.getPetalLength() - to.getPetalLength(), p);
        distance += Math.pow(from.getPetalWidth() - to.getPetalWidth(), p);
        return Math.pow(distance, 1/p);
    }

}
