package assignment3;

public enum Department {
    HUMAN_RESOURCES("Human Resources"),
    TECHNICAL("Technical"),
    BUSINESS("Business");

    private final String DEPARTMENT;

    Department(String department) {
        DEPARTMENT = department;
    }

    public String toString() {return this.DEPARTMENT;}
}
