package 프로그래머스.Level1.부족한_금액_계산하기;

// Original price: price -> Nth price: price * N
// return: How much more money needed, when taking the ride "count"th.

class Solution {
    public long solution(int price, int money, int count) {
        long answer = -1;
        long totalPrice = 0L;


        for(int i = 1; i <= count; i++) {
            totalPrice += price * i;
        }
        if(money >= totalPrice) return 0;

        answer = totalPrice - money;
        return answer;
    }
}