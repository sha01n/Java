package model;

public enum Sex {
    MALE("Чоловіча"),
    FEMALE("Жіноча");

    private final String description;

    Sex(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}