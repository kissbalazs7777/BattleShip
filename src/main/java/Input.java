import java.util.Scanner;

public class Input {
    //methods
    public String userInput() {
        Scanner scanner = new Scanner(System.in);
        String read = scanner.nextLine();
        if(read.equals("exit")){
            System.out.println("\nGoodbye...");
            System.exit(0);
        }
        return read;
    }

    public void isItExit(String userInput){

    }

    public boolean menuValidation(String userInput) {//pl valid 1 vagy invalid pl A
        try {
            int validInt = Integer.parseInt(userInput);

            if (validInt < 4 && validInt > 0) {
                return true;
            }

        } catch (Exception e) {
            System.out.println("Wrong input!");
            return false;
        }
        System.out.println("Wrong input!");
        return false;
    }

    public boolean placementValidation(String userInput) {//pl VA1 v HA10 invalid: 1. karakter: H v V, 2. -> A-j, 3. -> 1-10
        String firstCharacter = Character.toString(userInput.charAt(0)).toUpperCase();
        if (!(firstCharacter.equals("V") || firstCharacter.equals("H"))) {
            System.out.println("Wrong input, please choose again");
            return false;
        }
        char secondCharacter = Character.toUpperCase(userInput.charAt(1));
        if (!((int)secondCharacter >= 65 && (int)secondCharacter <= 74)) {
            System.out.println("Wrong input!");
            return false;
        }
        String thirdCharacter = userInput.substring(2);
        try {
            int thirdCharacterToInt = Integer.parseInt(thirdCharacter);
            if (!(thirdCharacterToInt <= 10 && thirdCharacterToInt >= 1)) {
                System.out.println("Wrong input!");
                return false;
            }

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean shootingValidation(String userInput){
        char firstCharacter = Character.toUpperCase(userInput.charAt(0));
        if (!((int)firstCharacter >= 65 && (int)firstCharacter <= 74)) {
            System.out.println("Wrong input!");
            return false;
        }
        String secondCharacter = userInput.substring(1);
        try {
            int secondCharacterToInt = Integer.parseInt(secondCharacter);
            if (!(secondCharacterToInt <= 10 && secondCharacterToInt >= 1)) {
                System.out.println("Wrong input!");
                return false;
            }

        } catch (Exception e) {
            System.out.println("Wrong input!");
            return false;
        }
        return true;
    }
    public boolean boardSizeValidation(String userInput){
        try {
            int validInt = Integer.parseInt(userInput);
            if (validInt <= 10 && validInt >= 5) {
                return true;
            }
            System.out.println("Wrong input!");
            return false;
        } catch (Exception e) {
            System.out.println("Wrong input!");
            return false;
        }
    }

    public boolean computerSelectValidation(String userInput){
        try {
            int validInt = Integer.parseInt(userInput);
            if (validInt <= 2 && validInt >= 1) {
                return true;
            }
            System.out.println("Wrong input!");
            return false;
        } catch (Exception e) {
            System.out.println("Wrong input!");
            return false;
        }
    }

    public boolean validateName(String userInput){
        if(userInput.length() > 10){
            System.out.println("Invalid input! Type maximum 10 characters!");
            return  false;
        }
        return true;
    }
}
