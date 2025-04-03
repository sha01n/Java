package controller;

import model.Department;
import model.Human;

public class DepartmentCreator {
    public Department createDepartment(String name, Human head) {
        return new Department(name, head);
    }
}