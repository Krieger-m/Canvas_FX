package com.krieger.canvas_fx;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {

    private static final double MAX_ATTRACTION_DISTANCE = 300;
    private static final double MIN_ATTRACTION_DISTANCE = 0.1;
    private static final double ATTRACTION_FORCE = 2500;
    

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
            mouseX = e.getX();
            mouseY = e.getY();

        });


        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public Parent createContent() {

        for (int y = 0; y < height /10; y++) {

            for (int x = 0; x < width /10; x++) {

                particles.add(new Particle(x* 10 , y* 10, Color.BLUEVIOLET));
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

        double originalX;
        double originalY;



        public Particle(double x, double y, Color color) {
            this.x = x;
            this.y = y;
            this.color = color;

            originalX = x;
            originalY = y;
        }

        void update (Point2D cursorDistance) {
            double distance = cursorDistance.distance(x,y);

            if(distance > MAX_ATTRACTION_DISTANCE){
                x= originalX;
                y = originalY;
            } else if(distance < MIN_ATTRACTION_DISTANCE){
                x = cursorDistance.getX()/2;
                y = cursorDistance.getY()/2;

            } else {

                Point2D vector = cursorDistance.subtract(x, y);
                double scaledLength = ATTRACTION_FORCE * 1 / distance;
                vector = vector.normalize().multiply(scaledLength);

                x = originalX + vector.getX()/10;
                y = originalY + vector.getY()/10;

                // c * 1 / d
                // * ----> x
                // * -->   x

            }


        }
    }


    public static void main(String[] args) {
        launch();
    }
}