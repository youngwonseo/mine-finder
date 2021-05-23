package io.app;

public class Block {

    private BlockState blockState = BlockState.CLOSE;

    private char state = '0';
    private boolean marker = false;

    public void toOpen() {
        blockState = BlockState.OPEN;
    }

    public boolean isOpen() {
        return blockState == BlockState.OPEN;
    }

    public void setMine() {
        state = '@';
    }

    public boolean isMine() {
        return state == '@';
    }

    public void incState() {
        state++;
    }

    public boolean isMineAtNear() {
        return state != '@' && state > '0';
    }

    public void convertMarker() {
        marker = !marker;
    }

    @Override
    public String toString() {
        if (blockState == BlockState.OPEN) {
            return String.valueOf(state);
        }

        if (marker) {
            return "v";
        }

        return "*";
    }

}
