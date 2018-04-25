/*
 * Name : Yernar Tursyn
 * Email : ernarttrsyn@mail.ru
 * Date and venue : 21.02.2018 / SDU (Suleyman Demirel University)
 * Description : for LabTask about project prototype by using JavaFX
  */


package sample;

// imported of all methods
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import HElp.Help;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ProjectPrototype extends Application { // create a class

    // starting program
    public void start(Stage primaryStage) throws Exception{
        MenuBar bar =new MenuBar();// create a Menubar
        Menu help  = new Menu("Help"); // add a menu
        Menu option  = new Menu("Option"); // add a menu

        // give a items in the menu
        MenuItem rules = new MenuItem("Rules");
        MenuItem myPlayer = new MenuItem("Manual");
        MenuItem comp = new MenuItem("Machine");
        help.getItems().addAll(rules); // added all items for menu
        option.getItems().addAll(myPlayer,comp); // added all items for menu
        bar.getMenus().addAll(help,option); // added all items for menu

        // create a button
        Button start = new Button("   START   " );
        Button restart = new Button("RESTART");
        Button more = new Button(    "  MORE  ");
        Button exit = new Button(    "    EXIT  ");
        start.setStyle(("-fx-background-color: #90EE90;")); // give a color
        restart.setStyle(("-fx-background-color: #90EE90;")); // give a color
        more.setStyle(("-fx-background-color: #90EE90;")); // give a color
        exit.setStyle(("-fx-background-color: #90EE90;")); // give a color
        exit.setOnAction(event -> { // action for exit button
            primaryStage.close();
        });

        // create a labels
        Label welcome = new Label(" WELCOME to Packman Hunting");
        Label timer  = new Label("You have 10 seconds to collect maximum points");
        timer.setTranslateX(20);
        timer.setTranslateY(5);
        timer.setStyle("-fx-text-fill: white");
        timer.setFont(new Font("Times New Roman",18));
        welcome.getStyleClass().add("button-welcome"); // give a style
        welcome.setFont(new Font("Comic Sans MS", 25)); // give a size
      //  welcome.setStyle("-fx-background-color: D2691E;");
        GridPane pane = new GridPane(); // gridpane for menu
        //GridPane grid  = new GridPane(); // gridpane for buttons
        VBox menu = new VBox(); // create VBox and add some buttons and labels

        // add the pane and write coordinates
        pane.add(start,1,2);
        pane.add(restart,1,3);
        pane.add(more,1,4);
        pane.add(exit,1,5);
        //grid.add(bar,0,0);
        //grid.add(pane,0,2);
        //grid.add(welcome,0,1);
  //    grid.setAlignment(Pos.BASELINE_LEFT);
        pane.setAlignment(Pos.CENTER);
        pane.setVgap(20);
        //grid.setVgap(10);
        //grid.setAlignment(Pos.TOP_LEFT);
        //grid.setHgap(10);
        Label space = new Label("          "); // add space label because give the places between label and buttons
        Label space1 = new Label("          "); // add space label because give the places between label and buttons
        pane.setHgap(10);
        menu.getChildren().addAll(bar,space,space1,welcome,pane,timer);

    //    menu.getChildren().addAll(grid,pane);

        //pane.setAlignment(Pos.CENTER);

        primaryStage.setTitle("Yernar"); // a display's title
         Scene scene = new Scene(menu,400,400); // a scene sizes
     //   String []args = {"a","b","c"};
         primaryStage.setScene(scene); // add the scene window
         primaryStage.show(); // show this method
        scene.getStylesheets().add(0, "sample/background.css");
        start.setOnAction(event -> {
            pacman_map run = new pacman_map();
            try {
                run.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // it is a main class
    public static void main(String[] args) {

        launch(args);
    }
}
