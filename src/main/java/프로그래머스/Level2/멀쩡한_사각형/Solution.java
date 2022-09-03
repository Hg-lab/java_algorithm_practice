package 프로그래머스.Level2.멀쩡한_사각형;

class Solution {
    public long solution(int w, int h) {
        long answer = 1;
        int GCD = getGCD(w,h);
        int w1 = w/GCD; int h1 = h/GCD;
        int unusable = 0;
        for(int i = 0; i < h1; i++) {
            unusable += Math.ceil((i+1) * (double)w1/h1) - Math.floor(i * (double)w1/h1);
        }

        unusable *= GCD;
        return answer = ((long)w*h - unusable);
    }

    public int getGCD(int x, int y) {
        if(x == 0) return y;

        int max = Math.max(x,y);
        int min = Math.min(x,y);

        return getGCD(max%min, min);
    }
}