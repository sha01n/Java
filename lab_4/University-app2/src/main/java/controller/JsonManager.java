package controller; // Або persistence

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.University;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

/**
 * Клас для збереження та завантаження об'єкта University у/з формату JSON.
 */
public class JsonManager {

    private final Gson gson;

    public JsonManager() {
        // Створюємо Gson з налаштуванням PrettyPrinting для кращої читабельності файлу
        this.gson = new GsonBuilder()
                .setPrettyPrinting() // Робить JSON файл форматованим
                .create();
    }

    /**
     * Зберігає об'єкт University у файл у форматі JSON.
     *
     * @param university Об'єкт університету для збереження.
     * @param filePath   Шлях до файлу, куди буде збережено JSON.
     * @throws IOException Якщо виникає помилка під час запису файлу.
     */
    public void saveToFile(University university, String filePath) throws IOException {
        // Використовуємо try-with-resources для автоматичного закриття FileWriter
        try (Writer writer = new FileWriter(filePath, StandardCharsets.UTF_8)) {
            gson.toJson(university, writer); // Записуємо JSON безпосередньо у потік
        }
    }

    /**
     * Завантажує об'єкт University з файлу JSON.
     *
     * @param filePath Шлях до файлу JSON.
     * @return Відновлений об'єкт University.
     * @throws IOException Якщо виникає помилка під час читання файлу.
     */
    public University loadFromFile(String filePath) throws IOException {
        // Використовуємо try-with-resources для автоматичного закриття FileReader
        try (Reader reader = new FileReader(filePath, StandardCharsets.UTF_8)) {
            return gson.fromJson(reader, University.class); // Читаємо та десеріалізуємо JSON
        }
    }
}