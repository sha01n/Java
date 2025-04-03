import controller.*;
import model.*;

import java.util.Arrays;

public class Run {

    public static void main(String[] args) {
        University typicalUniversity = createTypicalUniversity();

        System.out.println("Створено університет: " + typicalUniversity);
        System.out.println("\nСтруктура університету:");

        // Структура
        for (Faculty faculty : typicalUniversity.getFaculties()) {
            System.out.println("- Факультет: " + faculty.getName() + " (Декан: " + faculty.getHead().getFullName() + ")");
            for (Department department : faculty.getDepartments()) {
                System.out.println("  - Кафедра: " + department.getName() + " (Зав. кафедри: " + department.getHead().getFullName() + ")");
                 for (Group group : department.getGroups()) {
                     System.out.println("    - Група: " + group.getName() + " (Староста: " + (group.getHead() != null ? group.getHead().getFullName() : "Немає") + ")");
                     for (Student student : group.getStudents()) {
                         System.out.println("      - Студент: " + student.getFullName());
                     }
                 }
            }
        }
    }


    public static University createTypicalUniversity() {
        // Створюємо екземпляри Creator-ів
        HumanCreator humanCreator = new HumanCreator();
        StudentCreator studentCreator = new StudentCreator();
        GroupCreator groupCreator = new GroupCreator();
        DepartmentCreator departmentCreator = new DepartmentCreator();
        FacultyCreator facultyCreator = new FacultyCreator();
        UniversityCreator universityCreator = new UniversityCreator();

        // 1. Створюємо людей
        Human rector = humanCreator.createHuman("Віктор", "Андрущенко", "Петрович", Sex.MALE);
        Human deanCS = humanCreator.createHuman("Ольга", "Черненко", "Іванівна", Sex.FEMALE);
        Human deanMath = humanCreator.createHuman("Сергій", "Петренко", "Васильович", Sex.MALE);
        Human headCSE = humanCreator.createHuman("Максим", "Зайцев", "Олегович", Sex.MALE);
        Human headIS = humanCreator.createHuman("Ірина", "Білоус", "Михайлівна", Sex.FEMALE);
        Human headAlgebra = humanCreator.createHuman("Андрій", "Волков", "Дмитрович", Sex.MALE);

        Student student1 = studentCreator.createStudent("Іван", "Мельник", "Андрійович", Sex.MALE);
        Student student2 = studentCreator.createStudent("Марія", "Ковальчук", "Сергіївна", Sex.FEMALE);
        Student student3 = studentCreator.createStudent("Петро", "Бондаренко", "Вікторович", Sex.MALE);
        Student student4 = studentCreator.createStudent("Оксана", "Шевченко", "Олегівна", Sex.FEMALE);
        Student student5 = studentCreator.createStudent("Василь", "Поліщук", "Ігорович", Sex.MALE);
        Student student6 = studentCreator.createStudent("Дарина", "Ткаченко", "Юріївна", Sex.FEMALE);


        // 2. Університет
        University university = universityCreator.createUniversity("Національний Технічний Університет", rector);

        // 3. Факультети
        Faculty csFaculty = facultyCreator.createFaculty("Факультет Комп'ютерних Наук", deanCS);
        Faculty mathFaculty = facultyCreator.createFaculty("Фізико-Математичний Факультет", deanMath);

        // 4. Кафедри
        Department cseDepartment = departmentCreator.createDepartment("Кафедра Комп'ютерної Інженерії", headCSE);
        Department isDepartment = departmentCreator.createDepartment("Кафедра Інформаційних Систем", headIS);
        Department algebraDepartment = departmentCreator.createDepartment("Кафедра Алгебри та Геометрії", headAlgebra);

        // 5. Групи (староста - студент)
        Group groupCS101 = groupCreator.createGroup("КН-101", student1); // student1 - староста
        Group groupIS102 = groupCreator.createGroup("ІС-102", student4); // student4 - староста
        Group groupMA101 = groupCreator.createGroup("МА-101", null);     // Без старости

        // 6. студенти до груп
        groupCS101.addStudent(student1);
        groupCS101.addStudent(student2);
        groupCS101.addStudent(student3);

        groupIS102.addStudent(student4);
        groupIS102.addStudent(student5);

        groupMA101.addStudent(student6);



        cseDepartment.addGroup(groupCS101);
        isDepartment.addGroup(groupIS102);
        algebraDepartment.addGroup(groupMA101);


        csFaculty.addDepartment(cseDepartment);
        csFaculty.addDepartment(isDepartment);
        mathFaculty.addDepartment(algebraDepartment);


        university.addFaculty(csFaculty);
        university.addFaculty(mathFaculty);


        return university;
    }
}