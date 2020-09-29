package entity;

import java.util.*;

public class DFA extends FiniteAutomata {

    private List<String> states;
    private List<Character> inputs;
    private Map<CompositeKey, String> transition = new HashMap<>();
    private String startState;
    private List<String> finalStates;

    public DFA(List<String> states, List<Character> inputs, List<List<String>> transition,
                    String startState, List<String> finalStates){
        this.states = states;
        this.inputs = inputs;
        this.startState = startState;
        this.finalStates = finalStates;
        for(List<String> ts : transition){
            this.transition.put(new CompositeKey(ts.get(0), ts.get(1)), ts.get(2));
        }
    }

    @Override
    public boolean isAccepted(String input){
        // TODO add exact behavior of dfa
        return true;
    }

    @Override
    public void print(){
        System.out.println("Q: "+states.toString());
        System.out.println("Sigma: "+inputs);
        System.out.println("Delta: as shown below");
        System.out.println("q0: "+startState);
        System.out.println("F: "+finalStates);
        System.out.println("where Delta is");
        for(CompositeKey ck : transition.keySet()){
            System.out.println("\tDelta("+ck.getKey1()+", "+ck.getKey2()+") = "+transition.get(ck));
        }
    }
}