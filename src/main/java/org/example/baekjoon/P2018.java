package org.example.baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class P2018 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int N = Integer.parseInt(stringTokenizer.nextToken());
        int l = 0;
        int r = 0;
        int sum = 0;
        int ans = 0;
        while(r <= N || l <= N) {
            if(sum < N) {
                r++;
                sum += r;
            }
            else if(sum > N){
                l++;
                sum -= l;
            }
            else {
                l++;
                ans++;
                sum -= l;

            }
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();
        bufferedWriter.flush();
        bufferedReader.close();
    }
}
