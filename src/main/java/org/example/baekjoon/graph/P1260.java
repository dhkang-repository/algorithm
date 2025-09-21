package org.example.baekjoon.graph;

import java.io.*;
import java.util.*;

public class P1260 {
    static ArrayList<Integer>[] adj;
    static boolean[] visited;
    static StringBuilder out = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 정점 수
        int M = Integer.parseInt(st.nextToken()); // 간선 수
        int V = Integer.parseInt(st.nextToken()); // 시작 정점

        // 인접 리스트 생성
        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

        // 간선 입력 (무방향)
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }

        // 방문 순서가 항상 "작은 번호부터"가 되도록 정렬
        for (int i = 1; i <= N; i++) Collections.sort(adj[i]);

        // DFS
        visited = new boolean[N + 1];
        dfs(V);
        out.append('\n');

        // BFS
        visited = new boolean[N + 1];
        bfs(V);

        System.out.print(out);
    }

    static void dfs(int x) {
        visited[x] = true;
        out.append(x).append(' ');
        for (int nxt : adj[x]) {
            if (!visited[nxt]) dfs(nxt);
        }
    }

    static void bfs(int start) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        visited[start] = true;
        q.add(start);

        while (!q.isEmpty()) {
            int cur = q.poll();
            out.append(cur).append(' ');
            for (int nxt : adj[cur]) {
                if (!visited[nxt]) {
                    visited[nxt] = true;
                    q.add(nxt);
                }
            }
        }
    }
}