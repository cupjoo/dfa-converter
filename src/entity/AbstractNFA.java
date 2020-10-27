package entity;

import entity.embedded.CompositeKey;
import entity.embedded.MultipleState;

import java.util.*;

import static java.util.stream.Collectors.toList;

public abstract class AbstractNFA extends FiniteAutomata{

    private DFA dfa;
    protected List<String> startState = new ArrayList<>();
    protected Set<MultipleState> states = new HashSet<>();
    protected Map<CompositeKey, MultipleState> transition = new HashMap<>();

    public AbstractNFA(List<Character> inputs, List<List<String>> transition,
                       String startState, List<String> finalStates){
        Queue<MultipleState> q = new ArrayDeque<>();
        Map<CompositeKey, MultipleState> singleTransition = new HashMap<>();

        this.inputs = inputs;
        for(List<String> ts : transition){
            String from = ts.get(0);
            char input = ts.get(1).charAt(0);
            MultipleState to = new MultipleState(ts.subList(2, ts.size()));
            singleTransition.put(new CompositeKey(from, input), to);
        }
        setStartState(startState);
        // subset construction
        MultipleState state = getMultipleState(startState);
        states.add(state);
        q.offer(state);

        while(!q.isEmpty()){
            MultipleState now = q.poll();
            if(now.isFinal(finalStates)){
                this.finalStates.add(multipleStateToString(now));
            }
            for(char in : inputs){
                CompositeKey key = new CompositeKey(now, in);
                MultipleState next = new MultipleState();
                for(String s : now.toList()){
                    MultipleState multipleState = singleTransition.get(new CompositeKey(s, in));
                    if(multipleState != null)
                        next.addAll(multipleStateToList(multipleState));
                }
                this.transition.put(key, next);
                if(!states.contains(next)){
                    states.add(next);
                    q.offer(next);
                }
            }
        }
        dfa = toDFA();
    }

    abstract void setStartState(String startState);

    abstract String multipleStateToString(MultipleState state);

    abstract List<String> multipleStateToList(MultipleState state);

    abstract MultipleState getMultipleState(String startState);

    protected DFA toDFA(){
        List<String> dfaStates = states.stream()
                .map(MultipleState::toString).collect(toList());
        List<List<String>> dfaTransition = new ArrayList<>();
        for(CompositeKey ck : transition.keySet()){
            List<String> ts = new ArrayList<>();
            ts.add(ck.getKey1().toString());
            ts.add(ck.getKey2()+"");
            ts.add(transition.get(ck).toString());
            dfaTransition.add(ts);
        }
        return new DFA(dfaStates, inputs, dfaTransition,
                startState.toString(), new ArrayList<>(finalStates));
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
