package 프로그래머스.Level2.과제_진행하기;
import java.util.*;

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        String[] solution1 = solution.solution(new String[][]{{"a", "12:00", "20"}, {"b", "12:05", "10"}, {"c", "12:10", "5"}, {"d", "12:15", "5"}, {"e", "12:22", "3"}});
//        System.out.println(Arrays.equals(new String[]{"c", "d", "e", "b", "a"}, solution1));

        String[] solution2 = solution.solution(new String[][]{{"A", "12:00", "30"}, {"B", "12:10", "20"}, {"C", "15:00", "40"}, {"D", "15:10", "30"}});
        System.out.println(Arrays.equals(new String[]{"B", "A", "D", "C"}, solution2));

        // c d e b a
    }
}

class Solution {
    class Plan implements Comparable<Plan> {
        String title;
        int startTime;
        int restMinute;

        Plan(String[] plan) {
            this.title = plan[0];
            this.startTime = Integer.parseInt(plan[1].split("\\:")[0]) * 60 + Integer.parseInt(plan[1].split("\\:")[1]);
            this.restMinute = Integer.parseInt(plan[2]);
        }

        public void doPlan(int minute) {
            this.restMinute -= minute;
        }

        @Override
        public int compareTo(Plan o) {
            return (this.startTime - o.startTime);
        }

    }


    public String[] solution(String[][] plans) {
        List<String> doneList = new ArrayList<>();
        List<Plan> planList = new ArrayList<>();
        Arrays.stream(plans).forEach(p -> {
            Plan plan = new Plan(p);
            planList.add(plan);
        });
        Collections.sort(planList);
        Stack<Plan> onGoing = new Stack<>();
        int now = 0;
        for (Plan nextPlan : planList) {
            if (onGoing.isEmpty()) {
                onGoing.push(nextPlan);
                now = onGoing.peek().startTime;
                continue;
            }
            while (!onGoing.isEmpty() && now + onGoing.peek().restMinute <= nextPlan.startTime) {
                now += onGoing.peek().restMinute;
                onGoing.peek().doPlan(onGoing.peek().restMinute);

                doneList.add(onGoing.pop().title);
            }

            int gap = nextPlan.startTime - now;
            if (!onGoing.isEmpty()) {
                now += gap;
                onGoing.peek().doPlan(gap);
            }
            onGoing.push(nextPlan);
            now = nextPlan.startTime;
        }

        while (!onGoing.empty()) {
            doneList.add(onGoing.pop().title);
        }

        return doneList.stream().toArray(String[]::new);
    }

}