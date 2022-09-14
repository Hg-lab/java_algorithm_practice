package 프로그래머스.Level2.전력망을_둘로_나누기;

import java.util.*;

class Solution {
    int[][] map;
    boolean[] checked;
    int n;

    public int solution(int n, int[][] wires) {
        int answer = n;
        this.n = n;
        checked = new boolean[n+1];
        map = new int[n+1][n+1];

        // 인접행렬 그리기
        for(int i = 0; i < wires.length; i++) {
            for(int j = 0; j < wires[i].length; j++) {
                map[wires[i][0]][wires[i][1]] = 1;
                map[wires[i][1]][wires[i][0]] = 1;
            }
        }

        int a, b;
        for(int cutIndex = 0; cutIndex < wires.length; cutIndex++) {
            a = wires[cutIndex][0];
            b = wires[cutIndex][1];

            // 끊기
            map[a][b] = 0;
            map[b][a] = 0;

            answer = Math.min(bfs(a, map), answer);

            // 초기화
            checked = new boolean[n+1];
            map[a][b] = 1;
            map[b][a] = 1;
        }

        return answer;
    }

    private int[][] copyMap(int[][] map) {
        int[][] copiedMap = new int[map.length][map[0].length];
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                copiedMap[i][j] = map[i][j];
            }
        }
        return copiedMap;
    }

    private int bfs(int node, int[][] map) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(node);
        checked[node] = true;
        int count = 1;
        while(!q.isEmpty()) {
            int startNode = q.poll();
            for(int i = 1; i < map[startNode].length; i++) {
                if(map[startNode][i] == 1) {
                    int nextNode = i;
                    if(!isOnBoundary(nextNode, n)) continue;
                    if(checked[nextNode]) continue;
                    q.offer(nextNode);
                    checked[nextNode] = true;
                    count++;
                }
            }
        }

        return (int)Math.abs(n - count - count);
    }

    private boolean isOnBoundary(int x, int n) {
        if(x <= 0 || x > n) return false;
        return true;
    }
}
