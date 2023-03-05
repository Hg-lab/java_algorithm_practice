package 기타.buildings;

// parameters: int[] buildings
// return: int - 관측가능한 빌딩 수의 합
// 빌딩 관측 문제
// 자신보다 같거나 큰 높이의 빌딩은 관측가능함
// 빌딩이 가리는 경우는 앞의 빌딩보다 작거나 같은 경우
public class Solution {
    public static void main(String[] args) {
        System.out.println(7 == solution(new int[]{1,3,4,2,4}));
        System.out.println(24 == solution(new int[]{3,4,1,8,7,3,2,8,9}));
        System.out.println(10 == solution(new int[]{1,1,1,1,1,1}));
    }

    public static int solution(int[] buildings) {
        int answer = 0;
        for(int k = 0; k < buildings.length; ++k) {
            int now = buildings[k];
            int max = now;
            boolean countFirst = false; // 첫 관측시 더 큰게 관측되면 그 뒤의 빌딩은 첫 관측 빌딩보다 커야지 관측이 가능하다
            int count = 0;
            //see right
            for(int i = k+1; i < buildings.length; ++i) {
                int target = buildings[i];
                if(now > target) continue;
                if(now <= target && !countFirst) {
                    ++count;
                    countFirst = true;
                    max = Math.max(target, max);
                    continue;
                }
                if(target > max) {
                    ++count;
                    max = Math.max(target, max);
                }
            }

            max = now;
            countFirst = false;
            for(int i = k-1; i >= 0; --i) {
                int target = buildings[i];
                if(now > target) continue;
                if(now <= target && !countFirst) {
                    ++count;
                    countFirst = true;
                    max = Math.max(target, max);
                    continue;
                }
                if(target > max) {
                    ++count;
                    max = Math.max(target, max);
                }
            }
            answer += count;
        }
        return answer;
    }
}
