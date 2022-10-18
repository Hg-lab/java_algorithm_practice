package 프로그래머스.Level3.n_queens;

import java.util.*;

class Solution {
    int count = 0;

    Set<Integer> negativeDiagonal;
    Set<Integer> positiveDiagonal;

    public int solution(int n) {
        int answer = 0;

        for(int i = 0; i < n; i++) {
            boolean[] checked = new boolean[n];
            checked[i] = true; // 세로 기준 겹치면 안됨

            List<Integer> list = new ArrayList<>();
            list.add(i); // 첫쨋줄 입력


            negativeDiagonal = new HashSet<>();
            positiveDiagonal = new HashSet<>();

            negativeDiagonal.add(0-i);
            positiveDiagonal.add(0+i);

            bt(i, checked, n, list);
        }

        return answer = this.count;
    }

    private void bt(int first, boolean[] checked, int n, List<Integer> list) {
        if(list.size() == n) {

            // if(isValid(list)) {
            // System.out.println(list.toString());
            this.count++;
            // }
            return;
        }

        for(int i = 0; i < n; i++) {
            if(checked[i]) continue;
            if(negativeDiagonal.contains(list.size() - i)) continue;
            if(positiveDiagonal.contains(list.size() + i)) continue;

            negativeDiagonal.add(list.size() - i);
            positiveDiagonal.add(list.size() + i);

            checked[i] = true;
            list.add(i);

            bt(first, checked, n, list);

            checked[i] = false;
            list.remove(list.size()-1);

            negativeDiagonal.remove(list.size() - i);
            positiveDiagonal.remove(list.size() + i);

        }
    }

    // 시간복잡도 문제로 미사용
    private boolean isValid(List<Integer> list) {
        negativeDiagonal = new HashSet<>();
        positiveDiagonal = new HashSet<>();

        for(int i = 0; i < list.size(); i++) {

            int negative = i - list.get(i);
            int positive = i + list.get(i);

            if(!negativeDiagonal.add(negative)) return false;
            if(!positiveDiagonal.add(positive)) return false;
        }
        return true;
    }
}