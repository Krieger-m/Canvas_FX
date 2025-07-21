package com.krieger.mandelbrot_set_fx;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {

    private double mouseX;
    private double mouseY;

    double height = 720;
    double width = 1080;

    GraphicsContext g;

    private List<Particle> particles = new ArrayList<>();


    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());

        scene.setOnMouseMoved(e ->{

        });


        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public Parent createContent() {

        for (int y = 0; y < 720 /10; y++) {

            for (int x = 0; x < 1080 /10; x++) {

                particles.add(new Particle(x * 10, y *10, Color.BLUEVIOLET));
            }
            
        }
        
        Canvas canvas = new Canvas(width,height);
        g = canvas.getGraphicsContext2D();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                onUpdate();
            }
        };
        timer.start();
        VBox pane = new VBox(canvas);
        return pane;

    }

    private void onUpdate( ) {
        g.clearRect(0,0,1080,720);

        Point2D cursorPos= new Point2D(mouseX,mouseY);

        particles.forEach(p ->{ //     hier weiter
            p.update(cursorPos);        //x<-  https://youtu.be/db8h1gDgt6M?t=714

            g.setFill(p.color);
            g.fillOval(p.x-1,p.y-1,5,5);
        });
    }

    private static class Particle{
        double x;
        double y;

        Color color;

        public Particle(double x, double y, Color color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }

        void update (Point2D cursorDistance) {

        }
    }

    public static void main(String[] args) {
        launch();
    }
}