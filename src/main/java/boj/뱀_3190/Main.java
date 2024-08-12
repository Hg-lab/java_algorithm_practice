package boj.뱀_3190;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int mapSizeN;
    static int appleNumK;
    static int rotateNumL;

    public static void main(String[] args) throws IOException {
        Snake snake = new Snake();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        mapSizeN = Integer.parseInt(br.readLine());
        int[][] map = new int[mapSizeN][mapSizeN];

        appleNumK = Integer.parseInt(br.readLine());
        for (int i = 0; i < appleNumK; i++) {
            String s = br.readLine();
            int r = Integer.parseInt(s.split(" ")[0]) - 1;
            int c = Integer.parseInt(s.split(" ")[1]) - 1;
            map[r][c] = 1; // apple
        }

        rotateNumL = Integer.parseInt(br.readLine());
        Map<Integer, Character> timeRotate = new HashMap<>();
        for (int i = 0; i < rotateNumL; i++) {
            String s = br.readLine();
            int time = Integer.parseInt(s.split(" ")[0]);
            char rotate = s.split(" ")[1].charAt(0);
            timeRotate.put(time, rotate);
        }
        // end - input
        TimeTable timeTable = new TimeTable(timeRotate);
        int nowTime = 0;
        while (true) {
            int[] direction = timeTable.getDirection(nowTime);
            snake.move(map, direction[0], direction[1]);
            if(snake.isOver) break;
            ++nowTime;
        }

        System.out.println(nowTime+1);
    }
}

class TimeTable {
    List<Character> directionOfSec = new ArrayList<>();
    TimeTable(Map<Integer, Character> timeRotate) {
        init(timeRotate);
    }

    // rotateRight -> R B L T
    // rotateLeft -> R T L B
    int[] getDirection(int nowTime) {
        char dir = directionOfSec.get(nowTime);
        if(dir == 'R') return new int[]{0, 1};
        if(dir == 'B') return new int[]{1, 0};
        if(dir == 'L') return new int[]{0, -1};
        if(dir == 'T') return new int[]{-1, 0};
        return null;
    }

    void init(Map<Integer, Character> timeRotate) {
        directionOfSec.add('R');
        for (int i = 1; i < 10001; i++) {
            Character c = timeRotate.getOrDefault(i, '?') ;
            char prevDir = directionOfSec.get(i - 1);
            if (c == 'D') {
                directionOfSec.add(rotateRight(prevDir));
            } else if (c == 'L') {
                directionOfSec.add(rotateLeft(prevDir));
            } else {
                directionOfSec.add(prevDir);
            }
        }
    }

    char rotateRight(char prevDir) {
        if(prevDir == 'R') return 'B';
        if(prevDir == 'B') return 'L';
        if(prevDir == 'L') return 'T';
        if(prevDir == 'T') return 'R';
        return 'R';
    }

    char rotateLeft(char prevDir) {
        if(prevDir == 'R') return 'T';
        if(prevDir == 'T') return 'L';
        if(prevDir == 'L') return 'B';
        if(prevDir == 'B') return 'R';
        return 'R';
    }
}

class Snake {
    LinkedList<Point> body = new LinkedList<>();
    boolean isOver = false;

    Snake(){
        body.add(new Point(0, 0));
    }

    public Point getHead() {
        return body.get(0);
    }

    public void checkIsOver(int[][] map) {
        if(!getHead().isInBoundary(map) || this.isHeadOnBody()) this.isOver = true;
    }

    public void move(int[][] map, int dr, int dc) {
        Point head = getHead();
        Point nextHead = new Point(head.r + dr, head.c + dc);
        // if apple
        if (nextHead.isInBoundary(map) && map[nextHead.r][nextHead.c] == 1) {
            stretch(nextHead);
            checkIsOver(map);
            nextHead.removeApple(map);
        } else {
            stretch(nextHead);
            checkIsOver(map);
            cutTail();
        }

    }

    void stretch(Point nextHead) {
        this.body.addFirst(nextHead);
    }

    void cutTail() {
        this.body.removeLast();
    }

    boolean isHeadOnBody() {
        Point head = getHead();
        for (int i = 1; i < body.size(); i++) {
            if(head.equals(body.get(i))) return true;
        }
        return false;
    }
}

class Point{
    int r;
    int c;

    Point(int r, int c) {
        this.r = r;
        this.c = c;
    }

    public boolean isInBoundary(int[][] map) {
        return r >= 0 && c >= 0 && r < map.length && c < map[0].length;
    }

    public boolean equals(Point point) {
        return this.r == point.r && this.c == point.c;
    }

    public void removeApple(int[][] map) {
        map[this.r][this.c] = 0;
    }
}