package 프로그래머스.Level2.카펫;

class Solution_Easy {
    public int[] solution(int brown, int yellow) {
        int[] answer = {};
        int sum = brown + yellow;

        for(int w = 1; w <= sum/2; w++) {
            int h = sum/w;

            int y = (w - 2) * (h - 2);
            int b = w * h - y;

            if(y == yellow && b == brown) {
                answer = new int[2];
                answer[0] = w;
                answer[1] = h;
            }
        }

        return answer;
    }
}