package boj.game2048easy_12100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int maxNumResult = 0;
    static int N = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N];
        int maxNum = 0;
        for (int r = 0; r < N; r++) {
            String[] str = br.readLine().split(" ");
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(str[c]);
                maxNum = Math.max(map[r][c], maxNum);
            }
        }
        GameMap gameMap = new GameMap(map, maxNum);
        game(5, gameMap);

        System.out.println(maxNumResult);
    }

    private static void game(int count, GameMap gameMap) {
        if (count == 0) {
            maxNumResult = Math.max(gameMap.maxNum, maxNumResult);
            return;
        }

        game(count - 1, move(gameMap, 'U'));
        game(count - 1, move(gameMap, 'D'));
        game(count - 1, move(gameMap, 'L'));
        game(count - 1, move(gameMap, 'R'));
    }

    private static GameMap move(GameMap gameMap, char direction) {
        int[][] map = gameMap.map;

        GameMap newGameMap = new GameMap(new int[N][N], gameMap.maxNum);
        int[][] newMap = newGameMap.map;

        if (direction == 'U') {
            for (int c = 0; c < N; c++) {
                int nextIdx = 0;
                int baseBlock = -1;
                for (int r = 0; r < N; r++) {
                    if(map[r][c] == 0) continue;
                    if (baseBlock == map[r][c]) {
                        newMap[nextIdx - 1][c] = map[r][c] * 2;
                        baseBlock = -1;
                        newGameMap.resetMaxNum(newMap[nextIdx - 1][c]);
                    } else {
                        newMap[nextIdx++][c] = map[r][c];
                        baseBlock = map[r][c];
                    }
                }
            }
            return newGameMap;
        }
        if (direction == 'D') {
            for (int c = 0; c < N; c++) {
                int nextIdx = N - 1;
                int baseBlock = -1;
                for (int r = N - 1; r >= 0; r--) {
                    if (map[r][c] == 0) continue;
                    if (baseBlock == map[r][c]) {
                        newMap[nextIdx + 1][c] = map[r][c] * 2;
                        baseBlock = -1;
                        newGameMap.resetMaxNum(newMap[nextIdx + 1][c]);
                    } else {
                        newMap[nextIdx--][c] = map[r][c];
                        baseBlock = map[r][c];
                    }
                }
            }
            return newGameMap;
        }
        if (direction == 'L') {
            for (int r = 0; r < N; r++) {
                int nextIdx = 0;
                int baseBlock = -1;
                for (int c = 0; c < N; c++) {
                    if(map[r][c] == 0) continue;
                    if (baseBlock == map[r][c]) {
                        newMap[r][nextIdx - 1] = map[r][c] * 2;
                        baseBlock = -1;
                        newGameMap.resetMaxNum(newMap[r][nextIdx - 1]);
                    } else {
                        newMap[r][nextIdx++] = map[r][c];
                        baseBlock = map[r][c];
                    }
                }
            }
            return newGameMap;
        }
        if (direction == 'R') {
            for (int r = 0; r < N; r++) {
                int nextIdx = N - 1;
                int baseBlock = -1;
                for (int c = N - 1; c >= 0; c--) {
                    if (map[r][c] == 0) continue;
                    if (baseBlock == map[r][c]) {
                        newMap[r][nextIdx + 1] = map[r][c] * 2;
                        baseBlock = -1;
                        newGameMap.resetMaxNum(newMap[r][nextIdx + 1]);
                    } else {
                        newMap[r][nextIdx--] = map[r][c];
                        baseBlock = map[r][c];
                    }
                }
            }
            return newGameMap;
        }

        return newGameMap;
    }
}

class GameMap {
    int[][] map;
    int maxNum;

    public GameMap(int[][] map, int maxNum) {
        this.map = map;
        this.maxNum = maxNum;
    }

    public void resetMaxNum(int num) {
        this.maxNum = Math.max(this.maxNum, num);
    }
}
