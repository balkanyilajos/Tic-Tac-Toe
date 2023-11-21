package model;

import java.util.Arrays;

public class Model {
    private int row;
    private int col;
    private GameStates[][] table;
    private int emptyCells;
    private GameStates actualPlayer = GameStates.X;
    public final int WINLIMIT = 4;
    
    
    public Model(int row, int col) {
        this.row = row;
        this.col = col;
        emptyCells = row * col;
        table = new GameStates[row][col];
        initializeMatrix();
    }
    
    private void initializeMatrix() {
        for(GameStates[] array : table) {
            Arrays.fill(array, GameStates.Nobody);
        }
    }
    
    public boolean isInsertable(int inCol) {
        return inCol < col && table[row - 1][inCol] == GameStates.Nobody;
    }
    
    public int insertIntoTable(int inCol, GameStates player) {
        if(isInsertable(inCol)) {
            int i = 0;
            while( table[i][inCol] != GameStates.Nobody ) {
                i += 1;
            }
            table[i][inCol] = player;
            emptyCells -= 1;
            actualPlayer = (actualPlayer == GameStates.X) ? GameStates.O : GameStates.X;
            return i;
        }
        throw new IllegalArgumentException();
    }

    public GameStates getActualPlayer() {
        return actualPlayer;
    }

    public GameStates getWinner(int inRow, int inCol) {
    GameStates player = table[inRow][inCol];
    if(emptyCells == 0) {
        return GameStates.Draw;
    }
    
    // horizontally
    {
        int count = 1;
        for(int i = inCol + 1; i < col && table[inRow][i] == player; i++) {
            count += 1;
        }
        for(int i = inCol - 1; i >= 0 && table[inRow][i] == player; i--) {
            count += 1;
        }
        if(count >= WINLIMIT) {
            return player;
        }
    }

    // diagonally up
    {
        int count = 1;
        for(int i = inRow + 1, j = inCol + 1; i < row && j < col && table[i][j] == player; i++, j++) {
            count += 1;
        }
        for(int i = inRow - 1, j = inCol - 1; i >= 0 && j >= 0 && table[i][j] == player; i--, j--) {
            count += 1;
        }
        if(count >= WINLIMIT) {
            return player;
        }
    }

    // diagonally down
    {
        int count = 1;
        for(int i = inRow - 1, j = inCol + 1; i >= 0 && j < col && table[i][j] == player; i--, j++) {
            count += 1;
        }
        for(int i = inRow + 1, j = inCol - 1; i < row && j >= 0 && table[i][j] == player; i++, j--) {
            count += 1;
        }
        if(count >= WINLIMIT) {
            return player;
        }
    }
    return GameStates.Nobody;
} 

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = row - 1; i >= 0; i--) {
            String separator = "";
            for(int j = 0; j < col; j++) {
                sb.append(separator).append(table[i][j].getValue());
                separator = " ";
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    
}
