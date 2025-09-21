package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2470 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        // -99 -2 -1 4 98
        int l = 0, r = N-1;
        long sum = Long.MAX_VALUE;
        int ansL = arr[l];
        int ansR = arr[r];
        while (l < r) {
            long v = Math.abs(arr[l] + arr[r]);
            if(v < sum) {
                sum = v;
                ansL = arr[l];
                ansR = arr[r];
                if(v == 0) break;
            }

            if(arr[l] + arr[r] > 0) r--;
            else l++;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(ansL + " " + ansR);
        bw.flush();
        bw.close();
    }
}
