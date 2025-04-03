import java.util.Scanner;
import java.util.Random;

public class Main {
    // Константи для діапазону рандомних чисел
    private static final int RANDOM_MIN = 0;
    private static final int RANDOM_MAX = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Зчитування розмірів матриці
        System.out.println("Введіть висоту матриці (не більше 20):");
        int rows = getValidSize(scanner);
        System.out.println("Введіть ширину матриці (не більше 20):");
        int cols = getValidSize(scanner);

        // Вибір способу створення матриці
        System.out.println("Оберіть спосіб створення матриці: ");
        System.out.println("1 - Ввести елементи вручну");
        System.out.println("2 - Заповнити матрицю рандомно");
        int choice = getValidChoice(scanner);

        int[][] matrix;
        if (choice == 1) {
            matrix = createMatrixManually(rows, cols, scanner);
        } else {
            matrix = createMatrixRandomly(rows, cols);
        }

        // Виведення матриці
        System.out.println("Сформована матриця:");
        printMatrix(matrix);

        // Виконання операцій із матрицею
        int minElement = findMin(matrix);
        int maxElement = findMax(matrix);
        double average = calculateAverage(matrix);

        // Результати
        System.out.println("Мінімальний елемент: " + minElement);
        System.out.println("Максимальний елемент: " + maxElement);
        System.out.println("Середнє арифметичне: " + average);
    }

    // Введення розміру матриці з перевіркою на діапазон
    public static int getValidSize(Scanner scanner) {
        int size;
        do {
            size = scanner.nextInt();
            if (size < 1 || size > 20) {
                System.out.println("Некоректний розмір. Введіть число від 1 до 20:");
            }
        } while (size < 1 || size > 20);
        return size;
    }

    // Вибір способу створення матриці з перевіркою вводу
    public static int getValidChoice(Scanner scanner) {
        int choice;
        do {
            choice = scanner.nextInt();
            if (choice != 1 && choice != 2) {
                System.out.println("Некоректний вибір. Введіть 1 або 2:");
            }
        } while (choice != 1 && choice != 2);
        return choice;
    }

    // Створення матриці вручну
    public static int[][] createMatrixManually(int rows, int cols, Scanner scanner) {
        int[][] matrix = new int[rows][cols];
        System.out.println("Введіть елементи матриці (" + rows + "x" + cols + "):");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("Елемент [" + i + "][" + j + "]: ");
                matrix[i][j] = scanner.nextInt();
            }
        }
        return matrix;
    }

    // Створення матриці рандомно
    public static int[][] createMatrixRandomly(int rows, int cols) {
        int[][] matrix = new int[rows][cols];
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = RANDOM_MIN + random.nextInt(RANDOM_MAX - RANDOM_MIN + 1);
            }
        }
        return matrix;
    }

    // Виведення матриці
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int element : row) {
                System.out.print(element + "\t");
            }
            System.out.println();
        }
    }

    // Знаходження мінімального елемента в матриці
    public static int findMin(int[][] matrix) {
        int min = Integer.MAX_VALUE;
        for (int[] row : matrix) {
            for (int element : row) {
                if (element < min) {
                    min = element;
                }
            }
        }
        return min;
    }

    // Знаходження максимального елемента в матриці
    public static int findMax(int[][] matrix) {
        int max = Integer.MIN_VALUE;
        for (int[] row : matrix) {
            for (int element : row) {
                if (element > max) {
                    max = element;
                }
            }
        }
        return max;
    }

    // Обчислення середнього арифметичного елементів матриці
    public static double calculateAverage(int[][] matrix) {
        int sum = 0;
        int count = 0;
        for (int[] row : matrix) {
            for (int element : row) {
                sum += element;
                count++;
            }
        }
        return (double) sum / count;
    }
}
