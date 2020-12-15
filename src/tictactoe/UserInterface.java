package tictactoe;

class UserInterface {
    private Match current;

    UserInterface() {
        this.current = null;
    }

    void start() {

        boolean gameRunning = true;

        while (gameRunning) {
            String command = enterCommand();

            if (command.equals("exit")) {
                gameRunning = false;
                return;
            }

            String[] commandParts = command.split(" ");
            String firstPlayer = commandParts[1];
            String secondPlayer = commandParts[2];

            current = new Match(Player.of(firstPlayer), Player.of(secondPlayer));

            current.play();
            System.out.println();
        }
    }

    private String enterCommand() {
        String command = null;

        boolean commandNotValid = true;
        while (commandNotValid) {
            System.out.print("Input command: ");
            Main.scanner.useDelimiter(System.lineSeparator());
            command = Main.scanner.next();

            if (!command.matches("(start\\s(easy|user|medium|hard)\\s(easy|user|medium|hard))|exit")) {
                System.out.println("Bad parameters!");
                continue;
            }
            commandNotValid = false;
        }

        return command;
    }
}
