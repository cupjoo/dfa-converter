import entity.DFA;
import io.FiniteAutomataReader;

import java.io.*;

public class Main {
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new FileReader("testcase/dfa.txt"));
        DFA dfa = FiniteAutomataReader.readDFA(br);
        dfa.print();
    }
}
