package model;

import java.util.Objects;

public abstract class StructuralUnit {
    protected String name;
    protected Human head; // Голова підрозділу

    public StructuralUnit(String name, Human head) {
        this.name = name;
        this.head = head;
    }

    // Getters
    public String getName() {
        return name;
    }

    public Human getHead() {
        return head;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setHead(Human head) {
        this.head = head;
    }

    @Override
    public String toString() {
        return "Назва='" + name + '\'' +
               ", Керівник=" + (head != null ? head.getFullName() : "Не призначено");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StructuralUnit)) return false;
        StructuralUnit that = (StructuralUnit) o;
        return Objects.equals(name, that.name) && Objects.equals(head, that.head);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, head);
    }
}