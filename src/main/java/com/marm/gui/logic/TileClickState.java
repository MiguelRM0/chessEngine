package com.marm.gui.logic;

public enum TileClickState {
    NOT_ON_SOURCE_TILE {
        @Override
        public boolean isNotOnSourceTile() {
            return true;
        }

        @Override
        public boolean isOnSourceTile() {
            return false;
        }
    },
    ON_SOURCE_TILE {
        @Override
        public boolean isNotOnSourceTile() {
            return false;
        }

        @Override
        public boolean isOnSourceTile() {
            return true;
        }
    };

    public abstract boolean isNotOnSourceTile();

    public abstract boolean isOnSourceTile();

}
