package 프로그래머스.Level2._1차_프렌즈4블록;

import java.util.*;

class Solution {

    boolean hasBlocks;
    String[] board;
    int[][] move = {{1,0}, {0,1}, {1,1}};
    int m; int n;
    int count = 0;
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        this.hasBlocks = true;
        this.board = board;
        this.m = m;
        this.n = n;

        while(isRemoving()) {

            String[] boardCopied = board.clone();
            boolean[][] checked = new boolean[m][n];
            for(int r = 0; r < m - 1; ++r ){
                for(int c = 0; c < n - 1; ++c) {
                    checked = bfs(boardCopied, new Node(r,c), checked);
                }
            }
            removeBlocks(checked);
            moveBlocks();
        }
        return answer = count;
    }
    // [TTTANT,   FA  ,    F  ,    RAA,   MMMF, TMMTTJ]
    private boolean[][] bfs(String[] boardCopied, Node node, boolean[][] checked) {
        boolean[][] copiedChecked = new boolean[m][n];
        for(int i = 0; i < m; ++i) {
            copiedChecked[i] = checked[i].clone();
        }

        copiedChecked[node.x][node.y] = true;
        for(int i = 0; i < move.length; ++i) {
            int nextX = node.x + move[i][0];
            int nextY = node.y + move[i][1];
            if(!isOnBoundary(nextX, nextY)) continue;
            if(boardCopied[node.x].charAt(node.y) != boardCopied[nextX].charAt(nextY)) return checked;
            copiedChecked[nextX][nextY] = true;
        }

        return copiedChecked;

    }

    private void removeBlocks(boolean[][] checked) {
        for(int r = 0; r < board.length; ++r) {
            for(int c = 0; c < board[r].length(); ++c) {
                char b = board[r].charAt(c);
                if(!checked[r][c]) continue;
                if(b == ' ') continue;

                StringBuilder sb = new StringBuilder(board[r]);
                sb.setCharAt(c, ' ');
                ++this.count;
                board[r] = sb.toString();
            }
        }
    }

    private boolean isOnBoundary(int x, int y) {
        if(x >= 0 && y >= 0 && x < m && y < n) return true;
        return false;
    }

    private void moveBlocks() {
        for(int c = 0; c < board[0].length(); ++c) {
            Queue<Character> q = new LinkedList<>();
            for(int r = board.length-1; r >= 0; --r) {
                if(board[r].charAt(c) == ' ') continue;
                q.offer(board[r].charAt(c));
            }
            while(q.size() < m) q.offer(' ');

            while(!q.isEmpty()) {
                char ch = q.poll();
                StringBuilder sb = new StringBuilder(board[q.size()]);
                sb.setCharAt(c, ch);
                board[q.size()] = sb.toString();

            }
        }

    }

    private boolean isRemoving() {
        for(int r = 0; r < board.length - 1; ++r) {
            for(int c = 0; c < board[r].length() - 1; ++c) {
                int cnt = 1;
                for(int i = 0; i < move.length; ++i) {
                    if(board[r].charAt(c) == ' ') continue;
                    if(board[r].charAt(c) == board[r+move[i][0]].charAt(c+move[i][1])) ++cnt;
                    if(cnt == 4) return true;
                }

            }
        }
        return false;
    }
}

class Node {
    int x; int y;
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

}