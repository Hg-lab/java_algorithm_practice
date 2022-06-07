package 프로그래머스.키패드_누르기;

import java.util.*;

class Solution {
    public class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int diff(int x1, int y1) {
            return Math.abs(this.x - x1) + Math.abs(this.y - y1);
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }
    }
    public String solution(int[] numbers, String hand) {
        String answer = "";
        Integer[] leftNum = {1,4,7}; // 1: 0,3 4: 0,2 7: 0,1 -> 3x+number = 10
        Integer[] rightNum = {3,6,9};// 3: 2,3 6: 2,2 9: 2,1
        Point lefthand = new Point(0,0);
        Point righthand = new Point(2,0);
        for(int number : numbers) {
            if(Arrays.asList(leftNum).indexOf(number) > -1) {
                answer += "L";
                lefthand = new Point(0,(10-number)/3);
            } else if(Arrays.asList(rightNum).indexOf(number) > -1) {
                answer += "R";
                righthand = new Point(2,(12-number)/3);
            } else {
                // 2: 1,3 5: 1,2, 8: 1,1 0: 1,0
                Point center = new Point(1, (11-number)/3);
                if(number == 0) {
                    center = new Point(1,0);
                }
                int leftdiff = center.diff(lefthand.getX(), lefthand.getY());
                int rightdiff = center.diff(righthand.getX(), righthand.getY());

                if(leftdiff == rightdiff) {
                    if(hand.substring(0,1).equals("l")) {
                        answer+="L";
                        lefthand = new Point(1, (11-number)/3);
                        if(number == 0) {
                            lefthand = new Point(1,0);
                        }
                    }
                    else {
                        answer += "R";
                        righthand = new Point(1, (11-number)/3);
                        if(number == 0) {
                            righthand = new Point(1,0);
                        }
                    }
                } else if(leftdiff > rightdiff) {
                    answer += "R";
                    righthand = new Point(1, (11-number)/3);
                    if(number == 0) {
                        righthand = new Point(1,0);
                    }
                } else {
                    answer +="L";
                    lefthand = new Point(1, (11-number)/3);
                    if(number == 0) {
                        lefthand = new Point(1,0);
                    }
                }
            }
        }
        return answer;
    }
}