/* Store player's name and give them game symbol.
   Also, receive the move's input from players */

import java.util.Scanner;

public class Player {
    protected String playername;
    protected String position;
    protected Type symbol;
    private Scanner scan = new Scanner(System.in);

    Player()
    {
        this.playername = "";
        this.symbol = Type.blank;
    }

    public void setPlayname()
    {
        do {
            playername = scan.nextLine();
            if (playername.length() == 0) {
                System.out.print("Cannot be Empty ! ");
            }
        } while (playername.length() == 0);
    }

    public String getPlayname()
    {
        return playername;
    }

    public void setSymbol(Type symbol)
    {
        this.symbol = symbol;
    }

    public Type getSymbol()
    {
        return symbol;
    }

    public void PlayerMove(String opponentMove)
    {
        do {
            System.out.println();
            System.out.print(playername + "'s move "
                            + "("+ symbol + ") : ");
            position = scan.next();
            if (position.length() < 2) {
                System.out.println("Invalid move. Enter again!");
            }
        } while (position.length() < 2);
    }

    public String getPlayerMove()
    {
        return position;
    }

}
