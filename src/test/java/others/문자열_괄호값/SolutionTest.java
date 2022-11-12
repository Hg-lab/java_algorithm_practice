package others.문자열_괄호값;

import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
* 우리가 흔히 사용하고 있는 괄호는 (), {}, [], 3종류이다.
* 이 괄호로 올바르게 짝이 지워진 문자열이 있다.
* 이 문자열에 대한 괄호 값을 다음과 같이 정의한다
* () = 2, {} = 3, [] = 5
* (){}[] = 2 + 3 + 5 = 10
* [(){}][] = 5 * (2 + 3) + 5 = 30
* */
public class SolutionTest {

    @Test
    void test() {

        Solution solution = new Solution();
        System.out.println(solution.solution(("[(){}][]")) + " = " + 30);
        System.out.println(solution.solution(("({}[[]])")) + " = " + 56);
        System.out.println(solution.solution(("{()()}[[]]")) + " = " + 37);
        System.out.println(solution.solution(("{[][()]}[{}{}]")) + " = " + 75);
        System.out.println(solution.solution(("(){}[][")) + " = " + -1);

        assertEquals(30, solution.solution("[(){}][]"));
        assertEquals(10, solution.solution("(){}[]"));
        assertEquals(56, solution.solution("({}[[]])"));
        assertEquals(37, solution.solution("{()()}[[]]"));
        assertEquals(75, solution.solution("{[][()]}[{}{}]"));
        assertEquals(-1, solution.solution("{[][()]}[{}{]]}"));
        assertEquals(-1, solution.solution("(){}[]["));

    }


}
