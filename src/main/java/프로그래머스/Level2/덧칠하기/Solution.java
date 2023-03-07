package 프로그래머스.Level2.덧칠하기;

import java.util.*;

class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(4 == s.solution(10, 2, new int[]{1, 3, 5, 6, 7}));
    }
}
class Solution {

    Map<Integer, Boolean> doneSectionMap;

    public int solution(int n, int m, int[] section) {
        int answer = 0;
        this.doneSectionMap = getSectionMap(section);

        // painting
        int startSection = section[0];
        for(int i = startSection; i <= n; ++i) {
            if(doneSectionMap.getOrDefault(i, true)) continue;
            if(!doneSectionMap.keySet().contains(i)) continue;
            answer++;
            doPaint(i, m);
        }
        return answer;
    }

    private Map getSectionMap(int[] section) {
        Map<Integer, Boolean> map = new HashMap<>();
        for(int s: section) {
            map.put(s, false);
        }
        return map;
    }

    private void doPaint(int startSection, int m) {
        for(int i = startSection; i < startSection + m; ++i) {
            if(doneSectionMap.keySet().contains(i)) {
                this.doneSectionMap.put(i, true);
            }
        }
    }
}

class Short_Code_Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 0;
        int maxPaintedSection = 0;
        for(int i = 0; i < section.length; i++) {
            int startSection = section[i];
            if(maxPaintedSection < startSection) {
                answer++;
                maxPaintedSection = startSection + m - 1;
            }

        }

        return answer;
    }
}