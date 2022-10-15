package 프로그래머스.Level3.합승_택시_요금;
import java.util.*;

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int x = solution.solution(6,4,6,2,new int[][]{{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}}
        );
        System.out.println(x);
    }
}

class Solution {
    boolean[] checked;
    List<List<Edge>> graph = new ArrayList<>();
    int n;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;

        // global variables
         this.n = n;

        for(int i = 0; i <= n; i++)
            graph.add(new ArrayList<>());

        for(int[] fare: fares) {
            int start = fare[0];
            int end = fare[1];
            int cost = fare[2];

            graph.get(start).add(new Edge(end,cost));
            graph.get(end).add(new Edge(start,cost));
        }

        int[] fromS = dijkstra(s);
        int[] fromA = dijkstra(a);
        int[] fromB = dijkstra(b);

        // i: where to split
        for(int i = 1; i <= n; i++){
            if(answer > fromS[i] + fromA[i] + fromB[i]){
                answer = fromS[i] + fromA[i] + fromB[i];
            }
        }

        return answer;
    }

    private int[] dijkstra(int start) {
        checked = new boolean[n+1];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));
        int[] distance = new int[n+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        while(!pq.isEmpty()) {
            Edge edge = pq.poll();
            if(distance[edge.v] < edge.c) continue;
            distance[edge.v] = edge.c;

            for(Edge next: graph.get(edge.v)) {
                int cost = edge.c + next.c;
                if(distance[next.v] < cost) continue;
                pq.add(new Edge(next.v, cost));
            }
        }
        return distance;
    }
}

class Edge implements Comparable<Edge>{
    int v;
    int c;
    public Edge(int vertex, int cost) {
        v = vertex;
        c = cost;
    }

    @Override
    public int compareTo(Edge e1) {
        return this.c - e1.c;
    }


    public String toString() {
        return v + " / " + c;
    }
}