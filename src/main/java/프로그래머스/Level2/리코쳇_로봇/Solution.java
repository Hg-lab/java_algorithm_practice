package 프로그래머스.Level2.리코쳇_로봇;

import java.util.*;

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(new String[]{"...D..R", ".D.G...", "....D.D", "D....D.", "..D...."});
    }
}

class Solution {
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, -1, 0, 1};
    Point gPoint = new Point(0,0, 0);
    Point rPoint = new Point(0, 0, 0);
    String[] board;
    int answer = 0;
    boolean[][] checked;

    public int solution(String[] board) {
        int answer = 0;
        this.board = board;
        this.checked = new boolean[board.length][board[0].length()];

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length(); j++) {
                if(board[i].charAt(j) == 'G') gPoint = new Point(j,i, 0);
                if(board[i].charAt(j) == 'R') rPoint = new Point(j,i, 0);
            }
        }

        // G 옆에 장애물이 없거나 끝이 아니면 -1리턴
        if(!isPossible(gPoint)) return -1;
        return bfs();
    }

    private boolean isPossible(Point gPoint) {
        for(int i = 0; i < 4; i ++) {
            int nextX = gPoint.x + dx[i];
            int nextY = gPoint.y + dy[i];
            if( !isOnBoundary(nextX, nextY)||board[nextY].charAt(nextX) == 'D') return true;
        }
        return false;
    }

    private boolean isOnBoundary(int x, int y) {
        if(x < 0 || y < 0 || x >= board[0].length() || y >= board.length) return false;
        return true;
    }

    private int bfs() {
        Queue<Point> q = new LinkedList<>();
        Point startPoint = new Point(rPoint);
        q.add(startPoint);

        while(!q.isEmpty()) {
            Point nowPoint = q.poll();
            this.checked[nowPoint.y][nowPoint.x] = true;
            if(nowPoint.equals(gPoint)) return nowPoint.moveCount;

            for(int i = 0; i < 4; i++) {
                int nextX = nowPoint.x;
                int nextY = nowPoint.y;

                while(true) {
                    if(!isOnBoundary(nextX + dx[i], nextY + dy[i]) ||
                            board[nextY + dy[i]].charAt(nextX + dx[i]) == 'D') break;
                    nextX += dx[i];
                    nextY += dy[i];
                }

                if(checked[nextY][nextX]) continue;
                Point nextPoint = new Point(nextX, nextY, nowPoint.moveCount + 1);
                if(!isOnBoundary(nextX, nextY))break;
                q.add(nextPoint);
            }
        }
        return -1;
    }

    class Point {
        int x;
        int y;
        int moveCount;


        private Point(Point point) {
            this.x = point.x;
            this.y = point.y;
        }

        private Point(int x, int y, int moveCount) {
            this.x = x;
            this.y = y;
            this.moveCount = moveCount;
        }
        private boolean equals(Point point) {
            if(this.x == point.x && this.y == point.y) return true;
            return false;
        }
    }
}