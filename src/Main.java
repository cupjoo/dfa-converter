import entity.FiniteAutomata;
import io.FiniteAutomataReader;

import java.io.*;

public class Main {
    static BufferedReader br;
    static int faCode = 2;
    static String[] faName = {"dfa", "nfa", "enfa"};
    static int caseNum = 1;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new FileReader("test/"+ faName[faCode]+"/definition"+caseNum+".txt"));
        FiniteAutomata fa = FiniteAutomataReader.readFA(br, faCode);
        fa.print();

        br = new BufferedReader(new FileReader("test/" + faName[faCode] + "/tc" + caseNum + ".txt"));
        String tc;
        while((tc = br.readLine()) != null){
            fa.check(tc);
        }
    }
}
