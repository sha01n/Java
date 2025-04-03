package model;

public class Student extends Human {

    public Student(String firstName, String lastName, String patronymic, Sex sex) {
        super(firstName, lastName, patronymic, sex);
    }

    @Override
    public String toString() {
        return "Student{" +
               "ПІБ='" + getFullName() + '\'' +
               ", Стать=" + sex +
               '}';
    }
}