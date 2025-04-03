package model;

import java.util.ArrayList;
import java.util.List;

public class Faculty extends StructuralUnit {
    private List<Department> departments;

    public Faculty(String name, Human head) {
        super(name, head);
        this.departments = new ArrayList<>();
    }

    public void addDepartment(Department department) {
        this.departments.add(department);
    }

    public List<Department> getDepartments() {
        return new ArrayList<>(departments);
    }

     public void setDepartments(List<Department> departments) {
        this.departments = new ArrayList<>(departments);
    }

    @Override
    public String toString() {
        return "Факультет {" + super.toString() + ", Кількість кафедр=" + departments.size() + "}";
    }
}