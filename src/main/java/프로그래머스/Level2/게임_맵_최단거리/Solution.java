package 프로그래머스.Level2.게임_맵_최단거리;

import java.util.*;

class Node {
    int x; int y; int distance;
    Node(int x, int y, int distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }
}

// 0 : 벽
class Solution {
    int[][] maps;
    boolean[][] checked;
    int[][] move = {{1,0}, {0,1}, {-1,0}, {0,-1}};

    public int solution(int[][] maps) {
        int answer = 0;
        this.maps = maps;
        this.checked = new boolean[maps.length][maps[0].length];
        for(int i = 0; i < maps.length; i++) {
            for(int j = 0; j < maps[i].length; j++) {
                if(maps[i][j] == 0) checked[i][j] = true;
            }
        }

        return answer = bfs(new Node(0,0,1));
    }

    private int bfs(Node node) {
        Queue<Node> q = new LinkedList<>();
        q.offer(node);
        checked[node.x][node.y] = true;

        while(!q.isEmpty()) {
            Node startNode = q.poll();
            if(startNode.x == maps.length - 1 && startNode.y == maps[0].length - 1) {
                return startNode.distance;
            }

            for(int i = 0; i < move.length; i++) {
                int nextX = startNode.x + move[i][0];
                int nextY = startNode.y + move[i][1];

                if(!isInBoundary(nextX, nextY, maps)) continue;
                if(maps[nextX][nextY] == 0) continue;
                if(checked[nextX][nextY]) continue;

                checked[nextX][nextY] = true;
                Node nextNode = new Node(nextX, nextY, startNode.distance + 1);
                q.offer(nextNode);
            }
        }
        return -1;
    }

    private boolean isInBoundary(int x, int y, int[][] maps) {
        if(x < 0 || y < 0 || x >= maps.length || y >= maps[0].length) return false;
        return true;
    }

}