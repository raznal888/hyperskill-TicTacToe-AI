package tictactoe;

class User extends Player {

    @Override
    int[] getMoveCoordinates(Match match) {
        return enterCoordinates(match);
    }

    private int[] enterCoordinates(Match match) {
        int[] coordinates = new int[2];

        boolean moveNotValid = true;
        while (moveNotValid) {
            System.out.print("Enter the coordinates: ");
            Main.scanner.useDelimiter(System.lineSeparator());
            String input = Main.scanner.next();

            if(!input.matches("\\d\\s\\d")) {
                System.out.println("You should enter numbers!");
                continue;
            }

            String[] inputParts = input.split(" ");
            int selectedRow = Integer.valueOf(inputParts[0]);
            int selectedColumn = Integer.valueOf(inputParts[1]);

            if (!(selectedRow >= 1 && selectedRow <= 3) || !(selectedColumn >= 1 && selectedColumn <= 3)) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            if (match.isCellOccupied(selectedRow, selectedColumn)) {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }

            coordinates[0] = selectedRow - 1;
            coordinates[1] = selectedColumn - 1;

            moveNotValid = false;
        }

        return coordinates;
    }
}
