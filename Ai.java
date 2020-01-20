/* This class is to store Computer's name and move */
import java.util.Random;

class Ai extends Player {
    private Random rand;

    Ai(String inputName)
    {
        super();
        this.playername = inputName;
    }

    // if opponentMove =="X0", means the first player must be AI.
    // and he always places the mark in B2.
    @Override
    public void PlayerMove(String opponentMove)
    {
        System.out.println();
        System.out.print(playername + "'s move "
                         + "("+ symbol + ") : ");
        if (opponentMove == "X0")
        {
            position = "B2";
        } else {
              Random rand = new Random();
              int pos1 = rand.nextInt(3)+65;
              int pos2 = rand.nextInt(3)+1;
              position = String.valueOf((char) pos1) +
                         Integer.toString(pos2);
        }

        System.out.print(position+"\n");
    }
}
