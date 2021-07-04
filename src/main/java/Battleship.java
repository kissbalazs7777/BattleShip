import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Battleship {
    //properties
    private final Display display = new Display();
    private final Input input = new Input();

    //getters
    public Display getDisplay() {
        return display;
    }

    public Input getInput() {
        return input;
    }

    //methods
    public String menu(){
        String optionOne;
        while(true) {
            System.out.println("Welcome our Battleship OOP Game");
            System.out.println("If you want to start a new game Press 1\nIf you want to exit the game Press 2\nIf you want to see highscores Press 3\n(You can quit from the game anytime typing exit!)");
            optionOne = input.userInput();
            input.menuValidation(optionOne);
            if (input.menuValidation(optionOne)) {
                break;
            }
        }
        return optionOne;
    }
    public void askForName(Game game, String mode){
        if(mode.equals("1")){
            while (true){
                System.out.println("\nPlayer 1 please add your name (max 10 characters)");
                String userInput = input.userInput();
                if(input.validateName(userInput)){
                    game.getPlayers()[0].setName(userInput);
                    break;
                }
            }
            while (true){
                System.out.println("\nPlayer 2 please add your name (max 10 characters)");
                String userInput = input.userInput();
                if(input.validateName(userInput)){
                    game.getPlayers()[1].setName(userInput);
                    break;
                }
            }
        }else{
            while (true){
                System.out.println("\nPlayer 1 please add your name (max 10 characters)");
                String userInput = input.userInput();
                if(input.validateName(userInput)){
                    game.getPlayers()[0].setName(userInput);
                    break;
                }
            }
            game.getPlayers()[1].setName("Computer");
        }
        System.out.println("\nGet ready for battle: " + game.getPlayers()[0].getName() + " vs " + game.getPlayers()[1].getName() + "\n");

    }
    public String askForSize(){
        String selectedBoardSize;
        while(true){
            System.out.println("\nPlease select board size between 5-10");
            selectedBoardSize = input.userInput();
            if(input.boardSizeValidation(selectedBoardSize)){
                break;
            }
        }
        return selectedBoardSize;
    }

    public String askForComputerPlayer(){
        System.out.println("\nPress 1 to play in hot seat mode or press 2 to play vs computer!");
        return input.userInput();
    }

    public void turns() {
        Game game = new Game(this);
        String mode;
        String selectedSize;
        while(true) {
            String option = menu();
            if(option.equals("1")) {
                do {
                    mode = askForComputerPlayer();
                } while (!input.computerSelectValidation(mode));
                do {
                    selectedSize = askForSize();
                } while (!input.boardSizeValidation(selectedSize));
                game.generateBoards(Integer.parseInt(selectedSize));
                game.generatePlayers(mode);
                askForName(game, mode);
                game.placementPhase(mode);
                game.shootingPhase(mode);
            }
            else if(option.equals("2")) {
                System.out.println("\nGoodbye...");
                System.exit(0);
            }
            else {
                ArrayList<String> winners = new ArrayList<>();
                System.out.println("\nHigh scores: \n");
                File myObj = new File("winner.txt");
                try {
                    Scanner myReader = new Scanner(myObj);
                    while (myReader.hasNextLine()) {
                        String data = myReader.nextLine();
                        winners.add(data);
                    }
                    Collections.sort(winners);
                    for(int i = 0; i < winners.size(); i++){
                        String[] splitData = winners.get(i).split(" ");
                        System.out.println((i+1) + ". Player: " + splitData[1] + " turns: " + splitData[0]);
                    }
                    System.out.println("\nPress enter to continue...");
                    input.userInput();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
