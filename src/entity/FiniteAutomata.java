package entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class FiniteAutomata {
    protected List<Character> inputs;
    protected Set<String> finalStates = new HashSet<>();

    public abstract boolean isAccepted(String input);
    public abstract void print();

    public void check(String input){
        System.out.print(isAccepted(input) ? "Accepted" : "Rejected");
        System.out.println(" "+input);
    }
}
