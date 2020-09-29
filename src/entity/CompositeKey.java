package entity;

public class CompositeKey {
    private final Object key1;
    private final char key2;

    public CompositeKey(Object key1, char key2) {
        this.key1 = key1;
        this.key2 = key2;
    }

    public Object getKey1(){
        return key1;
    }

    public Object getKey2(){
        return key2;
    }

    @Override
    public boolean equals(Object obj){
        if (!(obj instanceof CompositeKey)) return false;
        CompositeKey other = (CompositeKey) obj;
        return this.key1.equals(other.key1) && this.key2 == other.key2;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hashCode = 17;
        hashCode = prime * hashCode + key1.hashCode();
        hashCode = prime * hashCode + key2;
        return hashCode;
    }
}
