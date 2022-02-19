import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayNode extends Node implements Iterable<Node> {

    // Private
    private ArrayList<Node> nodes;
    
    // Public
    public ArrayNode() {
        
        this.nodes = new ArrayList<>();
    }
    
    public void add(Node node) {
        
        this.nodes.add(node);
    }
    
    public int size() { return this.nodes.size(); }
    
    // Iterator
    private class ANIterator implements Iterator<Node> {
        
        private int index = 0;
        private Node current = null;
        
        @Override
        public boolean hasNext() { return this.index != ArrayNode.this.nodes.size(); }
        
        @Override
        public Node next() throws NoSuchElementException {
            
            if (this.index == ArrayNode.this.nodes.size()) {
                throw new NoSuchElementException("You've gone too far!");
            } else {
                this.current = ArrayNode.this.nodes.get(this.index);
                Node ret = this.current;
                this.index++;
                return ret;
            }
        }
    }
    
    @Override
    public Iterator<Node> iterator() {
        
        return new ANIterator();
    }
}
