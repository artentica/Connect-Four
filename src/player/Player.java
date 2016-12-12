package player;


public class Player{

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    private String name;
    private String symbol;
    private int index;

    public void win() {
        this.victory++;
    }

    public int getVictory() {
        return victory;
    }

    private int victory;

    public Player(String name, String symbol, int index){
        this.name = name;
        this.symbol = symbol;
        this.index = index;
        this.victory = 0;
    }

}