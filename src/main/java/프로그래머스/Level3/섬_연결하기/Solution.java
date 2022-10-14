package 프로그래머스.Level3.섬_연결하기;

import java.util.*;

class Solution {
    public int solution(int n, int[][] costs) {
        int answer = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>(new EdgeComparator());
        boolean[] checked = new boolean[n];
        int[][] adjMatrix = new int[n][n];

        // init adjacency graph
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // add distances
        // 각 노드에서 연결된 Edge만 추가해줌(양방향)
        for (int i = 0; i < costs.length; i++) {
            int start = costs[i][0];
            int end = costs[i][1];
            int w = costs[i][2];

            graph.get(start).add(new Edge(end, w));
            graph.get(end).add(new Edge(start, w));
        }

        pq.add(new Edge(0,0));

        while(!pq.isEmpty()) {
            Edge edge = pq.poll();
            if(checked[edge.v]) continue;
            checked[edge.v] = true;
            answer += edge.w;
            for (Edge next : graph.get(edge.v)) {
                if(checked[next.v]) continue;
                pq.add(next);
            }
        }


        return answer;
    }
}

class Edge {
    int v;
    int w;

    public Edge(int vertex, int weight) {
        this.v = vertex;
        this.w = weight;
    }

    @Override
    public String toString() {
        return v + "/ " + w;
    }
}

class EdgeComparator implements Comparator<Edge> {
    @Override
    public int compare(Edge e1, Edge e2) {
        int w1 = e1.w;
        int w2 = e2.w;
        return w1-w2;
    }

}