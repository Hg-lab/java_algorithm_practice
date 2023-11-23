package 프로그래머스.Level2.숫자_변환하기;

import java.util.*;

class Main {
    public static void main(String[] args) {
        int solution = new Solution().solution(10, 40, 5);
        System.out.println("solution = " + solution);
    }
}

public class Solution {
    class Pair {
        int y;
        int count;

        Pair(int y, int count) {
            this.y = y;
            this.count = count;
        }
    }
    public int solution(int x, int y, int n) {
        int answer = 0;
        return bfs(x, n, new Pair(y, 0));
    }
    private int bfs(int x, int n, Pair pair) {

        Queue<Pair> queue = new LinkedList<>();
        queue.offer(pair);
        while (!queue.isEmpty()) {
            Pair startPair = queue.poll();
            int y = startPair.y;

            if(y == x) return startPair.count;

            if (y % 3 == 0 && y / 3 >= x) {
                queue.add(new Pair(y / 3, startPair.count + 1));
            }
            if (y % 2 == 0 && y / 2 >= x) {
                queue.add(new Pair(y / 2, startPair.count + 1));
            }
            if (y - n >= x) {
                queue.add(new Pair(y - n, startPair.count + 1));
            }
        }
        return -1;
    }
}

class Solution_dfs {

    int x;
    int n;
    int answer = Integer.MAX_VALUE;
    public int solution(int x, int y, int n) {
        this.x = x;
        this.n = n;
        dfs(y, 0);

        if(answer == Integer.MAX_VALUE) return -1;
        return answer;
    }

    private void dfs(int y, int count) {
        if (x >= y) {
            if(x==y) {
                answer = Math.min(answer, count);
            }
            return;
        }
        if(y%3 == 0 && y/3 >= x && count+1 <= answer) dfs(y / 3, count + 1);
        if(y%2 == 0 && y/2 >= x && count+1 <= answer) dfs(y / 2, count + 1);
        if(y - n >= x && count+1 <= answer) dfs(y - n, count + 1);
    }

}