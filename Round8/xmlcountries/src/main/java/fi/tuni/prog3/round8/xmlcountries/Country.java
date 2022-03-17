package fi.tuni.prog3.round8.xmlcountries;

// import here

public class Country implements Comparable<Country> {
    
    // Private
    private final String name;
    private final double area;
    private final long population;
    private final double gdp;
    
    //Public
    public Country(String name, double area, long population, double gdp) {
        
        this.name = name;
        this.area = area;
        this.population = population;
        this.gdp = gdp;
    }
    
    public String getName() {
        
        return name;
    }
    
    public double getArea() {
        
        return area;
    }
    
    public long getPopulation() {
        
        return population;
    }
    
    public double getGdp() {
        
        return gdp;
    }

    @Override
    public String toString() {
        
        String data = "";
        data += String.format("%s\n  ", name);
        data += String.format("Area: %.1f km2\n  ", area);
        data += String.format("Population: %d\n  ", population);
        data += String.format("GDP: %.1f (2015 USD)\n\n", gdp);
        
        return data;
    }
    
    @Override
    public int compareTo(Country other) {
        
        int cmp = name.compareTo(other.getName());
        return cmp;
    }
}
