package 프로그래머스.Level2.유사_칸토어_비트열;

class Main {
    public static void main(String[] args) {
        new Solution().solution(2, 4l, 17l);
    }
}

class Solution {
    public int solution(int n, long l, long r) {
        return func(n, r) - func(n, l-1);
    }

    private int func(int n, long k) {
        if(n == 0) return 1;
        if(n == 1) {
            return k <= 2l ? (int)k : (int)k-1;
        }
        long part = (long)Math.pow(5,n-1);
        int partWithK;

        if(k%5 == 0) partWithK = (int)((k)/ part) - 1;
        else partWithK = (int)(k/ part);

        if(partWithK < 2)
            return (int) (Math.pow(4,n-1) * partWithK  + func(n-1, k - part * (long)partWithK));
        if(partWithK == 2)
            return (int) (Math.pow(4,n-1) * partWithK);
        else
            return (int) (Math.pow(4,n-1) * (partWithK-1) + func(n-1, k - part * (long)partWithK));
    }
}



