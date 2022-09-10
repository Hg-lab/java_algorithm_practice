package 프로그래머스.Level2.소수_찾기;

import java.util.*;

class Solution {
    List<Integer> numList = new ArrayList<>();
    boolean[] checked;

    public int solution(String numbers) {
        int answer = 0;
        checked = new boolean[numbers.length()];
        for(int i = 1; i <= numbers.length(); i++) {
            permutation(numbers, checked, new StringBuilder(), i);
        }

        permutation(numbers, checked, new StringBuilder(), 2);

        for(Integer n : numList) {
            if(!isPrime(n)) answer++;
        }
        return answer;
    }

    private void permutation(String numbers, boolean[] checked, StringBuilder sb, int r) {
        if(sb.length() == r) {
            if(!numList.contains(Integer.parseInt(sb.toString()))) {
                numList.add(Integer.parseInt(sb.toString()));
            }
            return;
        }
        for(int i = 0; i < numbers.length(); i++) {
            if(!checked[i]) {
                checked[i] = true;
                sb.append(numbers.charAt(i));
                permutation(numbers, checked, sb, r);
                checked[i] = false;
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }

    private boolean isPrime(int x) {
        if(x == 0) return true;
        if(x == 1) return true;

        for(int i = 2; i <= Math.sqrt(x); i++) {
            if(x % i == 0) {
                return true;
            }
        }
        return false;
    }
}