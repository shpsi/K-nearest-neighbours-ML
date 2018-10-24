package fr.enssat.caronnantel.algorithm;

import java.util.Comparator;

 class DistanceComparator implements Comparator<Result> {

    @Override
    public int compare(Result a, Result b) {
        return a.getDistance() < b.getDistance() ? -1 : a.getDistance() == b.getDistance() ? 0 : 1;
    }
}
