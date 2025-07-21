module com.krieger.mandelbrot_set_fx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.krieger.mandelbrot_set_fx to javafx.fxml;
    exports com.krieger.mandelbrot_set_fx;
}