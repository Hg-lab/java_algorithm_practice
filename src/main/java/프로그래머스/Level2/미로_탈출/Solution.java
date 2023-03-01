package 프로그래머스.Level2.미로_탈출;

import java.util.*;

class Solution {

    private int[] dx = {0,1,0,-1};
    private int[] dy = {1,0,-1,0};
    private String[] maps;
    private boolean[][] checked;
    private Node leverNode;

    public int solution(String[] maps) {
        int answer = -1;
        this.maps = maps;
        this.checked = checkedFactory(maps);

        Node start = getStartNode(maps);
        int startToLever = bfs(start, 'L');
        System.out.println("startToLever = " + startToLever);
        if(startToLever == -1) return -1;

        int leverToExit = bfs(leverNode, 'E');
        System.out.println("leverToExit = " + leverToExit);
        if(leverToExit == -1) return -1;
        answer = startToLever + leverToExit;

        return answer;
    }

    private boolean[][] checkedFactory(String[] maps) {
        boolean[][] checked = new boolean[maps.length][maps[0].length()];
        return checked;
    }

    private Node getStartNode(String[] maps) {
        for(int y = 0; y < maps.length; y++) {
            for(int x = 0; x < maps[y].length(); x++) {
                if(maps[y].charAt(x) == 'S')
                    return new Node(x,y);
            }
        }
        return null;
    }

    private int bfs(Node start, char destination) {
        int distance = 0;
        Queue<Node> q = new LinkedList<>();
        q.offer(start);
        checked[start.x][start.y] = true;

        while(!q.isEmpty()) {
            Node nowNode = q.poll();
            if(maps[nowNode.y].charAt(nowNode.x) == destination) {
                if(destination == 'L') this.leverNode = nowNode;
                return distance;
            }

            for(int i = 0; i < 4; i++) {
                int nextX = nowNode.x + dx[i];
                int nextY = nowNode.y + dy[i];
                if(!isOnBoundary(nextX, nextY)) continue;
                Node nextNode = new Node(nextX, nextY);

                if(isBlocked(nextNode)) continue;
                if(checked[nextNode.y][nextNode.y]) continue;
                q.offer(nextNode);
                checked[nextNode.y][nextNode.x] = true;
            }
        }
        return -1;
    }

    private boolean isOnBoundary(int x, int y) {
        if(x < 0 || y < 0) return false;
        if(x >= maps[0].length() || y >= maps.length) return false;
        return true;
    }

    private boolean isBlocked(Node node) {
        return (maps[node.y].charAt(node.x) == 'X');
    }

    class Node {
        int x;
        int y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}