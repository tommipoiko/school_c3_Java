package fi.tuni.prog3.json;

import java.util.TreeMap;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * A class for representing a JSON object.
 */
public final class ObjectNode extends Node implements Iterable<String> {
    
    private final TreeMap<String, Node> nodes;
    
    /**
     * Constructs an initially empty JSON object node.
     */
    public ObjectNode(){
        
        this.nodes = new TreeMap<>();
    }
    
    /**
     * Returns the number of JSON nodes stored under this JSON object.
     * @return the number of JSON nodes under this JSON object.
     */
    public int size() { return this.nodes.size(); }
    
    /**
     * Returns the JSON node stored under the given name.
     * @param name the name of the name-node pair whose node should be returned.
     * @return the JSON node corresponding to name, or null if such node does
     * not exist.
     */
    public Node get(String name) {
        
        return this.nodes.get(name);
    }
    
    /**
     * Stores a name - JSON node pair into this JSON object. If a name-node
     * pair with the same name already exists, the previously existing node
     * will be replaced.
     * @param name the name of the name-node pair.
     * @param node the JSON node of the name-node pair.
     */
    public void set(String name, Node node) {
        
        this.nodes.put(name, node);
    }

    private ArrayList<String> keys() {
        
        ArrayList<String> keys = new ArrayList<>();
        
        for (var a : this.nodes.keySet()) {
            keys.add(a);
        }
        
        return keys;
    }
    
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
    
    /**
     * Returns a String iterator that iterates the names of the name-node pairs
     * stored in this JSON object in natural String order.
     * @return a String iterator that iterates the names of the stored name-node
     * pairs in natural String order.
     */
    @Override
    public Iterator<String> iterator() {
        
        return new ONIterator();
    }
}
