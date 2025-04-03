package controller;

import model.Human;
import model.Sex;

public class HumanCreator {
    public Human createHuman(String firstName, String lastName, String patronymic, Sex sex) {
        return new Human(firstName, lastName, patronymic, sex);
    }
}