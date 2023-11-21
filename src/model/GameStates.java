package model;

public enum GameStates {
    X("X"),
    O("O"),
    Nobody(" "),
    Draw("draw");

    private String value;
    private GameStates(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
