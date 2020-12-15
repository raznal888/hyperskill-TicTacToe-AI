package tictactoe;

import java.util.Arrays;

class Match {
    final int FIELD_HEIGHT = 3;
    final int FIELD_LENGTH = 3;
    private char[][] field;
    private Player[] players;
    private Player currentPlayer;
    private int[][] playerMoves;
    private int turnCounter;

    Match(Player firstPlayer, Player secondPlayer) {
        field = new char[FIELD_HEIGHT][FIELD_LENGTH];
        for (char[] row: field) {
            Arrays.fill(row, Mark.EMPTY.getChar());
        }

        this.players = new Player[]{firstPlayer, secondPlayer};
        this.currentPlayer = firstPlayer;
        this.turnCounter = 0;
        this.playerMoves = new int[2][2];

        players[0].mark = Mark.FIRST;
        players[1].mark = Mark.SECOND;
    }

    char[][] getField() {
        return field;
    }

    void setField(int[] move) {
        field[move[0]][move[1]] = currentPlayer.mark.getChar();
    }

    int[][] getPlayerMoves() {
        return playerMoves;
    }

    int getTurnCounter() {
        return turnCounter;
    }

    Player getCurrentPlayer() {
        return currentPlayer;
    }

    private void switchCurrentPlayer() {
        if (currentPlayer == players[0]) {
            currentPlayer = players[1];
        } else {
            currentPlayer = players[0];
        }
    }

    boolean isCellOccupied(int selectedRow, int selectedColumn) {
        return field[selectedRow - 1][selectedColumn - 1] != Mark.EMPTY.getChar();
    }

    void play() {

        printField();

        boolean gameNotFinished = true;
        while (gameNotFinished) {

            playerMoves[currentPlayer.mark.ordinal()] = currentPlayer.getMoveCoordinates(this);

            int[] currentMove = playerMoves[currentPlayer.mark.ordinal()];

            setField(currentMove);

            printField();

            if (isWon(currentMove[0], currentMove[1]) != Mark.EMPTY.getChar()) {
                break;
            }

            turnCounter++;

            if (turnCounter == 9) {
                gameNotFinished = false;
                break;
            }

            switchCurrentPlayer();
        }

        printResult(gameNotFinished);

    }

    private void printField() {
        System.out.println("---------");
        for (int row = 0; row < FIELD_HEIGHT; row++) {

            System.out.print("| ");
            for (int column = 0; column < FIELD_LENGTH; column++) {
                System.out.print(field[row][column] + " ");
            }
            System.out.println("|");

        }
        System.out.println("---------");
    }

    private void printResult(boolean gameNotFinished) {
        if (gameNotFinished) {
            System.out.println(currentPlayer.mark.getChar() + " wins");
        } else {
            System.out.println("Draw");
        }
    }

    char isWon(int lastMoveRow, int lastMoveColumn) {

        boolean wins = checkRow(lastMoveRow) || checkColumn(lastMoveColumn)
                    || checkMainDiagonal() || checkSideDiagonal();
        if (wins) {
            return field[lastMoveRow][lastMoveColumn];
        }

        return Mark.EMPTY.getChar();
    }

    private boolean checkRow(int lastMoveRow) {
        return field[lastMoveRow][2] == field[lastMoveRow][1] && field[lastMoveRow][1] == field[lastMoveRow][0];
    }

    private boolean checkColumn(int lastMoveColumn) {
        return field[2][lastMoveColumn] == field[1][lastMoveColumn] && field[1][lastMoveColumn] == field[0][lastMoveColumn];
    }

    private boolean checkMainDiagonal() {
        return field[0][0] == field[1][1] && field[1][1] == field[2][2] && field[0][0] != Mark.EMPTY.getChar();
    }

    private boolean checkSideDiagonal() {
        return field[0][2] == field[1][1] && field[1][1] == field[2][0] && field[0][2] != Mark.EMPTY.getChar();
    }


}
