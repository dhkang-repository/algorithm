package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class P1806 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine());
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int l = 0;
        int r = 0;
        long sum = 0;
        int length = Integer.MAX_VALUE;
        while (true) {
            if (sum >= S) {
                length = Math.min(length, r - l);
                sum -= arr[l++];
            } else if (r < N) {
                sum += arr[r++];
            } else {
                break;
            }
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        if (length == Integer.MAX_VALUE) {
            bw.write("0\n");
        } else {
            bw.write(length + "\n");
        }
        bw.flush();
        bw.close();
    }
}
