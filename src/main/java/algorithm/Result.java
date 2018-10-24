package fr.enssat.caronnantel.algorithm;

import fr.enssat.caronnantel.model.IrisClass;

class Result {

    private double distance;
    private IrisClass irisClass;

     Result(double distance, IrisClass irisClass) {
        this.distance = distance;
        this.irisClass = irisClass;
    }

     double getDistance() {
        return distance;
    }

     void setDistance(double distance) {
        this.distance = distance;
    }

     IrisClass getIrisClass() {
        return irisClass;
    }

     void setIrisClass(IrisClass irisClass) {
        this.irisClass = irisClass;
    }
}
