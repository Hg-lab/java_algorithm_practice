package boj.감시_15683;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[] up = {-1, 0};
    static int[] down = {1, 0};
    static int[] left = {0, -1};
    static int[] right = {0, 1};

    static int[][][][] d = {
            {{up}, {down}, {left}, {right}},
            {{up, down}, {left, right}},
            {{up, left}, {up, right}, {down, left}, {down, right}},
            {{down, left, right}, {down, left, up}, {down, right, up}, {up, left, right}},
            {{up, down, left, right}}
    };

    static int rN;
    static int cM;
    static int[][] map;
    static List<int[]> cctvs = new ArrayList<>();

    static int minSpace = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        rN = Integer.parseInt(st.nextToken());
        cM = Integer.parseInt(st.nextToken());
        map = new int[rN][cM];

        for (int r = 0; r < rN; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < cM; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (isCCTV(map[r][c])) {
                    cctvs.add(new int[]{r, c});
                }
            }
        }

        doCombination(copyMap(map), 0);

        System.out.println(minSpace);
    }

    private static int[][] copyMap(int[][] map) {
        int[][] copyMap = new int[map.length][map[0].length];
        for (int r = 0; r < map.length; r++) {
            copyMap[r] = map[r].clone();
        }
        return copyMap;
    }

    private static void doCombination(int[][] map, int cctvIdx) {
        if (cctvIdx == cctvs.size()) {
            computeMin(map);
            return;
        }

        int r = cctvs.get(cctvIdx)[0];
        int c = cctvs.get(cctvIdx)[1];
        int cctvType = map[r][c];
        int[][][] dirN = d[cctvType - 1];

        for (int[][] dirs : dirN) {
            doCombination(copyMap(doCensor(map, r, c, dirs)), cctvIdx + 1);
        }

    }

    private static int[][] doCensor(int[][] map, int startR, int startC, int[][] dirs) {
        for (int[] dir : dirs) {
            int r = startR;
            int c = startC;
            while (true) {
                r = r + dir[0];
                c = c + dir[1];

                if (!isInBoundary(r, c)) break;
                if (map[r][c] == 6) break;

                if (isCCTV(map[r][c])) continue;
                map[r][c] = 9; // # 처리
            }
        }
        return map;
    }

    private static boolean isCCTV(int n) {
        return 1 <= n && n <= 5;
    }

    private static boolean isInBoundary(int r, int c) {
        return r >= 0 && c >= 0 && r < rN && c < cM;
    }

    private static void computeMin(int[][] map) {
        int count = 0;
        for (int r = 0; r < rN; r++) {
            for (int c = 0; c < cM; c++) {
                if(map[r][c] == 0) ++count;
            }
        }
        minSpace = Math.min(count, minSpace);
    }
}
