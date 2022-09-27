package 프로그래머스.Level2.배달;

import java.util.*;

class Solution {
    int[] dp;
    List<Node>[] list;

    /* 방문했어도 길이 2개 이상인 경우 재방문하여 거리를 갱신한다 */
    // boolean[] checked;

    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        this.dp = new int[N+1];
        this.list = new ArrayList[N+1];
        this.checked = new boolean[N+1];

        for(int i = 0; i < list.length; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < road.length; i++) {
            int from = road[i][0];
            int to = road[i][1];
            int cost = road[i][2];

            list[from].add(new Node(to, cost));
            list[to].add(new Node(from, cost));
        }

        Arrays.fill(dp, Integer.MAX_VALUE);

        dijkstra(1);

        for(int distance: dp) {
            if(K >= distance) ++answer;
        }

        return answer;
    }

    private void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>(new NodeComparator());
        // PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start,0));
        dp[start] = 0;

        while(!pq.isEmpty()) {
            Node node = pq.poll();

            for(Node nextNode: list[node.to]) {
                if(dp[nextNode.to] > dp[node.to] + nextNode.cost) {
                    dp[nextNode.to] = dp[node.to] + nextNode.cost;
                    pq.add(new Node(nextNode.to, nextNode.cost));
                }
            }
        }
    }
}

class Node implements Comparable<Node>{
    int to;
    int cost;

    Node(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.cost, o.cost);
    }
}

class NodeComparator implements Comparator<Node> {
    @Override
    public int compare(Node o1, Node o2) {
        Integer i1 = Integer.valueOf(o1.cost);
        Integer i2 = Integer.valueOf(o2.cost);
        return i1.compareTo(i2);
    }
}