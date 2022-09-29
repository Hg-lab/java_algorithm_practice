package 프로그래머스.Level2.k진수에서_소수_개수_구하기;
import java.util.*;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;

        StringBuilder sb = new StringBuilder();
        while(n > 0) {
            int x = n%k;
            n /= k;
            sb.insert(0, String.valueOf(x));
        }

        String[] numbers = sb.toString().split("0{1,}");

        for(String s: numbers) {
            if(isPrime(Long.parseLong(s))) ++answer;
        }
        return answer;
    }

    public boolean isPrime(long x) {
        if(x == 1) return false;
        if(x == 2) return true;
        if(x == 3) return true;
        for(int i = 2; i <= Math.sqrt(x); i++) {
            if(x % i == 0) return false;
        }
        return true;
    }
}