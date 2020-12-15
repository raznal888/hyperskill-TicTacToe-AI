package tictactoe;

abstract class Player {
    Mark mark;

    static Player of(String type) {
        switch(type) {
            case "user":
                return new User();
            case "easy":
                return new EasyAI();
            case "medium":
                return new MediumAI();
            case "hard":
                return new HardAI();
            default:
                return null;
        }
    }

    abstract int[] getMoveCoordinates(Match match);
}
