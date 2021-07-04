import java.util.ArrayList;

public class Ship {

    //properties
    private ArrayList<Square> coordinates = new ArrayList<>();
    private final ShipType type;
    private int lives;

    //getter-setter
    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public ShipType getType() {
        return type;
    }

    public ArrayList<Square> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<Square> coordinates) {
        this.coordinates = coordinates;
    }

    //methods
    public Boolean isAlive() {
        if (lives < 1 ) return false;
        return true;
    }

    //constructor
    public Ship(ShipType type) {
        this.type = type;
        lives = type.length;
    }
}
