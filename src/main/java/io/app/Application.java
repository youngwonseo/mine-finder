package io.app;

import java.util.Scanner;

public class Application {

    private final int ROWS = 10;
    private final int COLS = 10;
    private final int NUM_OF_MINE = 10;

    private MineFinderGame game;

    public void start() {
        game = new MineFinderGame(ROWS, COLS, NUM_OF_MINE);

        Scanner scanner = new Scanner(System.in);
        GameState gameState = GameState.CONTINUE;

        while(gameState == GameState.CONTINUE){
            game.print();

            String line = scanner.nextLine();
            String[] command = line.split("\\s");

            int x = Integer.parseInt(command[0]);
            int y = Integer.parseInt(command[1]);
            boolean marking = command.length > 2 && "@".equals(command[2]) ? true: false;

            if(marking) {
                game.marking(x, y);
            }else{
                gameState = game.step(x, y);
            }

        }

        game.print();

        String result = null;
        if(gameState == GameState.WIN) {
            result = "Win!";
        }else{
            result = "Lose..";
        }

        System.out.println(result);
    }





    public static void main(String[] args) {
        new Application().start();
    }
}
