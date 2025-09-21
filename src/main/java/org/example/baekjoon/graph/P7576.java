package org.example.baekjoon.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P7576 {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        ArrayDeque<int[]> q = new ArrayDeque<>();
        boolean existZero = false;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) {
                    q.add(new int[] {i, j});
                } else if(map[i][j] == 0) {
                    existZero = true;
                }
            }
        }

        int day = 0;

        if(!existZero) {
            System.out.println(0);
            return;
        }

        if(q.isEmpty()) {
            System.out.println(-1);
            return;
        }

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];
            for(int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if(nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] == 0) {
                    map[nx][ny] = map[cx][cy] + 1;
                    day = Math.max(day, map[nx][ny]);
                    q.add(new int[] {nx, ny});
                }
            }
        }

        boolean b = Arrays.stream(map).anyMatch(x -> Arrays.stream(x).anyMatch(y -> y == 0));
        Arrays.stream(map).forEach(x -> Arrays.stream(x).anyMatch(y -> y == 0));
        if(b) System.out.println(-1);
        else System.out.println(day-1);
    }
}
