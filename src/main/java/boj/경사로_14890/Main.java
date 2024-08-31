package boj.경사로_14890;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int L;
    static int[][] map;

    static int resCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        for (int r = 0; r < N; r++) {
            int[] way = map[r];
            if (canBeTrail(way)) {
                ++resCount;
            }
        }

        for (int c = 0; c < N; c++) {
            int[] way = new int[N];
            for (int r = 0; r < N; r++) {
                way[r] = map[r][c];
            }
            if (canBeTrail(way)) {
                ++resCount;
            }
        }

        System.out.println(resCount);
    }

    private static boolean canBeTrail(int[] way) {
        boolean[] visited = new boolean[N];

        for (int i = 0; i < N-1; i++) {
            int diff = way[i + 1] - way[i];
            if(Math.abs(diff) > 1) return false;

            if (diff == 1) { // 오르막길, i부터 - 방향
                int height = way[i];
                int start = i - L + 1;
                for (int j = start; j <= i; j++) {
                    if(j < 0 || visited[j] || way[j] != height) return false;
                }

                for (int j = start; j < i; j++) {
                    visited[j] = true;
                }
            }

            if (diff == -1) { // 내리막길, 다음(i+1)부터 + 방향
                int height = way[i + 1];
                for (int j = i+1; j <= i+L; j++) { // i+1 부터 L개 가능한지?
                    if(j >= N || visited[j] || way[j] != height) return false;
                }

                for (int j = i+1; j <= i+L; j++) { // i+1 부터 L개 가능한지?
                    visited[j] = true;
                }
            }
        }
        return true;
    }
}
