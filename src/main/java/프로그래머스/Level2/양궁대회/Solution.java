package 프로그래머스.Level2.양궁대회;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Main {
    public static void main(String[] args) {
        int[] solution = new Solution().solution(10, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3});
        for (int i : solution) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}

class Solution {

    int[] info;
    List<Point> ryanPointList = new ArrayList<>();

    public int[] solution(int n, int[] info) {
        this.info = info;
        dfs(new int[11], n, 0);
        sort(ryanPointList);
        return ryanPointList.size() == 0 ? new int[]{-1} : ryanPointList.get(0).aimInfo;
    }

    private void dfs(int[] ryanInfo, int n, int start) {
        if(n <= 0) {
            int[] points = getPoints(ryanInfo, info);
            int[] minAim = getMinAimAndCount(ryanInfo);
            if(points[0] > points[1])
                ryanPointList.add(new Point(points[0] - points[1], ryanInfo.clone(), minAim[0], minAim[1]));
            return;
        }

        for(int i = start; i < ryanInfo.length; i++) {
            ryanInfo[i]++;
            dfs(ryanInfo, n - 1, i);
            ryanInfo[i]--;
        }
    }
    private int[] getPoints(int[] ryanInfo, int[] info) {
        int ryanPoint = 0;
        int apeachPoint = 0;
        for (int i = 0; i < ryanInfo.length; i++) {
            if(ryanInfo[i] > info[i]) ryanPoint += 10 - i;
            else if(info[i] != 0) apeachPoint += 10 - i;
        }
        // 0: ryanPoint, 1: apeachPoint
        return new int[]{ryanPoint, apeachPoint};
    }

    private int[] getMinAimAndCount(int[] info) {
        int minAim = Integer.MAX_VALUE;
        int minAimCount = 0;
        for (int i = 0; i < info.length; i++) {
            if(info[i] > 0 && minAim > 10 - i) {
                minAim = 10 - i;
                minAimCount = info[i];
            }
        }
        return new int[]{minAim, minAimCount};
    }

    private void sort(List<Point> ryanPointList) {
        Collections.sort(ryanPointList, (r1, r2) -> {
            if(r1.gap == r2.gap) {
                if(r1.minAim == r2.minAim) return r2.minAimCount - r1.minAimCount;
                return r1.minAim - r2.minAim;
            }
            return r2.gap - r1.gap;
        });
    }

    class Point{

        int gap;
        int[] aimInfo;
        int minAim;
        int minAimCount;

        Point(int gap, int[] aimInfo, int minAim, int minAimCount) {
            this.gap = gap;
            this.aimInfo = aimInfo;
            this.minAim = minAim;
            this.minAimCount = minAimCount;
        }
    }
}
