package 프로그래머스.Level1._124나라의_숫자;

// 1 2 4(10) 11 12 14(20) 21 22
// 3진법 > 0, 1, 2
// 실제 3진법은 1, 2, 10 으로 자리수를 올리지만
// 여기서는 1, 2, 4로 자리수가 안올라가므로 3의배수에서 n-1해주어 자리수를 낮춤
// 0 대신 4
// 1 <= n <= 500,000,000
class Solution {
    public String solution(int n) {
        String answer = "";
        String[] nums = {"4", "1", "2"};

        while(n > 0) {
            int x = n%3;
            if(n%3==0) n-=1;
            n /= 3;
            System.out.println(nums[x]);
            answer = nums[x] + answer;
        }


        return answer;
    }
}