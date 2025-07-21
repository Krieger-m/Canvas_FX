module com.krieger.canvas_fx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.krieger.canvas_fx to javafx.fxml;
    exports com.krieger.canvas_fx;
}