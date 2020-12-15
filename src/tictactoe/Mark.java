package tictactoe;

enum Mark {
    FIRST('X'), SECOND('O'), EMPTY('_');

    private char mark;

    private Mark(char symbol) {
        this.mark = symbol;
    }

    public char getChar() {
        return mark;
    }

    public char getOtherMark() {

        if (this == Mark.SECOND) {
            return Mark.FIRST.getChar();
        } else {
            return Mark.SECOND.getChar();
        }

    }
}
