package org.example.baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

class FastScanner {
    BufferedReader br;
    StringTokenizer st;
    public FastScanner(InputStream stream) {
        br = new BufferedReader(new InputStreamReader(stream));
    }
    public String next() throws IOException {
        while (st==null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }

    public int nextInt() throws IOException {
        return Integer.parseInt(next());
    }
    public Long nextLong() throws IOException {
        return Long.parseLong(next());
    }
    public Double nextDouble() throws IOException {
        return Double.parseDouble(next());
    }
    public String nextLine() throws IOException {
        return br.readLine();
    }

}

public class P3273 {

    public static void main(String[] args) throws IOException {
        FastScanner fastScanner = new FastScanner(System.in);
        int N = fastScanner.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = fastScanner.nextInt();
        }
        int K = fastScanner.nextInt();
        int cnt = 0;
        Set<Integer> visit = new HashSet<>();
        for (int X : arr) {
            int target = K - X;

            if(visit.contains(target)) {
                cnt++;
                visit.remove(target);
            } else {
                visit.add(X);
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(cnt));
        bw.flush();
    }
}
