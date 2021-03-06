package entity;

import entity.embedded.CompositeKey;
import entity.embedded.MultipleState;
import entity.impl.DFA;

import java.util.*;

import static java.util.stream.Collectors.toList;

public abstract class AbstractNFA extends FiniteAutomata{

    private DFA dfa;
    protected List<String> startState = new ArrayList<>();
    protected Set<MultipleState> states = new HashSet<>();
    protected Map<CompositeKey, MultipleState> transition = new HashMap<>();
    protected Map<CompositeKey, MultipleState> singleTransition = new HashMap<>();

    public AbstractNFA(List<Character> inputs, List<List<String>> transition,
                       String startState, List<String> finalStates){
        setInputs(inputs);
        Queue<MultipleState> q = new ArrayDeque<>();
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
                    if(multipleState != null){
                        next.addAll(multipleStateToList(multipleState));
                    }
                }
                if(!next.isEmpty()){
                    this.transition.put(key, next);
                    if(!states.contains(next)){
                        states.add(next);
                        q.offer(next);
                    }
                }
            }
        }
        setDfa();
    }

    protected abstract void setInputs(List<Character> inputs);

    protected abstract void setStartState(String startState);

    protected abstract String multipleStateToString(MultipleState state);

    protected abstract List<String> multipleStateToList(MultipleState state);

    protected abstract MultipleState getMultipleState(String startState);

    private void setDfa(){
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
        String startState = this.startState.toString().replaceAll("\\s","");
        dfa = new DFA(dfaStates, inputs, dfaTransition, startState, new ArrayList<>(finalStates));
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
