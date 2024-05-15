module me.jehn {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;

    opens me.jehn to javafx.fxml;
    exports me.jehn;
}