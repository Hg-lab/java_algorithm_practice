package 프로그래머스.Level2.카펫;

import java.util.*;

class Node{
    int w; int h;
    Node(int w, int h) {
        this.w = w;
        this.h = h;
    }
}

class Solution {
    int brown; int yellow;
    public int[] solution(int brown, int yellow) {
        this.brown = brown;
        this.yellow = yellow;

        int[] answer = new int[2];
        boolean[][] checked = new boolean[2500][2500];
        Queue<Node> q = new LinkedList<>();

        int w = 3; int h = 3;
        int b = 2*w + 2*h - 4;
        int y = w*h - b;

        checked[w][h] = true;
        q.offer(new Node(w,h));
        int[] dx = {-1, 0 , 1, 0};
        int[] dy = {0 , -1, 0 ,1};
        while(!q.isEmpty()) {
            Node startNode = q.poll();

            for(int i = 0; i < 4; i++) {
                int nextW = startNode.w + dx[i];
                int nextH = startNode.h + dy[i];
                if(nextW >= 3 && nextH >= 3 && nextW < 2500 && nextH <2500 && !checked[nextW][nextH] ) {
                    if(verifyAnswer(nextW, nextH)) {
                        answer[0] = nextW;
                        answer[1] = nextH;
                        return answer;
                    }
                    checked[nextW][nextH] = true;
                    q.add(new Node(nextW, nextH));
                }
            }

        }

        answer[0] = w;
        answer[1] = h;

        return answer;
    }

    private boolean verifyAnswer(int w, int h) {
        int b = 2*w + 2*h - 4;
        int y = w*h - b;
        if(b == brown && y == yellow) return true;
        return false;
    }
}