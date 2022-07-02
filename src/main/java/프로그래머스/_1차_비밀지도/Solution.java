package 프로그래머스._1차_비밀지도;


// 한변의 길이가 n인 정사각형 배열
// 두 장의 지도를 겹쳐야함
// 1: 벽, 0: 공백
// 배열 안에는 정수 -> 이진법으로 바꾸어야함
class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = {};

        String[] combinedArrStr = new String[n];

        for(int i = 0; i < n; i++) {
            // Integer number to binary number String
            String arr1ElementToBin = Integer.toBinaryString(arr1[i]);
            String arr2ElementToBin = Integer.toBinaryString(arr2[i]);

            // Make binary number String to n Digits
            String arr1StrToNDigits = toNDigits(n,arr1ElementToBin);
            String arr2StrToNDigits = toNDigits(n,arr2ElementToBin);

            // combine maps for each index
            combinedArrStr[i] = combineBinaryString(arr1StrToNDigits, arr2StrToNDigits);

            System.out.println(combinedArrStr[i]);

            combinedArrStr[i] = combinedArrStr[i].replaceAll("1","#");
            combinedArrStr[i] = combinedArrStr[i].replaceAll("0"," ");
        }


        answer = combinedArrStr;

        return answer;
    }

    public String toNDigits(int n, String binaryString) {
        String addZero = "";
        String rtnDigits = "";

        for(int i = 1; i <= n-binaryString.length(); i++) {
            addZero += "0";
        }

        rtnDigits = addZero + binaryString;

        return rtnDigits;
    }

    // s1.length() = s2.length()
    public String combineBinaryString(String s1, String s2) {
        String rtnStr = "";

        for(int i = 0; i < s1.length(); i++ ) {
            int s1ToInt = Integer.parseInt(Character.toString(s1.charAt(i)));
            int s2ToInt = Integer.parseInt(Character.toString(s2.charAt(i)));
            rtnStr += Integer.toString(s1ToInt+s2ToInt > 0 ? 1 : 0);
        }
        return rtnStr;
    }

}