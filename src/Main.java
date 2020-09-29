import entity.DFA;
import io.FiniteAutomataReader;

import java.io.*;

public class Main {
    static BufferedReader br;
    static int caseNum = 1;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new FileReader("test/dfa/definition"+caseNum+".txt"));
        DFA dfa = FiniteAutomataReader.readDFA(br);
        dfa.print();

        br = new BufferedReader(new FileReader("test/dfa/tc"+caseNum+".txt"));
        String tc;
        while((tc = br.readLine()) != null){
            dfa.check(tc);
        }
    }
}
