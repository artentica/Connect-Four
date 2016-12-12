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


        logger.setLevel(Level.ALL); //pour envoyer les messages de tous les niveaux
        logger.setUseParentHandlers(false); // pour supprimer la console par défaut



        // This block configure the logger with handler and formatter

        //IF YOU WANT TO PUT 1 SESSION LOG IN A FILE

        FileHandler fh;
        fh = new FileHandler("log.txt", 0, 1,  false);
        logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);

        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.SEVERE); // pour n'accepter que les message de niveau &Gt; INFO
        logger.addHandler(ch);



        System.out.println("------PUISSANCE 4 ENSEIRB------");
        logger.log(Level.INFO,"Programm start");

        int sizeLine = 0;
        int sizeColumn = 0;

        Row row;
        Console screen = new Console();

        boolean gridSize;

        if(screen.choiceGrid()){
            logger.log(Level.INFO,Motor.command("y") + " Custum grid");
            do {
                sizeLine = screen.nbColumn();
                sizeColumn = screen.nbLine();
                gridSize = Motor.gridRatio(sizeColumn,sizeLine);
                if (!gridSize) screen.badRatio();
            }while(!gridSize);

            row=Motor.createGrid(sizeColumn,sizeLine);

        }else{
            logger.log(Level.INFO,Motor.command("n") + " Not a custum grid");
            row =Motor.createGrid();
        }

        logger.log(Level.INFO,"Creation of the IHM");

		Vector<Player> players = new Vector<Player>();
		String tempName;
		String symbol;
        logger.log(Level.INFO,"Instanciation of the variables");


        int nbPlayer = screen.nbPlayer();
        logger.log(Level.INFO,Motor.command(String.valueOf(nbPlayer)) + "' Number of players");

		for(int i = 0; i<nbPlayer;i++){
            logger.log(Level.INFO,"Creation of the player n°" +(i+1));

			tempName = screen.namePlayer(i);

			while(Motor.usedName(players, tempName)){
                screen.checkName(players,tempName);
				tempName = screen.namePlayer(i);
			}

            logger.log(Level.INFO,Motor.command(tempName) + "' Name of the player n°" + (i+1));


            symbol = screen.symbolePlayer(tempName);


			while(Motor.usedSymbol(players, symbol)){
                screen.checkSymbol(players,symbol);
				symbol = screen.symbolePlayer(tempName);
			}
            logger.log(Level.INFO,Motor.command(symbol) +  "' Symbol of the player n°" + (i+1) +" named "+ tempName);

			players.add(new Player(tempName,symbol,i));
		}

		screen.begin();
        logger.log(Level.INFO,"Beginning of the game");

        int turn =0;
        int column = 0;

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

                logger.log(Level.INFO,Motor.command(String.valueOf(column)) + " " + current.getName() + " have choosen the column n°"+ column);
                Motor.addToken(current, row.getLine().elementAt(column-1));

                turn++;
            }catch(NotFoundException e){
                logger.log(Level.SEVERE,e.toString());
            }
		    //Motor.addTocken();
        }while (!Motor.checkWin(row,column-1, screen));


        screen.displayGrid(row.getLine());

        System.out.println("We have a winner");



        //displayGrid(row.getLine());

	}


}