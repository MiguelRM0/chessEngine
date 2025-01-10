package com.marm.chessengine.board;

public enum BoardDirection {
    NORMAL {
        @Override
        public boolean isNormal() {
            return true;
        }

        @Override
        public boolean isRotated() {
            return false;
        }

        @Override
        public int boardDirectionMultiplier() {
            return 1;
        }

        @Override
        public BoardDirection chooseOrientation() {
            return NORMAL;
        }

        @Override
        public BoardDirection getOpposite() {
            return ROTATED;
        }


    },
    ROTATED {
        @Override
        public boolean isNormal() {
            return false;
        }

        @Override
        public boolean isRotated() {
            return true;
        }

        @Override
        public int boardDirectionMultiplier() {
            return -1;
        }

        @Override
        public BoardDirection chooseOrientation() {
            return ROTATED;
        }

        @Override
        public BoardDirection getOpposite() {
            return NORMAL;
        }

    };

    public abstract boolean isNormal();
    public abstract boolean isRotated();

    public abstract int boardDirectionMultiplier();

    public abstract BoardDirection chooseOrientation();
    public abstract BoardDirection getOpposite();
}
