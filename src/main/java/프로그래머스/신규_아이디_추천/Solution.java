package 프로그래머스.신규_아이디_추천;

// 3<= id.length<= 15
//알파벳 소문자, 숫자, -, _, . 가능
// .은 처음 끝에 가능, 연속 불가

// 1. UpperCase -> Lowercase
// 2. 조건(-_.) 안맞는 모든 문자 제거
// 3. .. -> . 로 치환
// 4. 처음이나 끝에 . 이면 제거
// 5. 빈문자면 a 대입
// 6. 길이 16이상이면 1~15 제외 다 제거, . 끝에있으면 제거
// 7. 길이 2 이하면 마지막문자 연속 추가

// 정규표현식 과 비교
// toLowerCase();

class Solution {
    public String solution(String new_id) {
        String answer = "";

        // Step 1
        answer = new_id.toLowerCase();

        // Step 2
        answer = answer.replaceAll("[^a-z0-9-_.]","");

        // Step 3
        answer = answer.replaceAll("\\.{2,}",".");

        // Step 4
        answer = answer.replaceAll("^\\.|\\.$","");

        // Step 5
        if(answer.equals("")) answer = "a";
        System.out.println(answer);

        // Step 6
        if(answer.length() >= 16) answer = answer.substring(0,15);
        answer = answer.replaceAll("\\.$", "");

        // Step 7
        if(answer.length() <= 2) {
            while(answer.length() <= 2) {
                answer += answer.substring(answer.length()-1);
            }
        }

        return answer;
    }
}