package model;

import java.util.Objects;

public class Human {
    protected String firstName;
    protected String lastName;
    protected String patronymic; // По батькові
    protected Sex sex;

    public Human(String firstName, String lastName, String patronymic, Sex sex) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.sex = sex;
    }

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public Sex getSex() {
        return sex;
    }

    // Setters (опціонально, можна зробити клас immutable)
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getFullName() {
        return lastName + " " + firstName + " " + patronymic;
    }

    @Override
    public String toString() {
        return "Human{" +
               "ПІБ='" + getFullName() + '\'' +
               ", Стать=" + sex +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Human)) return false;
        Human human = (Human) o;
        return Objects.equals(firstName, human.firstName) &&
               Objects.equals(lastName, human.lastName) &&
               Objects.equals(patronymic, human.patronymic) &&
               sex == human.sex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, patronymic, sex);
    }
}