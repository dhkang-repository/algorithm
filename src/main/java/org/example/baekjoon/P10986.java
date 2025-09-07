package org.example.baekjoon;

import java.io.*;
import java.util.StringTokenizer;


public class P10986 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());

        int[] arr = new int[N];
        long[] cnt = new long[M];

        cnt[0] = 1;
        long sum = 0;
        long ans = 0;

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        for(int K : arr) {
            sum += K;
            int mod = (int) ((sum + M ) % M);
            ans += cnt[mod];
            cnt[mod]++;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(ans));
        bw.newLine();
        bw.flush();
    }
}
