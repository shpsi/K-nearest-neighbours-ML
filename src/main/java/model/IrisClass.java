package fr.enssat.caronnantel.model;

public enum IrisClass {
    SETOSA("Iris-setosa"), VERSICOLOUR("Iris-versicolor"), VIRGINICA("Iris-virginica");

    private String value;

    IrisClass(String value) {
        this.value = value;
    }

    public static IrisClass forValue(String value) {
        for (IrisClass type : IrisClass.values()) {
            if (type.value.equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }
}