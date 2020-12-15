package tictactoe;

import java.util.Random;

class EasyAI extends Player {
    Level level;

    EasyAI() {
        this.level = Level.EASY;
    }

    @Override
    int[] getMoveCoordinates(Match match) {

        System.out.printf("Making move level \"%s\"\n", level);

        return getRandomCoordinates(match);
    }

    int[] getRandomCoordinates(Match match) {
        Random random = new Random();

        int[] coordinates = new int[2];

        boolean moveNotValid = true;
        while (moveNotValid) {
            int selectedRow = random.nextInt(match.FIELD_HEIGHT) + 1;
            int selectedColumn = random.nextInt(match.FIELD_LENGTH) + 1;

            if (match.isCellOccupied(selectedRow, selectedColumn)) {
                continue;
            }

            coordinates[0] = selectedRow - 1;
            coordinates[1] = selectedColumn - 1;

            moveNotValid = false;
        }

        return coordinates;

    }
}
