package 프로그래머스.Level2.교점에_별_만들기;
import java.util.*;

class Solution {
    public String[] solution(int[][] line) {
        String[] answer = {};
        int[] xMinMax = new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};
        int[] yMinMax = new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};
        ArrayList<int[]> list = new ArrayList<>();
        for(int i = 0; i < line.length; i++) {
            for(int j = i+1; j < line.length; j++) {
                long[][] meetPoint = findMeetPoint(line[i], line[j]);
                if(isInteger(meetPoint)) {
                    int x = (int)(meetPoint[0][0]/meetPoint[0][1]);
                    int y = (int)(meetPoint[1][0]/meetPoint[1][1]);
                    xMinMax[0] = Math.min(xMinMax[0], x);
                    xMinMax[1] = Math.max(xMinMax[1], x);
                    yMinMax[0] = Math.min(yMinMax[0], y);
                    yMinMax[1] = Math.max(yMinMax[1], y);
                    list.add(new int[]{x,y});
                }
            }
        }
        int xGap = xMinMax[1] - xMinMax[0];
        int yGap = yMinMax[1] - yMinMax[0];
        answer = new String[yGap+1];
        char[][] answerArr = new char[yGap+1][xGap+1];

        for(int[] l : list) {
            int x = l[0] - xMinMax[0]; int y = Math.abs(yMinMax[1] - l[1]);
            answerArr[y][x] = '*';

        }

        for(int i = 0; i < answerArr.length; i++) {
            for(int j = 0; j < answerArr[i].length; j++) {
                if(answerArr[i][j] != '*')
                    answerArr[i][j] = '.';
            }
            answer[i] = new String(answerArr[i]);
        }
        return answer;
    }

    private long[][] findMeetPoint(int[] p1, int[] p2) {
        long a = p1[0]; long b = p1[1];
        long c = p2[0]; long d = p2[1];
        long e = p1[2]; long f = p2[2];
        long xDeno = b*f - e*d; long xNumer = a*d - b*c;
        long yDeno = e*c - a*f; long yNumer = a*d - b*c;
        return new long[][]{{xDeno, xNumer}, {yDeno, yNumer}};
    }


    private boolean isInteger(long[][] meetPoint) {
        long xDeno = meetPoint[0][0]; long xNumer = meetPoint[0][1];
        long yDeno = meetPoint[1][0]; long yNumer = meetPoint[1][1];
        if(xNumer==0 || yNumer==0) return false;
        return (xDeno%xNumer==0&&yDeno%yNumer==0);
    }
}
