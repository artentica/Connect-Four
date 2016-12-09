package game;

import exception.NotFoundException;
import grid.Column;
import grid.Row;
import player.Player;


import java.io.IOException;
import java.util.Vector;
import java.util.logging.*;


import show.Console;

import static game.Motor.*;


public class Game{
	public static void main( String args []) throws NotFoundException, IOException {


        // création d'un Logger et d'un Handler Logger
        Logger logger = Logger.getLogger("MyLogger");
        FileHandler fh;


        logger.setLevel(Level.ALL); //pour envoyer les messages de tous les niveaux
        logger.setUseParentHandlers(false); // pour supprimer la console par défaut



        // This block configure the logger with handler and formatter

        //IF YOU WANT TO PUT 1 SESSION LOG IN A FILE

        /*fh = new FileHandler("*PATH2MY_FOLDER*\/MyLogFile.log");
        logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);*/

        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.SEVERE); // pour n'accepter que les message de niveau &Ge; INFO
        logger.addHandler(ch);



        System.out.println("------PUISSANCE 4 ENSEIRB------");
        logger.log(Level.INFO,"Programm start");

		Row row = new Row();
		for(int i=0; i<row.getSize();i++){
			row.getLine().addElement(new Column());
			Column temp = (Column) row.getLine().get(i);
		}


		Console screen = new Console();
        logger.log(Level.INFO,"Creation of the IHM");

		Vector<Player> players = new Vector<Player>();
		String tempName;
		String symbol;
        logger.log(Level.INFO,"Instanciation of the variables");


        int nbPlayer = screen.nbPlayer();

		for(int i = 0; i<nbPlayer;i++){

			tempName = screen.namePlayer(i);

			while(Motor.usedName(players, tempName)){
                screen.checkName(players,tempName);
				tempName = screen.namePlayer(i);
			}

			symbol = screen.symbolePlayer(tempName);

			while(Motor.usedSymbol(players, symbol)){
                screen.checkSymbol(players,symbol);
				symbol = screen.symbolePlayer(tempName);
			}

			players.add(new Player(tempName,symbol,i));
		}

		screen.begin();
        logger.log(Level.INFO,"Beginning of the game");

        int turn =0;
        int column;

        do{
            try{
                Player current = Motor.turnOf(players,turn%nbPlayer);

                screen.turnOf(current);//write who have to play
                logger.log(Level.INFO,"It's to " + current.getName() + " to play");

                screen.displayGrid(row.getLine());

                column = screen.choiceColumn(row.getSize());

                while (!Motor.checkColumn(row.getLine().elementAt(column-1))) {
                    screen.columnFull(column);
                    column = screen.choiceColumn(row.getSize());
                }

                logger.log(Level.INFO,current.getName() + " have choosen the column n°"+ column);

                Motor.addToken(current, row.getLine().elementAt(column-1));

                turn++;
            }catch(NotFoundException e){
                logger.log(Level.SEVERE,e.toString());
            }
		    //Motor.addTocken();

        }while (true);



		//displayGrid(row.getLine());

	}


}