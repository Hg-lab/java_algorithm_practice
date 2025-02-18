package boj.미로탐색_2178;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};

    static char[][] MAP;
    static int N;
    static int M;

    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        MAP = new char[N][M];
        for (int r = 0; r < N; r++) {
            MAP[r] = br.readLine().toCharArray();
        }

        dfs(new Point(0, 0, 1), new boolean[N][M]);
        System.out.println(answer);
    }

    private static void dfs(Point nowPoint, boolean[][] visited) {
        if(nowPoint.dist >= answer) return;

        if (nowPoint.isDone()) {
            answer = Math.min(answer, nowPoint.dist);
            return;
        }

        for (int i = 0; i < 4; i++) {
            Point nextPoint = nowPoint.move(dr[i], dc[i]);
            if (!nextPoint.isOnBoundary()) continue;
            if (nextPoint.isOne() && !visited[nextPoint.r][nextPoint.c]) {
                nextPoint.visit(visited);
                dfs(nextPoint, visited);
                nextPoint.unVisit(visited);
            }
        }
    }

    static class Point {
        int r;
        int c;
        int dist;

        Point(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }

        Point move(int dr, int dc) {
            return new Point(this.r + dr, this.c + dc, this.dist + 1);
        }

        boolean isOne() {
            return MAP[this.r][this.c] == '1';
        }

        boolean isOnBoundary() {
            return r >= 0 && r < N && c >= 0 && c < M;
        }

        boolean isDone() {
            return r == N - 1 && c == M - 1;
        }

        public void visit(boolean[][] visited) {
            visited[this.r][this.c] = true;
        }

        public void unVisit(boolean[][] visited) {
            visited[this.r][this.c] = false;
        }

    }
}
