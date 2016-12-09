package show;

import game.Interface;
import game.Motor;
import grid.Column;
import grid.Token;
import player.Player;

import java.util.Scanner;
import java.util.Vector;

/**
 * Created by artentica on 06/12/2016.
 */
public class Console implements Interface {

    public void displayGrid(Vector<Column> vct) {
        int size = ((Column) vct.elementAt(0)).getSize();
        for (int x = 0; x < vct.size(); x++) {
            System.out.print(" " + (x + 1));
        }
        System.out.println("");

        for (int y = size - 1; y >= 0; y--) {
            for (int x = 0; x < vct.size() - 1; x++) {
                Column temp = (Column) vct.elementAt(x);
                System.out.print(" " + temp.getLine().elementAt(y).getSymbol());
            }
            System.out.println("");
        }
    }

    public int nbPlayer() {
        System.out.println("Combien de joueurs?");

        Scanner sc = new Scanner(System.in);
        String str;

        int nb = 0;


        while (nb < 2) {
            System.out.println("Nombre de joueur: ");
            str = sc.nextLine();
            try {
                nb = Integer.parseInt(str);
/*
                if (nb>2) break;
*/
            } catch (NumberFormatException e) {
                System.out.println("Mauvais nombre donné, seul les nombres sont acceptés: " + e);
                nb = 0;
            }

            if (nb < 2) System.out.println("Il doit y avoir au moins 2 joueurs, nombre de joueur rentré: " + nb);

        }
        return nb;
    }

    public String namePlayer(int i) {
        Scanner sc = new Scanner(System.in);
        String str = "";
        while (str.length() < 1 || str.length() > 15) {
            System.out.println("Nom du joueur n°" + (i + 1) + ": ");
            str = sc.nextLine();

            if (str.length() < 1 || str.length() > 15)
                System.out.println("Le nom doit faire entre 1 et 15 caractères, nombre de caractères actuel: " + str.length());
        }
        return str;
    }

    //return true if there is no one with the same name
    public void checkName(Vector<Player> vct, String name) {
        for (int i = 0; i < vct.size(); i++) {
            if (vct.elementAt(i).getName().equals(name)) {
                System.out.println("Le nom '" + name + "' est déjà utilisé par le joueur n°" + (vct.elementAt(i).getIndex() + 1));
            }
        }
    }

    //return true if there is no one with the same symbol
    public void checkSymbol(Vector<Player> vct, String symbol) {
        for (int i = 0; i < vct.size(); i++) {
            if (vct.elementAt(i).getSymbol().equals(symbol)) {
                System.out.println("Le symbole '" + symbol + "' est déjà utilisé par le joueur : " + vct.elementAt(i).getName());
            }
        }
    }

    public String symbolePlayer(String name) {

        Scanner sc = new Scanner(System.in);
        String str = "";
        String symbol = "";
        while (symbol.length() != 1 && !symbol.equals(".")) {
            System.out.println("Symbole du joueur " + name + ": ");
            symbol = sc.nextLine();
            if (symbol.length() != 1 && !symbol.equals("."))
                System.out.println("Le symbole ne doit faire qu'un seule et ne doit pas être un '.', taille actuel: " + symbol.length());
        }
        return symbol;
    }

    public void begin() {
        System.out.println("Vous pouvez maintenant commencer à jouer !!!");
    }

    public void turnOf(Player player) {
        System.out.println("C'est au tour de: " + player.getName());
    }

    public int choiceColumn(int max) {
        Scanner sc = new Scanner(System.in);
        String str = "";
        int nb = 0;

        do {
            System.out.println("Choisissez une colonne: ");
            str = sc.nextLine();
            try {
                nb = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                System.out.println("Mauvais nombre donné, seul les nombres sont acceptés: " + e);
                nb = 0;
            }
            if (nb < 1 || nb > max)
                System.out.println("Vous devez choisir une colonne entre 1 et " + max + " vous avez choisi: " + nb);

        } while (nb < 1 || nb > max);
        return nb;
    }


    public void columnFull(int column){
        System.out.println("La colonne n°" + column +" est pleine, veuillez en choisir une autre");
    }

}
