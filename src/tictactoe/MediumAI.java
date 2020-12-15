package tictactoe;

class MediumAI extends EasyAI {

    MediumAI() {
        this.level = Level.MEDIUM;
    }

    @Override
    int[] getMoveCoordinates(Match match) {

        System.out.printf("Making move level \"%s\"\n", level);

        return checkForMove(match);
    }

    private int[] checkForMove(Match match) {
        if (match.getTurnCounter() >= 3) {
            int[][] moves = new int[2][2];
            moves[0] = canWinInOneMove(match, match.getPlayerMoves()[match.getCurrentPlayer().mark.ordinal()]);
            moves[1] = canWinInOneMove(match, match.getPlayerMoves()[1 - match.getCurrentPlayer().mark.ordinal()]);

            if (moves[0] != null) {
                return moves[0];
            } else if (moves[1] != null) {
                return moves[1];
            }
        }

        return getRandomCoordinates(match);
    }

    private int[] canWinInOneMove(Match match, int[] move) {

        int[] coordinates = new int[]{-1, -1};

        char currentMove = match.getField()[move[0]][move[1]];
        for (int i = 0; i < match.FIELD_HEIGHT; i++) {
            for (int j = 0; j < match.FIELD_LENGTH; j++) {

                if (currentMove == match.getField()[move[0]][i] && i != move[1]) {
                    coordinates[0] = move[0];
                    coordinates[1] = (2 - move[1]) + (2 - i) - 1;

                    if (!match.isCellOccupied(coordinates[0] + 1, coordinates[1] + 1)) {
                        return coordinates;
                    }
                }

                if (currentMove == match.getField()[i][move[1]] && i != move[0]) {
                    coordinates[0] = (2 - move[0]) + (2 - i) - 1;
                    coordinates[1] = move[1];

                    if (!match.isCellOccupied(coordinates[0] + 1, coordinates[1] + 1)) {
                        return coordinates;
                    }
                }

                if (i != move[0] && j != move[1] && currentMove == match.getField()[i][j]) {

                    if (move[0] == move[1] && i == j) {
                        coordinates[0] = (2 - move[0]) + (2 - i) - 1;
                        coordinates[1] = coordinates[0];

                        if (!match.isCellOccupied(coordinates[0] + 1, coordinates[1] + 1)) {
                            return coordinates;
                        }
                    }

                    if (move[0] + move[1] == 2 && i + j == 2 && currentMove == match.getField()[i][j]) {
                        coordinates[0] = (2 - move[0]) + (2 - i) - 1;
                        coordinates[1] = 2 - coordinates[0];

                        if (!match.isCellOccupied(coordinates[0] + 1, coordinates[1] + 1)) {
                            return coordinates;
                        }
                    }
                }
            }
        }

        return null;
    }
}
