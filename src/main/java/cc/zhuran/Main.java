package cc.zhuran;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application{

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Canvas canvas = new Canvas(400, 300);
        //draw(canvas.getGraphicsContext2D());
        //root.getChildren().add(canvas);
        stage.setScene(new Scene(root, 800, 400, Color.SNOW));
        stage.show();

        Circle circle = new Circle(30, Color.YELLOWGREEN);
        circle.relocate(30, 30);
        root.getChildren().add(circle);

        animate(circle, root.getBoundsInLocal());
    }

    private void animate(final Circle circle, final Bounds bounds) {
        System.out.println(bounds.getMinX());
        System.out.println(bounds.getMinY());
        System.out.println(bounds.getMaxX());
        System.out.println(bounds.getMaxY());
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20.0), new EventHandler<ActionEvent>() {
            double offsetX = 0.8;
            double offsetY = 0.8;
            double acc = 0.1;

            @Override
            public void handle(ActionEvent actionEvent) {
                if (circle.getLayoutX() + circle.getRadius() >= bounds.getMaxX() || circle.getLayoutX() - circle.getRadius() <= bounds.getMinX()){
                    offsetX = -offsetX;
                }
                if (circle.getLayoutY() + circle.getRadius() >= bounds.getMaxY() || circle.getLayoutY() - circle.getRadius() <= bounds.getMinY()){
                    offsetY = -offsetY * 0.9;
                }
                else{
                    offsetY += acc;
                }
                circle.setLayoutX(circle.getLayoutX() + offsetX);
                circle.setLayoutY(circle.getLayoutY() + offsetY);
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void draw(GraphicsContext gc) {
        drawBall(gc, 0, 0);
    }

    private void drawBall(GraphicsContext gc, int x, int y) {
        gc.setFill(Color.YELLOWGREEN);
        gc.fillOval(x, y, 30, 30);

    }
}
