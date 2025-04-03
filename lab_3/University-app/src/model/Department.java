package model;

import java.util.ArrayList;
import java.util.List;

public class Department extends StructuralUnit {
    private List<Group> groups;

    public Department(String name, Human head) {
        super(name, head);
        this.groups = new ArrayList<>();
    }

    public void addGroup(Group group) {
        this.groups.add(group);
    }

    public List<Group> getGroups() {
        return new ArrayList<>(groups);
    }

    public void setGroups(List<Group> groups) {
        this.groups = new ArrayList<>(groups);
    }


    @Override
    public String toString() {
        return "Кафедра {" + super.toString() + ", Кількість груп=" + groups.size() + "}";
    }
}