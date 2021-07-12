package ucf.assignment;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

public class Model {

    private SimpleStringProperty taskName;
    private SimpleStringProperty dueDate;
    private CheckBox status;

    public Model(String taskName, String dueDate) {
        this.taskName = new SimpleStringProperty(taskName);
        this.dueDate = new SimpleStringProperty(dueDate);
        this.status = new CheckBox();

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

    public CheckBox getStatus() {
        return status;
    }

    public void setStatus(CheckBox status) {
        this.status = status;
    }
}
