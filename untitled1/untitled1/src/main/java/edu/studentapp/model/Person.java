package edu.studentapp.model;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Person {
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final IntegerProperty groupCode;
    private final ObjectProperty<LocalDate> birthday;
    private String groupName;
    private Double averageRating;
    private Integer yearOfGraduation;

    /**
     * Конструктор за замовчуванням.
     */
    public Person() {
        this(null, null, null, 0.0, 0);
    }

    /**
     * Конструктор із деякими початковими даними.
     *
     * @paramfirstName * @paramlastName
     */
    public Person(String firstName, String lastName, String groupName, Double averageRating, Integer yearOfGraduation) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.groupName = groupName;
        this.averageRating = averageRating;
        this.yearOfGraduation = yearOfGraduation;
        this.groupCode = new SimpleIntegerProperty(1747);
        this.birthday = new SimpleObjectProperty<>(LocalDate.of(2000, 2, 21));
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public int getGroupCode() {
        return groupCode.get();
    }

    public void setGroupCode(int groupCode) {
        this.groupCode.set(groupCode);
    }

    public IntegerProperty groupCodeProperty() {
        return groupCode;
    }

    public LocalDate getBirthday() {
        return birthday.get();
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }

    public String getGroupName() {
        return groupName;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public Integer getYearOfGraduation() {
        return yearOfGraduation;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public void setYearOfGraduation(Integer yearOfGraduation) {
        this.yearOfGraduation = yearOfGraduation;
    }
}