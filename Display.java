/* Show the Gameboard for Players & Receive input from players */
import java.util.Scanner;

class Display {
    private Player[][] board;
    private String mode;
    private String firstPlayer;
    private Scanner scan = new Scanner(System.in);

    public void gameStart()
    {
        System.out.println();
        System.out.println("*Welcome to Tic-tac-toe. Let's Start!*");
        System.out.println();
        System.out.println("Which Playing Mode would you like : A or B");
        System.out.print("(A) Player Vs Ai. (B) Player Vs Player : ");
    }

    public void modeChoose()
    {
        boolean flag;
        do {
            flag = false;
            mode = scan.nextLine();

            if (mode.length() == 0) {
                System.out.print("Cannot be Empty! Please Enter A or B :");
                flag = true;
            }
            else if (mode.charAt(0) != 'A' && mode.charAt(0) != 'B') {
                System.out.print("Invalid Input! Please Enter A or B :");
                flag = true;
            }
        } while (flag);
    }

    public String getModeChoose()
    {
        return mode;
    }

    public void firstPlayer()
    {
        boolean flag;
        do {
            flag = false;
            firstPlayer = scan.nextLine();

            if (mode.length() == 0) {
                System.out.print("Cannot be Empty! Enter A or B :");
                flag = true;
            }
            else if (mode.charAt(0) != 'A' && mode.charAt(0) != 'B') {
                System.out.print("Invalid Input! Enter A or B :");
                flag = true;
            }
        } while (flag);
    }

    public String getFirstPlayer()
    {
        return firstPlayer;
    }

    public void printBoard(Player[][] board)
    {
        char[] rowName = {'A','B','C'};
        System.out.println("    " + "1 " + "  2 " + "  3 ");
        System.out.println("  "+ "|---|---|---|");

        for (int i=0;i<3;i++){
            System.out.printf("%c | ", rowName[i]);
            for (int j=0;j<3;j++){
                if (board[i][j].getSymbol() == Type.blank){
                    System.out.printf("  | ");
                }else{
                    System.out.printf("%s | ",board[i][j].getSymbol());
                }
            }
            System.out.println();
            System.out.println("  "+ "|---|---|---|");
        }
    }

}
