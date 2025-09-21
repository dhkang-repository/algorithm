package org.example.baekjoon.graph;

import java.io.*;
import java.util.*;

public class P2178 {
    static int N, M;
    static int[][] map;
    static int[][] dist;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dist = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine().trim();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        bfs(0, 0);
        System.out.println(dist[N - 1][M - 1]);
    }

    static void bfs(int x, int y) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{x, y});
        dist[x][y] = 1; // 시작 지점 거리 = 1

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];

            for (int dir = 0; dir < 4; dir++) {
                int nx = cx + dx[dir];
                int ny = cy + dy[dir];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue; // 범위 체크
                if (map[nx][ny] == 0 || dist[nx][ny] != 0) continue; // 벽 or 방문

                dist[nx][ny] = dist[cx][cy] + 1;
                q.add(new int[]{nx, ny});
            }
        }
    }
}