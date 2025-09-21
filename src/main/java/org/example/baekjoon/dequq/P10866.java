package org.example.baekjoon.dequq;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class P10866 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine().trim());
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            String str = bf.readLine();

            if(str.startsWith("push_front")) {
                int v = Integer.parseInt(str.substring(10).trim());
                deque.addFirst(v);
            } else if (str.startsWith("push_back")) {
                int v = Integer.parseInt(str.substring(9).trim());
                deque.addLast(v);
            } else if (str.startsWith("pop_front")) {
                sb.append(deque.isEmpty() ? -1 : deque.pollFirst()).append('\n');
            } else if (str.startsWith("pop_back")) {
                sb.append(deque.isEmpty() ? -1 : deque.pollLast()).append('\n');
            } else if (str.startsWith("size")) {
                sb.append(deque.size()).append('\n');
            } else if (str.startsWith("empty")) {
                sb.append(deque.isEmpty() ?  1 : 0).append('\n');
            } else if (str.startsWith("front")) {
                sb.append(deque.isEmpty() ? -1 : deque.peekFirst()).append('\n');
            } else if (str.startsWith("back")) {
                sb.append(deque.isEmpty() ? -1 : deque.peekLast()).append('\n');
            }
        }

        System.out.printf("%s", sb);
    }
}
