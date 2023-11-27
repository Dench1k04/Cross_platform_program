package edu.studentapp;

import edu.studentapp.view.PersonEditDialogController;
import edu.studentapp.view.PersonOverviewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import edu.studentapp.model.Person;

import java.io.IOException;

public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

    private ObservableList<Person> personData = FXCollections.observableArrayList();

    public ObservableList<Person> getPersonData() {
        return personData;
    }

    public boolean showPersonEditDialog(Person person) {
        try {
// Завантажуємо файл fxml і створюємо нову сцену для діалогового вікна.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/edu/javafx5/PersonEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
//Створюємо діалогове вікно Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
// Передаємо студента до контролера.
            PersonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);
// Відображаємо діалогове вікно та чекаємо, поки користувач його не закриє
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        this.primaryStage.setTitle("StudentGroupApp");
        initRootLayout();
        showPersonOverview();

        personData.add(new Person("Петро", "П'яточкін", "A", 3.3, 2025));
        personData.add(new Person("Іван", "Зайців", "B", 2.2, 2026));
        personData.add(new Person("Катерина", "Васильченка", "B", 4.3, 2026));
        personData.add(new Person("Ольга", "Жук", "B", 3.1, 2025));
        personData.add(new Person("Людміла", "Алексєєва", "C", 4.5, 2025));
        personData.add(new Person("Даніл", "Кац", "A", 4.9, 2028));
        personData.add(new Person("Євген", "Васнецов", "B", 3.2, 2024));
        personData.add(new Person("Дмитро", "Жуликів", "C", 3.1, 2024));
        personData.add(new Person("Мрат", "Алібов", "C", 1.9, 2023));
        personData.add(new Person("Martin", "Mueller", "A", 2.1, 2026));
    }


    public void initRootLayout() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/edu/javafx5/RootLayout.fxml"));
        try {
            rootLayout = (BorderPane) fxmlLoader.load();
            Scene scene=new Scene(rootLayout);
            primaryStage.setScene(scene);

            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showPersonOverview() {
        FXMLLoader fxmlLoader = new FXMLLoader(); //Завантажуємо відомості про студентів.
        fxmlLoader.setLocation(getClass().getResource("/edu/javafx5/PersonOverview.fxml"));

        try {
            AnchorPane personOverview = fxmlLoader.load();
            rootLayout.setCenter(personOverview);
            PersonOverviewController controller = fxmlLoader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch();
    }
}