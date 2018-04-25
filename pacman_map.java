/*
 * Name : Yernar Tursyn
 * Email : ernarttrsyn@mail.ru
 * Date and venue : 18.04.2018 / SDU (Suleyman Demirel University)
 * Position: Student of 1 course
 * Description : for project by using JavaFX
  */


package sample;


// imported the classes
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

// create the main class
public class pacman_map extends Application {

    // add some methods for code
    public static int curPosX;
    public static int curPosY;

    public static int eaglePosX;
    public static int eaglePosY;

    public static int[][]  map  = {
            {1, 0, 0, 0, 0, 0, 1, 0, 0, 1},
            {0, 1, 0, 3, 0, 3, 1, 0, 0, 0},
            {0, 1, 0, 0, 0, 3, 1, 0, 0, 0},
            {0, 0, 0, 1, 2, 0, 1, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 3, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 1, 0, 4, 0, 0, 0, 1, 0, 1},
            {0, 1, 0, 1, 0, 1, 0, 0, 0, 1},
            {0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 1, 0, 0, 0}
    };

    public static ImageView fox;
    public static ImageView eagle;
    public static Pane pane;

    public static int size;
    public static int unit;


    public static Label eagletext;
    public static Label text;
    public static int score = 0;
    public static int eaglescore = 0;
    static boolean bool=false;

    // starting class
    public void start(Stage primaryStage) throws Exception {
        pane = new Pane(); // use the Pane
        //Eagle eagle = new Eagle();

        Scene scene = new Scene(pane,500,500);

        draw(); // the different class

        if (fox.getBoundsInParent().intersects(eagle.getBoundsInParent())){
            primaryStage.close(); // after the end is closed
        }

        // use the Timeline that is a timer
        Timeline tm=new Timeline(new KeyFrame(Duration.seconds(10),event -> {}));
        tm.play();
        tm.setOnFinished(event -> {

            primaryStage.close();


        });


        // control with keyboard
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()){
                case UP:
                    moveUp(curPosX, curPosY, 2);
                    draw();
                    bool=false;
                    break;
                case RIGHT:
                    moveRight(curPosX, curPosY, 2);
                    draw();
                    bool=false;
                    break;
                case LEFT:
                    moveLeft(curPosX, curPosY, 2);
                    draw();
                    bool=false;
                    break;
                case DOWN:
                    moveDown(curPosX, curPosY, 2);
                    draw();
                    bool=false;
                    break;

                case W:
                    moveUp(eaglePosX, eaglePosY, 4);
                    bool=true;
                    draw();
                    break;
                case D:
                    moveRight(eaglePosX, eaglePosY, 4);
                    draw();
                    bool=true;
                    break;
                case A:
                    moveLeft(eaglePosX, eaglePosY, 4);
                    draw();
                    bool=true;
                    break;
                case S:
                    moveDown(eaglePosX, eaglePosY, 4);
                    draw();
                    bool=true;
                    break;

            }
        });

        // stages and scene
        primaryStage.setScene(scene);
        primaryStage.setTitle("Yernar Tursyn");
        primaryStage.show();
        scene.getStylesheets().add(0, "sample/ynt.css");

    }

    // moveuo class in keyboard
    static void moveUp(int posX, int posY, int n) {
        System.out.println("Move Up");
        System.out.println("Cur X: " + posX);
        System.out.println("Cur Y: " + posY);
        if (posY > 0 && map[posY - 1][posX] != 1) {
            if (map[posY - 1][posX] == 0) {
                map[posY - 1][posX] = n;
                map[posY][posX] = 0;
            } else if (map[posY - 1][posX] == 3) {
                map[posY - 1][posX] = n;
                map[posY][posX] = 0;
                if (!bool) {
                    score++;
                }
                else {
                    eaglescore++;
                }

                text.setText("Fox Score: " + score);
                eagletext.setText("Eagle Score: " + eaglescore);

                generateFood();
            }

            pane.getChildren().clear();
        }


        System.out.println("Cur X: " + posX);
        System.out.println("Cur Y: " + posY);
    }

    // movedown class in keyboard
    static void moveDown(int posX, int posY, int n) {
        System.out.println("Move Down");
        System.out.println("Cur X: " + posX);
        System.out.println("Cur Y: " + posY);
        if (posY < map.length - 1 && map[posY + 1][posX] != 1) {
            if (map[posY + 1][posX] == 0) {
                map[posY + 1][posX] = n;
                map[posY][posX] = 0;
            } else if (map[posY + 1][posX] == 3) {
                map[posY + 1][posX] = n;
                map[posY][posX] = 0;
                if (!bool) {
                    score++;
                }
                else {
                    eaglescore++;
                }

                text.setText("Fox Score: " + score);
                eagletext.setText("Eagle Score: " + eaglescore);


                generateFood();
            }

            pane.getChildren().clear();
        }

        System.out.println("Cur X: " + posX);
        System.out.println("Cur Y: " + posY);
    }

    // moveleft class in keyboard
    static void moveLeft(int posX, int posY, int n) {

        System.out.println("Move Left");
        System.out.println("Cur X: " + posX);
        System.out.println("Cur Y: " + posY);
        if (posX > 0 && map[posY][posX - 1] != 1) {
            if (map[posY][posX - 1] == 0) {
                map[posY][posX - 1] = n;
                map[posY][posX] = 0;
            } else if (map[posY][posX - 1] == 3) {
                map[posY][posX - 1] = n;
                map[posY][posX] = 0;
                if (!bool) {
                    score++;
                }
                else {
                    eaglescore++;
                }

                text.setText("Fox Score: " + score);
                eagletext.setText("Eagle Score: " + eaglescore);

                generateFood();
            }

            pane.getChildren().clear();
        }

        System.out.println("Cur X: " + posX);
        System.out.println("Cur Y: " + posY);
    }

    // moveright class in keyboard
    static void moveRight(int posX, int posY, int n) {
        System.out.println("Move Right");
        System.out.println("Cur X: " + posX);
        System.out.println("Cur Y: " + posY);
        if (posX < map.length - 1 && map[posY][posX + 1] != 1) {
            if (map[posY][posX + 1] == 0) {
                map[posY][posX + 1] = n;
                map[posY][posX] = 0;
            } else if (map[posY][posX + 1] == 3){
                map[posY][posX + 1] = n;
                map[posY][posX] = 0;
                if (!bool) {
                    score++;
                }
                else {
                    eaglescore++;
                }

                text.setText("Fox Score: " + score);
                eagletext.setText("Eagle Score: " + eaglescore);

                generateFood();
            }

            pane.getChildren().clear();
        }

        System.out.println("Cur X: " + posX);
        System.out.println("Cur Y: " + posY);
    }

    // draw class with elements
    static void draw() {
        text = new Label("Fox Score: " + score);
        eagletext = new Label("Eagle Score " + eaglescore);
        eagletext.setTranslateX(330);
        eagletext.setTranslateY(50);
        eagletext.setFont(new Font("Helvetica", 25));
        text.setTranslateX(330);
        text.setTranslateY(0);
        text.setFont(new Font("Helvetica", 25));
        pane.getChildren().addAll(text,eagletext);
  //      pane.setLayoutX(100);
  //      pane.setLayoutY(100);

        size = 10;
        unit = 30;
        for (int i = 0; i < size+1; i++) {
            Line line = new Line(0, i*unit, size*unit, i*unit);
            pane.getChildren().add(line);
        }

        for (int i = 0; i < size+1; i++) {
            Line line = new Line(i*unit, 0, i*unit, size*unit);
            pane.getChildren().add(line);
        }


        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] == 2) {
                    Image image = new Image("sample/fox-311236_640.png"); // put the photo
                    fox = new ImageView(image); // give the image
                    // set the coordinates
                    fox.setFitHeight(30);
                    fox.setFitWidth(30);
                    pane.getChildren().add(fox);

                    fox.setTranslateX(j * unit);
                    fox.setTranslateY(i * unit);

                    curPosX = j;
                    curPosY = i;
                }

                if (map[i][j] == 1) {
                    Rectangle rect1 = new Rectangle();
                    rect1.setX(j * unit);
                    rect1.setY(i * unit);
                    rect1.setHeight(unit);
                    rect1.setWidth(unit);
                    rect1.setFill(Color.BLACK);
                    pane.getChildren().add(rect1);
                }

                if (map[i][j] == 3) {
                    Circle circle = new Circle();
                    circle.setCenterX(j*30+15);
                    circle.setCenterY(i*30+15);
                    circle.setRadius(3);
                    circle.setFill(Color.BLUE);
                    pane.getChildren().add(circle);
                }

                if (map[i][j] == 4) {
                    Image image1 = new Image("sample/65187-200.png"); // put the photo
                    eagle = new ImageView(image1); // give the image
                    // set the coordinates
                    eagle.setFitHeight(30);
                    eagle.setFitWidth(30);
                    pane.getChildren().add(eagle);

                    eagle.setTranslateX(j * unit);
                    eagle.setTranslateY(i * unit);

                    eaglePosX = j;
                    eaglePosY = i;
                }
            }
        }
    }

    // food class
    public static void generateFood() {
        int a = (int) (Math.random() * (size - 1));
        int b = (int) (Math.random() * (size - 1));

        while (map[a][b] != 0) {
            a = (int) (Math.random() * (size - 1));
            b = (int) (Math.random() * (size - 1));
        }

        map[a][b] = 3;
    }


    // main class
    public static void main(String[] args) {

        launch(args);
    }

    class Eagle {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                boolean isit= true;
                while(isit){
                System.out.println("run");
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try{
                        boolean foundWay = false;
                        while (!foundWay) {
                        int a = (int) (Math.random() * (3));

                        switch (a) {
                            case 0:
                                if (eaglePosY > 0 && map[eaglePosY - 1][eaglePosX] != 1) {
                                    if (map[eaglePosY - 1][eaglePosX] == 0) {
                                        map[eaglePosY - 1][eaglePosX] = 4;
                                        map[eaglePosY][eaglePosX] = 0;

                                        System.out.println("Eagle X: " + eaglePosX);
                                        System.out.println("Eagle Y: " + eaglePosY);
                                    } else if (map[eaglePosY - 1][eaglePosX] == 2) {
                                        pane.getChildren().clear();

                                        System.out.println("You loose");

                                        foundWay = true;
                                    }

                                    pane.getChildren().clear();
                                }
                                draw();
                                thread.interrupt();
                                break;
                            case 1:
                                if (eaglePosX < map.length - 1 && map[eaglePosY][eaglePosX + 1] != 1) {
                                    if (map[eaglePosY][eaglePosX + 1] == 0) {
                                        map[eaglePosY][eaglePosX + 1] = 4;
                                        map[eaglePosY][eaglePosX] = 0;

                                        System.out.println("Eagle X: " + eaglePosX);
                                        System.out.println("Eagle Y: " + eaglePosY);
                                    } else if (map[eaglePosY][eaglePosX + 1] == 2) {
                                        pane.getChildren().clear();

                                        System.out.println("You loose");

                                        foundWay = true;
                                    }

                                    pane.getChildren().clear();
                                }
                                draw();
                                Thread.sleep(3000);
                                thread.interrupt();
                                break;
                            case 2:
                                if (eaglePosY < map.length - 1 && map[eaglePosY + 1][eaglePosX] != 1) {
                                    if (map[eaglePosY + 1][eaglePosX] == 0) {
                                        map[eaglePosY + 1][eaglePosX] = 4;
                                        map[eaglePosY][eaglePosX] = 0;

                                        System.out.println("Eagle X: " + eaglePosX);
                                        System.out.println("Eagle Y: " + eaglePosY);
                                    } else if (map[eaglePosY + 1][eaglePosX] == 2) {
                                        pane.getChildren().clear();

                                        System.out.println("You loose");

                                        foundWay = true;
                                    }

                                    pane.getChildren().clear();
                                }
                                draw();
                                Thread.sleep(3000);
                                thread.interrupt();
                                break;
                            case 3:
                                if (eaglePosX > 0 && map[eaglePosY][eaglePosX - 1] != 1) {
                                    if (map[eaglePosY][eaglePosX - 1] == 0) {
                                        map[eaglePosY][eaglePosX - 1] = 4;
                                        map[eaglePosY][eaglePosX] = 0;

                                        System.out.println("Eagle X: " + eaglePosX);
                                        System.out.println("Eagle Y: " + eaglePosY);
                                    } else if (map[eaglePosY][eaglePosX - 1] == 2) {
                                        pane.getChildren().clear();

                                        System.out.println("You loose");

                                        foundWay = true;
                                    }

                                    pane.getChildren().clear();
                                }
                                draw();
                                Thread.sleep(3000);
                                thread.interrupt();
                                break;
                        }

                        try {
                            Thread.sleep(1000);
                            System.out.println("sleep");
                        } catch (InterruptedException ex) {
                            System.out.println(ex);
                        }
                    }
                    }catch (Exception ex){
                            System.out.println(ex.getMessage());
                        }
                    }
                });
                isit = false;
                }
            }

        });
        public Eagle(){
            thread.start();
        }
    }

}
