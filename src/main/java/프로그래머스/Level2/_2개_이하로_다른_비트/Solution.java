package 프로그래머스.Level2._2개_이하로_다른_비트;

class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        for(int i = 0; i < answer.length; i++) {
            if(numbers[i] % 2 == 0) {
                answer[i] = numbers[i] + 1;
                continue;
            }
            if(isPowerTwo(numbers[i]+1)) {
                answer[i] = numbers[i] + compute(numbers[i]);
                continue;
            }

            String s = Long.toBinaryString(numbers[i]);
            for(int j = s.length()-1; j >=0; j--) {
                if(s.charAt(j) == '0' && j < s.length()-1) {
                    StringBuilder sb = new StringBuilder(s);
                    sb.setCharAt(j,'1');
                    sb.setCharAt(j+1,'0');
                    answer[i] = getNumber(sb.toString());
                    break;
                } else answer[i] = numbers[i] + 1;
            }
        }
        return answer;
    }

    private long getNumber(String s) {
        char[] arr = s.toCharArray();
        long result = 0;
        int n = arr.length;
        for(int i = 0; i < arr.length; ++i) {
            if(arr[i] == '1')
                result += (long)Math.pow(2,n-i-1);
        }
        return result;
    }

    private boolean isPowerTwo(long x) {
        if(x == 0) return false;
        while(x > 1) {
            if(x%2 != 0) return false;
            x /= 2;
        }
        return true;
    }

    private long compute(long x) {
        long y = (long)(Math.log(x) / Math.log(2));
        return (long)Math.pow(2,y);
    }

}