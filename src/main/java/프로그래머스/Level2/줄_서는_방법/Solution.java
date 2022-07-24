package 프로그래머스.Level2.줄_서는_방법;

// n = 4, k = 10
// 4! = 24
// 1234
// 1243
// 1324
// 1342
// 1423
// 1432
// 2134
// 2143
import java.util.*;
class Solution {
    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        long[] totalFactNum = getFactorialNumber(n);
        List<Integer> numberList = initList(n);
        List<Integer> result = new ArrayList<>();

        while(true) {
            long num = totalFactNum[n-1];

            if(k % num == 0) {
                result.add(numberList.remove((int) (k/num)-1));
                break;
            } else {
                result.add(numberList.remove((int) (k/num)));
                k %= num;
            }
            --n;
        }
        if(!numberList.isEmpty()) {
            for(int i = numberList.size() - 1; i >= 0; i--) {
                result.add(numberList.get(i));
            }
        }

        answer = result.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }

    private List<Integer> initList(int n) {
        List<Integer> list = new ArrayList<>();
        for(int i = 1; i <= n ; i++) {
            list.add(i);
        }

        return list;
    }

    // Get total counts, doing factorial.
    private long[] getFactorialNumber(int n) {
        long[] dp = new long[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n; i++) {
            dp[i] = i * dp[i-1];
        }

        return dp;
    }
}