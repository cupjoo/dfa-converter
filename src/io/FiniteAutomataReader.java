package io;

import entity.DFA;
import entity.FiniteAutomata;
import entity.NFA;

import java.io.*;
import java.util.*;

public class FiniteAutomataReader {

    static StringTokenizer st;
    static List<String> q = new ArrayList<>();
    static List<Character> sigma = new ArrayList<>();
    static List<List<String>> delta = new ArrayList<>();
    static String q0, in;
    static List<String> f = new ArrayList<>();

    public static void read(BufferedReader br) throws IOException {
        q = new ArrayList<>();
        sigma = new ArrayList<>();
        delta = new ArrayList<>();
        f = new ArrayList<>();

        // read states
        st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()){
            q.add(st.nextToken());
        }
        // read inputs
        st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()){
            sigma.add(st.nextToken().charAt(0));
        }
        // read delta
        for(int r = 0; r < q.size()*sigma.size(); r++){
            delta.add(new ArrayList<>());
            st = new StringTokenizer(br.readLine());
            while(st.hasMoreTokens()){
                delta.get(r).add(st.nextToken());
            }
        }
        q0 = br.readLine();
        while((in = br.readLine()) != null){
            f.add(in);
        }
    }

    public static FiniteAutomata readFA(BufferedReader br, int code) throws IOException {
        read(br);
        if(code == 0){
            return new DFA(q, sigma, delta, q0, f);
        } else {
            return new NFA(sigma, delta, q0, f);
        }
    }
}
