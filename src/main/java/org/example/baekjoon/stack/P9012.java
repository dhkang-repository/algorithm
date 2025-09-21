package org.example.baekjoon.stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P9012 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            String s = bf.readLine();

            int bal = 0;
            boolean ok = true;
            for(int j=0;j<s.length();j++){
                char c = s.charAt(j);

                if(c=='(') bal++;
                else if(c==')') bal--;
                else continue;

                if(bal<0) {ok = false; break;}
            }
            if(ok && bal == 0) sb.append("YES\n");
            else sb.append("NO\n");
        }

        System.out.println(sb);
    }
}
