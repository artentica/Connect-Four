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
        int size = (vct.elementAt(0)).getSize();
        for (int x = 0; x < vct.size(); x++) {
            System.out.print(" " + (x + 1));
        }
        System.out.println("");

        for (int y = size - 1; y >= 0; y--) {
            for (int x = 0; x < vct.size(); x++) {
                Column temp = vct.elementAt(x);
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
            if (str.equals("exit")) System.exit(0);

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
    public int numberWinGame(int nbPlayers){

        Scanner sc = new Scanner(System.in);
        String str;

        int nb = 0;


        while (nb<=0 || (nb-1)%nbPlayers!=0 ) {
            System.out.println("Combien de manches?");
            str = sc.nextLine();
            if (str.equals("exit")) System.exit(0);

            try {
                nb = Integer.parseInt(str);
/*
                if (nb>2) break;
*/
            } catch (NumberFormatException e) {
                System.out.println("Mauvais nombre donné: " + e);
                nb = 0;
            }

            if (nb<=0 || nb-1%nbPlayers!=0 ) System.out.println("Il doit y avoir x*NbDeJoueur+1 parties impaire pour désigner un gagnant: " + nb);

        }
        return nb;
    }

    public String namePlayer(int i) {
        Scanner sc = new Scanner(System.in);
        String str = "";
        while (str.length() < 1 || str.length() > 15) {
            System.out.println("Nom du joueur n°" + (i + 1) + ": ");
            str = sc.nextLine();
            if (str.equals("exit")) System.exit(0);

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

            if (str.equals("exit")) System.exit(0);

            if (symbol.length() != 1 && !symbol.equals("."))
                System.out.println("Le symbole ne doit faire qu'un seule et ne doit pas être un '.', taille actuel: " + symbol.length());
        }
        return symbol;
    }

    public void begin() {
        System.out.println("Vous pouvez maintenant commencer à jouer !!!");
    }

    public void end() {
        System.out.println("--------------------------------------------------");
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
            if (str.equals("exit")) System.exit(0);
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

    public int nbColumn(){
        Scanner sc = new Scanner(System.in);
        String str = "";
        int nb = 0;

        do {
            System.out.println("Choisissez le nombre de colonnes (min. 4): ");
            str = sc.nextLine();
            if (str.equals("exit")) System.exit(0);
            try {
                nb = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                System.out.println("Mauvais nombre donné, seul les nombres sont acceptés: " + e);
                nb = 0;
            }
            if (nb<4)
                System.out.println("Vous devez avoir au minimum 4 colonnes");
        } while (nb<4);
        return nb;
    }

    public int nbLine(){
        Scanner sc = new Scanner(System.in);
        String str = "";
        int nb = 0;

        do {
            System.out.println("Choisissez le nombre de lignes (min. 2): ");
            str = sc.nextLine();
            if (str.equals("exit")) System.exit(0);
            try {
                nb = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                System.out.println("Mauvais nombre donné, seul les nombres sont acceptés: " + e);
                nb = 0;
            }
            if (nb<2)
                System.out.println("Vous devez avoir au minimum 2 lignes");
        } while (nb<2);
        return nb;
    }

    public boolean choiceGrid(){
        Scanner sc = new Scanner(System.in);
        String str = "";

        System.out.println("Voulez-vous choisir la taille de la grille?(y/n):");
        while (true){

            str = sc.nextLine();
            if (str.equals("exit")) System.exit(0);
            else if (str.equals("y")) return true;
            else if (str.equals("n")) return false;
            else System.out.println("Veuillez choisir entre oui(y) et non(n):");
        }


    }

    public void fullGrid(){
        System.out.println("La grille est pleine, aucun points de marquées");
    }

    public void badRatio() {
        System.out.println("Le nombre de lignes multiplié par le nombre de colonnes doit être pair");
    }


    public void winH(){
        System.out.println("Victoire horizontal -");
    }
    public void winV(){
        System.out.println("Victoire vertical |");
    }

    public void winDL2UR(){
        System.out.println("Victoire diagonal bas gauche à haut droite /");
    }
    public void winUL2DR(){
        System.out.println("Victoire diagonal haut gauche à bas droite \\");
    }

    public void score(Vector<Player> vct, int win, int limit){
        for(int i = 0; i<vct.size();i++){
            System.out.println("Player "+ vct.elementAt(i).getName() + " have " + vct.elementAt(i).getVictory() + " win(s)");
        }
        if(!this.finalWinner(vct, limit))System.out.println("There is "+ win + " game(s) to do !!!");
    }

    public void win(String name){
        System.out.println("The player: '" + name + "' won");
    }

    public boolean finalWinner(Vector<Player> players, int limit){
        Player winner;
        for (int i = 0; i<players.size(); i++){
            if (players.elementAt(i).getVictory() == (limit-1)/players.size()+1){
                winner = players.elementAt(i);
                System.out.println("Le joueur gagnant est " + winner.getName() + " avec " + winner.getVictory() + "/" + limit + " des parties gagnées");
                return true;
            }
        }
        return false;
    }

}
