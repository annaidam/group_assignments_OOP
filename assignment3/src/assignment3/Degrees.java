package assignment3;

public enum Degrees {
    BSC("BSc"),
    MSC("MSc"),
    PHD("PhD");

    private final String DEGREE_TYPES;
    Degrees(String degree) {
        this.DEGREE_TYPES = degree;
    }

    public String toString() { return this.DEGREE_TYPES; }
}


