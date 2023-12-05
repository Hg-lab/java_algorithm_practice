package 프로그래머스.Level2.숫자_카드_나누기;
import java.util.*;

class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        int n = arrayA.length;

        int gcdA = arrayA.length == 1 ? arrayA[0] : getGCD(arrayA[0], arrayA[1]);
        int gcdB = arrayB.length == 1 ? arrayB[0] : getGCD(arrayB[0], arrayB[1]);

        for(int i = 1; i < n; i++) {
            gcdA = getGCD(gcdA, arrayA[i]);
            gcdB = getGCD(gcdB, arrayB[i]);
        }

        for(int i = 0; i < n; i++) {
            if(arrayB[i] % gcdA == 0) gcdA = 1;
            if(arrayA[i] % gcdB == 0) gcdB = 1;
        }

        return Math.max(gcdA, gcdB) == 1 ? 0 : Math.max(gcdA, gcdB);

    }

    private int getGCD(int x, int y) {
        if(x == 0) return y;
        int max = Math.max(x, y);
        int min = Math.min(x, y);
        return getGCD(max%min, min);
    }
}