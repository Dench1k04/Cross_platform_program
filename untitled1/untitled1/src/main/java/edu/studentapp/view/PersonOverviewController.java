package edu.studentapp.view;

import edu.studentapp.MainApp;
import edu.studentapp.model.Person;
import edu.studentapp.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

public class PersonOverviewController {
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label groupCodeLabel;
    @FXML
    private Label birthdayLabel;
    @FXML
    private Label groupNameLabel;
    @FXML
    private Label averageRatingLabel;
    @FXML
    private Label yearOfGraduationLabel;


    private MainApp mainApp;

    /**
     * Конструктор.
     * Конструктор викликається раніше методом initialize().
     */
    public PersonOverviewController() {
    }

    /**
     * Ініціалізація класу-контролера. Цей метод викликається автоматично * після того, як файл fxml буде завантажений.
     */
    @FXML
    private void initialize() {
        firstNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().lastNameProperty());
// Очищення додаткової інформації про студента.
        showPersonDetails(null);
// Слухаємо зміни вибору і при зміні відображаємо
        // додаткову інформацію про студента.
        personTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));

//         myIntegerColumn.setCellValueFactory(cellData -> cellData.getValue().myIntegerProperty().asObject());
        firstNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().lastNameProperty());
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
// Додавання до таблиці даних із спостережуваного списку
        personTable.setItems(mainApp.getPersonData());
    }

    @FXML
    private void handleNewPerson() {
        Person tempPerson = new Person();
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
        if (okClicked) {
            mainApp.getPersonData().add(tempPerson);
        }
    }

    /**
     * Викликається, коли користувач клацне по кнопка Edit... * Відкриває діалогове вікно для зміни вибраного студента.
     */
    @FXML
    private void handleEditPerson() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showPersonDetails(selectedPerson);
            }
        } else {
// Нічого не вибрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");
            alert.showAndWait();
        }
    }

    private void showPersonDetails(Person person) {
        if (person != null) {
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            groupCodeLabel.setText(String.valueOf(person.getGroupCode()));
            birthdayLabel.setText(DateUtil.format(person.getBirthday()));
            groupNameLabel.setText(person.getGroupName());
            averageRatingLabel.setText(String.valueOf(person.getAverageRating()));
            yearOfGraduationLabel.setText(String.valueOf(person.getYearOfGraduation()));
        } else {
// Якщо Person = null, то забираємо весь текст.
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            groupCodeLabel.setText("");
            birthdayLabel.setText("");
            groupNameLabel.setText("");
            averageRatingLabel.setText("");
            yearOfGraduationLabel.setText("");
        }
    }

    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Запуск программы");
            alert.setHeaderText("Вы действительно хотите удалить пользователя?");
            alert.setContentText("Нажмите ОК для подтверждения и Cancel для отмены");

            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == null || option.get() == ButtonType.CANCEL) {
            } else if (option.get() == ButtonType.OK) {
                personTable.getItems().remove(selectedIndex);
            }
        } else {
// Нічого не вибрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");
            alert.showAndWait();
        }
    }

}