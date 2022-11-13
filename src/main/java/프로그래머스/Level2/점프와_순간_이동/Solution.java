package 프로그래머스.Level2.점프와_순간_이동;

import java.util.*;

public class Solution {
    public int solution(int n) {
        int ans = 0;

        int num = n;

        while(num > 1) {
            if(num % 2 == 1) {
                ++ans;
                --num;
            }
            num /= 2;
        }
        ++ans;
        return ans;
    }
}