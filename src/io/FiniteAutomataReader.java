package io;

import entity.impl.DFA;
import entity.impl.ENFA;
import entity.FiniteAutomata;
import entity.impl.NFA;

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
        // read initial state and final states
        q0 = br.readLine();
        st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()){
            f.add(st.nextToken());
        }
        // read delta
        while((in = br.readLine()) != null){
            delta.add(new ArrayList<>());
            st = new StringTokenizer(in);
            while(st.hasMoreTokens()){
                delta.get(delta.size()-1).add(st.nextToken());
            }
        }
    }

    public static FiniteAutomata readFA(BufferedReader br, int code) throws IOException {
        read(br);
        if(code == 0){
            return new DFA(q, sigma, delta, q0, f);
        } else if(code == 1) {
            return new NFA(sigma, delta, q0, f);
        } else {
            return new ENFA(sigma, delta, q0, f);
        }
    }
}
