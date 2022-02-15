// import here

public class Rectangle implements IShapeMetrics {

    // Private
    private double height;
    private double width;
    
    // Public
    public Rectangle(double height, double width) {
        this.height = height;
        this.width = width;
    }
    
    @Override
    public String toString() {
        
        double h = this.height;
        double w = this.width;
        
        return String.format("Rectangle with height %.2f and width %.2f", h, w);
    }
    
    @Override
    public String name() { return "rectangle"; }
    
    @Override
    public double area() { return this.height * this.width; }
    
    @Override
    public double circumference() { return 2 * this.height + 2 * this.width; }
}
