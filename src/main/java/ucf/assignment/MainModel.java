package ucf.assignment;
/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Erica Joseph
 */
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

public class MainModel {

    private SimpleStringProperty listName; //defining list name as simple string so tableview can process

    public MainModel(String listName) {
        this.listName = new SimpleStringProperty(listName);
    } //main model

    public String getListName() {
        return listName.get();
    }

    public void setListName(String listName) {
        this.listName = new SimpleStringProperty(listName);
    }

}
