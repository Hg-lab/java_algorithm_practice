package 프로그래머스.Level2.우박수열_정적분;

import java.util.*;

class Solution {
    public double[] solution(int k, int[][] ranges) {
        double[] answer = {};
        List<Integer> numList = getN(k, new ArrayList<>());
        int n = numList.size()-1;

        List<Double> answerList = new ArrayList<>();
        for(int[] range: ranges){
            int x1 = range[0];
            int x2 = n + range[1];
            answerList.add(getArea(numList, x1, x2));
        }

        return answer = answerList.stream().mapToDouble(Double::doubleValue).toArray();
    }

    private List<Integer> getN(int k, List<Integer> list) {
        list.add(k);
        if(k <= 1) return list;
        if(k % 2 == 0) k /= 2;
        else k = k*3 + 1;
        return getN(k, list);
    }

    private double getArea(List<Integer> list, int x1, int x2) {
        if(x1 == x2) return 0d;
        if(x1 > x2) return -1d;

        double res = 0d;

        for(int i = x1; i < x2; i++) {
            res += (double)(list.get(i) + list.get(i+1))/2d;
        }
        return res;
    }
}