import java.util.ArrayList;

public class Player {

    //property
    private final ArrayList<Ship> shipSet = new ArrayList<>();
    private String name;
    private final Square[][] shooting;
    private final Square[][] placement;
    private final Game game;

    //getter-setter
    public ArrayList<Ship> getShipSet() {
        return shipSet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Game getGame() {
        return game;
    }

    public Square[][] getPlacement() {
        return placement;
    }

    public Square[][] getShooting() {
        return shooting;
    }

    //methods
    public void generateShipSet(int size){
        int carrierNbr = size/2;
        int cruiserNbr = size/4;
        int bsNbr = size/5;
        int submarineNbr = size/6;
        int destroyerNbr = size/7;

        for(int i = 0; i < carrierNbr; i++){
            shipSet.add(new Ship(ShipType.CARRIER));
        }
        for(int i = 0; i < cruiserNbr; i++){
            shipSet.add(new Ship(ShipType.CRUISER));
        }
        for(int i = 0; i < bsNbr; i++){
            shipSet.add(new Ship(ShipType.BATTLESHIP));
        }
        for(int i = 0; i < submarineNbr; i++){
            shipSet.add(new Ship(ShipType.SUBMARINE));
        }
        for(int i = 0; i < destroyerNbr; i++){
            shipSet.add(new Ship(ShipType.DESTROYER));
        }
    }

    public void shoot(int enemyIndex, String mode){
        while(true){
            String coordinates;
            game.getBattleship().getDisplay().displayBoard(this.shooting);
            game.getBattleship().getDisplay().askForInput();
            if(mode.equals("2") && enemyIndex == 0){
                ComputerPlayer computerPlayer = (ComputerPlayer) game.getPlayers()[1];
                coordinates = computerPlayer.shootAi(getPlacement()[0].length);
                System.out.println("Computer player guess: " + coordinates);
            }else{
                coordinates = game.getBattleship().getInput().userInput();//pl A1
            }
            if(game.getBattleship().getInput().shootingValidation(coordinates)) {
                int x = Integer.parseInt(coordinates.substring(1)) - 1;
                int y = (int) (Character.toUpperCase(coordinates.charAt(0))) - 65;
                Player enemyPlayer = game.getPlayers()[enemyIndex];
                if (enemyPlayer.getPlacement()[y][x].getGraphic(enemyPlayer.getPlacement()[y][x].getSquareStatus()).equals("X")) {
                    this.shooting[y][x].setSquareStatus(SquareStatus.HIT);
                    enemyPlayer.placement[y][x].setSquareStatus(SquareStatus.HIT);
                    checkShipLives(enemyPlayer, y, x);

                } else if (enemyPlayer.getPlacement()[y][x].getGraphic(enemyPlayer.getPlacement()[y][x].getSquareStatus()).equals("0")) {
                    this.shooting[y][x].setSquareStatus(SquareStatus.MISSED);
                    enemyPlayer.placement[y][x].setSquareStatus(SquareStatus.MISSED);
                    System.out.println("There is no ship. Missed!\n");
                } else {
                    System.out.println("Oh no!!! You already tried these coordinates!\n");
                }
                break;
            }
        }
    }

    public void checkShipLives(Player enemyPlayer, int y, int x){
        ArrayList<Ship> enemyShipset = enemyPlayer.getShipSet();
        outerloop:
        for (int i = 0; i < enemyShipset.size(); i++) {
            ArrayList<Square> enemyShipCoordinates = enemyShipset.get(i).getCoordinates();
            for (int j = 0; j < enemyShipCoordinates.size(); j++) {
                if (enemyShipCoordinates.get(j).getX() == x && enemyShipCoordinates.get(j).getY() == y) {
                    Ship targetShip = enemyShipset.get(i);
                    int targetShipLife = targetShip.getLives();
                    targetShipLife--;
                    targetShip.setLives(targetShipLife);
                    if (!targetShip.isAlive()){
                        ArrayList<Square> sunkedShipCoordinates = targetShip.getCoordinates();
                        for (int k = 0; k < sunkedShipCoordinates.size(); k++) {
                            int squareToChangeX = sunkedShipCoordinates.get(k).getX();
                            int squareToChangeY = sunkedShipCoordinates.get(k).getY();
                            this.shooting[squareToChangeY][squareToChangeX].setSquareStatus(SquareStatus.SUNK);
                            enemyPlayer.placement[squareToChangeY][squareToChangeX].setSquareStatus(SquareStatus.SUNK);
                        }
                        enemyShipset.remove(targetShip);
                        System.out.println("Enemy "+ targetShip.getType() + " had been sunk!!\n");

                    } else {
                        System.out.println("Yesss! You hit a ship!\n");
                    }
                    break outerloop;
                }
            }
        }
    }

    public boolean isAlive(){
        if (shipSet.size() == 0){
            return false;
        }
        return true;
    }

    //constructor
    public Player(int size, Square[][] placement, Square[][] shooting, Game game) {
        generateShipSet(size);
        this.placement = placement;
        this.shooting = shooting;
        this.game = game;
    }

}
