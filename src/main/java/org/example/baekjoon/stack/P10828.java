package org.example.baekjoon.stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayDeque;

public class P10828 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        int N = Integer.parseInt(br.readLine().trim());
        ArrayDeque<Integer> stack = new ArrayDeque<>(); // 권장: Stack 대신 ArrayDeque

        for (int i = 0; i < N; i++) {
            String line = br.readLine();

            if (line.startsWith("push")) {
                // "push X"
                int x = Integer.parseInt(line.substring(5).trim());
                stack.push(x);
            } else if (line.equals("pop")) {
                out.append(stack.isEmpty() ? -1 : stack.pop()).append('\n');
            } else if (line.equals("size")) {
                out.append(stack.size()).append('\n');
            } else if (line.equals("empty")) {
                out.append(stack.isEmpty() ? 1 : 0).append('\n');
            } else if (line.equals("top")) {
                out.append(stack.isEmpty() ? -1 : stack.peek()).append('\n');
            }
        }

        System.out.print(out);
    }
}