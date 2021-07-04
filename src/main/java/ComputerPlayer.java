import java.util.ArrayList;
import java.util.Random;

public class ComputerPlayer extends Player{
    //properties
    ArrayList<Integer[]> usedCoordinates = new ArrayList<>();
    ArrayList<String> possibleDirections = new ArrayList<>();
    private String direction;
    private boolean isFollowingDirection;
    private int prevShotX;
    private int prevShotY;
    private int originalHitX;
    private int originalHitY;

    //methods
    public String shootAi(int size){
        if(isThere_H_OnBoard()){
            return makeCalculatedShot();
        }else{
            isFollowingDirection = false;
            possibleDirections.clear();
            return makeRandomShot(size);
        }
    }

    public String makeRandomShot(int size){
        Random random = new Random();
        int x, y;
        while (true){
            boolean generateCoordsAgain = false;
            x  = random.nextInt(size);//0
            y = random.nextInt(size);//4
            if (!isSNearby(y, x)){
                for(int i = 0; i < usedCoordinates.size(); i++){
                    if (usedCoordinates.get(i)[0] == y && usedCoordinates.get(i)[1] == x){
                        generateCoordsAgain = true;
                        break;
                    }
                }
            }else{
                generateCoordsAgain = true;
            }
            if (!generateCoordsAgain){
                break;
            }
        }
        if(getGame().getPlayers()[0].getPlacement()[y][x].getSquareStatus().equals(SquareStatus.SHIP)){
            originalHitX = x;//0
            originalHitY = y;//4
            possibleDirections.add("N");
            possibleDirections.add("S");
            possibleDirections.add("E");
            possibleDirections.add("W");
            updateDirection(y, x, size);
        }
        prevShotX = x;//0
        prevShotY = y;//4
        usedCoordinates.add(new Integer[]{y, x});
        return Character.toString((char)(y+65)) + (x+1);//E1
    }

    public String makeCalculatedShot(){
        Random random = new Random();
        int x, y;
        String direction;
        int[] directionInNumbers;
        if(isFollowingDirection){
            x = prevShotX;
            y = prevShotY;
            direction = this.direction;
            directionInNumbers = translateDirectionToNumbers(direction);
            try {
                if(!getGame().getPlayers()[0].getPlacement()[y+directionInNumbers[0]][x+directionInNumbers[1]].getSquareStatus().equals(SquareStatus.SHIP)){
                    possibleDirections.remove(direction);
                    isFollowingDirection = false;
                }
            }catch (Exception e){
                possibleDirections.remove(direction);
                isFollowingDirection = false;
            }
        }else{
            x = originalHitX;
            y = originalHitY;
            direction = possibleDirections.get(random.nextInt(possibleDirections.size()));
            directionInNumbers = translateDirectionToNumbers(direction);
            try {
                if(getGame().getPlayers()[0].getPlacement()[y+directionInNumbers[0]][x+directionInNumbers[1]].getSquareStatus().equals(SquareStatus.SHIP)){
                    shipHit(direction);
                    this.direction = direction;
                    isFollowingDirection = true;
                }else{
                    possibleDirections.remove(direction);
                }
            }catch (Exception e){
                possibleDirections.remove(direction);
            }
        }
        usedCoordinates.add(new Integer[]{y, x});
        prevShotY = y+directionInNumbers[0];
        prevShotX = x+directionInNumbers[1];
        System.out.println();
        return Character.toString((char)(y+65+directionInNumbers[0])) + (x+1+directionInNumbers[1]);
    }

    public void shipHit(String direction){
        switch (direction){
            case "S":
            case "N":
                possibleDirections.remove("E");
                possibleDirections.remove("W");
                break;
            case "E":
            case "W":
                possibleDirections.remove("N");
                possibleDirections.remove("S");
                break;
        }
    }

    public int[] translateDirectionToNumbers(String direction){
        int[] array = {0, 0};
        switch (direction){
            case "N":
                array = new int[]{-1, 0};
                break;
            case "S":
                array = new int[]{1, 0};
                break;
            case "E":
                array = new int[]{0, 1};
                break;
            case "W":
                array = new int[]{0, -1};
                break;
        }
        return array;
    }

    public void updateDirection(int y, int x, int size){
        try{
            if(!(x+1 < size) || !this.getShooting()[y][x+1].getSquareStatus().equals(SquareStatus.EMPTY) || isSNearby(y, x+1)){
                possibleDirections.remove("E");
            }
        }catch (Exception e){
            possibleDirections.remove("E");
        }
        try {
            if(!(x-1 >= 0) || !this.getShooting()[y][x-1].getSquareStatus().equals(SquareStatus.EMPTY) || isSNearby(y, x-1)){
                possibleDirections.remove("W");
            }
        }catch (Exception e){
            possibleDirections.remove("W");
        }
        try {
            if(!(y-1 >= 0) || !this.getShooting()[y-1][x].getSquareStatus().equals(SquareStatus.EMPTY) || isSNearby(y-1, x)){
                possibleDirections.remove("N");
            }
        }catch (Exception ignored){
            possibleDirections.remove("N");
        }
        try {
            if(!(y+1 < size) || !this.getShooting()[y+1][x].getSquareStatus().equals(SquareStatus.EMPTY) || isSNearby(y+1, x)){
                possibleDirections.remove("S");
            }
        }catch (Exception ignored){
            possibleDirections.remove("S");
        }
    }

    public boolean isThere_H_OnBoard(){
        for(int i = 0; i < this.getShooting()[0].length; i++){
            for(int j = 0; j < this.getShooting()[i].length; j++){
                if(this.getShooting()[i][j].getSquareStatus().equals(SquareStatus.HIT)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isSNearby(int y, int x){
        try{
            if (getGame().getPlayers()[0].getPlacement()[y+1][x].getSquareStatus().equals(SquareStatus.SUNK)){
                return true;
            }
        }catch (Exception ignored){

        }
        try{
            if (getGame().getPlayers()[0].getPlacement()[y-1][x].getSquareStatus().equals(SquareStatus.SUNK)){
                return true;
            }
        }catch (Exception ignored){

        }
        try{
            if (getGame().getPlayers()[0].getPlacement()[y][x+1].getSquareStatus().equals(SquareStatus.SUNK)){
                return true;
            }
        }catch (Exception ignored){

        }
        try{
            if (getGame().getPlayers()[0].getPlacement()[y][x-1].getSquareStatus().equals(SquareStatus.SUNK)){
                return true;
            }
        }catch (Exception ignored){

        }
        return false;
    }

    //constructor
    public ComputerPlayer(int size, Square[][] placement, Square[][] shooting, Game game) {
        super(size, placement, shooting, game);
    }

}
