package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


// dp[i] = max(arr[i],dp[i−1]+arr[i])
// answer = max dp[i]
public class P1912 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int current = arr[0];
        int answer = arr[0];

        for (int i = 1; i < N; i++) {
            // 이어가기 vs 새로 시작
            current = Math.max(arr[i], current + arr[i]);
            // 전체 최대값 갱신
            answer = Math.max(answer, current);
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        System.out.println(String.valueOf(answer) + "'");
    }
}
