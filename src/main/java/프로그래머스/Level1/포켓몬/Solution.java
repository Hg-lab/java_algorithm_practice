package 프로그래머스.Level1.포켓몬;

import java.util.*;
// N 중에 N/2
// 다른 것 고르는 최대 가지수
// 1<= nums.length <= 10000
// 1<= nums[i] <= 200000
class Solution {
    public int solution(int[] nums) {
        int answer = 0;

        Arrays.sort(nums);

        int pick = nums.length/2;

        HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();

        // 포켓몬별 갯수 해시맵 생성
        for(int i = 0; i < nums.length; i++) {
            int n = 1;
            if(hm.containsKey(nums[i])) {
                n = hm.get(nums[i]) + 1;
            }
            hm.put(nums[i], n);
        }

        int cnt = 0;
        for(Integer key : hm.keySet()) {
            cnt++;
        }

        if(cnt >= pick) answer = pick;
        else answer = cnt;
        return answer;
    }
}
