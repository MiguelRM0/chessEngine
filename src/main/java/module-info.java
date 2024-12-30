module com.marm.chessengine {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.marm.chessengine to javafx.fxml;
    exports com.marm.chessengine;
}