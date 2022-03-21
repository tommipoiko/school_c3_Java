package fi.tuni.prog3.json;

// import here

/**
 * A class for representing a JSON value.
 * <p>
 * @author tommipoiko
 */
public final class ValueNode extends Node {
    
    private double d;
    private boolean b;
    private String s;
    private final Object n = null;
    private final char valueType;
    
    /**
     * Constructs a JSON value node that stores the given double value.
     * @param value The double value to store in the new JSON value node.
     */
    public ValueNode(double value){
        
        this.d = value;
        this.valueType = 'd';
    }
    
    /**
     * Constructs a JSON value node that stores the given boolean value.
     * @param value The boolean value to store in the new JSON value node.
     */
    public ValueNode(boolean value) {
        
        this.b = value;
        this.valueType = 'b';
    }
    
    /**
     * Constructs a JSON value node that stores the given string or null.
     * @param value The string or null to store in the new JSON value node.
     */
    public ValueNode(String value) {
        
        if (value == null) {
            this.s = null;
            this.valueType = 'n';
        } else {
            this.s = value;
            this.valueType = 's';
        }
    }
    
    /**
     * Checks whether this value node stores a number (double).
     * @return true if this node stores a double value, otherwise false.
     */
    public boolean isNumber() {
        
        if (valueType == 'd') {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Checks whether this value node stores a boolean value.
     * @return true if this node stores a boolean value, otherwise false.
     */
    public boolean isBoolean() {
        
        if (valueType == 'b') {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Checks whether this value node stores a string.
     * @return true if this node stores a string, otherwise false.
     */
    public boolean isString() {
        
        if (valueType == 's') {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Checks whether this value node stores null.
     * @return true if this node stores null, otherwise false.
     */
    public boolean isNull() {
        
        if (valueType == 'n') {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Returns the stored value as a number (double).
     * @return the stored number as a double value.
     * @throws IllegalStateException if the stored value is not a number.
     */
    public double getNumber() {
        
        return d;
    }
    
    /**
     * Returns the stored value as a boolean value.
     * @return the stored boolean value.
     * @throws IllegalStateException if the stored value is not a boolean value.
     */
    public boolean getBoolean() {
        
        return b;
    }
    
    /**
     * Returns the stored value as a string.
     * @return the stored string.
     * @throws IllegalStateException if the stored value is not a string.
     */
    public String getString() {
        
        return s;
    }
    
    /**
     * Returns the stored value as null.
     * @return null.
     * @throws IllegalStateException if the stored value is not null.
     */
    public Object getNull() {
        
        return n;
    }
}
