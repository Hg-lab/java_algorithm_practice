package 프로그래머스.Level2.카카오프렌즈_컬러링북;

import java.util.Queue;
import java.util.LinkedList;

class Node {
    int x;
    int y;
    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {

    static int[][] move = {{1,0},{0,1},{-1,0},{0,-1}};
    static boolean[][] visited;
    static int[][] map;
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        visited = new boolean[m][n];
        map = picture;

        for(int i = 0; i < picture.length; i++) {
            for(int j = 0; j < picture[i].length; j++) {
                if(picture[i][j] != 0 && visited[i][j] == false) {
                    Node startNode = new Node(i,j);
                    int area = bfs(startNode, m, n);
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, area);
                    ++numberOfArea;
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    static int bfs(Node startNode, int m, int n) {
        Queue<Node> q = new LinkedList<>();
        q.offer(startNode);
        visited[startNode.x][startNode.y] = true;
        int area = 1;
        int colorNumOfStartNode = map[startNode.x][startNode.y];
        while(!q.isEmpty()) {
            Node node = q.poll();
            for(int i = 0; i < move.length; i++) {

                Node nextNode = new Node(node.x+move[i][0], node.y+move[i][1]);
                if(isValidRange(nextNode.x, nextNode.y, m, n) && map[nextNode.x][nextNode.y] == colorNumOfStartNode && visited[nextNode.x][nextNode.y] == false) {

                    visited[nextNode.x][nextNode.y] = true;
                    q.offer(nextNode);
                    ++area;
                }
            }
        }


        return area;
    }

    static boolean isValidRange(int x, int y, int m, int n) {
        if(x >= 0 && y >= 0 && x < m && y < n) return true;
        return false;
    }
}