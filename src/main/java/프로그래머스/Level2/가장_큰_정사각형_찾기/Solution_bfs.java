package 프로그래머스.Level2.가장_큰_정사각형_찾기;

import java.util.*;

class Solution_bfs {
    int startR;
    int startC;
    int h;
    int w;
    int[][] board;
    int[][] move = {{1,0}, {0,1}, {1,1}};
    int maxDist = 0;

    public int solution(int [][]board) {
        int answer = 1234;
        this.h = board.length;
        this.w = board[0].length;
        this.board = board;
        boolean allZero = true;
        for(int r = 0; r < h; ++r) {
            for(int c = 0; c < w; ++c) {
                if(board[r][c] == 0) continue;
                if(board[r][c] == 1) allZero = false;
                this.startR = r;
                this.startC = c;
                Node startNode = new Node(r, c);
                bfs(startNode, new boolean[h][w]);
            }
        }

        if(allZero) return 0;
        if(maxDist == 0) return 1;
        return answer = (int)Math.pow(maxDist, 2);
    }

    private void bfs(Node startNode, boolean[][] checked) {
        Queue<Node> q = new LinkedList<>();
        q.offer(startNode);
        checked[startNode.r][startNode.c] = true;
        boolean hasZero = false;
        while(!q.isEmpty() && !hasZero) {
            Node node = q.poll();
            for(int i = 0; i < move.length; ++i) {
                int nextR = node.r + move[i][0];
                int nextC = node.c + move[i][1];
                if(!isOnBoundary(nextR, nextC)) continue;
                if(checked[nextR][nextC]) continue;
                if(board[nextR][nextC] == 0) {
                    hasZero = true;
                    continue;
                }
                checked[nextR][nextC] = true;
                Node nextNode = new Node(nextR, nextC);
                q.offer(nextNode);

                if(nextR-startR == nextC-startC && !hasZero) {
                    this.maxDist = Math.max(maxDist, nextR-startR + 1);
                }
            }
        }
    }

    private boolean isOnBoundary(int r, int c) {
        if(r >= 0 && r < h && c >= 0 && c < w)
            return true;
        return false;
    }
}

class Node {
    int r;
    int c;
    public Node(int r, int c) {
        this.r = r;
        this.c = c;
    }
}