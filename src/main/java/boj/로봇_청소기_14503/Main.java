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
            if (!point.isCleaned(map)) {
                point.clean(map);
                count++;
            }

            if (point.mustCleanAround(map)) {
                for (int i = 0; i < 4; i++) {
                    point.rotate();
                    if (point.mustCleanForward(map)) {
                        point.goForward(map);
                        break;
                    }
                }
            } else {
                if (point.canBackward(map)) {
                    point.goBackward(map);
                } else {
                    break;
                }
            }


        }
        System.out.println(count);

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

    public boolean mustCleanAround(int[][] map) {
        for (int d = 0; d < 4; d++) {
            int nextR = DirUtil.getNextR(this.r, d);
            int nextC = DirUtil.getNextC(this.c, d);
            if (isInBoundary(nextR, nextC, map) && map[nextR][nextC] == 0) {
                return true;
            }
        }
        return false;
    }

    private boolean isInBoundary(int r, int c, int[][] map) {
        return r >= 0 && c >= 0 && r < map.length && c < map[r].length;
    }

    public void rotate() {
        this.dir = DirUtil.getRotateDir(this.dir);
    }

    public boolean mustCleanForward(int[][] map) {
        int nextR = DirUtil.getNextR(this.r, this.dir);
        int nextC = DirUtil.getNextC(this.c, this.dir);
        if (isInBoundary(nextR, nextC, map)) {
            return map[nextR][nextC] == 0;
        }
        return false;
    }

    public void goForward(int[][] map) {
        int nextR = DirUtil.getNextR(this.r, this.dir);
        int nextC = DirUtil.getNextC(this.c, this.dir);
        if (isInBoundary(nextR, nextC, map)) {
            this.r = nextR;
            this.c = nextC;
        }
    }

    public void goBackward(int[][] map) {
        int backwardDir = DirUtil.getBackwardDir(this.dir);
        int nextR = DirUtil.getNextR(this.r, backwardDir);
        int nextC = DirUtil.getNextC(this.c, backwardDir);
        if (isInBoundary(nextR, nextC, map)) {
            this.r = nextR;
            this.c = nextC;
        }
    }

    public boolean canBackward(int[][] map) {
        int backwardDir = DirUtil.getBackwardDir(this.dir);
        int nextR = DirUtil.getNextR(this.r, backwardDir);
        int nextC = DirUtil.getNextC(this.c, backwardDir);
        if (isInBoundary(nextR, nextC, map)) {
            return map[nextR][nextC] != 1;
        }
        return false;
    }
}

class DirUtil {
    // 0123 북동남서
    final static int[][] DIR_ARR = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    // 0123 북동남서 -> 서북동남
    static int getRotateDir(int d) {
        switch (d) {
            case 0:
                return 3;
            case 1:
                return 0;
            case 2:
                return 1;
            case 3:
                return 2;
        }
        return -1;
    }

    static int getNextR(int r, int dir) {
        return r + DIR_ARR[dir][0];
    }
    static int getNextC(int c, int dir) {
        return c + DIR_ARR[dir][1];
    }

    // 0123 북동남서 -> 남서북동
    static int getBackwardDir(int dir) {
        switch (dir) {
            case 0:
                return 2;
            case 1:
                return 3;
            case 2:
                return 0;
            case 3:
                return 1;
        }
        return -1;
    }
}