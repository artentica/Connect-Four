package game;

import exception.NotFoundException;
import grid.Column;
import grid.Token;
import player.Player;

import java.util.Vector;

public class Motor{
	public void addToken(int x, int y){

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
        for (int i=0; i<vct.size(); i++){
            if (vct.elementAt(i).getName().equals(name))return true;
        }
        return false;
    }

    //return true if there is no one with the same symbol
    public static boolean usedSymbol(Vector<Player> vct, String symbol) {
        for (int i=0; i<vct.size(); i++){
            if (vct.elementAt(i).getSymbol().equals(symbol))return true;
        }
        return false;
    }

    public static Player turnOf(Vector<Player> vct, int index) throws NotFoundException{
        for (int i=0; i<vct.size(); i++){
            if (vct.elementAt(i).getIndex() == index)return vct.elementAt(i);
        }
        throw new NotFoundException("Index du joueur non trouvé: " + String.valueOf(index));
    }

    public static void addToken(Player current, Column column) {

        Token token = new Token(current.getSymbol());

        column.getLine().set(column.getSize()-column.getFree(),token);

        column.setFree(column.getFree() -1);

    }

    public static boolean checkColumn(Column column) {

	    if (column.getFree()>0) return true;
	    else return false;

    }
}