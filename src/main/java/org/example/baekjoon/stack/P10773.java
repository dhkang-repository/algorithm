package org.example.baekjoon.stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class P10773 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        long sum = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            int v = Integer.parseInt(bf.readLine());
            if (v == 0 && !stack.isEmpty()) {
                sum -= stack.pop();
            } else {
                sum += v;
                stack.push(v);
            }
        }
        System.out.println(sum);
    }
}
