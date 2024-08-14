package boj.주사위_굴리기_14499;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int rN;
    static int cM;
    static int rStartX;
    static int cStartY;
    static int commandCountK;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line1 = br.readLine().split(" ");
        rN = Integer.parseInt(line1[0]);
        cM = Integer.parseInt(line1[1]);
        rStartX = Integer.parseInt(line1[2]);
        cStartY = Integer.parseInt(line1[3]);
        commandCountK = Integer.parseInt(line1[4]);

        int[][] map = new int[rN][cM];
        for (int r = 0; r < rN; r++) {
            String[] str = br.readLine().split(" ");
            for (int c = 0; c < cM; c++) {
                map[r][c] = Integer.parseInt(str[c]);
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] commands = new int[commandCountK];
        for (int i = 0; i < commandCountK; i++) {
            commands[i] = Integer.parseInt(st.nextToken());
        }
        //input - end

        Dice dice = new Dice();
        Point point = new Point(rStartX, cStartY);
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < commandCountK; i++) {
            int command = commands[i];
            char dirChar = DirectionUtil.compute(command);
            Point nextPoint = point.getNextPoint(dirChar);
            if (!nextPoint.isInBoundary(map)) {
                continue;
            }
            dice.roll(dirChar);
            dice.copyNumber(map, nextPoint);
            res.add(dice.getTopNum());

            point = nextPoint;
        }

        //output
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }

    }
}

/*
* 0
*123
* 4
* 5
* */
// bottom: 2
// top: 5
class Dice {
    int[] numbers = new int[6];

    int getTopNum() {
        return numbers[5];
    }

    int getBottomNum() {
        return numbers[2];
    }

    void setBottomNum(int num) {
        numbers[2] = num;
    }

    void roll(char direction) {
        int[] n = new int[6];
        if (direction == 'E') {
            n[0] = numbers[0]; n[1] = numbers[2]; n[2] = numbers[3]; n[3] = numbers[5]; n[4] = numbers[4]; n[5] = numbers[1];
        } else if (direction == 'W') {
            n[0] = numbers[0]; n[1] = numbers[5]; n[2] = numbers[1]; n[3] = numbers[2]; n[4] = numbers[4]; n[5] = numbers[3];
        } else if (direction == 'N') {
            n[0] = numbers[5]; n[1] = numbers[1]; n[2] = numbers[0]; n[3] = numbers[3]; n[4] = numbers[2]; n[5] = numbers[4];
        } else if (direction == 'S') {
            n[0] = numbers[2]; n[1] = numbers[1]; n[2] = numbers[4]; n[3] = numbers[3]; n[4] = numbers[5]; n[5] = numbers[0];
        }
        this.numbers = n;
    }

    void copyNumber(int[][] map, Point point) {
        int mapNum = map[point.r][point.c];
        if (mapNum == 0) {
            map[point.r][point.c] = getBottomNum();
        } else {
            setBottomNum(mapNum);
            map[point.r][point.c] = 0;
        }
    }
}

class Point {
    int r;
    int c;

    Point(int r, int c) {
        this.r = r;
        this.c = c;
    }

    boolean isInBoundary(int[][] map) {
        return r >= 0 && c >= 0 && r < map.length && c < map[0].length;
    }

    public Point getNextPoint(char dir) {
        if (dir == 'E') {
            return new Point(r, c + 1);
        } else if (dir == 'W') {
            return new Point(r, c - 1);
        } else if (dir == 'N') {
            return new Point(r - 1, c);
        } else if (dir == 'S') {
            return new Point(r + 1, c);
        }
        return null;
    }
}

class DirectionUtil {
    static char compute(int command) {
        switch (command) {
            case 1:
                return 'E';
            case 2:
                return 'W';
            case 3:
                return 'N';
            case 4:
                return 'S';
        }
        return 'X';
    }
}
