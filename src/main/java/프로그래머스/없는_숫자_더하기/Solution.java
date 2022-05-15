package 프로그래머스.없는_숫자_더하기;
import java.util.*;
import java.util.*;

class Solution {
    public int solution(int[] numbers) {
        int answer = -1;
        int sum = 0;
        Arrays.sort(numbers);

        System.out.println(Arrays.toString(numbers));
        for(int num = 0; num <= 9; num ++) {
            if (!contains(numbers,num)) {
                sum += num;
            }
        }
        answer = sum;

        return answer;
    }
    public static boolean contains(final int[] arr, final int key) {
        return Arrays.stream(arr).anyMatch(i -> i == key);
    }

}