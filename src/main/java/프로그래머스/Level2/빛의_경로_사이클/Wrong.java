package 프로그래머스.Level2.빛의_경로_사이클;

import java.util.*;

class Main {
    public static void main(String[] args) {
        int[] solution = new Wrong().solution(new String[]{"SL", "LR"});
    }
}

class Wrong {
    class Point {
        int row;
        int col;
        String dir;

        Point(int row, int col, String dir) {
            this.row = row;
            this.col = col;
            this.dir = dir;
        }


    }

    int rMax;
    int cMax;
    Map<String, int[]> map = new HashMap<>();
    String[] grid;
    public int[] solution(String[] grid) {
        int[] answer = {};
        this.grid = grid;
        this.rMax = grid.length;
        this.cMax = grid[0].length();
        init();
        Set<String> checked = new HashSet<>();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length(); j++) {
                Point start = new Point(i, j, String.valueOf(grid[i].charAt(j)));
//                dfs(start, checked);
                dfs(start, list);
            }
        }
        return answer;
    }

    int[] dx = new int[]{0, 1, 0, -1};
    int[] dy = new int[]{1, 0, -1, 0};
    //checked [from][to]
//    void dfs(Point prev, Set<String> checked) {
    void dfs(Point prev, List<String> checked) {
        for (int i = 0; i < 4; i++) {
            int nowR = fix(prev.row + dx[i], rMax);
            int nowC = fix(prev.col + dy[i], cMax);
            String fromTo = String.valueOf(prev.row) + prev.col + nowR + nowC;
            if (checked.contains(fromTo)) {
                return;
            }
            checked.add(fromTo);
            dfs(nextPointFrom(prev, new Point(nowR, nowC, String.valueOf(grid[nowR].charAt(nowC)))), checked);
//            checked.remove(fromTo);
        }
    }


    private Point nextPointFrom(Point prev, Point now) {
        String fromDir = getDirFrom(prev, now);
        String dir = now.dir;
        int row = fix(prev.row + map.get(fromDir + dir)[0], rMax);
        int col = fix(prev.col + map.get(fromDir + dir)[1], cMax);
        return new Point(row, col, String.valueOf(grid[row].charAt(col)));
    }

    String getDirFrom(Point prev, Point now) {
        int rDiff = fixDiff(now.row - prev.row, rMax);
        int cDiff = fixDiff(now.col - prev.col, cMax);
        if (rDiff == 0 && cDiff == 1) {
            return "left";
        } else if (rDiff == 0 && cDiff == -1) {
            return "right";
        } else if (rDiff == 1 && cDiff == 0) {
            return "upper";
        } else if (rDiff == -1 && cDiff == 0) {
            return "lower";
        }
        return "";
    }

    int fix(int idx, int size) {
        if(idx == 0) return 0;
        if(idx < 0) return idx + size;
        if(idx >= size) return idx - size;
        return idx;
    }

    int fixDiff(int diff, int max) {
        if (diff == 0) return 0;
        if (Math.abs(diff) > 1) {
            if (diff < 0) {
                return max - Math.abs(diff);
            } else {
                return -max + Math.abs(diff);
            }
        }
        return diff;
    }

    private void init() {
        map.put("leftL", new int[]{-1, 1});
        map.put("rightL", new int[]{1, -1});
        map.put("lowerL", new int[]{-1, -1});
        map.put("upperL", new int[]{1, 1});
        map.put("leftR", new int[]{1, 1});
        map.put("rightR", new int[]{-1, -1});
        map.put("lowerR", new int[]{-1, 1});
        map.put("upperR", new int[]{1, -1});
        map.put("leftS", new int[]{0, 2});
        map.put("rightS", new int[]{0, -2});
        map.put("lowerS", new int[]{-2, 0});
        map.put("upperS", new int[]{2, 0});
    }

}