import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// Класс MusicComposition представляет музыкальную композицию.
class MusicComposition {
    private String title;   // Название композиции
    private String artist;  // Имя исполнителя
    private int duration;   // Продолжительность композиции в секундах

    public MusicComposition(String title, String artist, int duration) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
    }

    public void play() {
        System.out.println("Playing " + title + " by " + artist);
    }

    public int getDuration() {
        return duration;
    }

    public String getTitle() {
        return title;
    }
}

// Класс Song наследует MusicComposition и добавляет свойство "genre" для представления жанра песни.
class Song extends MusicComposition {
    private String genre;  // Жанр песни

    public Song(String title, String artist, int duration, String genre) {
        super(title, artist, duration);
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }
}

// Класс Album представляет музыкальный альбом, который содержит список треков (MusicComposition).
class Album {
    private String title;  // Название альбома
    private String artist; // Имя исполнителя альбома
    private List<MusicComposition> tracks = new ArrayList<>(); // Список композиций в альбоме

    public Album(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    public void addTrack(MusicComposition track) {
        tracks.add(track);
    }

    // Метод для подсчета общей продолжительности альбома.
    public int getTotalDuration() {
        int totalDuration = 0;
        for (MusicComposition track : tracks) {
            totalDuration += track.getDuration();
        }
        return totalDuration;
    }

    // Метод для перемешивания треков в альбоме.
    public void shuffleTracks() {
        Collections.shuffle(tracks);
    }

    // Метод для поиска треков в заданном диапазоне продолжительности.
    public List<MusicComposition> findTracksInDurationRange(int minDuration, int maxDuration) {
        List<MusicComposition> filteredTracks = new ArrayList<>();
        for (MusicComposition track : tracks) {
            int duration = track.getDuration();
            if (duration >= minDuration && duration <= maxDuration) {
                filteredTracks.add(track);
            }
        }
        return filteredTracks;
    }

    public List<MusicComposition> getTracks() {
        return tracks;
    }
}

// Класс MusicPlayer содержит метод main для управления музыкальной библиотекой.
public class MusicPlayer {
    public static void main(String[] args) {
        // Создаем несколько песен и добавляем их в альбом.
        Song song1 = new Song("Song 1", "Artist 1", 180, "Pop");
        Song song2 = new Song("Song 2", "Artist 2", 240, "Rock");
        Song song3 = new Song("Song 3", "Artist 3", 210, "Pop");

        Album album = new Album("Album 1", "Various Artists");
        album.addTrack(song1);
        album.addTrack(song2);
        album.addTrack(song3);

        // Создаем объект Scanner для ввода пользовательских команд.
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Выводим меню с опциями для пользователя.
            System.out.println("\nМеню:");
            System.out.println("1. Відтворити всі пісні");
            System.out.println("2. Розрахувати тривалість альбому");
            System.out.println("3. Перемішати треки");
            System.out.println("4. Знайти треки в заданому діапазоні тривалості");
            System.out.println("5. Вийти");
            System.out.print("Виберіть опцію: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    // Воспроизводим все композиции в альбоме.
                    for (MusicComposition track : album.getTracks()) {
                        track.play();
                    }
                    break;
                case "2":
                    // Выводим общую продолжительность альбома.
                    int totalDuration = album.getTotalDuration();
                    System.out.println("Загальна тривалість альбому: " + totalDuration + " секунд");
                    break;
                case "3":
                    // Перемешиваем треки в альбоме.
                    album.shuffleTracks();
                    System.out.println("Треки були перемішані");
                    break;
                case "4":
                    // Пользователь указывает диапазон продолжительности, и мы выводим соответствующие треки.
                    System.out.print("Мінімальна тривалість (секунди): ");
                    int minDuration = Integer.parseInt(scanner.nextLine());
                    System.out.print("Максимальна тривалість (секунди): ");
                    int maxDuration = Integer.parseInt(scanner.nextLine());
                    List<MusicComposition> filteredTracks = album.findTracksInDurationRange(minDuration, maxDuration);

                    if (!filteredTracks.isEmpty()) {
                        System.out.println("Пісні у вказаному діапазоні:");
                        for (MusicComposition track : filteredTracks) {
                            System.out.println(track.getTitle() + " - " + track.getDuration() + " секунд");
                        }
                    } else {
                        System.out.println("Не знайдено пісень у вказаному діапазоні.");
                    }
                    break;
                case "5":
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
    }
}
