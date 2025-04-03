package controller;

import model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir; // Для створення тимчасової папки

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class UniversityJsonTest {

    private JsonManager jsonManager;
    private University oldUniversity;
    private final String testFileName = "test_university.json";
    private Path testFilePath; // Використовуємо Path для кращої роботи з файлами

    @TempDir // JUnit створить тимчасову директорію для тестів
    Path tempDir;

    @BeforeEach // Метод, що виконується перед кожним тестом
    void setUp() {
        jsonManager = new JsonManager();
        oldUniversity = createTestUniversity(); // Створюємо тестовий університет
        testFilePath = tempDir.resolve(testFileName); // Створюємо шлях до файлу в тимчасовій папці
    }

    @AfterEach // Метод, що виконується після кожного тесту
    void tearDown() throws IOException {
        // Видаляємо тимчасовий файл, якщо він існує
        Files.deleteIfExists(testFilePath);
        // Тимчасова папка @TempDir видаляється автоматично JUnit
    }

    @Test
    void testUniversitySerializationDeserializationEquality() {
        try {
            // 1. Зберегти oldUniversity у файл
            System.out.println("Збереження університету у файл: " + testFilePath);
            jsonManager.saveToFile(oldUniversity, testFilePath.toString());
            assertTrue(Files.exists(testFilePath), "JSON файл повинен бути створений");
            System.out.println("Університет збережено.");

            // 2. Завантажити університет з файлу як newUniversity
            System.out.println("Завантаження університету з файлу...");
            University newUniversity = jsonManager.loadFromFile(testFilePath.toString());
            assertNotNull(newUniversity, "Завантажений університет не повинен бути null");
            System.out.println("Університет завантажено.");

            // 3. Порівняти oldUniversity та newUniversity за допомогою equals()
            System.out.println("Порівняння оригінального та завантаженого університетів...");
            assertEquals(oldUniversity, newUniversity,
                    "Оригінальний та завантажений університети повинні бути еквівалентними (equals)");

            // Додаткова перевірка: вони не повинні бути одним і тим же об'єктом у пам'яті
            assertNotSame(oldUniversity, newUniversity,
                    "Оригінальний та завантажений університети не повинні бути одним і тим же об'єктом в пам'яті");

            System.out.println("Тест успішно пройдено!");

        } catch (IOException e) {
            // Якщо сталася помилка IO, тест повинен впасти
            fail("Помилка під час роботи з файлом JSON: " + e.getMessage());
        }
    }

    /**
     * Допоміжний метод для створення тестового університету зі структурою 2x2x2.
     * Університет -> 2 Факультети -> 4 Кафедри -> 8 Груп -> 16 Студентів
     */
    private University createTestUniversity() {
        HumanCreator humanCreator = new HumanCreator();
        StudentCreator studentCreator = new StudentCreator();
        GroupCreator groupCreator = new GroupCreator();
        DepartmentCreator departmentCreator = new DepartmentCreator();
        FacultyCreator facultyCreator = new FacultyCreator();
        UniversityCreator universityCreator = new UniversityCreator();

        // Керівники
        Human rector = humanCreator.createHuman("Ректор", "Ректоренко", "Ректорович", Sex.MALE);
        University university = universityCreator.createUniversity("Тестовий Університет", rector);

        for (int f = 1; f <= 2; f++) { // 2 Факультети
            Human dean = humanCreator.createHuman("Декан" + f, "Деканенко" + f, "Деканович" + f, f % 2 == 0 ? Sex.FEMALE : Sex.MALE);
            Faculty faculty = facultyCreator.createFaculty("Факультет " + f, dean);

            for (int d = 1; d <= 2; d++) { // 2 Кафедри на факультет
                int depId = (f - 1) * 2 + d;
                Human headDep = humanCreator.createHuman("ЗавКаф" + depId, "Кафенко" + depId, "Кафедрович" + depId, Sex.MALE);
                Department department = departmentCreator.createDepartment("Кафедра " + depId, headDep);

                for (int g = 1; g <= 2; g++) { // 2 Групи на кафедру
                    int groupId = (depId - 1) * 2 + g;
                    // Створимо одного студента заздалегідь, щоб призначити старостою
                    Student headStudent = studentCreator.createStudent("Староста" + groupId, "Старостенко" + groupId, "Групович" + groupId, Sex.FEMALE);
                    Group group = groupCreator.createGroup("Група " + groupId, headStudent); // Староста

                    // Додамо старосту до списку студентів групи
                    group.addStudent(headStudent);

                    for (int s = 1; s <= 1; s++) { // Ще 1 студент у групу (разом буде 2)
                        int studentId = (groupId -1 ) * 2 + s + 1; // +1 бо староста вже є (номер 1)
                        Student student = studentCreator.createStudent("Студент" + studentId, "Студененко" + studentId, "Студенович" + studentId, s % 2 == 0 ? Sex.FEMALE : Sex.MALE);
                        group.addStudent(student);
                    }
                    department.addGroup(group); // Додати групу до кафедри
                }
                faculty.addDepartment(department); // Додати кафедру до факультету
            }
            university.addFaculty(faculty); // Додати факультет до університету
        }
        return university;
    }
}