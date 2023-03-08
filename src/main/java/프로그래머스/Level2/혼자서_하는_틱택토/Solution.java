package 프로그래머스.Level2.혼자서_하는_틱택토;
class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(1 == s.solution(new String[]{"O.X", ".O.", "..X"}));
    }
}

class Solution {
    String[] board;
    int xCount = 0;
    int oCount = 0;
    public int solution(String[] board) {
        this.board = board;
        int answer = 1;
        count();
        if(xMoreThanO()) return 0;
        if(diffIsOverOne()) return 0;
        if(bothWin()) return 0;
        return answer;
    }

    private void count() {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length(); j++) {
                if(board[i].charAt(j) == 'O') this.oCount++;
                if(board[i].charAt(j) == 'X') this.xCount++;
            }
        }
    }

    private boolean xMoreThanO() {
        return xCount > oCount;
    }

    private boolean diffIsOverOne() {
        return Math.abs(xCount - oCount) > 1;
    }

    private boolean bothWin() {
        return checkWinner('O') || checkWinner('X');
    }

    private boolean checkWinner(char target) {

        for(int i = 0; i < 3; i++) {
            char c0 = board[i].charAt(0);
            char c1 = board[i].charAt(1);
            char c2 = board[i].charAt(2);
            if(c0 == target && c0 == c1 && c0 == c2) return true;
        }

        for(int i = 0; i < 3; i++) {
            char c0 = board[0].charAt(i);
            char c1 = board[1].charAt(i);
            char c2 = board[2].charAt(i);
            if(c0 == target && c0 == c1 && c0 == c2) return true;
        }

        char c0 = board[0].charAt(0);
        char c1 = board[1].charAt(1);
        char c2 = board[2].charAt(2);
        if(c0 == target && c0 == c1 && c0 == c2) return true;

        c0 = board[0].charAt(2);
        c1 = board[1].charAt(1);
        c2 = board[2].charAt(0);
        if(c0 == target && c0 == c1 && c0 == c2) return true;

        return false;
    }
}

class Short_Code_Solution {
    public char[][] map;
    public int win(char c) {
        int game = 0;
        for(int i = 0; i < 3; i++)
        {
            if(map[i][0] == c && map[i][0] == map[i][1] && map[i][1] == map[i][2])
                game++;
            if(map[0][i] == c && map[0][i] == map[1][i] && map[1][i] == map[2][i])
                game++;
        }
        if(map[0][0] == c && map[0][0] == map[1][1] && map[1][1] == map[2][2])
            game++;
        if(map[0][2] == c && map[0][2] == map[1][1] && map[1][1] == map[2][0])
            game++;
        return game;
    }
    public int solution(String[] board) {
        int answer = 1;
        map = new char[3][3];
        int oCnt = 0;
        int xCnt = 0;
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                map[i][j] = board[i].charAt(j);
                if(map[i][j] == 'O')
                    oCnt++;
                if(map[i][j] == 'X')
                    xCnt++;
            }
        }
        if(xCnt > oCnt || oCnt - xCnt > 1)
            return 0;
        if(win('O') > 0 && win('X') > 0)
            return 0;
        if(win('O') > 0) {
            if(oCnt == xCnt)//o가 이겼으면 o가 한개 많아야함
                return 0;
        }
        if(win('X') > 0) {
            if(oCnt > xCnt)
                return 0;
        }

        return answer;
    }
    //x가 이기면 수가 같아야하고, o가 이기면 o가 한개 많아야함

}
