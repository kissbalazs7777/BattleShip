import java.util.ArrayList;
import java.util.Random;

public class BoardFactory {

    //methods
    public void manualPlacement(Board boardObj, ArrayList<Ship> shipSet, Display display,Input input){
        for(int i = 0; i < shipSet.size(); i++){
            while (true){
                display.displayBoard(boardObj.getOcean());
                System.out.println("Place your: " + shipSet.get(i).getType() + "! (length: " + shipSet.get(i).getType().length + ")");
                System.out.println("Ships left: " + (shipSet.size() - i));//Ez majd a display class dolga lesz
                System.out.println("Input: (For example: VA1 or HA1)");
                String placementCoordinates = input.userInput();
                if(input.placementValidation(placementCoordinates)){
                    Object[] cuttedInput = cutInput(placementCoordinates);// falvágjuk az iputot és a betű koordinátát számmá alakítjuk és egy listába eltároljuk, így fog a lista kinézni pl:VA1-ből: [V, 1, 1]
                    if (boardObj.isPlacementOk(boardObj.getOcean(), (String)cuttedInput[0], (int)cuttedInput[2]-1, (int)cuttedInput[1]-1, shipSet.get(i).getType().length)){//ha minden jó a validációnál, megyünk tovább
                        updateShipCoordinateAndSquareStatus(cuttedInput, boardObj.getOcean(), shipSet.get(i));//meghívjuk az update függvényt
                        break;
                    }
                }
            }
        }
    }

    public void randomPlacement(Board boardObj, ArrayList<Ship> shipSet){
        for(int i = 0; i < shipSet.size(); i++){
            while (true){
                Random random = new Random();
                String direction;
                int directionRndNbr = random.nextInt(2);
                if(directionRndNbr == 0){
                    direction = "H";
                }else{
                    direction = "V";
                }
                int x = random.nextInt(boardObj.getOcean().length);//4
                int y = random.nextInt(boardObj.getOcean().length);//4
                Object[] cuttedInput = new Object[3];
                cuttedInput[0] = direction;//V
                cuttedInput[1] = y+1;//5
                cuttedInput[2] = x+1;//5
                //System.out.println("Direction: " + direction + " X: " + (x) + " Y: " + (y));
                if (boardObj.isPlacementOk(boardObj.getOcean(), direction, x, y, shipSet.get(i).getType().length)){//x:4, y:4
                    updateShipCoordinateAndSquareStatus(cuttedInput, boardObj.getOcean(), shipSet.get(i));//V,5,5
                    break;
                }
            }
        }
    }

    public void updateShipCoordinateAndSquareStatus(Object[] cuttedInput, Square[][] board, Ship shipToPlace){
        int shipToPlaceLength = shipToPlace.getType().length;
        ArrayList<Square> shipToPlaceCoordinates = new ArrayList<>();

        if(cuttedInput[0].equals("H")){
            for(int j = 0; j < shipToPlaceLength; j++){
                Square square = board[(int)cuttedInput[1]-1][(int)cuttedInput[2]+j-1];
                square.setSquareStatus(SquareStatus.SHIP);
                shipToPlaceCoordinates.add(square);
            }
        }else{
            for(int j = 0; j < shipToPlaceLength; j++){
                Square square = board[(int)cuttedInput[1]+j-1][(int)cuttedInput[2]-1];
                square.setSquareStatus(SquareStatus.SHIP);
                shipToPlaceCoordinates.add(square);
            }
        }
        shipToPlace.setCoordinates(shipToPlaceCoordinates);
    }

    public Object[] cutInput(String input){
        Object[] cuttedInput = new Object[3];
        cuttedInput[0] = Character.toString(input.charAt(0)).toUpperCase();
        cuttedInput[1] = (int)(Character.toUpperCase(input.charAt(1)))-64;
        cuttedInput[2] = Integer.parseInt(input.substring(2));
        return cuttedInput;
    }
}
