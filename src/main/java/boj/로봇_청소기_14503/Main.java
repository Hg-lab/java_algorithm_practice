package boj.로봇_청소기_14503;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int rN;
    static int cM;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        rN = Integer.parseInt(st.nextToken());
        cM = Integer.parseInt(st.nextToken());
        map = new int[rN][cM];

        st = new StringTokenizer(br.readLine());
        int robotR = Integer.parseInt(st.nextToken());
        int robotC = Integer.parseInt(st.nextToken());
        int robotDir = Integer.parseInt(st.nextToken());

        for (int r = 0; r < rN; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < cM; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        Point point = new Point(robotR, robotC, robotDir);
        int count = 0;
        while (true) {
            if (point.isCleaned(map)) {
                point.clean(map);
                count++;
            }

            if (!point.mustCleanAround()) {
                if (point.canBackward()) {
                    point.goBackward();
                    continue;
                }
                break;
            } else {
                point.rotate();
                if (point.mustCleanForward()) {
                    point.goForward();
                }
            }
        }
        /*
        * 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
            현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우,
                바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.
                바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
            현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우,
                반시계 방향으로 90도 회전한다.
                바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
                1번으로 돌아간다.
        *
        * */

    }


}

class Point {
    int r;
    int c;
    int dir;

    public Point(int r, int c, int dir) {
        this.r = r;
        this.c = c;
        this.dir = dir;
    }

    public boolean isCleaned(int[][] map) {
        return map[r][c] == 2;
    }

    public void clean(int[][] map) {
        map[r][c] = 2;
    }

    public boolean mustCleanAround() {
        return false;
    }

    public void rotate() {

    }

    public boolean mustCleanForward() {
        return false;
    }

    public void goForward() {

    }

    public void goBackward() {

    }

    public boolean canBackward() {
        return false;
    }
}