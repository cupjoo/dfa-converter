package entity;

public abstract class FiniteAutomata {
    public abstract boolean isAccepted(String input);
    public abstract void print();

    public void check(String input){
        System.out.print(isAccepted(input) ? "Accepted" : "Rejected");
        System.out.println(" "+input);
    }
}
