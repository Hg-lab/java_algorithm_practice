package 프로그래머스.Level1.바탕화면_정리;

class Solution {
    public int[] solution(String[] wallpaper) {
        int[] answer = {};

        int minX = Integer.MAX_VALUE; int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE; int maxY = Integer.MIN_VALUE;

        for(int x = 0; x < wallpaper.length; x++) {
            for(int y = 0; y < wallpaper[x].length(); y++) {
                if(wallpaper[x].charAt(y) == '.') continue;
                minX = Math.min(minX, x);
                minY = Math.min(minY, y);

                maxX = Math.max(maxX, x);
                maxY = Math.max(maxY, y);
            }
        }
        answer = new int[]{minX, minY, maxX+1, maxY+1};
        return answer;
    }
}