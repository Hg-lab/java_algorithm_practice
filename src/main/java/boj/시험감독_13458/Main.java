package boj.시험감독_13458;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int classNumN;
    static int[] stuNumsA;
    static int mainCapB;
    static int subCapC;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        classNumN = Integer.parseInt(br.readLine());
        stuNumsA = new int[classNumN];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < classNumN; i++) {
            stuNumsA[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        mainCapB = Integer.parseInt(st.nextToken());
        subCapC = Integer.parseInt(st.nextToken());

        long mainNum = 0L;
        long subNum = 0L;

        for (int i = 0; i < classNumN; i++) {
            stuNumsA[i] -= mainCapB;
            ++mainNum;
            if(stuNumsA[i] <= 0) continue;

            subNum += stuNumsA[i]/subCapC;
            if (stuNumsA[i] % subCapC != 0) {
                ++subNum;
            }
        }

        System.out.println(mainNum + subNum);
    }
}
