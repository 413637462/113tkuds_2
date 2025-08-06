public class TicTacToeBoard {
    private char[][] board = new char[3][3];
    
    public TicTacToeBoard() {
        for(char[] row : board) Arrays.fill(row, '-');
    }
    
    public boolean placeMark(int row, int col, char mark) {
        if(row<0 || row>2 || col<0 || col>2 || board[row][col]!='-') return false;
        board[row][col] = mark;
        return true;
    }
    
    public boolean checkWin() {
        for(int i=0; i<3; i++) {
            if(board[i][0]!='-' && board[i][0]==board[i][1] && board[i][1]==board[i][2]) return true;
            if(board[0][i]!='-' && board[0][i]==board[1][i] && board[1][i]==board[2][i]) return true;
        }
        if(board[0][0]!='-' && board[0][0]==board[1][1] && board[1][1]==board[2][2]) return true;
        return board[0][2]!='-' && board[0][2]==board[1][1] && board[1][1]==board[2][0];
    }
    
    public boolean isDraw() {
        for(char[] row : board) for(char c : row) if(c=='-') return false;
        return true;
    }
    
    public void printBoard() {
        for(char[] row : board) System.out.println(Arrays.toString(row));
    }
}