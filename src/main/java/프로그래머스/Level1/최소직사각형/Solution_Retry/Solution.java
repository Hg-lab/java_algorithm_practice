package 프로그래머스.Level1.최소직사각형.Solution_Retry;

class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;

        System.out.println(sizes.length);

        int maxW = 0;
        int maxH = 0;

        // 둘중에 작은거중 큰것
        // 큰것중 가장큰것
        for(int i = 0; i < sizes.length; i++) {
            int longer = Math.max(sizes[i][0],sizes[i][1]);
            int shorter = Math.min(sizes[i][0],sizes[i][1]);

            if(maxW <= longer) maxW = longer;
            if(maxH <= shorter) maxH = shorter;
        }

        answer = maxW * maxH;

        return answer;
    }
}