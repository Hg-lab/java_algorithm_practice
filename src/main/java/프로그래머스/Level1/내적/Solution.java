package 프로그래머스.Level1.내적;
class Solution {
    public int solution(int[] a, int[] b) {
        int answer = 0;

        // 내적 : a[0]*b[0] + a[1]*b[1] + ... + a[n-1]*b[n-1]
        // 1 <= a.length == b.length <= 1000
        // -1000 <= a[i], b[i] <= 1000

        for(int i = 0; i < a.length; i++) {
            answer += a[i]*b[i];
        }

        return answer;
    }
}