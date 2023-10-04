public class lab2 {
    public static String reverseEveryOtherWord(String input) {
        // Розділяємо вхідний рядок на слова за пробілами
        String[] words = input.split(" ");
        StringBuilder result = new StringBuilder();

        // Перебираємо слова і реверсуємо кожне друге слово
        for (int i = 0; i < words.length; i++) {
            if (i % 2 == 1) { // Якщо індекс слова непарний (починаючи з 1), реверсуємо слово
                result.append(reverseWord(words[i]));
            } else { // Якщо індекс слова парний (починаючи з 0), залишаємо слово без змін
                result.append(words[i]);
            }

            if (i < words.length - 1) {
                result.append(" "); // Додаємо пробіл після кожного слова, крім останнього
            }
        }

        return result.toString();
    }

    // Метод для реверсу слова
    private static String reverseWord(String word) {
        return new StringBuilder(word).reverse().toString();
    }

    public static void main(String[] args) {
        String input = "Це є приклад рядка для тестування";
        String output = reverseEveryOtherWord(input);
        System.out.println(output); // Виводимо результат
    }
}
