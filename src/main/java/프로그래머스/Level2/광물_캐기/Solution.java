package 프로그래머스.Level2.광물_캐기;

import java.util.*;

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int solution1 = solution.solution(new int[]{1, 3, 2}, new String[]{"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"});
        System.out.println("solution1 = " + solution1);
    }
}

class Solution {
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;

        int totalPicks = picks[0] + picks[1] + picks[2];
        List<int[]> fatigueAmountList = new ArrayList<>();
        int[] fatigueAmount = new int[3];
        for (int i = 0; i < minerals.length && fatigueAmountList.size() < totalPicks; i++) {
            String mineral = minerals[i];
            if (i > 0 && i % 5 == 0 && fatigueAmountList.size() < totalPicks) {
                fatigueAmountList.add(fatigueAmount);
                fatigueAmount = new int[3];
            }

            if(mineral.equals("diamond")) {
                fatigueAmount[0] += 1;
                fatigueAmount[1] += 5;
                fatigueAmount[2] += 25;
            } else if (mineral.equals("iron")) {
                fatigueAmount[0] += 1;
                fatigueAmount[1] += 1;
                fatigueAmount[2] += 5;
            } else if (mineral.equals("stone")) {
                fatigueAmount[0] += 1;
                fatigueAmount[1] += 1;
                fatigueAmount[2] += 1;
            }

            if (i == minerals.length - 1) {
                fatigueAmountList.add(fatigueAmount);
            }
        }

        Collections.sort(fatigueAmountList, (o1, o2) -> o2[2] - o1[2]);

        for (int[] amt : fatigueAmountList) {
            System.out.println("amt = " + amt[2]);
            if (picks[0] > 0) {
                answer += amt[0];
                picks[0]--;
            } else if (picks[1] > 0) {
                answer += amt[1];
                picks[1]--;
            } else if (picks[2] > 0) {
                answer += amt[2];
                picks[2]--;
            }
        }

        return answer;
    }
}