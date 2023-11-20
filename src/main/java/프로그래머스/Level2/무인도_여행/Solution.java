package 프로그래머스.Level2.무인도_여행;

import java.util.*;

class Main {
    public static void main(String[] args) {
        int[] solution = new Solution().solution(new String[]{"X591X", "X1X5X", "X231X", "1XXX1"});
    }
}

public class Solution {

    boolean[][] checked;
    String[] maps;
    ArrayList<Integer> answerList = new ArrayList<>();
    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};

    public int[] solution(String[] maps) {
        this.maps = maps;
        this.checked = new boolean[maps.length][maps[0].length()];
        for (int y = 0; y < maps.length; y++) {
            for (int x = 0; x < maps[y].length(); x++) {
                Node startNode = new Node(x, y);
                if(startNode.isChecked(checked)) continue;
                if(startNode.getIslandNumber(maps) == 0) continue;
                answerList.add(bfs(startNode));
            }
        }

        if(answerList.isEmpty()) return new int[]{-1};

        Collections.sort(answerList);
        return answerList.stream().mapToInt(Integer::intValue).toArray();
    }

    private int bfs(Node node) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        node.checkNode(checked);

        int total = 0;
        while (!queue.isEmpty()) {
            Node startNode = queue.poll();
            for (int i = 0; i < 4; i++) {
                Node nextNode = startNode.getNextNode(dx[i], dy[i]);
                if(!nextNode.isOnBoundary(maps)) continue;
                if(nextNode.isChecked(checked)) continue;
                if(nextNode.getIslandNumber(maps) == 0) continue;
                queue.add(nextNode);
                nextNode.checkNode(checked);
            }
            total+= startNode.getIslandNumber(maps);
        }
        return total;
    }

    class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }


        void checkNode(boolean[][] checked) {
            checked[y][x] = true;
        }

        boolean isChecked(boolean[][] checked) {
            return checked[y][x];
        }

        Node getNextNode(int dx, int dy) {
            return new Node(this.x + dx, this.y + dy);
        }

        boolean isOnBoundary(String[] maps) {
            if(this.x < 0 || this.y < 0 || this.x >= maps[0].length() || this.y >= maps.length) {
                return false;
            }
            return true;
        }

        int getIslandNumber(String[] maps) {
            String c = String.valueOf(maps[this.y].charAt(x));
            if(c.equals("X")) return 0;
            return Integer.parseInt(c);
        }
    }
}
