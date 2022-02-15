// import here

public class Circle implements IShapeMetrics {

    // Private
    private double radius;
    
    // Public
    public Circle(double radius) {
        this.radius = radius;
    }
    
    @Override
    public String toString() {
        
        double r = this.radius;
        
        return String.format("Circle with radius: %.2f", r);
    }
    
    @Override
    public String name() { return "circle"; }
    
    @Override
    public double area() { return PI * PI * this.radius; }
    
    @Override
    public double circumference() { return 2 * PI * this.radius; }
    
}
