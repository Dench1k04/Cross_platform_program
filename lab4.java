import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Створюємо список чисел
        List<Integer> numbers = new ArrayList<>();
        numbers.add(3);
        numbers.add(-2);
        numbers.add(8);
        numbers.add(-5);
        numbers.add(0);
        numbers.add(4);

        // Індекс лівого і правого вказівника
        int left = 0; // Початок списку
        int right = numbers.size() - 1; // Кінець списку

        // Поки лівий вказівник менший за правий
        while (left < right) {
            // Пересувати лівий вказівник, поки знаходимо негативний елемент
            while (numbers.get(left) < 0 && left < right) {
                left++; // Пересуваємо лівий вказівник до позитивного елементу
            }

            // Пересувати правий вказівник, поки знаходимо позитивний елемент
            while (numbers.get(right) >= 0 && left < right) {
                right--; // Пересуваємо правий вказівник до негативного елементу
            }

            // Якщо лівий вказівник не перевищує правий, то обмін значеннями
            if (left < right) {
                // Обмін значеннями
                int temp = numbers.get(left);
                numbers.set(left, numbers.get(right));
                numbers.set(right, temp);
                left++;
                right--;
            }
        }

        // Вивести результат
        System.out.println("Результат:");
        for (int number : numbers) {
            System.out.print(number + " ");
        }
    }
}
