package fi.tuni.prog3.json;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A class for representing a JSON array.
 * <p>
 * @author tommipoiko
 */
public final class ArrayNode extends Node implements Iterable<Node> {

    private final ArrayList<Node> nodes;

    /**
     * Constructs an initially empty JSON array node.
     */
    public ArrayNode() {
        
        this.nodes = new ArrayList<>();
    }
    
    /**
     * Returns the number of JSON nodes stored in this JSON array.
     * @return the number of JSON nodes in this JSON array.
     */
    public int size() {
        
        return this.nodes.size();
    }

    /**
     * Adds a new JSON node to the end of this JSON array.
     * @param node the new JSON node to be added. 
     */
    public void add(Node node) {
        
        this.nodes.add(node);
    }
    
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
    
    /**
     * Returns a Node iterator that iterates the JSON nodes stored in this JSON array.
     * @return a Node iterator that iterates the JSON nodes stored in this JSON array.
     */
    @Override
    public Iterator<Node> iterator() {
        
        return new ANIterator();
    }
}
