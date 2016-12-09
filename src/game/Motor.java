package game;

import exception.NotFoundException;
import grid.Column;
import grid.Row;
import grid.Token;
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


    public static boolean checkHorizontal(Row row, int column) {
        int count = 1;//we start with the current token
        int columnTemp = column;
        int index = row.getLine().elementAt(column).getSize() - row.getLine().elementAt(column).getFree()-1;
        String symbol = row.getLine().elementAt(column).getLine().elementAt(index).getSymbol();
        while (columnTemp > 0 && row.getLine().elementAt(columnTemp - 1).getLine().elementAt(index).getSymbol().equals(symbol)) {
            count++;
            columnTemp--;
        }
        columnTemp = column;

        while (columnTemp + 1 < row.getLine().size() && row.getLine().elementAt(columnTemp + 1).getLine().elementAt(index).getSymbol().equals(symbol)) {
            count++;
            columnTemp++;
        }
        if (count >= 4) return true;
        else return false;

    }

    public static boolean checkWin(Row row, int column) {
        return (Motor.checkHorizontal(row, column) || checkWinVertical(row, column));
/*      checkDiagUL2DR(Row row,int colonne);
        checkDiagDL2UR(Row row,int colonne);*/
    }

    private static boolean checkWinVertical(Row row, int column) {
        int count = 1;//we start with the current token
        int index = row.getLine().elementAt(column).getSize() - row.getLine().elementAt(column).getFree()-1;


        String symbol = row.getLine().elementAt(column).getLine().elementAt(index).getSymbol();

        while (index > 0 && row.getLine().elementAt(column).getLine().elementAt(index - 1).getSymbol().equals(symbol)) {
            count++;
            index--;
        }
        if (count >= 4) return true;
        else return false;

    }

}