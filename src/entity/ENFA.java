package entity;

import entity.embedded.CompositeKey;
import entity.embedded.MultipleState;

import java.util.*;

public class ENFA extends AbstractNFA{

    public ENFA(List<Character> inputs, List<List<String>> transition,
                String startState, List<String> finalStates){
        super(inputs, transition, startState, finalStates);
    }

    private List<String> getClosure(List<String> states){
        Set<String> closure = new HashSet<>();
        for(String state : states){
            closure.addAll(getClosure(state));
        }
        List<String> collect = new ArrayList<>(closure);
        Collections.sort(collect);
        return collect;
    }

    private List<String> getClosure(String state){
        Queue<String> q = new ArrayDeque<>();
        Set<String> closure = new HashSet<>();
        closure.add(state);
        q.add(state);

        while(!q.isEmpty()){
            MultipleState now = singleTransition.get(new CompositeKey(q.poll(), '$'));
            if(now == null) continue;
            for(String next : now.toList()){
                if(closure.contains(next))  continue;
                closure.add(next);
                q.add(next);
            }
        }
        List<String> collect = new ArrayList<>(closure);
        Collections.sort(collect);
        return collect;
    }

    @Override
    void setInputs(List<Character> inputs) {
        inputs.remove(inputs.indexOf('$'));
        this.inputs = inputs;
    }

    @Override
    void setStartState(String startState) {
        this.startState.addAll(getClosure(startState));
    }

    @Override
    String multipleStateToString(MultipleState state) {
        return getClosure(state.toList()).toString().replaceAll("\\s","");
    }

    @Override
    List<String> multipleStateToList(MultipleState state){
        return getClosure(state.toList());
    }

    @Override
    MultipleState getMultipleState(String startState){
        return new MultipleState(getClosure(startState));
    }
}
