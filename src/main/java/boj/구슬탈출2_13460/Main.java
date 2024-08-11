package boj.구슬탈출2_13460;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int rMaxN;
    static int cMaxM;

    static final char[] DIRECTIONS = {'L', 'R', 'T', 'B'};

    static GameMap gameMap;

    static int result = 11;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        rMaxN = Integer.parseInt(str.split(" ")[0]);
        cMaxM = Integer.parseInt(str.split(" ")[1]);

        char[][] initialMap = new char[rMaxN][cMaxM];
        Point red = null;
        Point blue = null;
        Point hole = null;
        for (int r = 0; r < rMaxN; r++) {
            String s = br.readLine();
            for (int c = 0; c < cMaxM; c++) {
                if(s.charAt(c) == 'R') red = new Point(r, c);
                if(s.charAt(c) == 'B') blue = new Point(r, c);
                if(s.charAt(c) == 'O') hole = new Point(r, c);

                initialMap[r][c] = s.charAt(c);
            }
        }
        gameMap = new GameMap(initialMap, red, blue, hole);

        dfs(gameMap, 10);
        if(result == 11) result = -1;
        System.out.println(result);
    }

    private static void dfs(GameMap gameMap, int depth) {
        if (depth == -1) {
            return;
        }
        if (gameMap.isLose()) {
            return;
        }
        if(gameMap.isWin()) {
            result = Math.min(result, 10 - depth);
            return;
        }

        dfs(gameMap.tilt(DIRECTIONS[0]), depth - 1);
        dfs(gameMap.tilt(DIRECTIONS[1]), depth - 1);
        dfs(gameMap.tilt(DIRECTIONS[2]), depth - 1);
        dfs(gameMap.tilt(DIRECTIONS[3]), depth - 1);


    }
}

class GameMap {
    char[][] map;
    Point red;
    Point blue;
    Point hole;

    GameMap(char[][] map, Point red, Point blue, Point hole) {
        this.map = map;
        this.red = red;
        this.blue = blue;
        this.hole = hole;
    }

    GameMap tilt(char direction) {
        int dr = 0;
        int dc = 0;
        if(direction == 'L') {
            dr = 0;
            dc = -1;
        }
        if(direction == 'R') {
            dr = 0;
            dc = 1;
        }
        if (direction == 'T') {
            dr = -1;
            dc = 0;
        }
        if (direction == 'B') {
            dr = 1;
            dc = 0;
        }

        Point movedRed = red.tilt(this.map, dr, dc);
        Point movedBlue = blue.tilt(this.map, dr, dc);

        return refreshMap(movedRed, movedBlue);
    }

    GameMap refreshMap(Point movedRed, Point movedBlue) {
        char[][] newMap = new char[this.map.length][this.map[0].length];

        for (int r = 0; r < this.map.length; r++) {
            for (int c = 0; c < this.map[r].length; c++) {
                newMap[r][c] = this.map[r][c];
            }
        }
        newMap[red.r][red.c] = '.';
        newMap[blue.r][blue.c] = '.';

        newMap[movedRed.r][movedRed.c] = 'R';
        newMap[movedBlue.r][movedBlue.c] = 'B';

        return new GameMap(newMap, movedRed, movedBlue, this.hole);
    }

    boolean isWin() {
        return red.equals(hole) && !isLose();
    }

    boolean isLose() {
        return blue.equals(hole);
    }
}

class Point {
    int r;
    int c;

    Point(int r, int c) {
        this.r = r;
        this.c = c;
    }

    boolean equals(Point point) {
        return this.r == point.r && this.c == point.c;
    }

    public Point tilt(char[][] map, int dr, int dc) {
        int nextR = this.r;
        int nextC = this.c;
        boolean anotherBall = false;
        while (true) {
            nextR += dr;
            nextC += dc;
            char c = map[nextR][nextC];
            if (c == 'O') {
                return new Point(nextR, nextC);
            }
            if (c != '#' && c != '.') {
                anotherBall = true;
            }
            if (c == '#') {
                break;
            }
        }
        if(anotherBall) {
            dr *= 2;
            dc *= 2;
        }

        return new Point(nextR - dr, nextC - dc);
    }
}