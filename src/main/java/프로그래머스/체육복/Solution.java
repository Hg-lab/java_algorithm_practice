package 프로그래머스.체육복;

import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;

        Arrays.sort(lost);
        Arrays.sort(reserve);

        // 4 -> 3, 4 -> 5
        // n:전체학생, lost:도난당한 학생들의 번호, reserve:여벌 체육복 가진 학생,
        // 2 <= n <= 30
        // 1 <= reserve <= n
        // reserve만 빌려줄수있음, 도난당했을경우 1개만 남아 빌려줄수 없음

        HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();

        // 1. 모든 학생 1개씩 있다
        // 2. reserve 학생은 1개씩 더한다.
        // 3. lost 학생은 1개씩 뺀다.
        for(int stu = 1; stu <= n; stu++) {
            hm.put(stu,1);
            if(contains(reserve, stu)) {
                hm.put(stu, hm.get(stu)+1);
            }
            if(contains(lost, stu)) {
                hm.put(stu, hm.get(stu)-1);
            }
        }

        System.out.println("first = " + hm.toString());

        int hasNothing = 0, hasOne = 0, hasTwo = 0;
        for(int key : hm.keySet()) {
            if(hm.get(key) == 0) {
                if(hm.getOrDefault(key-1,99) == 2 ||
                        hm.getOrDefault(key+1,99) == 2) {
                    hm.put(key, 1);
                    if(hm.getOrDefault(key-1,99) == 2) hm.put(key-1,1);
                    else hm.put(key+1,1);
                }
            }
        }


        System.out.println("second = " + hm.toString());


        for(int key : hm.keySet()) {
            if(hm.get(key) > 0) {
                answer++;
            }
        }

        return answer;
    }

    public static boolean contains(int[] arr, int number) {
        return Arrays.stream(arr).anyMatch(i -> i == number);
    }

}