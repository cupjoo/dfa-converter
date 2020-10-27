package entity;

import entity.embedded.MultipleState;

import java.util.*;

public class NFA extends AbstractNFA{

    public NFA(List<Character> inputs, List<List<String>> transition,
               String startState, List<String> finalStates){
        super(inputs, transition, startState, finalStates);
    }

    @Override
    void setStartState(String startState) {
        this.startState.add(startState);
    }

    @Override
    String multipleStateToString(MultipleState state) {
        return state.toString();
    }

    @Override
    List<String> multipleStateToList(MultipleState state) {
        return state.toList();
    }

    @Override
    MultipleState getMultipleState(String startState) {
        return new MultipleState(startState);
    }
}
