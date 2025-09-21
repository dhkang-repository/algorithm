package org.example.baekjoon.queue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayDeque;

public class P10845 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        int N = Integer.parseInt(br.readLine().trim());
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            String line = br.readLine();

            if (line.startsWith("push")) {
                int x = Integer.parseInt(line.substring(5).trim());
                queue.add(x); // addLast
            } else if (line.equals("pop")) {
                out.append(queue.isEmpty() ? -1 : queue.poll()).append('\n');
            } else if (line.equals("size")) {
                out.append(queue.size()).append('\n');
            } else if (line.equals("empty")) {
                out.append(queue.isEmpty() ? 1 : 0).append('\n');
            } else if (line.equals("front")) {
                out.append(queue.isEmpty() ? -1 : queue.peek()).append('\n');
            } else if (line.equals("back")) {
                out.append(queue.isEmpty() ? -1 : queue.peekLast()).append('\n');
            }
        }

        System.out.print(out);
    }
}