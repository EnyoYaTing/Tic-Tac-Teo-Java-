import java.util.Random;

class Oxo {
    private int round;
    private int totalPlayer = 2;
    private Player[] players;
    private Board oxoGame;
    private Ai computer;
    private Display display;

    public static void main (String[] args){
        Oxo program = new Oxo();
        program.run();
    }

    private void run()
    {
        newBoard();
        newDisplay();
        playMode();
        newPlayers();
        playersInfo();
        decideWhoFirst();
        showBoard();
        playGame();
    }

    private void newBoard()
    {
        oxoGame = new Board(3,3);
    }

    private void newDisplay()
    {
        display = new Display();
        display.gameStart();
    }

    private void playMode()
    {
        display.modeChoose();
        display.getModeChoose();
    }

    private void newPlayers()
    {
        players = new Player[totalPlayer];

        if (display.getModeChoose().charAt(0)=='A') {
            players[0]= new Player();
            players[1]= new Ai("AI");
        }
        else {
            for (int i=0; i<totalPlayer; i++) {
                players[i]= new Player();
            }
        }
    }

    private void playersInfo()
    {
        for (int i=0; i<totalPlayer; i++) {
            if (i==0) {
                players[i].setSymbol(Type.o);
                System.out.print("Please enter Player o's name: ");
                players[i].setPlayname();
            }
            else {
                players[i].setSymbol(Type.x);
                if (display.getModeChoose().charAt(0)=='B') {
                    System.out.print("Please enter Player x's name: ");
                    players[i].setPlayname();
                }
            }
        }
    }

    //Let Players choose who want to be the first player.
    public void decideWhoFirst()
    {
        System.out.println();
        System.out.println("Decide who to be the First :");
        System.out.print("(A) "+ players[0].getPlayname() +
                         " (B) "+ players[1].getPlayname() +
                         " (C) Random " + " : ");
        display.firstPlayer();

        if (display.getFirstPlayer().charAt(0)=='B') {
            round++;
        }
        else if (display.getFirstPlayer().charAt(0)=='C') {
            round = new Random().nextInt(2);
        }
    }

    private void showBoard()
    {
        System.out.println("");
        System.out.println("Enter the position you want (eg.A1): ");
        System.out.println("");
        display.printBoard(oxoGame.getBoard());
    }

    private void playGame()
    {
        do {
            yourTurn();
            display.printBoard(oxoGame.getBoard());
            round++;
        }while (!gameover());
    }

    //change player to place the mark, opponentMove is for Ai.
    //default opponentMove = X0, just make sure when Ai be the first palyer.
    //Ai will know he must places the mark in specific position.
    private void yourTurn()
    {
        int index;
        index = round % 2;
        String opponentMove = "X0";

        do {
            players[index].PlayerMove(opponentMove);
            opponentMove = players[index].getPlayerMove();
            oxoGame.convertPosition(players[index].getPlayerMove());

            if (oxoGame.checkMoveValid()) {
                System.out.println("Invalid move! Enter again !");
            }
        } while (oxoGame.checkMoveValid());

        oxoGame.placeMark(players[index].getSymbol());
    }

    private boolean gameover()
    {
        if (oxoGame.isWinner()) {
            System.out.print("Congrat!! ");
            System.out.println(players[(round-1)%totalPlayer].getPlayname()+
                               " Wins!");
            System.out.println();
            return true;
        }
        else if (oxoGame.isDraw()) {
            System.out.println("It's a Draw !");
            System.out.println();
            return true;
        }
        return false;
    }

}
