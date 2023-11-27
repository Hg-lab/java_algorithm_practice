package 프로그래머스.Level2.디펜스_게임;


import java.util.PriorityQueue;

class Main {
    public static void main(String[] args) {
        System.out.println(new Solution().solution(7, 3, new int[]{4, 2, 4, 5, 3, 3, 1}));
    }
}

class Solution {
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i = 0; i < enemy.length; i++) {
            if(n == 0) return i;
            if(n < 0) return i-1;
            if (k > 0) {
                pq.add(enemy[i]);
                k--;
                continue;
            }

            if (!pq.isEmpty() && pq.peek() < enemy[i]) {
                n -= pq.poll();
                pq.add(enemy[i]);
            } else {
                n -= enemy[i];
            }

        }

        if(n < 0) return enemy.length-1;
        return enemy.length;
    }
}

