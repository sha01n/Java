package controller;

import model.Group;
import model.Human;
import model.Student; // Потрібен для перевірки типу голови групи

public class GroupCreator {
    public Group createGroup(String name, Human head) {
        if (head != null && !(head instanceof Student)) {
             System.err.println("ПОМИЛКА: Голова групи '" + name + "' повинен бути студентом (або відсутнім). Встановлено null.");
             head = null;
        }
        return new Group(name, head);
    }
}