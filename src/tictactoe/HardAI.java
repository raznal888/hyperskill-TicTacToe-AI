package tictactoe;

class HardAI extends EasyAI {

    int[] move;

    HardAI() {
        super();
        this.level = Level.HARD;
        move = new int[2];
    }

    @Override
    int[] getMoveCoordinates(Match match) {

        //A minimax algoritmus implementációjának forrása:
        // https://github.com/CodingTrain/website/blob/main/CodingChallenges/CC_154_Tic_Tac_Toe_Minimax/P5/minimax.js

        int bestScore = -1000;
        int[] step = new int[2];

        for (int i = 0; i < match.FIELD_HEIGHT; i++) {
            for (int j = 0; j < match.FIELD_LENGTH; j++) {
                if (match.getField()[i][j] == Mark.EMPTY.getChar()) {
                    match.getField()[i][j] = mark.getChar();
                    move[0] = i;
                    move[1] = j;

                    int score = minimax(match, 1, false);

                    match.getField()[i][j] = Mark.EMPTY.getChar();

                    if (score > bestScore) {
                        bestScore = score;
                        step[0] = i;
                        step[1] = j;
                    }
                }
            }
        }

        System.out.printf("Making move level \"%s\"\n", level);

        return step;
    }

    private int minimax(Match match, int depth, boolean isMaximizing) {

        if (match.isWon(move[0], move[1]) == mark.getChar()) {
            return 1;
        } else if (match.isWon(move[0], move[1]) == mark.getOtherMark()) {
            return -1;
        } else if (match.getTurnCounter() + depth == 9) {
            return 0;
        }

        int score;
        int bestScore = isMaximizing ? -1000 : 1000;

        for (int i = 0; i < match.FIELD_HEIGHT; i++) {
            for (int j = 0; j < match.FIELD_LENGTH; j++) {
                if (match.getField()[i][j] == Mark.EMPTY.getChar()) {
                    move[0] = i;
                    move[1] = j;

                    match.getField()[i][j] = isMaximizing ? mark.getChar() : mark.getOtherMark();

                    score = minimax(match, depth + 1, !isMaximizing);

                    match.getField()[i][j] = Mark.EMPTY.getChar();

                    if (isMaximizing && score > bestScore || !isMaximizing && score < bestScore) {
                        bestScore = score;
                    }
                }
            }
        }

        return bestScore;
    }
}