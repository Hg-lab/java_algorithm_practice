package 프로그래머스.Level2.마법의_엘리베이터;

class Main {
    public static void main(String[] args) {
        int solution = new Solution().solution(1111111111);
    }
}

class Solution {
    public int solution(int storey) {
        int storey1 = storey;
        int answer1 = 0;
        int answer2 = 0;
        for (int i = 1; i <= String.valueOf(storey1).length(); i++) {
            int number = storey1 % (int) Math.pow(10, i) / (int) Math.pow(10, i-1);
            if (number <= 5) {
                answer1 += number;
                storey1 -= number;
            } else {
                storey1 += (10 - number) * (int) Math.pow(10, i - 1);
                answer1 += (10 - number);
            }
        }

        for (int i = 1; i <= String.valueOf(storey).length(); i++) {
            int number = storey % (int) Math.pow(10, i) / (int) Math.pow(10, i-1);
            if (number < 5) {
                answer2 += number;
                storey -= number;
            } else {
                storey += (10 - number) * (int) Math.pow(10, i - 1);
                answer2 += (10 - number);
            }
        }

        return Math.min(answer1, answer2);
    }

}