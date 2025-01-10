package com.marm.chessengine;

import com.marm.chessengine.board.Board;
import com.marm.chessengine.board.BoardDirection;
import com.marm.chessengine.player.BlackPlayer;
import com.marm.chessengine.player.Player;
import com.marm.chessengine.player.WhitePlayer;

public enum Alliance {
    WHITE {
        @Override
        public int getDirection(BoardDirection boardDirection) {
            return -1 * boardDirection.boardDirectionMultiplier();
        }

        @Override
        public boolean isWhite() {
            return true;
        }

        @Override
        public boolean isBlack() {
            return false;
        }

        @Override
        public Player choosePlayer(final WhitePlayer whitePlayer,
                                   final BlackPlayer blackPlayer) {
            return whitePlayer;
        }


        @Override
        public String toString(){
            return"W";
        }
    },
    BLACK {
        @Override
        public String toString() {
            return "B";
        }

        @Override
        public int getDirection(BoardDirection boardDirection) {
            return boardDirection.boardDirectionMultiplier();
        }


        @Override
        public boolean isWhite() {
            return false;
        }

        @Override
        public boolean isBlack() {
            return true;
        }

        @Override
        public Player choosePlayer(final WhitePlayer whitePlayer,
                                   final BlackPlayer blackPlayer) {
            return blackPlayer;
        }


    };

    public abstract String toString();

    public abstract int getDirection(BoardDirection boardDirection);

    public abstract boolean isWhite();

    public abstract boolean isBlack();

    public abstract Player choosePlayer(WhitePlayer whitePlayer, BlackPlayer blackPlayer);
}
