public enum SquareStatus {
    EMPTY("0"),
    HIT("H"),
    SHIP("X"),
    MISSED("M"),
    SUNK("S");

    public final String character;

    SquareStatus(String character) {
        this.character = character;
    }

    public String getCharacter(SquareStatus squareStatus){
        String character;
        switch (squareStatus){
            case MISSED:
                character = MISSED.character;
                break;
            case EMPTY:
                character = EMPTY.character;
                break;
            case HIT:
                character = HIT.character;
                break;
            case SHIP:
                character = SHIP.character;
                break;
            case SUNK:
                character = SUNK.character;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + squareStatus);
        }
        return character;
    }
}
