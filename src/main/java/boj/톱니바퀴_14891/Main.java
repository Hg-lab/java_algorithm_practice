package boj.톱니바퀴_14891;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int WHEEL_NUM = 4;
    static final int TOOTH_NUM = 8;
    static int[][] gears = new int[WHEEL_NUM][TOOTH_NUM];
    static int timesK;
    static int[][] directions;

    static int score;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < WHEEL_NUM; i++) {
            String[] arr = br.readLine().split("");
            for (int j = 0; j < TOOTH_NUM; j++) {
                gears[i][j] = Integer.parseInt(arr[j]);
            }
        }

        timesK = Integer.parseInt(br.readLine());
        directions = new int[timesK][2];
        for (int i = 0; i < timesK; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startGearIdx = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());
            directions[i][0] = startGearIdx;
            directions[i][1] = dir;
        }

        for (int i = 0; i < timesK; i++) {
            rotate(gears, directions[i][0], directions[i][1], 0);
        }
        computeScore(gears);
        System.out.println(score);
    }

    private static void rotate(int[][] gears, int gearIdx, int dir, int leftOrRight) {
        if (!isInBoundary(gearIdx)) {
            return;
        }

        int leftIdx = gearIdx - 1;
        if (leftOrRight <= 0 && isDifferent(gearIdx, leftIdx)) {
            rotate(gears, leftIdx, dir * (-1), -1);
        }

        int rightIdx = gearIdx + 1;
        if (leftOrRight >= 0 && isDifferent(gearIdx, rightIdx)) {
            rotate(gears, rightIdx, dir * (-1), 1);
        }

        // rotate
        int[] gear = gears[gearIdx];
        if (dir == -1) { // 반시계
            int first = gear[0];
            for (int i = 1; i < TOOTH_NUM; i++) {
                gear[i-1] = gear[i];
            }
            gear[TOOTH_NUM - 1] = first;
        } else {
            int last = gear[TOOTH_NUM-1];
            for (int i = TOOTH_NUM - 1; i >= 1; i--) {
                gear[i] = gear[i-1];
            }
            gear[0] = last;
        }
    }

    private static void computeScore(int[][] gears) {
        for (int i = 0; i < WHEEL_NUM; i++) {
            if (gears[i][0] == 1) {
                int newScore = (int) Math.pow(2, i);
                score += newScore;
            }
        }
    }

    private static boolean isInBoundary(int gearIdx) {
        return gearIdx >= 0 && gearIdx < WHEEL_NUM;
    }

    private static boolean isDifferent(int gearIdx, int nextIdx) {
        if (isInBoundary(gearIdx) && isInBoundary(nextIdx)) {

            if (gearIdx < nextIdx) {
                return gears[gearIdx][2] != gears[nextIdx][6];
            } else {
                return gears[gearIdx][6] != gears[nextIdx][2];
            }
        }
        return true;
    }
}