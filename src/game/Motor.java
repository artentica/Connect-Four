package game;

import exception.NotFoundException;
import grid.Column;
import grid.Row;
import grid.Token;
import javafx.stage.Screen;
import player.Player;

import java.util.Vector;

public class Motor {
    public void addToken(int x, int y) {

    }

//    public static void displayGrid(Vector<Column> vct){
//        int size = ((Column) vct.elementAt(0)).getSize();
//        for (int y = size-1;y>=0;y--){
//            for(int x=vct.size()-1;x>=0;x--){
//                Column temp = (Column) vct.elementAt(x);
//                System.out.print(temp.getLine().elementAt(y)+ " ");
//            }
//            System.out.println("");
//        }
//    }

//    //return true if there is no one with the same name
//    public static boolean checkName(String player, String name) {
//        return player.equals(name);
//    }
//
//    //return true if there is no one with the same symbol
//    public static boolean checkSymbol(String player, String symbol) {
//        return player.equals(symbol);
//    }

    //return true if there is no one with the same name
    public static boolean usedName(Vector<Player> vct, String name) {
        for (int i = 0; i < vct.size(); i++) {
            if (vct.elementAt(i).getName().equals(name)) return true;
        }
        return false;
    }

    //return true if there is no one with the same symbol
    public static boolean usedSymbol(Vector<Player> vct, String symbol) {
        for (int i = 0; i < vct.size(); i++) {
            if (vct.elementAt(i).getSymbol().equals(symbol)) return true;
        }
        return false;
    }

    public static Player turnOf(Vector<Player> vct, int index) throws NotFoundException {
        for (int i = 0; i < vct.size(); i++) {
            if (vct.elementAt(i).getIndex() == index) return vct.elementAt(i);
        }
        throw new NotFoundException("Index du joueur non trouvé: " + String.valueOf(index));
    }

    public static void addToken(Player current, Column column) {

        Token token = new Token(current.getSymbol());

        column.getLine().set(column.getSize() - column.getFree(), token);

        column.setFree(column.getFree() - 1);

    }

    public static boolean checkColumn(Column column) {

        if (column.getFree() > 0) return true;
        else return false;

    }

    public static boolean gridRatio(int sizeColumn, int sizeLine) {
        if ((sizeColumn * sizeLine) % 2 == 0) return true;
        else return false;
    }

    public static Row createGrid() {
        Row row = new Row();

        for (int i = 0; i < row.getSize(); i++) {
            row.getLine().addElement(new Column());
        }
        return row;
    }

    public static Row createGrid(int sizeColumn, int sizeLine) {
        Row row = new Row(sizeLine);
        for (int i = 0; i < row.getSize(); i++) {
            row.getLine().addElement(new Column(sizeColumn));
        }
        return row;
    }


    public static boolean checkHorizontal(Row row, int column, Interface screen) {
        int columnTemp = column;
        int index = row.getLine().elementAt(column).getSize() - row.getLine().elementAt(column).getFree()-1;
        Vector<Token> win = new Vector<Token>();
        win.add(row.getLine().elementAt(columnTemp).getLine().elementAt(index));

        String symbol = row.getLine().elementAt(column).getLine().elementAt(index).getSymbol();
        while (columnTemp > 0 && row.getLine().elementAt(columnTemp - 1).getLine().elementAt(index).getSymbol().equals(symbol)) {
            win.add(row.getLine().elementAt(columnTemp - 1).getLine().elementAt(index));
            columnTemp--;
        }
        columnTemp = column;

        while (columnTemp + 1 < row.getLine().size() && row.getLine().elementAt(columnTemp + 1).getLine().elementAt(index).getSymbol().equals(symbol)) {
            win.add(row.getLine().elementAt(columnTemp + 1).getLine().elementAt(index));
            columnTemp++;
        }
        if (win.size() >= 4){
            screen.winH();
            return true;
        }
        else return false;

    }

    public static boolean checkWin(Row row, int column, Interface screen) {
        return (Motor.checkHorizontal(row, column, screen) || checkWinVertical(row, column, screen)  || checkDiagUL2DR(row, column, screen) || checkDiagDL2UR(row, column, screen));
    }

    private static boolean checkDiagUL2DR(Row row, int column, Interface screen) {
        int columnTemp = column;
        int maxColumn = row.getSize()-1;
        int index = row.getLine().elementAt(column).getSize() - row.getLine().elementAt(column).getFree()-1;
        int indexTemp = index;
        Vector<Token> win = new Vector<Token>();
        win.add(row.getLine().elementAt(columnTemp).getLine().elementAt(indexTemp));

        String symbol = row.getLine().elementAt(column).getLine().elementAt(index).getSymbol();
        while (columnTemp + 1 < row.getLine().size() && indexTemp > 0 && row.getLine().elementAt(columnTemp + 1).getLine().elementAt(indexTemp -1).getSymbol().equals(symbol)) {
            win.add(row.getLine().elementAt(columnTemp + 1).getLine().elementAt(indexTemp -1));
            columnTemp++;
            indexTemp--;
        }
        columnTemp = column;
        indexTemp=index;
        while (columnTemp > 0  && indexTemp + 1 < maxColumn && row.getLine().elementAt(columnTemp - 1).getLine().elementAt(indexTemp+1).getSymbol().equals(symbol)) {
            win.add(row.getLine().elementAt(columnTemp - 1).getLine().elementAt(indexTemp +1));

            columnTemp--;
            indexTemp++;
        }
        if (win.size() >= 4){
            screen.winUL2DR();

            return true;
        }
        else return false;
    }

    private static boolean checkDiagDL2UR(Row row, int column, Interface screen) {
        int columnTemp = column;
        int maxColumn = row.getSize()-1;
        int index = row.getLine().elementAt(column).getSize() - row.getLine().elementAt(column).getFree()-1;
        int indexTemp = index;
        Vector<Token> win = new Vector<Token>();
        win.add(row.getLine().elementAt(columnTemp).getLine().elementAt(indexTemp));

        String symbol = row.getLine().elementAt(column).getLine().elementAt(index).getSymbol();
        while (columnTemp > 0 && indexTemp > 0 && row.getLine().elementAt(columnTemp - 1).getLine().elementAt(indexTemp -1).getSymbol().equals(symbol)) {
            win.add(row.getLine().elementAt(columnTemp - 1).getLine().elementAt(indexTemp -1));
            columnTemp--;
            indexTemp--;
        }
        columnTemp = column;
        indexTemp=index;
        while (columnTemp + 1 < row.getLine().size() && indexTemp + 1 < maxColumn && row.getLine().elementAt(columnTemp + 1).getLine().elementAt(indexTemp+1).getSymbol().equals(symbol)) {
            win.add(row.getLine().elementAt(columnTemp + 1).getLine().elementAt(indexTemp+1));
            columnTemp++;
            indexTemp++;
        }
        if (win.size() >= 4){
            screen.winDL2UR();
            return true;
        }

        else{
            return false;
        }
    }

    private static boolean checkWinVertical(Row row, int column, Interface screen) {
        int index = row.getLine().elementAt(column).getSize() - row.getLine().elementAt(column).getFree()-1;
        Vector<Token> win = new Vector<Token>();
        win.add(row.getLine().elementAt(column).getLine().elementAt(index));

        String symbol = row.getLine().elementAt(column).getLine().elementAt(index).getSymbol();

        while (index > 0 && row.getLine().elementAt(column).getLine().elementAt(index - 1).getSymbol().equals(symbol)) {
            win.add(row.getLine().elementAt(column).getLine().elementAt(index - 1));
            index--;
        }
        if (win.size() >= 4){
            screen.winV();
            return true;
        }
        else return false;

    }

    public static String command(String command){
        String str = "Command('";
        return str + command +"')";
    }

}