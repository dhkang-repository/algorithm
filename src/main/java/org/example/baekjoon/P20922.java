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
        int l = 0, r = 0;
        int cnt = Integer.MIN_VALUE;
        while(r < N && l <= r) {
            if(inf[arr[r]] < K) {
                inf[arr[r]]++;
                r++;
                cnt = Math.max(cnt, r - l);
            }
            else {
                inf[arr[l]]--;
                l++;
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        if(cnt == Integer.MIN_VALUE) {
            bw.write(0 + "\n");
        } else {
            bw.write(cnt + "\n");
        }
        bw.flush();
        bw.close();
    }
}
