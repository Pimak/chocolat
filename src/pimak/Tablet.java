package pimak;

import java.util.Arrays;

public class Tablet {

    public static final int HORIZONTAL_SIZE = 4;
    public static final int VERTICAL_SIZE = 6;

    private final boolean[][] squares;
    private long ways;

    public Tablet(){
        this.squares = new boolean[HORIZONTAL_SIZE+1][VERTICAL_SIZE+1];
        for (int column = 0; column<=HORIZONTAL_SIZE; column++){
            for (int row = 0; row<=VERTICAL_SIZE; row++){
                this.squares[column][row] = false;
            }

        }
        this.squares[1][1] = true;
        this.ways = 1;
    }

    public Tablet(boolean[][] squares, long ways){
        this.squares = squares;
        this.ways = ways;
    }



    public boolean isHorizontallyFull(){
        for (int l = 1 ; l<=VERTICAL_SIZE; l++){
            if (this.squares[HORIZONTAL_SIZE][l]){
                return true;
            }
        }
        return false;
    }
    public boolean isVerticallyFull(){
        for (int c = 1 ; c<=HORIZONTAL_SIZE; c++){
            if (this.squares[c][VERTICAL_SIZE]){
                return true;
            }
        }
        return false;
    }

    public boolean isValid(int column, int row){
        if (row==0 && this.isVerticallyFull()){
            return false;
        }
        if (column ==0 && this.isHorizontallyFull()){
            return false;
        }
        if (this.squares[column][row]){
            return false;
        }
        if (column<HORIZONTAL_SIZE && this.squares[column+1][row]){
            return true;
        }
        if (row<VERTICAL_SIZE && this.squares[column][row+1]){
            return true;
        }
        if (row>0 && this.squares[column][row-1]){
            return true;
        }
        if(column>0 && this.squares[column-1][row]){
            return true;
        }
        return false;
    }

    public void addWays(long ways){
        this.ways+=ways;
    }
    public long getWays(){
        return this.ways;
    }
    public boolean[][] getSquares(){
        boolean[][] result = new boolean[HORIZONTAL_SIZE+1][VERTICAL_SIZE+1];
        for (int i = 0; i<=HORIZONTAL_SIZE;i++){
            System.arraycopy(this.squares[i], 0, result[i], 0, VERTICAL_SIZE + 1);
        }
        return result;
    }

    public boolean isEqual(boolean[][] tableau){
        for (int i = 0;i<=HORIZONTAL_SIZE; i++){
            if (!Arrays.equals(tableau[i], this.squares[i])){
                return false;
            }
        }
        return true;
    }

    public boolean[][] addSquare(int column, int row){
        boolean[][] result = this.getSquares();
        result[column][row] = true;
        if (column==0){
            for (int i=HORIZONTAL_SIZE;i>0;i--){
                System.arraycopy(result[i - 1], 0, result[i], 0, VERTICAL_SIZE + 1);
            }
            result[column][row]=false;
        }
        if (row==0){
            for (int i=0; i<=HORIZONTAL_SIZE;i++){
                System.arraycopy(result[i], 0, result[i], 1, VERTICAL_SIZE);
            }
            result[column][row]=false;
        }
        return result;
    }

    @Override
    public String toString() {
        String res = "Tablet{" +
        "squares=\n" ;
        for (boolean[] bools : this.squares){
            res+= Arrays.toString(bools);
            res+="\n";
        }

        res += ", ways=" + ways +
        '}';
        return res;
    }
    public String getHash(){
        StringBuilder hash = new StringBuilder();
        for (int i = 1; i <= HORIZONTAL_SIZE; i++) {
            for (int j = 1; j <= VERTICAL_SIZE; j++) {
                hash.append(this.squares[i][j] ? 1 : 0);
            }
        }
        return hash.toString();
    }
}
