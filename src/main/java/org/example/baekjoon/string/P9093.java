package org.example.baekjoon.string;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class P9093 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for (int i = 0; i < T; i++) {
            String[] words = br.readLine().split(" ");
            for (int j = 0; j < words.length; j++) {
                StringBuilder sb = new StringBuilder(words[j]);
                out.append(sb.reverse());
                if (j < words.length - 1) out.append(" ");
            }
            out.append("\n");
        }

        System.out.print(out);
    }
}