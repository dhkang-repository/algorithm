package org.example.baekjoon.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1697 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N  = Integer.parseInt(st.nextToken());
        int K  = Integer.parseInt(st.nextToken());

        if (N >= K) { // 뒤로만 가면 됨
            System.out.println(N - K);
            return;
        }

        int[] dist = new int[Math.max(2*N, 2*K) + 1];
        Arrays.fill(dist, -1);
        ArrayDeque<Integer> q = new ArrayDeque<>();

        dist[N] = 0;
        q.add(N);

        while( !q.isEmpty() ) {
            int cur = q.poll();
            if(cur == K) break;

            int[] nexts = {cur - 1, cur + 1, 2*cur};
            for (int nx : nexts) {
                if(nx < 0 || nx >= 2*K) continue;
                if (dist[nx] != -1) continue;
                q.add(nx);
                dist[nx] = dist[cur] + 1;
            }

        }

        System.out.println(dist[K]);
    }
}
