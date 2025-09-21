package org.example.baekjoon;

import java.io.*;
import java.util.*;

public class P1644 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());
        if (N == 1) {
            System.out.println(0);
            return;
        }

        // 1) 에라토스테네스
        boolean[] isComp = new boolean[N + 1];
        isComp[0] = isComp[1] = true;
        for (int p = 2; p * p <= N; p++) {
            if (!isComp[p]) {
                for (int q = p * p; q <= N; q += p) isComp[q] = true;
            }
        }

        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= N; i++)
            if (!isComp[i]) primes.add(i);

        // 2) 투 포인터로 연속합 == N 개수
        int l = 0, r = 0, sum = 0, cnt = 0;
        while (true) {
            if (sum >= N) {
                if (sum == N) cnt++;
                sum -= primes.get(l++);
            } else {
                if (r == primes.size()) break;
                sum += primes.get(r++);
            }
        }
        System.out.println(cnt);
    }
}