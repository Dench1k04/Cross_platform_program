package laba6_1;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class laba6_1 extends Application {
    private double brushSize = 5.0; // Начальный размер кисти

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("House");

        Canvas canvas = new Canvas(400, 400);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        canvas.setOnMouseDragged(e -> handleMouseDragged(gc, e));

        // Добавление слайдера для выбора размера кисти
        Slider brushSizeSlider = new Slider(1, 20, brushSize);
        brushSizeSlider.setTranslateY(100);
        brushSizeSlider.setShowTickMarks(true);
        brushSizeSlider.setShowTickLabels(true);
        brushSizeSlider.setMajorTickUnit(5);
        brushSizeSlider.setMinorTickCount(4);
        brushSizeSlider.setOnMouseClicked(e -> handleSliderClick(brushSizeSlider));

        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if (t.getClickCount() > 2) {
                    reset(canvas, Color.WHITE);
                    drawHouse(gc);
                }
            }
        });

        Label brushSizeLabel = new Label("Brush Size: ");
        brushSizeLabel.setTranslateY(130);
        Label currentBrushSizeLabel = new Label(String.format("%.1f", brushSize));
        currentBrushSizeLabel.setTranslateY(130);
        currentBrushSizeLabel.setTranslateX(50);

        // Добавление элементов интерфейса в макет
        StackPane root = new StackPane();
        root.getChildren().addAll(canvas, brushSizeLabel, brushSizeSlider, currentBrushSizeLabel);

        // Отрисовка дома
        drawHouse(gc);

        primaryStage.setScene(new Scene(root, 400, 450));
        primaryStage.show();
    }

    private void reset(Canvas canvas, Color color) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(color);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }


    private void handleMouseDragged(GraphicsContext gc, MouseEvent event) {
        if (event.isPrimaryButtonDown()) {
            gc.setFill(Color.WHITE);
            gc.fillOval(event.getX() - brushSize / 2, event.getY() - brushSize / 2, brushSize, brushSize);
        }
    }

    private void handleSliderClick(Slider brushSizeSlider) {
        brushSize = brushSizeSlider.getValue();
        brushSizeSlider.setValue(brushSize);
    }

    private void drawHouse(GraphicsContext gc) {
        // Нарисовать основу дома (прямоугольник)
        gc.setFill(Color.BROWN);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.fillRect(150, 150, 100, 100);
        gc.strokeRect(150, 150, 100, 100);

        // Нарисовать крышу (треугольник)
        gc.setFill(Color.RED);
        gc.setStroke(Color.BLACK);
        gc.beginPath();
        gc.moveTo(150, 150);
        gc.lineTo(200, 100);
        gc.lineTo(250, 150);
        gc.closePath();
        gc.fill();
        gc.stroke();

        // Добавить дверь (прямоугольник)
        gc.setFill(Color.WHITE);
        gc.setStroke(Color.BLACK);
        gc.fillRect(180, 200, 40, 50);
        gc.strokeRect(180, 200, 40, 50);

        // Добавить окно (прямоугольник)
        gc.setFill(Color.LIGHTBLUE);
        gc.setStroke(Color.BLACK);
        gc.fillRect(200, 170, 20, 20);
        gc.strokeRect(200, 170, 20, 20);
    }
}
