// import here

public class ValueNode extends Node {
    
    // Private
    private double d;
    private boolean b;
    private String s;
    private char valueType;
    
    // Public
    public ValueNode(double value){
        
        this.d = value;
        this.valueType = 'd';
    }
    
    public ValueNode(boolean value) {
        
        this.b = value;
        this.valueType = 'b';
    }
    
    public ValueNode(String value) {
        
        if (value == null) {
            this.s = null;
            this.valueType = 'n';
        } else {
            this.s = value;
            this.valueType = 's';
        }
    }
    
    public boolean isNumber() {
        
        if (valueType == 'd') {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean isBoolean() {
        
        if (valueType == 'b') {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean isString() {
        
        if (valueType == 's') {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean isNull() {
        
        if (valueType == 'n') {
            return true;
        } else {
            return false;
        }
    }
    
    public double getNumber() {
        
        return d;
    }
    
    public boolean getBoolean() {
        
        return b;
    }
    
    public String getString() {
        
        return s;
    }
}
