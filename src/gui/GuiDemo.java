package gui;


import generator.Chamber;
import generator.Passage;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;
import generator.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class GuiDemo<toReturn> extends Application {
    private Controller theController;
    private BorderPane root;  //the root element of this GUI
    private Popup descriptionPane;
    private Popup editPane;
    private Stage primaryStage;  //The stage that is passed in on initialization
    private TextArea area;
    private ComboBox box;
    private ListView list;
    private ComboBox addMonster;
    private ComboBox removeMonster;
    private ComboBox addTreasure;
    private ComboBox removeTreasure;


    /*a call to start replaces a call to the constructor for a JavaFX GUI*/
    @Override
    public void start(Stage assignedStage) {
        /*Initializing instance variables */
        theController = new Controller(this);
        primaryStage = assignedStage;
        /*Border Panes have  top, left, right, center and bottom sections */
        root = setUpRoot();
        descriptionPane = createPopUp(200, 300, "Example Description of something");
        editPane = createPopUp(800, 600);
        Scene scene = new Scene(root, 300, 300);
        primaryStage.setTitle("DnD Level Generator");
        primaryStage.getIcons().add(new Image("res/icon.png"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void start(Stage assignedStage, Controller toAdd) {
        /*Initializing instance variables */
        theController = toAdd;
        theController.setGui(this);
        primaryStage = assignedStage;
        /*Border Panes have  top, left, right, center and bottom sections */
        root = setUpRoot();
        descriptionPane = createPopUp(200, 300, "Example Description of something");
        editPane = createPopUp(800, 600);
        Scene scene = new Scene(root, 300, 300);
        primaryStage.setTitle("DnD Level Generator");
        primaryStage.getIcons().add(new Image("res/icon.png"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private BorderPane setUpRoot() {
        BorderPane temp = new BorderPane();
        area = new TextArea();
        box = new ComboBox();
        list = new ListView();
        Node left = setLeftButtonPanel();  //separate method for the left section
        temp.setLeft(left);
        Node center = setCenterPanel("");
        temp.setCenter(center);
        Node right = setRightPanel();
        temp.setRight(right);
        Node top = setTop();
        temp.setTop(top);
        return temp;
    }

    private Node setCenterPanel(String text){
        VBox temp = new VBox();
        temp.setStyle( "-fx-padding: 10;" +
        "-fx-border-style: solid inside;" +
        "-fx-border-width: 2;" +
        "-fx-border-insets: 5;" +
        "-fx-border-radius: 5;" +
        "-fx-border-color: grey;");
        Label label = new Label("Description of component selected:");
        area = new TextArea(text);
        temp.getChildren().add(label);
        temp.getChildren().add(area);
        return temp;
    }


    private Node setRightPanel(){
        VBox temp = new VBox();
        temp.setStyle( "-fx-padding: 10;" +
        "-fx-border-style: solid inside;" +
        "-fx-border-width: 2;" +
        "-fx-border-insets: 5;" +
        "-fx-border-radius: 5;" +
        "-fx-border-color: grey;");
        box.setValue("Doors list");
        temp.getChildren().add(box);
        return temp;
    }

    private Node setLeftButtonPanel() {
        VBox temp = new VBox();
        temp.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: grey;");

        Label label = new Label("List of level components:");
        temp.getChildren().add(label);

        Button editButton = createButton("Edit", "-fx-background-color: #FFFFFF; ");
        editButton.setOnAction((ActionEvent) -> {
            editPane.show(primaryStage);
        });

        Button button = new Button("Read Selected Component");
        button.setOnAction(event -> {
            ObservableList selectedIndices = list.getSelectionModel().getSelectedIndices();
            for(Object o : selectedIndices){
                box.getItems().clear();
                area.setText((theController.getDescription((int)o)));
            }
            descriptionPane.hide();
        });

         for(Chamber c: theController.getChambers()){
             list.getItems().add(c.getName());
         }

         for(Passage p: theController.getPassages()){
             list.getItems().add(p.getName());
         }
         temp.getChildren().add(list);
         temp.getChildren().add(button);


        Button hideButton = createButton("Hide popup", "-fx-background-color: #FFFFFF; ");
        hideButton.setOnAction((ActionEvent event) -> {
            descriptionPane.hide();
            editPane.hide();
        });


        temp.getChildren().add(editButton);
        temp.getChildren().add(hideButton);

        list.setOnMouseClicked((ActionEvent) -> {
            editPane.hide();
            descriptionPane.hide();
        });

        return temp;

    }

    public void updateDoors(ArrayList<Door> list){
     for(Door p: list){
          box.getItems().add(p.getName());
     }
     EventHandler<ActionEvent> event = e -> {
         descriptionPane.hide();
         changeDescriptionText(theController.getDoorDescription((String) box.getValue()));
         descriptionPane.show(primaryStage);
     };

      box.setOnAction(event);


    }

    private Popup createPopUp(int x, int y, String text) {
        Popup popup = new Popup();
        popup.setX(x);
        popup.setY(y);
        TextArea textA = new TextArea(text);
        popup.getContent().addAll(textA);
        textA.setStyle(" -fx-background-color: white;");
        textA.setMinWidth(80);
        textA.setMinHeight(50);

        return popup;
    }

    private Popup createPopUp(int x, int y){
    Popup popup = new Popup();
    popup.setX(x);
    popup.setY(y);
    BorderPane temp = new BorderPane();
    VBox box1 = new VBox();
    VBox box2 = new VBox();
    box1.setStyle( "-fx-padding: 10;" +
    "-fx-border-style: solid inside;" +
    "-fx-border-width: 2;" +
    "-fx-border-insets: 5;" +
    "-fx-border-radius: 5;" +
    "-fx-border-color: grey;");

    box2.setStyle( "-fx-padding: 10;" +
    "-fx-border-style: solid inside;" +
    "-fx-border-width: 2;" +
    "-fx-border-insets: 5;" +
    "-fx-border-radius: 5;" +
    "-fx-border-color: grey;");

    HBox hbox1 = new HBox();
    hbox1.setStyle( "-fx-padding: 10;" +
    "-fx-border-style: solid inside;" +
    "-fx-border-width: 2;" +
    "-fx-border-insets: 5;" +
    "-fx-border-radius: 5;" +
    "-fx-border-color: grey;");

    HBox hbox2 = new HBox();
    hbox2.setStyle( "-fx-padding: 10;" +
    "-fx-border-style: solid inside;" +
    "-fx-border-width: 2;" +
    "-fx-border-insets: 5;" +
    "-fx-border-radius: 5;" +
    "-fx-border-color: grey;");

    HBox hbox3 = new HBox();
    hbox3.setStyle( "-fx-padding: 10;" +
    "-fx-border-style: solid inside;" +
    "-fx-border-width: 2;" +
    "-fx-border-insets: 5;" +
    "-fx-border-radius: 5;" +
    "-fx-border-color: grey;");

    HBox hbox4 = new HBox();
    hbox4.setStyle( "-fx-padding: 10;" +
    "-fx-border-style: solid inside;" +
    "-fx-border-width: 2;" +
    "-fx-border-insets: 5;" +
    "-fx-border-radius: 5;" +
    "-fx-border-color: grey;");

    Button addMonsterButton = createButton("ADD", "-fx-background-color: #ff0000; -fx-background-radius: 10, 10, 10, 10;");
    Button addTreasureButton = createButton("ADD", "-fx-background-color: #ff0000; -fx-background-radius: 10, 10, 10, 10;");
    Button removeMonsterButton = createButton("REMOVE", "-fx-background-color: #ff0000; -fx-background-radius: 10, 10, 10, 10;");
    Button removeTreasureButton = createButton("REMOVE", "-fx-background-color: #ff0000; -fx-background-radius: 10, 10, 10, 10;");

    addMonster = new ComboBox(FXCollections.observableList(setupMonsters()));
    addMonster.setValue("Add Monster");
    removeMonster = new ComboBox();
    removeMonster.setValue("Remove Monster");

    addMonsterButton.setOnAction((ActionEvent) -> {
        ObservableList selectedIndices = list.getSelectionModel().getSelectedIndices();
        for(Object o : selectedIndices){
            theController.addToDescription("Monster: ", (int) o);
            theController.addToDescription((String) addMonster.getValue(), (int) o);
            theController.addToDescription("\n", (int) o);
            box.getItems().clear();
            area.setText((theController.getDescription((int)o)));
            removeMonster.getItems().add((String) addMonster.getValue());

        }
    });

    removeMonsterButton.setOnAction((ActionEvent) -> {
        ObservableList selectedIndices = list.getSelectionModel().getSelectedIndices();
        for(Object o : selectedIndices){
            theController.removeFromDescription("Monster: "+(String) removeMonster.getValue()+"\n", (int) o);
            box.getItems().clear();
            area.setText((theController.getDescription((int)o)));
            removeMonster.getItems().remove((String) removeMonster.getValue());
        }
    });

    hbox1.getChildren().add(addMonster);
    hbox1.getChildren().add(addMonsterButton);

    hbox3.getChildren().add(removeMonster);
    hbox3.getChildren().add(removeMonsterButton);


    box1.getChildren().add(hbox1);
    box1.getChildren().add(hbox3);


    addTreasure = new ComboBox(FXCollections.observableList(setupTreasures()));
    addTreasure.setValue("Add Treasure");
    removeTreasure = new ComboBox();
    removeTreasure.setValue("Remove Treasure");

    addTreasureButton.setOnAction((ActionEvent) -> {
        ObservableList selectedIndices = list.getSelectionModel().getSelectedIndices();
        for(Object o : selectedIndices){
            theController.addToDescription("Treasure: ", (int) o);
            theController.addToDescription((String) addTreasure.getValue(), (int) o);
            theController.addToDescription("\n", (int) o);
            box.getItems().clear();
            area.setText((theController.getDescription((int)o)));
            removeTreasure.getItems().add((String) addTreasure.getValue()) ;
        }
    });

    removeTreasureButton.setOnAction((ActionEvent) -> {
        ObservableList selectedIndices = list.getSelectionModel().getSelectedIndices();
        for(Object o : selectedIndices){
            theController.removeFromDescription("Treasure: "+(String) removeTreasure.getValue()+"\n", (int) o);
            box.getItems().clear();
            area.setText((theController.getDescription((int)o)));
            removeTreasure.getItems().remove((String) removeTreasure.getValue());
        }
    });

    hbox2.getChildren().add(addTreasure);
    hbox2.getChildren().add(addTreasureButton);
    hbox4.getChildren().add(removeTreasure);
    hbox4.getChildren().add(removeTreasureButton);

    box2.getChildren().add(hbox2);
    box2.getChildren().add(hbox4);

    temp.setLeft(box1);
    temp.setCenter(box2);
    popup.getContent().addAll(temp);
    return popup;
    }

    private Button createButton(String text, String format) {
        Button btn = new Button();
        btn.setText(text);
        btn.setStyle("");
        return btn;
    }

    private void changeDescriptionText(String text) {
        ObservableList<Node> list = descriptionPane.getContent();
        for (Node t : list) {
            if (t instanceof TextArea) {
                TextArea temp = (TextArea) t;
                temp.clear();
                temp.setText(text);
            }
        }
    }

    private ArrayList<String> setupMonsters(){
        ArrayList<String> monsters = new ArrayList<>();
        monsters.add("Ant, giant");
        monsters.add("Badger");
        monsters.add("Beetle, fire");
        monsters.add("Demon, manes");
        monsters.add("Dwarf");
        monsters.add("Ear Seeker");
        monsters.add("Elf");
        monsters.add("Gnome");
        monsters.add("Goblin");
        monsters.add("Hafling");
        monsters.add("Hobgoblin");
        monsters.add("Human Bandit");
        monsters.add("Kobold");
        monsters.add("Orc");
        monsters.add("Piercer");
        monsters.add("Rat, giant");
        monsters.add("Rot grub");
        monsters.add("Shrieker");
        monsters.add("Skeleton");
        monsters.add("Zombie");
        return monsters;
    }

    private ArrayList<String> setupTreasures(){
        ArrayList<String> treasures = new ArrayList<>();
        treasures.add("1000 copper pieces");
        treasures.add("1000 silver pieces");
        treasures.add("750 electrum pieces");
        treasures.add("250 gold pieces");
        treasures.add("100 platinum pieces");
        treasures.add("1-4 gems");
        treasures.add("1 piece iewelry");
        return treasures;
    }

    private MenuBar setTop(){
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem save = new MenuItem("Save");
        MenuItem load = new MenuItem("Load");

        save.setOnAction((ActionEvent) -> {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Save your level");

            try{
                File file = chooser.showSaveDialog(primaryStage);
                theController.serialize(file.toString());
            }
            catch (NullPointerException n){
                System.out.println("File not Saved");
            }


        });

        load.setOnAction((ActionEvent) -> {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Load your level");

            try{
               File file = chooser.showOpenDialog(primaryStage);
               theController.deserialize(file.toString());
               this.start(primaryStage, this.theController);
            }
            catch (NullPointerException n){
                System.out.println("No file selected");
            }
        });

        fileMenu.getItems().add(save);
        fileMenu.getItems().add(load);

        menuBar.getMenus().add(fileMenu);
        return menuBar;
    }


    public static void main(String[] args) {
        launch(args);
    }

}
