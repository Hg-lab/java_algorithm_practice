package 프로그래머스.Level1.소수_만들기.Solution_Combination;// return : 주어진 숫자 중 3개 더하여 소수가 되는 경우의 갯수
// 1 <= nums[i] <= 1000, 중복이 없음
// 3 <= nums.length <= 50
import java.util.*;

class Solution {

    static int ans = 0;

    public int solution(int[] nums) {
        int answer = -1;

        // 3개를 뽑는 경우
        boolean[] checked = new boolean[nums.length];


        combination(nums, checked, 0, nums.length, 3);

        answer = ans;

        return answer;
    }

    public boolean isPrime(int num) {
        for(int i = 2; i <= Math.sqrt(num); i++) {
            if(num % i == 0) {
                return false;
            }
        }
        return true;
    }

    // nCr
    public static void combination(int[] arr, boolean[] visited, int start, int n, int r) {

        if(r == 0) {
            int sum = 0;
            for(int i = 0 ; i < visited.length; i++) {
                if(visited[i]) {
                    sum += arr[i];
                }
            }

            Solution solution = new Solution();

            if(solution.isPrime(sum)) {
                ans++;
            }
            return ;

        }

        for(int i = start; i < n; i++) {
            visited[i] = true;
            combination(arr, visited, i+1, n, r-1);
            visited[i] = false;
        }

    }

}