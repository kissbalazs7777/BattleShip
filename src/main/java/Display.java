public class Display {

    //methods
    public void displayBoard(Square[][] board){

        for(int i = -1; i < board.length; i++){
            StringBuilder line = new StringBuilder();
            for(int j = -1; j < board.length; j++){
                if (i == -1){
                    if(j == -1){
                        line.append("   ");
                    }else{
                        line.append(j+1).append("  ");
                    }
                }else{
                    if(j == -1){
                        line.append((char)(i+65)).append("  ");
                    }else{
                        line.append(board[i][j].getGraphic(board[i][j].getSquareStatus())).append("  ");
                    }
                }
            }
            System.out.println(line);
        }
    }


    public void youAreWinner(String value){
        System.out.println("\nDear "+value+", You win the game!\n");
    }

    public void askForInput(){
        System.out.println("Input: (For example: A1)");
    }
}
