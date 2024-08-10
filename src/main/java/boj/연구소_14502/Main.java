package boj.연구소_14502;

import java.io.*;
import java.util.*;

public class Main {

    static int N; // 세로
    static int M; // 가로
    static int[][] map;
    static boolean[][] visited;

    static List<Point> virusLocations = new ArrayList<>();
    static int barrierCount = 3;

    static int maxSafeCount;

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        N = Integer.parseInt(str.split(" ")[0]); // rMax
        M = Integer.parseInt(str.split(" ")[1]); // cMax

        map = new int[N][M];
        visited = new boolean[N][M];

        StringTokenizer st;
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] == 1) ++barrierCount;
                if (map[r][c] == 2) virusLocations.add(new Point(r, c));
            }
        }// 입력 완료

        int depth = 3;
        buildBarrierByDFS(depth, mapCopy(map));

        // 답안
        System.out.println(maxSafeCount);
    }

    private static void buildBarrierByDFS(int depth, int[][] map) {
        if (depth == 0) {
            int virusCount = infectByBFS(mapCopy(map));
            int safeCount = N * M - virusCount - barrierCount;
            maxSafeCount = Math.max(maxSafeCount, safeCount);
            return;
        }

        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[r].length; c++) {
                if (!visited[r][c] && map[r][c] == 0) {
                    map[r][c] = 1;
                    visited[r][c] = true;
                    buildBarrierByDFS(depth - 1, mapCopy(map));
                    visited[r][c] = false;
                    map[r][c] = 0;
                }
            }
        }



    }

    private static int infectByBFS(int[][] map) {
        int virusCount = 0;
        for (Point virusPoint : virusLocations) {
            Point startPoint = virusPoint;

            Queue<Point> q = new LinkedList<>();
            q.add(startPoint);
            while (!q.isEmpty()) {
                Point nowPoint = q.remove();
                ++virusCount;
                for (int i = 0; i < 4; i++) {
                    int r = nowPoint.r + dr[i];
                    int c = nowPoint.c + dc[i];
                    Point point = new Point(r, c);
                    if (point.isInBoundary(map) && point.isSafe(map)) {
                        point.infect(map);
                        q.add(point);
                    }
                }
            }
        }

        return virusCount;
    }

    private static int[][] mapCopy(int[][] map) {
        int[][] copiedMap = new int[map.length][map[0].length];
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[r].length; c++) {
                copiedMap[r][c] = map[r][c];
            }
        }
        return copiedMap;
    }
}

class Point {
    int r;
    int c;

    Point(int r, int c) {
        this.r = r;
        this.c = c;
    }

    public boolean isInBoundary(int[][] map) {
        if (r >= map.length || r < 0 || c >= map[0].length || c < 0) {
            return false;
        }
        return true;
    }

    public boolean isSafe(int[][] map) {
        if(map[r][c] == 0) return true;
        return false;
    }

    public void infect(int[][] map) {
        map[r][c] = 2;
    }
}


