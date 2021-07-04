public class Square {

    //properties
    private int x;
    private int y;
    private SquareStatus squareStatus;

    //getter-setter
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public SquareStatus getSquareStatus() {
        return squareStatus;
    }

    public void setSquareStatus(SquareStatus squareStatus) {
        this.squareStatus = squareStatus;
    }

    //methods
    public String getGraphic(SquareStatus squareStatus){
        String graphic;
        switch (squareStatus){
            case MISSED:
                graphic = SquareStatus.MISSED.getCharacter(squareStatus);
                break;
            case HIT:
                graphic = SquareStatus.HIT.getCharacter(squareStatus);
                break;
            case SHIP:
                graphic = SquareStatus.SHIP.getCharacter(squareStatus);
                break;
            case EMPTY:
                graphic = SquareStatus.EMPTY.getCharacter(squareStatus);
                break;
            case SUNK:
                graphic = SquareStatus.SUNK.getCharacter(squareStatus);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + squareStatus);
        }
        return graphic;
    }

    //constructor
    public Square(int x, int y) {
        this.x = x;
        this.y = y;
        squareStatus = SquareStatus.EMPTY;
    }

}
