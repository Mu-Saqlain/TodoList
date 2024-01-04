package com.example.todolist;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.Scanner;

public class HelloController implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        datePicker.setValue(LocalDate.now());
        SpinnerValueFactory<Integer> fachr = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 12);
        hrsp.setValueFactory(fachr);
        fachr.setValue(LocalTime.now().getHour());
        SpinnerValueFactory<Integer> facmin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
        minsp.setValueFactory(facmin);
        facmin.setValue(LocalTime.now().getMinute());
        String[] alpha = {"AM", "PM"};
        choicebox.getItems().setAll(alpha);
    }
    @FXML
    private Label welcomeText;
    @FXML
    private Spinner<Integer> hrsp;
    @FXML
    private Spinner<Integer> minsp;
    @FXML
    private ChoiceBox choicebox;

    @FXML
    protected void onHelloButtonClick()
    {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    @FXML
    Button addEvent;

    @FXML
    Button showTasks;
    @FXML
    TextField description;
    @FXML
    DatePicker datePicker;
    @FXML
    ListView<CC> listt;
    @FXML
    ObservableList<CC> list = FXCollections.observableArrayList();
    @FXML
    Label err;
    @FXML
    private void addEvent(Event e) throws IOException {
        CC cc = new CC(description.getText(),datePicker.getValue());

        list.add(cc);
        listt.setItems(list);
        LocalTime time = LocalTime.of(hrsp.getValue(), minsp.getValue());
        HelloController.add(cc.getDesc(),cc.getDate(),time);
        showTasks.fire();

    }
    public static void add(String desc, LocalDate date, LocalTime time) throws IOException {
        CC.create();
        FileWriter write = new FileWriter("todolist.txt",true);
        BufferedWriter w = new BufferedWriter(write);
        w.write(desc);
        w.newLine();
        w.write(String.valueOf(time));
        w.newLine();
        w.write(date.toString());
        w.newLine();
        w.close();
    }
    @FXML
    private void remove(Event e){
        try {
            int sel = listt.getSelectionModel().getSelectedIndex();
            listt.getItems().remove(sel);

        }catch (Exception exception){
            err.setText("Select the item you want to remove...");
        }
    }
    public void reader() throws FileNotFoundException {
        FileReader r = new FileReader("todolist.txt");
        Scanner reader = new Scanner(r);

        StringBuilder toDO = new StringBuilder();


        int i = 0;
        while (reader.hasNextLine()) {
            if(i == 3) {
                i = 0;
                toDO.append("\n");
            }
            toDO.append(reader.nextLine() + " ");
            i++;
        }

        Scanner scan = new Scanner(String.valueOf(toDO));
//        list.add(new CC(scan.nextLine()));

        listt.setItems(list);
        while(scan.hasNextLine()){list.add(new CC(scan.nextLine()));
        listt.setItems(list);
        }
    }
}