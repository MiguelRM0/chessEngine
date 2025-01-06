module com.marm.chessengine {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.marm.gui.FXML;
    opens com.marm.gui.FXML to javafx.fxml;
}