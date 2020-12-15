package tictactoe;

enum Level {
    EASY("easy"), MEDIUM("medium"), HARD("hard")
    ;

    String name;

    private Level(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
