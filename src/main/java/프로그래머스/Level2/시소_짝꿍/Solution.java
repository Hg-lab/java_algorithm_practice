package 프로그래머스.Level2.시소_짝꿍;


import java.util.HashMap;
import java.util.Map;

class Main {
    public static void main(String[] args) {
        long solution = new Solution().solution(new int[]{100, 180, 360, 100, 270});
    }
}
public class Solution {

    int[] weights;
    long answer = 0;
    public long solution(int[] weights) {
        long answer = 0;
        this.weights = weights;

        Map<Integer, Long> weightCount= new HashMap<>();
        for (int weight : weights) {
            weightCount.put(weight, weightCount.getOrDefault(weight, 0L) + 1);
        }

        int[] weightArray = weightCount.keySet().stream().mapToInt(Integer::intValue).toArray();
        for (int i = 0; i < weightArray.length; i++) {
            for (int j = i+1; j < weightArray.length; j++) {
                if (isPartner(weightArray[i], weightArray[j])) {
                    answer += weightCount.get(weightArray[i]) * weightCount.get(weightArray[j]);
                }
            }
            if (weightCount.get(weightArray[i]) > 1) {
                answer += (weightCount.get(weightArray[i]) * (weightCount.get(weightArray[i]) - 1)) / 2;
            }
        }

        return answer;
    }

    private boolean isPartner(int a, int b) {
        if(a==b) return true;
        if (a < b) {
            int temp = a;
            a = b;
            b = temp;
        }

        // when a > b
        if (a == 2 * b) return true;
        else if (2 * a == 3 * b) return true;
        else if (3 * a == 4 * b) return true;
        else return false;
    }
}
