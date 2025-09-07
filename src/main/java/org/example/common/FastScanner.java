package org.example.common;

import java.io.*;
import java.util.StringTokenizer;

public class FastScanner {
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
