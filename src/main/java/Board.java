public class Board {

    //properties
    private final Square[][] ocean;

    //getter-setter
    public Square[][] getOcean() {
        return ocean;
    }

    //methods
    public boolean isPlacementOk(Square[][] board, String direction ,int x, int y, int length){
        for(int i = 0; i < length; i++){
            if(direction.equals("H")){
                try{
                    if (board[y][x + i].getGraphic(board[y][x + i].getSquareStatus()).equals("X")){
                        System.out.println("A ship is already there!");//printek a display dolga lesz
                        return false;
                    }
                }catch(Exception e){
                    System.out.println("Ship would be out of board!");
                    return false;
                }
                try{
                    if(board[y + 1][x + i].getGraphic(board[y + 1][x + i].getSquareStatus()).equals("X")){
                        System.out.println("Ship too close!");
                        return false;
                    }
                }catch (Exception ignored){

                }
                try{
                    if (board[y - 1][x + i].getGraphic(board[y - 1][x + i].getSquareStatus()).equals("X")){
                        System.out.println("Ship too close!");
                        return false;
                    }
                }catch (Exception ignored){

                }
                try{
                    if (board[y][x - 1].getGraphic(board[y][x - 1].getSquareStatus()).equals("X")){
                        System.out.println("Ship too close");
                        return false;
                    }
                }catch (Exception ignored){

                }
                try{
                    if (board[y][x + length].getGraphic(board[y][x + length].getSquareStatus()).equals("X")){
                        System.out.println("Ship too close");
                        return false;
                    }
                }catch (Exception ignored){

                }
            }else{
                try{
                    if (board[y + i][x].getGraphic(board[y + i][x].getSquareStatus()).equals("X")){
                        System.out.println("Ship too close!");
                        return false;
                    }
                }catch(Exception e){
                    System.out.println("Ship would be out of board!");
                    return false;
                }
                try{
                    if(board[y + i][x + 1].getGraphic(board[y + i][x + 1].getSquareStatus()).equals("X")){
                        System.out.println("Ship too close!");
                        return false;
                    }
                }catch (Exception ignored){

                }
                try{
                    if (board[y + i][x - 1].getGraphic(board[y + i][x - 1].getSquareStatus()).equals("X")){
                        System.out.println("Ship too close!");
                        return false;
                    }
                }catch (Exception ignored){

                }
                try{
                    if (board[y - 1][x].getGraphic(board[y - 1][x].getSquareStatus()).equals("X")){
                        System.out.println("Ship too close");
                        return false;
                    }
                }catch (Exception ignored){

                }
                try{
                    if (board[y + length][x].getGraphic(board[y + length][x].getSquareStatus()).equals("X")){
                        System.out.println("Ship too close");
                        return false;
                    }
                }catch (Exception ignored){

                }
            }
        }
        return true;
    }

    //constructor
    public Board(int size) {
        Square[][] tempOcean = new Square[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                tempOcean[i][j] = new Square(j, i);
            }
        }
        ocean = tempOcean;
    }
}
