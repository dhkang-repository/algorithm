package org.example.baekjoon.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class P16928 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] map = new int[101]; // 0이면 없음, !=0이면 사다리/뱀 도착 칸
        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x] = y;
        }

        boolean[] visited = new boolean[101];
        int[] dist = new int[101]; // 주사위 굴린 횟수
        ArrayDeque<Integer> q = new ArrayDeque<>();

        visited[1] = true;
        dist[1] = 0;
        q.add(1);

        while (!q.isEmpty()) {
            int cur = q.poll();
            if (cur == 100) break; // 최단 도달

            for (int d = 1; d <= 6; d++) {
                int nxt = cur + d;
                if (nxt > 100) continue;

                if (map[nxt] != 0) {
                    nxt = map[nxt]; // 사다리/뱀 타기
                }

                if (!visited[nxt]) {
                    visited[nxt] = true;
                    dist[nxt] = dist[cur] + 1;
                    q.add(nxt);
                }
            }
        }

        System.out.println(dist[100]);
    }
}
