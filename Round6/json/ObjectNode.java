import java.util.TreeMap;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Iterator;

public class ObjectNode extends Node implements Iterable<String> {
    
    // Private
    private TreeMap<String, Node> nodes;
    
    // Public
    public ObjectNode(){
        
        this.nodes = new TreeMap<>();
    }
    
    public Node get(String key) {
        
        return this.nodes.get(key);
    }
    
    public void set(String key, Node node) {
        
        this.nodes.put(key, node);
    }
    
    public int size() { return this.nodes.size(); }
    
    public ArrayList<String> keys() {
        
        ArrayList<String> keys = new ArrayList<>();
        
        for (var a : this.nodes.keySet()) {
            keys.add(a);
        }
        
        return keys;
    }
    
    // Iterator
    private class ONIterator implements Iterator<String> {
        
        private int index = 0;
        private String current = null;
        
        @Override
        public boolean hasNext() { return this.index != ObjectNode.this.nodes.size(); }
        
        @Override
        public String next() throws NoSuchElementException {
            
            if (this.index == ObjectNode.this.nodes.size()) {
                throw new NoSuchElementException("You've gone too far!");
            } else {
                this.current = ObjectNode.this.keys().get(this.index);
                String ret = this.current;
                this.index++;
                return ret;
            }
        }
    }
    
    @Override
    public Iterator<String> iterator() {
        
        return new ONIterator();
    }
}
