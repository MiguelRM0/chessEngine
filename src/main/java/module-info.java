module com.marm.chessengine {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.marm.chessengine to javafx.fxml;
//    exports com.marm.chessengine;
    exports com.marm.chessengine.board;
    opens com.marm.chessengine.board to javafx.fxml;
    exports com.marm.chessengine.pieces;
    opens com.marm.chessengine.pieces to javafx.fxml;
    exports com.marm.chessengine.FXML;
    opens com.marm.chessengine.FXML to javafx.fxml;
    exports com.marm.chessengine;
    exports com.marm.gui;
    opens com.marm.gui to javafx.fxml;
    exports com.marm.gui.FXML;
    opens com.marm.gui.FXML to javafx.fxml;
}