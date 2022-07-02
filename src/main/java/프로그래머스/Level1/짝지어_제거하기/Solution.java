package 프로그래머스.Level1.짝지어_제거하기;

// return : 0 or 1
// "baabaa" -> "bbaa" -> "aa" -> ""

class Solution
{
    public int solution(String s)
    {

        if(s.length()%2 == 1) return 0;

        int answer = -1;
        StringBuilder sb = new StringBuilder(s);
        while(sb.length() > 1) {
            for(int i = 1; i < sb.length(); i++) {
                if(sb.charAt(i) == sb.charAt(i-1)) {
                    sb.deleteCharAt(i);
                    sb.deleteCharAt(i-1);
                    break;
                }
                if(i+1 == sb.length()) return 0;
            }
        }

        if(sb.length() ==1 ) answer = 0;
        else answer = 1;

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("Hello Java");

        return answer;
    }
}