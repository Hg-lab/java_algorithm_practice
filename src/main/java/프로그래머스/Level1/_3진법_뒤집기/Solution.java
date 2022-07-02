package 프로그래머스.Level1._3진법_뒤집기;

// 1 <= n <= 100,000,000
// ex) 45 -> 1200 -> 0021 -> 7

class Solution {
    public int solution(int n) {
        int answer = 0;
        int number = n;
        String ternaryStr = "";

        // 뒤에서 부터 숫자를 더하면서 뒤집는 과정까지 완료
        if(number < 3) {
            ternaryStr += Integer.toString(number % 3);
        }
        while(number >= 3) {
            ternaryStr += Integer.toString(number % 3);
            number /= 3;
            if(number < 3) {
                ternaryStr += Integer.toString(number % 3);
                break;
            }
        }

        for(int i = 0; i < ternaryStr.length(); i++) {
            answer += Math.pow(3, ternaryStr.length()-i-1) * Integer.parseInt(ternaryStr.substring(i,i+1));
        }

        return answer;
    }
}