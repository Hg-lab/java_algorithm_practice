package 프로그래머스.Level2.빛의_경로_사이클;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Main2 {
    public static void main(String[] args) {
        new Solution().solution(new String[]{"SL", "LR"});
    }
}

class Solution {
    int rSize;
    int cSize;
    int[][] move = new int[][]{{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
    Set<String> checked = new HashSet<>();
    String[] grid;
    public int[] solution(String[] grid) {
        this.grid = grid;
        this.rSize = grid.length;
        this.cSize = grid[0].length();

        List<Integer> answer = new ArrayList<>();

        for (int row = 0; row < rSize; row++) {
            for (int col = 0; col < cSize; col++) {
                for (int m = 0; m < move.length; m++) {
                    String way = getWay(row, col, m);
                    if (!checked.contains(way)) {
                        answer.add(light(row, col, m));
                    }
                }
            }
        }

        return answer.stream().sorted().mapToInt(i->i).toArray();
    }

    private int light(int row, int col, int moveIdx) {
        int count = 0;

        int nextRow = (row + move[moveIdx][0] + rSize) % rSize;
        int nextCol = (col + move[moveIdx][1] + cSize) % cSize;
        while (checked.add(getWay(row, col, moveIdx))) {
            count++;
            row = nextRow;
            col = nextCol;
            if (grid[nextRow].charAt(nextCol) == 'L') {
                moveIdx = (moveIdx + 3) % 4;
                nextRow = row + move[moveIdx][0];
                nextCol = col + move[moveIdx][1];
            } else if (grid[nextRow].charAt(nextCol) == 'R') {
                moveIdx = (moveIdx + 1) % 4;
                nextRow = row + move[moveIdx][0];
                nextCol = col + move[moveIdx][1];
            } else {
                nextRow = row + move[moveIdx][0];
                nextCol = col + move[moveIdx][1];
            }
            nextRow = (nextRow + rSize) % rSize;
            nextCol = (nextCol + cSize) % cSize;

        }
        return count;
    }

    private String getWay(int row, int col, int m) {
        return "row"+ row + "col"+ col + "way" + m;
    }

}