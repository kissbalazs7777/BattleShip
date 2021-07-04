import java.io.FileWriter;
import java.io.IOException;

public class Game {

    //properties
    private final BoardFactory boardFactory = new BoardFactory();
    private final Battleship battleship;
    private final Board[] boards = new Board[4];
    private final Player[] players = new Player[2];
    private String winner;

    //getter
    public Player[] getPlayers() {
        return players;
    }

    public Battleship getBattleship() {
        return battleship;
    }

    //methods
    public void placementPhase(String mode){
        System.out.println("It's the placement phase!");
        System.out.println("Press enter to continue...");
        battleship.getInput().userInput();
        if(mode.equals("1")){
            for(int i = 0; i < 2; i++){
                System.out.println("Its " + players[i].getName() + "'s turn!");
                System.out.println("Press enter to continue...");
                battleship.getInput().userInput();
                boardFactory.manualPlacement(boards[i], players[i].getShipSet(), battleship.getDisplay(),battleship.getInput());
            }
        }else{
            System.out.println("Its " + players[0].getName() + "'s turn!");
            System.out.println("Press enter to continue...");
            battleship.getInput().userInput();
            boardFactory.manualPlacement(boards[0], players[0].getShipSet(), battleship.getDisplay(),battleship.getInput());
            System.out.println("\nIt's Computer's turn!");
            System.out.println("Computer's placement movements:\n");
            boardFactory.randomPlacement(boards[1], players[1].getShipSet());
        }

    }

    public void writeFile(int turn, String winner){
        try {
            FileWriter myFile = new FileWriter("winner.txt", true);
            myFile.write(turn + " " + winner);
            myFile.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void shootingPhase(String mode){
        int turn = 0;
        System.out.println("\nIt's shooting time!!");
        System.out.println("Press enter to continue...");
        battleship.getInput().userInput();
        while (true){
            if (turn % 2 == 0){
                System.out.println("It's " + players[0].getName() + "'s turn!");
                System.out.println("Press enter to continue...");
                battleship.getInput().userInput();
                players[0].shoot(1, mode);//egyes játékos lő
            }else{
                System.out.println("It's " + players[1].getName() + "'s turn!");
                if(mode.equals("1")){
                    System.out.println("Press enter to continue...");
                    battleship.getInput().userInput();
                }
                players[1].shoot(0, mode);//kettes játékos lő
                if (isGameOver()){
                    if(winner != null){
                        writeFile(turn, winner);
                    }
                    break;
                }

            }
            turn++;
        }
    }

    public void generatePlayers(String mode){
        if(mode.equals("1")){
            for(int i = 0; i < 2; i++){
                players[i] = new Player(boards[i].getOcean().length, boards[i].getOcean(), boards[i+2].getOcean(), this);
            }
        }else{
            players[0] = new Player(boards[0].getOcean().length, boards[0].getOcean(), boards[2].getOcean(), this);
            players[1] = new ComputerPlayer(boards[1].getOcean().length, boards[1].getOcean(), boards[3].getOcean(), this);
        }
    }

    public void generateBoards(int selectedSize){
        for(int i = 0; i < 4; i++){
            boards[i] = new Board(selectedSize);
        }
    }

    public boolean isGameOver(){
        if (!players[0].isAlive()&&!players[1].isAlive()){
            System.out.println("It's a DRAW!\n");
            winner = null;
            return true;
        } else if (!players[0].isAlive() && players[1].isAlive()){
            battleship.getDisplay().youAreWinner(players[1].getName());
            winner = players[1].getName();
            return true;
        } else if (players[0].isAlive() && !players[1].isAlive()) {
            battleship.getDisplay().youAreWinner(players[0].getName());
            winner = players[0].getName();
            return true;
        }
        return false;
    }

    public Game(Battleship battleship) {
        this.battleship = battleship;
    }
}
