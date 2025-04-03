package model;

import java.util.ArrayList;
import java.util.List;

public class University extends StructuralUnit {
    private List<Faculty> faculties;

    public University(String name, Human head) {
        super(name, head);
        this.faculties = new ArrayList<>();
    }

    public void addFaculty(Faculty faculty) {
        this.faculties.add(faculty);
    }

    public List<Faculty> getFaculties() {
        return new ArrayList<>(faculties);
    }

    public void setFaculties(List<Faculty> faculties) {
        this.faculties = new ArrayList<>(faculties);
    }

    @Override
    public String toString() {
        return "Університет {" + super.toString() + ", Кількість факультетів=" + faculties.size() + "}";
    }
}