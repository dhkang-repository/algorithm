package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class P20922 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int max = 0;
        int[] arr = new int[N];

        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if(arr[i] > max) {
                max = arr[i];
            }
        }
        int[] inf = new int[max + 1];
        int l = 0;
        int maxLen = 0;
        for (int r = 0; r < N; r++) {
            int v = arr[r];
            inf[v]++;
            while (inf[v] > K) {
                inf[arr[l]]--;
                l++;
            }
            maxLen = Math.max(maxLen, r - l + 1);
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(maxLen + "\n");
        bw.flush();
        bw.close();
    }
}
