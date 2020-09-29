package io;

import entity.DFA;
import java.io.*;
import java.util.*;

public class FiniteAutomataReader {

    public static DFA readDFA(BufferedReader br) throws IOException {
        StringTokenizer st;
        List<String> q = new ArrayList<>();
        List<Character> sigma = new ArrayList<>();
        List<List<String>> delta = new ArrayList<>();
        String q0, in;
        List<String> f = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()){
            q.add(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()){
            sigma.add(st.nextToken().charAt(0));
        }
        for(int r = 0; r < q.size()*sigma.size(); r++){
            delta.add(new ArrayList<>());
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < 3; c++){
                delta.get(r).add(st.nextToken());
            }
        }
        q0 = br.readLine();
        while((in = br.readLine()) != null){
            f.add(in);
        }
        return new DFA(q, sigma, delta, q0, f);
    }
}
