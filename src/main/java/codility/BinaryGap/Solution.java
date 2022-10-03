package codility.BinaryGap;

// you can also use imports, for example:
import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
    public int solution(int N) {
        String binN = Integer.toBinaryString(N);
        int maxGap = 0;
        int count = 0;
        int start = 0;
        for(int i = 1; i < binN.length(); i++) {
            if(binN.charAt(i) == '1') {
                maxGap = Math.max(count, maxGap);
                count = 0;
            } else ++count;
        }
        return maxGap;
    }
}
