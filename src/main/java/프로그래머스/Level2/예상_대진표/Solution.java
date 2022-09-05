package 프로그래머스.Level2.예상_대진표;

import java.util.*;

class Solution
{
    public int solution(int n, int a, int b)
    {
        int answer = 0;

        // 2그룹씩 Set로 묶은 후 전체 리스트로 구성
        List<Set<Integer>> groupList = new ArrayList<>();

        for(int i = 1; i <= n; i++) {
            Set<Integer> initialGroup = new HashSet<>();
            initialGroup.add(i);
            groupList.add(initialGroup);
        }

        int aGroupNum = a; int bGroupNum = b;

        while(aGroupNum != bGroupNum) {
            ++answer;
            List<Set<Integer>> nextGroupList = new ArrayList<>();
            for(int i = 0; i < groupList.size() - 1; i++) {
                if(i%2 == 1) continue;
                if(i%2 == 0) {
                    Set<Integer> nextGroup = new HashSet<>();
                    nextGroup.addAll(groupList.get(i));
                    nextGroup.addAll(groupList.get(i+1));
                    nextGroupList.add(nextGroup);
                    if(nextGroup.contains(a)) {
                        aGroupNum = nextGroupList.size()-1;
                    }
                    if(nextGroup.contains(b)) {
                        bGroupNum = nextGroupList.size()-1;
                    }
                    if(aGroupNum == bGroupNum) break;
                }
            }
            groupList = nextGroupList;
        }

        return answer;
    }
}