package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class P13144 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        int[] arr = new int[N];
        int max = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        int[] freq = new int[max + 1];
        int l=0;
        long ans = 0;
        for (int r = 0; r < N; r++) {
            int v = arr[r];
            freq[v]++;

            while (freq[v] > 1) {
                freq[arr[l]]--;
                l++;
            }

            ans += (r - l + 1);
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(ans + "\n");
        bw.flush();
        bw.close();
    }
}
