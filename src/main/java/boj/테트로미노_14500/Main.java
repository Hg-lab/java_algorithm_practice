package boj.테트로미노_14500;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int rN;
    static int cM;
    static int[][] map;

    static int[] dy = {0, -1, 0, 1};
    static int[] dx = {-1, 0, 1, 0};

    static int maxSum = -1;
    static int maxNum = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        rN = Integer.parseInt(st.nextToken());
        cM = Integer.parseInt(st.nextToken());

        map = new int[rN][cM];
        for (int r = 0; r < rN; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < cM; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                maxNum = Math.max(map[r][c], maxNum);
            }
        }

        boolean[][] visited = new boolean[rN][cM];
        for (int r = 0; r < rN; r++) {
            for (int c = 0; c < cM; c++) {
                Node node = new Node(r, c, map[r][c], 1);
                visited[r][c] = true;
                tetrominoDFS(node, visited);
                visited[r][c] = false;
                maxSum = Math.max(tetromino(node), maxSum);
            }
        }
        System.out.println(maxSum);
    }

    private static int tetromino(Node baseNode) {
        int minNum = Integer.MAX_VALUE;
        int sum = baseNode.sum;
        int count = 0;
        for (int i = 0; i < dy.length; i++) {
            int nextR = baseNode.r + dy[i];
            int nextC = baseNode.c + dx[i];
            if (Node.isInBoundary(nextR, nextC, map)) {
                sum += map[nextR][nextC];
                minNum = Math.min(map[nextR][nextC], minNum);
                count++;
            }
        }
        if(count == 3) return sum;
        else if(count == 4) return sum - minNum;
        return -1;
    }

    private static void tetrominoDFS(Node node, boolean[][] visited) {
        if(node.sum + maxNum * (4-node.count) <= maxSum) return;

        if (node.count == 4) {
            maxSum = Math.max(node.sum, maxSum);
            return;
        }

        for (int i = 0; i < dy.length; i++) {
            int nextR = node.r + dy[i];
            int nextC = node.c + dx[i];
            if (!Node.isInBoundary(nextR, nextC, map) || visited[nextR][nextC]) {
                continue;
            }

            Node nextNode = node.getNextNode(dy[i], dx[i], map);
            visited[nextNode.r][nextNode.c] = true;
            tetrominoDFS(nextNode, visited);
            visited[nextNode.r][nextNode.c] = false;
        }
    }
}

class Node {
    int r;
    int c;
    int sum;
    int count;

    public Node(int r, int c, int sum, int count) {
        this.r = r;
        this.c = c;
        this.sum = sum;
        this.count = count;
    }

    public static boolean isInBoundary(int nextR, int nextC, int[][] map) {
        return nextR >= 0 && nextC >= 0 && nextR < map.length && nextC < map[nextR].length;
    }

    public Node getNextNode(int dy, int dx, int[][] map) {
        int nextR = this.r + dy;
        int nextC = this.c + dx;
        return new Node(nextR, nextC, this.sum + map[nextR][nextC], this.count + 1);
    }
}