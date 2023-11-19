package 프로그래머스.Level2.당구_연습;

import java.util.*;

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] solution1 = solution.solution(10, 10, 3, 7, new int[][]{{2, 7}});
        Arrays.stream(solution1).forEach(s -> System.out.println(s));
    }
}

class Solution {
    class Point {
        int x;
        int y;
        String mirror;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Point(int x, int y, String mirror) {
            this.x = x;
            this.y = y;
            this.mirror = mirror;
        }

        Point(int[] ball) {
            this.x = ball[0];
            this.y = ball[1];
        }

        int getDistanceFrom(Point p) {
            return (int) (Math.pow(Math.abs(p.x - this.x), 2) + Math.pow(Math.abs(p.y - this.y), 2));
        }

        Point getOppositePoint(Point mirrorPoint) {
            return new Point(2 * mirrorPoint.x - x, 2 * mirrorPoint.y - y);
        }


        String getDirectionIfDirect(Point ball) {
            if (x == ball.x) {
                if(y < ball.y) return "top";
                else return "bottom";
            }
            if (y == ball.y) {
                if(x < ball.x) return "right";
                else return "left";
            }

            return "none";
        }

    }

    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        List<Integer> answers = new ArrayList<>();
        Point startPoint = new Point(startX, startY);
        ArrayList<Point> mirrorPoints = initMirrorPoints(m, n, startX, startY);

        for (int[] ball : balls) {
            int minDistance = Integer.MAX_VALUE;
            Point ballPoint = new Point(ball);
            for (Point mirrorPoint : mirrorPoints) {
                if(startPoint.getDirectionIfDirect(ballPoint).equals(mirrorPoint.mirror)) continue;
                Point oppositePoint = startPoint.getOppositePoint(mirrorPoint);
                int distance = ballPoint.getDistanceFrom(oppositePoint);
                if(minDistance > distance) minDistance = distance;
            }
            answers.add(minDistance);
        }

        return answers.stream().mapToInt(Integer::intValue).toArray();
    }

    private ArrayList<Point> initMirrorPoints(int m, int n, int startX, int startY) {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, startY, "left"));
        points.add(new Point(startX, 0, "bottom"));
        points.add(new Point(m, startY, "right"));
        points.add(new Point(startX, n, "top"));
        return points;
    }
}