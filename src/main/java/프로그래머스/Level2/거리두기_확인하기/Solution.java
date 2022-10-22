package 프로그래머스.Level2.거리두기_확인하기;

import java.util.*;

class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        List<Integer> ansList = new ArrayList<>();
        for(String[] place: places) {
            if(isProperRoom(place)) ansList.add(1);
            else ansList.add(0);
        }
        return answer = ansList.stream().mapToInt(Integer::intValue).toArray();
    }

    // 5 X 5
    private boolean isProperRoom(String[] p) {
        char[][] place = new char[p.length][];
        for(int i = 0; i < p.length; ++i) {
            place[i] = p[i].toCharArray();
        }

        for(int r = 0; r < 5; ++r) {
            for(int c = 0; c < 5; ++c) {
                if(place[r][c] != 'P') continue;
                if(!bfs(place, new Node(r,c))) return false;
            }
        }
        return true;
    }

    private boolean bfs(char[][] place, Node node) {

        Node startNode = node;
        for(int i = -2; i <= 2; ++i) {
            for(int j = -2; j <= 2; ++j) {
                if(Math.abs(i)+Math.abs(j) > 2) continue;
                if(i == 0 && j == 0) continue;
                int r = startNode.r; int c = startNode.c;
                int nextR = startNode.r + i;
                int nextC = startNode.c + j;
                if(!isOnBoundary(nextR, nextC)) continue;
                if(place[nextR][nextC] != 'P') continue;

                // P 인것 중
                // (1,0) (0,1)
                if(Math.abs(i)+Math.abs(j) == 1) return false;

                // (2,0) (0,2)
                if(i == 0) {
                    if(place[r][nextC - j/2] != 'X') return false;
                } else if(j == 0) {
                    if(place[nextR - i/2][nextC] != 'X') return false;

                } else { // (1,1)
                    if(place[nextR][c] != 'X' || place[r][nextC] != 'X') return false;
                }
            }
        }

        return true;
    }

    private boolean isOnBoundary(int x, int y) {
        return (x >= 0&& y >= 0&& x < 5 && y < 5);
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