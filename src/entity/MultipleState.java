package entity;

import java.util.*;

public class MultipleState {

    private Set<String> states = new HashSet<>();

    public MultipleState(){}

    public MultipleState(String state){
        add(state);
    }

    public MultipleState(List<String> states){
        addAll(states);
    }

    public void add(String item){
        states.add(item);
    }

    public void addAll(List<String> states){
        this.states.addAll(states);
    }

    public List<String> toList(){
        return new ArrayList<>(states);
    }

    public boolean isFinal(List<String> finalState){
        for(String state : finalState){
            if(states.contains(state))  return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MultipleState)) return false;
        MultipleState other = (MultipleState) obj;
        if(states.size() != other.toList().size())  return false;
        return states.containsAll(other.toList());
    }

    @Override
    public String toString(){
        List<String> collect = new ArrayList<>(states);
        Collections.sort(collect);
        return collect.toString().replaceAll("\\s","");
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
