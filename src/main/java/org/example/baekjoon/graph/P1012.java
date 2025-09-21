package org.example.baekjoon.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1012 {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int C = Integer.parseInt(bf.readLine());
        for (int l = 0; l < C; l++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[][] map = new int[M][N];
            int[][] visited = new int[M][N];

            ArrayDeque<int[]> q = new ArrayDeque<>();
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(bf.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[x][y] = 1;
                q.add(new int[]{x, y});
            }

            int count = 0;
            while (!q.isEmpty()) {
                int[] poll = q.poll();
                int x = poll[0];
                int y = poll[1];
                if (visited[x][y] == 0) {
                    dfs(x, y, M, N, map, visited);
                    count++;
                }
            }
            sb.append(count).append("\n");
        }
        System.out.printf("%s", sb);
    }

    static public void dfs(int x, int y, int M, int N, int[][] map, int[][] visited) {
        if(map[x][y] == 1 && visited[x][y] == 0) {
            visited[x][y] = 1;
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx < 0 || ny < 0 || nx >= M || ny >= N) {continue;}
                dfs(nx, ny, M, N, map, visited);
            }
        }
    }
}
