module com.marm.chessengine {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.marm.gui.FXML;
    opens com.marm.gui.FXML to javafx.fxml;
    exports com.marm.gui.Practice;
    opens com.marm.gui.Practice to javafx.fxml;
}