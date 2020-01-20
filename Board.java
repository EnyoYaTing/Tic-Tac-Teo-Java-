class Board {

    private int rowSize, colSize;
    private int row, col;
    private Type currentPlayerMark;
    private Player[][] board;

    Board(int rowSize, int colSize)
    {
        this.rowSize = rowSize;
        this.colSize = colSize;
        initBoard();
    }

    public Player[][] getBoard()
    {
        return this.board;
    }

    private void initBoard()
    {
        board = new Player[rowSize][colSize];
        for (int i=0; i<rowSize; i++) {
            for (int j=0; j<colSize; j++) {
                board[i][j] = new Player();
            }
        }
    }

    public void currentPlayerMark(Type mark)
    {
        this.currentPlayerMark = mark;
    }

    //convert position eg.A1->00; B2->11
    public void convertPosition(String position)
    {
        char c = position.charAt(0);
        char number = position.charAt(1);
        int ascii = (int) c;
        int num = (int) number;

        if (c=='A' || c=='B' || c=='C') {
            this.row = ascii-65;
        } else {
            this.row = ascii-97;
        }

        this.col = num-49;
    }

    public boolean checkMoveValid()
    {
        return outOfBound() || doublePlaceMark();
    }

    private boolean outOfBound()
    {
        if (row<0 || row>=rowSize || col<0 || col >=colSize) {
            return true;
        }
        return false;
    }

    private boolean doublePlaceMark()
    {
        if (board[row][col].getSymbol() != Type.blank) {
            return true;
        }
        return false;
    }

    public void placeMark(Type mark)
    {
        board[row][col].setSymbol(mark);
    }

    public boolean isWinner()
    {
        return checkRowsForWin()
            || checkColumnsForWin()
            || checkDiagonalsForWin();
    }

    private boolean checkRowsForWin()
    {
        for (int i=0; i<3; i++) {
            if (checkMark(board[i][0].getSymbol(),
                          board[i][1].getSymbol(),
                          board[i][2].getSymbol())==true) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumnsForWin()
    {
        for (int j=0; j<3; j++) {
            if (checkMark(board[0][j].getSymbol(),
                          board[1][j].getSymbol(),
                          board[2][j].getSymbol())==true) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonalsForWin()
    {
        if (checkMark(board[0][0].getSymbol(),
                      board[1][1].getSymbol(),
                      board[2][2].getSymbol())==true) {
            return true;
        }
        else if (checkMark(board[0][2].getSymbol(),
                           board[1][2].getSymbol(),
                           board[2][0].getSymbol())==true) {
            return true;
        }
        return false;
    }

    private boolean checkMark(Type a, Type b, Type c)
    {
        if(a != Type.blank && a==b && b==c) {
            return true;
        }
        return false;
    }

    public boolean isDraw()
    {
        return boardIsFull();
    }

    private boolean boardIsFull()
    {
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                if (board[i][j].getSymbol()==Type.blank) {
                    return false;
                }
            }
        }
        return true;
    }


    //-----testing----------------------------
    public static void main (String[] args) {
        Board program = new Board(3, 3);
        program.run();
    }

    private void run()
    {
        boolean testing = false;
        assert(testing = true);
        if (! testing) throw new Error("Use java -ea Board");
        testOutOfBound();
        testDoublePlaceMark();
        testCheckMark();
        testCheckRowsForWin();
        testCheckColumnsForWin();
        testCheckDiagonalsForWin();
        testBoardIsFull();
        System.out.println("All test pass!");
    }

    private void testOutOfBound()
    {
        row=0; col=0;
        assert(!outOfBound());
        row=0; col=2;
        assert(!outOfBound());
        row=1; col=0;
        assert(!outOfBound());
        row=1; col=2;
        assert(!outOfBound());
        row=2; col=0;
        assert(!outOfBound());
        row=2; col=2;
        assert(!outOfBound());

        row=3; col=0;
        assert(outOfBound());
        row=0; col=3;
        assert(outOfBound());
        row=-1; col=0;
        assert(outOfBound());
        row=0; col=-1;
        assert(outOfBound());
        row=100000; col=10000;
        assert(outOfBound());
    }

    private void testDoublePlaceMark()
    {
        row=1; col=2;
        board[row][col].setSymbol(Type.blank);
        board[row][col].getSymbol();
        assert(!doublePlaceMark());

        row=1; col=2;
        board[row][col].setSymbol(Type.o);
        board[row][col].getSymbol();
        assert(doublePlaceMark());

        row=1; col=2;
        board[row][col].setSymbol(Type.x);
        board[row][col].getSymbol();
        assert(doublePlaceMark());
    }

    private void testCheckMark()
    {
        assert(checkMark(Type.x, Type.x, Type.x));
        assert(checkMark(Type.o, Type.o, Type.o));

        assert(!checkMark(Type.o, Type.o, Type.x));
        assert(!checkMark(Type.o, Type.x, Type.x));
        assert(!checkMark(Type.o, Type.x, Type.blank));
        assert(!checkMark(Type.blank, Type.blank, Type.blank));
    }

    private void testCheckRowsForWin()
    {
        board[0][0].setSymbol(Type.x);
        board[0][1].setSymbol(Type.x);
        board[0][2].setSymbol(Type.x);
        assert(checkMark(board[0][0].getSymbol(),
                         board[0][1].getSymbol(),
                         board[0][2].getSymbol()));
        assert(checkRowsForWin());

        cleanBoard();
        board[0][0].setSymbol(Type.x);
        board[1][0].setSymbol(Type.x);
        board[2][0].setSymbol(Type.x);
        assert(checkMark(board[0][0].getSymbol(),
                         board[1][0].getSymbol(),
                         board[2][0].getSymbol()));
        assert(!checkRowsForWin());

        cleanBoard();
        board[0][0].setSymbol(Type.x);
        board[1][1].setSymbol(Type.x);
        board[2][2].setSymbol(Type.x);
        assert(checkMark(board[0][0].getSymbol(),
                         board[1][1].getSymbol(),
                         board[2][2].getSymbol()));
        assert(!checkRowsForWin());
    }

    private void testCheckColumnsForWin()
    {
        board[0][0].setSymbol(Type.o);
        board[1][0].setSymbol(Type.o);
        board[2][0].setSymbol(Type.o);
        assert(checkMark(board[0][0].getSymbol(),
                         board[1][0].getSymbol(),
                         board[2][0].getSymbol()));
        assert(checkColumnsForWin());

        cleanBoard();
        board[0][0].setSymbol(Type.o);
        board[0][1].setSymbol(Type.o);
        board[0][2].setSymbol(Type.o);
        assert(checkMark(board[0][0].getSymbol(),
                         board[0][1].getSymbol(),
                         board[0][2].getSymbol()));
        assert(!checkColumnsForWin());

        cleanBoard();
        board[0][2].setSymbol(Type.o);
        board[1][1].setSymbol(Type.o);
        board[2][0].setSymbol(Type.o);
        assert(checkMark(board[0][2].getSymbol(),
                         board[1][1].getSymbol(),
                         board[2][0].getSymbol()));
        assert(!checkColumnsForWin());
    }

    private void testCheckDiagonalsForWin()
    {
        board[0][0].setSymbol(Type.o);
        board[1][1].setSymbol(Type.o);
        board[2][2].setSymbol(Type.o);
        assert(checkMark(board[0][0].getSymbol(),
                         board[1][1].getSymbol(),
                         board[2][2].getSymbol()));
        assert(checkDiagonalsForWin());

        cleanBoard();
        board[0][0].setSymbol(Type.o);
        board[0][1].setSymbol(Type.o);
        board[0][2].setSymbol(Type.o);
        assert(checkMark(board[0][0].getSymbol(),
                         board[0][1].getSymbol(),
                         board[0][2].getSymbol()));
        assert(!checkDiagonalsForWin());

        cleanBoard();
        board[0][0].setSymbol(Type.o);
        board[1][0].setSymbol(Type.o);
        board[2][0].setSymbol(Type.o);
        assert(checkMark(board[0][0].getSymbol(),
                         board[1][0].getSymbol(),
                         board[2][0].getSymbol()));
        assert(!checkDiagonalsForWin());
    }

    private void cleanBoard()
    {
        for (int i=0; i<rowSize; i++) {
            for (int j=0; j<colSize; j++) {
                board[i][j] = new Player();
            }
        }
    }

    private void testBoardIsFull()
    {
        for (int i=0; i<rowSize; i++) {
            for (int j=0; j<colSize; j++) {
                board[i][j].setSymbol(Type.o);
            }
        }
        assert(boardIsFull());

        for (int i=0; i<rowSize; i++) {
            for (int j=0; j<colSize; j++) {
                board[i][j].setSymbol(Type.blank);
            }
        }
        assert(!boardIsFull());

        for (int i=0; i<rowSize; i++) {
            for (int j=0; j<colSize; j++) {
                if (i==0 && j==0) {
                    board[i][j].setSymbol(Type.blank);
                }
                else {
                    board[i][j].setSymbol(Type.x);
                }
            }
        }
        assert(!boardIsFull());
    }


}
