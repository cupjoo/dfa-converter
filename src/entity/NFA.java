package entity;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class NFA extends FiniteAutomata{

    private DFA dfa;
    private Set<MultipleState> states = new HashSet<>();
    private Map<CompositeKey, MultipleState> multiTransition = new HashMap<>();
    private Set<String> newFinalStates = new HashSet<>();
    private List<Character> inputs;
    private String startState;

    public NFA(List<Character> inputs, List<List<String>> transition,
               String startState, List<String> finalStates){

        Queue<MultipleState> q = new ArrayDeque<>();
        Map<CompositeKey, MultipleState> singleTransition = new HashMap<>();

        this.inputs = inputs;
        this.startState = startState;
        for(List<String> ts : transition){
            String from = ts.get(0);
            char input = ts.get(1).charAt(0);
            MultipleState to = new MultipleState(ts.subList(2, ts.size()));
            singleTransition.put(new CompositeKey(from, input), to);
        }

        // subset construction
        MultipleState state = new MultipleState(startState);
        states.add(state);
        q.offer(state);

        while(!q.isEmpty()){
            MultipleState now = q.poll();
            if(now.isFinal(finalStates)){
                newFinalStates.add(now.toString());
            }
            for(char in : inputs){
                CompositeKey key = new CompositeKey(now, in);
                MultipleState next = new MultipleState();
                for(String s : now.toList()){
                    MultipleState multipleState = singleTransition.get(new CompositeKey(s, in));
                    if(multipleState != null)
                        next.addAll(multipleState.toList());
                }
                multiTransition.put(key, next);
                if(!states.contains(next)){
                    states.add(next);
                    q.offer(next);
                }
            }
        }
        convertToDFA();
    }

    public void convertToDFA(){
        List<String> dfaStates = states.stream()
                .map(MultipleState::toString).collect(toList());
        List<List<String>> dfaTransition = new ArrayList<>();
        for(CompositeKey ck : multiTransition.keySet()){
            List<String> ts = new ArrayList<>();
            ts.add(ck.getKey1().toString());
            ts.add(ck.getKey2()+"");
            ts.add(multiTransition.get(ck).toString());
            dfaTransition.add(ts);
        }
        dfa = new DFA(dfaStates, inputs, dfaTransition, "["+startState+"]", new ArrayList<>(newFinalStates));
    }

    @Override
    public boolean isAccepted(String input) {
        return dfa.isAccepted(input);
    }

    @Override
    public void print() {
        dfa.print();
    }
}
