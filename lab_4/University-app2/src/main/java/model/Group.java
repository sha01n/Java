package model;

import java.util.ArrayList;
import java.util.List;

public class Group extends StructuralUnit {
    private List<Student> students;

    public Group(String name, Human head) {
        super(name, head);
        if (!(head instanceof Student) && head != null) {
            System.err.println("Попередження: Голова групи '" + name + "' не є студентом.");
        }
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }

     public void setStudents(List<Student> students) {
        this.students = new ArrayList<>(students);
    }

    @Override
    public String toString() {
        return "Група {" + super.toString() + ", Кількість студентів=" + students.size() + "}";
    }
}