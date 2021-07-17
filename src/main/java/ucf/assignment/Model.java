package ucf.assignment;
/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Erica Joseph
 */
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class Model {

    private SimpleStringProperty taskName; //making it so tableview can accept while defining
    private SimpleStringProperty dueDate;//making it so tableview can accept while defining
    private SimpleBooleanProperty status;//making it so tableview can accept while defining

    public Model(String taskName, String dueDate, boolean status) {
        this.taskName = new SimpleStringProperty(taskName); //stating task name as retrievable
        this.dueDate = new SimpleStringProperty(dueDate);
        this.setStatus(new SimpleBooleanProperty(status));

    }

    public String getTaskName() {
        return taskName.get();
    }

    public void setTaskName(String taskName) {
        this.taskName = new SimpleStringProperty(taskName);
    }

    public String getDueDate() {
        return dueDate.get();
    }

    public void setDueDate(String dueDate) {
        this.dueDate = new SimpleStringProperty(dueDate);
    }

    public boolean getStatus() {
        return status.get();
    }

    public void setStatus(SimpleBooleanProperty status) {
        this.status = status;
    }
}
