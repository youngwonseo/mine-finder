package io.app;

public class MineFinderGame {

    private final int WIDTH;
    private final int HEIGHT;
    private final int NUM_OF_MINE;
    private final Block[][] board;



    public MineFinderGame(int WIDTH, int HEIGHT, int NUM_OF_MINE) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.NUM_OF_MINE = NUM_OF_MINE;

        board = new Block[HEIGHT][WIDTH];

        for (int h = 0; h < HEIGHT; h++) {
            for (int w = 0; w < WIDTH; w++) {
                board[h][w] = new Block();
            }
        }


        for (int i = 0; i < NUM_OF_MINE; i++) {
            int x = (int) (Math.random() * WIDTH),
                    y = (int) (Math.random() * HEIGHT);

            if (board[y][x].isMine()) {
                i--;
                continue;
            }

            board[y][x].setMine();

            // 주위 칸들 카운트
            for (int h = -1; h <= 1; h++) {
                for (int w = -1; w <= 1; w++) {
                    if (h == 0 && w == 0) {
                        continue;
                    }

                    if (isValidPos(x + w, y + h) && !board[y + h][x + w].isMine()) {
                        board[y + h][x + w].incState();
                    }
                }
            }
        }
    }


    public GameState step(int x, int y){
        if(isMine(x, y)){
            return GameState.LOSE;
        }

        open(x, y);

        if(isGameWin()){
            return GameState.WIN;
        }

        return GameState.CONTINUE;
    }


    public void marking(int x, int y) {
        if (board[y][x].isOpen()) return;

        board[y][x].convertMarker();
    }


    private boolean isMine(int x, int y){
        if (!isValidPos(x, y)) return false;

        return board[y][x].isMine();
    }

    private void open(int x, int y) {
        if (!isValidPos(x, y)) return;

        if (board[y][x].isOpen()) return;

        board[y][x].toOpen();

        if(board[y][x].isMineAtNear()) return;

        for (int h = -1; h <= 1; h++) {
            for (int w = -1; w <= 1; w++) {
                if (h == 0 && w == 0) continue;
                open(x + w, y + h);
            }
        }
    }



    private boolean isGameWin() {
        for (int h = 0; h < HEIGHT; h++) {
            for (int w = 0; w < WIDTH; w++) {
                if (!board[h][w].isMine() && !board[h][w].isOpen()) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValidPos(int x, int y){
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) return false;

        return true;
    }

    public void print() {
        for (int h = 0; h < HEIGHT; h++) {
            System.out.println("");
            for (int w = 0; w < WIDTH; w++) {
                System.out.print(" " + board[h][w] + " ");
            }
        }
        System.out.println("");
    }
}
