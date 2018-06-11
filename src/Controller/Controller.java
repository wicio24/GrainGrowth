package Controller;

import Model.DrawerTask;
import Model.Game;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.Random;

public class Controller {
    private GraphicsContext gc;

    private int pixelSize = 4;
    private int speed = 100;
    private int tabSizeHeight;
    private int tabSizeWidth;
    private int startingPoints;
    private Game game;
    private Random generator;
    private DrawerTask task;
    private boolean startLoopFlag;


    @FXML
    private Canvas canvas;

    @FXML
    private Button startButton;

    @FXML
    private Button startLoopButton;

    @FXML
    private Button stopLoopButton;

    @FXML
    private Button nextStepButton;

    @FXML
    private TextField textField;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private ComboBox<String> comboBox2;

    @FXML
    private ComboBox<String> comboBox3;


    @FXML
    private TextField textFieldDistance;

    @FXML
    private Button monteCarloButton;


    @FXML
    private void initialize() {
        comboBox.getItems().addAll("von Neumanna", "Moore’a", "leftPentagonal", "rightPentagonal", "upPentagonal",
                "downPentagonal", "randomPentagonal", "leftHexagonal", "rightHexagonal", "randomHexagonal");
        comboBox.setValue("von Neumanna");
        comboBox2.getItems().addAll("Random", "Evenly placement", "Placement with radius");
        comboBox2.setValue("Random");

        comboBox3.getItems().addAll("closed", "Periodic");
        comboBox3.setValue("closed");

        gc = canvas.getGraphicsContext2D();

        tabSizeWidth = (int) gc.getCanvas().getWidth() / pixelSize;
        tabSizeHeight = (int) gc.getCanvas().getHeight() / pixelSize;
        startingPoints = Integer.parseInt(this.textField.getText());
        game = new Game(tabSizeWidth, tabSizeHeight);
        generator = new Random();
        startLoopFlag = false;
    }


    @FXML
    private void handleMouseClick(MouseEvent mouseEvent) {
        int x = (int) mouseEvent.getX();
        int y = (int) mouseEvent.getY();

        x = (x / pixelSize) * pixelSize;
        y = (y / pixelSize) * pixelSize;

        int gridX = x / pixelSize;
        int gridY = y / pixelSize;

        if (gridX > tabSizeHeight - 1)
            gridX = tabSizeWidth - 1;

        if (gridX < 0)
            gridX = 0;


        if (gridY > tabSizeHeight - 1)
            gridY = tabSizeHeight - 1;

        if (gridY < 0)
            gridY = 0;

        int xFinal = gridX;
        int yFinal = gridY;

        Platform.runLater(() -> {
            if (game.getTab1()[yFinal][xFinal].getState() == 0) {
                game.setTab1Cell(xFinal, yFinal, 1);
                game.getTab1()[yFinal][xFinal].setColor(Color.rgb(generator.nextInt(255), generator.nextInt(255), generator.nextInt(255)));

                gc.setFill(game.getTab1()[yFinal][xFinal].getColor());
                gc.fillRect(xFinal * pixelSize, yFinal * pixelSize, pixelSize, pixelSize);

            }
        });
    }


    @FXML
    private void handleStart() {
        if (startLoopFlag) startLoopButton.setDisable(true);
        else startLoopButton.setDisable(false);

        nextStepButton.setDisable(false);


        randomNewPoints();

    }

    @FXML
    private void handleStartLoop() {
        stopLoopButton.setDisable(false);
        startLoopButton.setDisable(true);
        startLoopFlag = true;
        task = new DrawerTask(this);
        task.setStopFlag(false);
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

    @FXML
    private void handleStopLoop() {
        stopLoopButton.setDisable(true);
        startLoopButton.setDisable(false);
        startLoopFlag = false;
        task.setStopFlag(true);
    }

    @FXML
    private void handleNextStep() {
        drawCanvas();
    }


    @FXML
    private void handleMonteCarlo()
    {
        game.monteCarlo(startingPoints);
        printCanvas();
    }


    public void randomNewPoints() {

        clearCanvas();
        startingPoints = Integer.parseInt(textField.getText());

        if (comboBox3.getValue() == "closed") game.setPeriodic(false);
        else game.setPeriodic(true);


        if (comboBox2.getValue() == "Evenly placement") {
            textFieldDistance.setDisable(false);
            game.fillEvenlyPlacement(startingPoints, Integer.parseInt(this.textFieldDistance.getText()) / pixelSize);
        } else if (comboBox2.getValue() == "Placement with radius") game.fillRandomlyWithRadius(startingPoints,Integer.parseInt(this.textFieldDistance.getText()) / pixelSize) ;
        else game.fillRandomly(startingPoints);


        printCanvas();

    }

    public void clearCanvas() {
        game.clearGrid();
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight()); // wyczysc plansze
    }

    public void drawCanvas() {


        Platform.runLater(() -> {
            if (comboBox.getValue() == "von Neumanna") game.vonNeumannNeighborhood();
            else if (comboBox.getValue() == "Moore’a") game.mooreNeighborhood();
            else if (comboBox.getValue() == "leftPentagonal") game.pentagonal(Game.NeighborhoodType.leftPentagonal);
            else if (comboBox.getValue() == "rightPentagonal") game.pentagonal(Game.NeighborhoodType.rightPentagonal);
            else if (comboBox.getValue() == "upPentagonal") game.pentagonal(Game.NeighborhoodType.upPentagonal);
            else if (comboBox.getValue() == "downPentagonal") game.pentagonal(Game.NeighborhoodType.downPentagonal);
            else if (comboBox.getValue() == "randomPentagonal") game.pentagonal(Game.NeighborhoodType.randomPentagonal);

            else if (comboBox.getValue() == "leftHexagonal") game.hexagonal(Game.NeighborhoodType.leftHexagonal);
            else if (comboBox.getValue() == "rightHexagonal") game.hexagonal(Game.NeighborhoodType.rightHexagonal);
            else if (comboBox.getValue() == "randomHexagonal") game.hexagonal(Game.NeighborhoodType.randomHexagonal);

            printCanvas();


        });


    }

    public void printCanvas() {
        for (int i = 0; i < tabSizeHeight; i++) {
            for (int j = 0; j < tabSizeWidth; j++) {
                if (game.getTab1()[i][j].getState() == 1) {
                    gc.setFill(game.getTab1()[i][j].getColor());
                    gc.fillRect(j * pixelSize, i * pixelSize, pixelSize, pixelSize);
                }

            }
        }
    }

    public int getSpeed() {
        return speed;
    }
}
