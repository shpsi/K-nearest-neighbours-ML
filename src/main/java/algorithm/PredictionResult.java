package fr.enssat.caronnantel.algorithm;

import fr.enssat.caronnantel.model.IrisClass;

public class PredictionResult {

    private IrisClass expected;
    private IrisClass predicted;

    public PredictionResult(IrisClass expected, IrisClass predicted) {
        this.expected = expected;
        this.predicted = predicted;
    }

    public IrisClass getExpected() {
        return expected;
    }

    public void setExpected(IrisClass expected) {
        this.expected = expected;
    }

    public IrisClass getPredicted() {
        return predicted;
    }

    public void setPredicted(IrisClass predicted) {
        this.predicted = predicted;
    }
}
