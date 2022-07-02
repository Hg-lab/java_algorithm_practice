package 프로그래머스.Level1.H_Index;

import java.util.*;
// n = citations.length
// 1 <= n <= 1000
// citations[i] <= 10000
// 7 -> 0 0 0 0

// 1번 예시
// [5,4,3,8,10] => [10,8,5,4,3]
// index >= arr[index] = 4번째 인덱스
// 그러므로 h-index는 4

// 2번 예시
// [5,3,3,8,10] => [10,8,5,3,3]
// index >= arr[index] = 3번째 인덱스
// 그러므로 h-index는 3

// 3번예시
// [100,100,100]
// index >= arr[index] = 없음
// 그러므로 index가 제일 큰 2번째가 h-index



class Solution {

    public int solution(int[] citations) {
        int answer = 0;
        Arrays.sort(citations);
        int h = 0;
        for (int i = 0; i < citations.length; i++) {

            if (citations[i] >= citations.length-i) {
                h = citations.length-i;
                break;
            }
        }
        answer = h;
        return answer;
    }
}