package entity.impl;

import entity.AbstractNFA;
import entity.embedded.MultipleState;

import java.util.*;

public class NFA extends AbstractNFA {

    public NFA(List<Character> inputs, List<List<String>> transition,
               String startState, List<String> finalStates){
        super(inputs, transition, startState, finalStates);
    }

    @Override
    protected void setInputs(List<Character> inputs) {
        this.inputs = inputs;
    }

    @Override
    protected void setStartState(String startState) {
        this.startState.add(startState);
    }

    @Override
    protected String multipleStateToString(MultipleState state) {
        return state.toString();
    }

    @Override
    protected List<String> multipleStateToList(MultipleState state) {
        return state.toList();
    }

    @Override
    protected MultipleState getMultipleState(String startState) {
        return new MultipleState(startState);
    }
}
