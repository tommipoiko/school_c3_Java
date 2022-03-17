package fi.tuni.prog3.round8.xmlcountries;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;


public class CountryData {
    
    // Private
    private static Country createCountry(String name, double area,
            long popu, double gdp) {
        
        Country ctr = new Country(name, area, popu, gdp);
        return ctr;
    }
    
    // Public 
    public static ArrayList<Country> readFromXmls(String areaFile, String populationFile,
            String gdpFile) throws JDOMException, IOException {
        
        ArrayList<String> fileTypes = new ArrayList<>();
        fileTypes.add(areaFile);
        fileTypes.add(populationFile);
        fileTypes.add(gdpFile);
        
        TreeMap<String, Double> areaTemp = new TreeMap<>();
        TreeMap<String, Long> popuTemp = new TreeMap<>();
        TreeMap<String, Double> gdpTemp = new TreeMap<>();
        
        for (var namn : fileTypes) {
            File file = new File(namn);
            SAXBuilder sax = new SAXBuilder();
            Document doc = sax.build(file);
            
            Element root = doc.getRootElement();
            Element data = root.getChild("data");

            for (Element eElement : data.getChildren("record")) {
                String name = "A";
                String value = "1";
                for (Element e : eElement.getChildren("field")) {
                    if (e.getAttributeValue("name").equals("Country or Area")) {
                        name = e.getText();
                    } else if (e.getAttributeValue("name").equals("Value")) {
                        value = e.getText();
                    }
                }
                if (namn.equals(areaFile)) {
                    areaTemp.put(name, Double.parseDouble(value));
                } else if (namn.equals(populationFile)) {
                    popuTemp.put(name, Long.parseLong(value));
                } else {
                    gdpTemp.put(name, Double.parseDouble(value));
                }
            }
        }
        
        ArrayList<Country> countryList = new ArrayList<>();
        
        for (var area : areaTemp.keySet()) {
            for (var popu : popuTemp.keySet()) {
                for (var gdp : gdpTemp.keySet()) {
                    if (area.equals(popu) && area.equals(gdp) && popu.equals(gdp)) {
                        countryList.add(createCountry(area, areaTemp.get(area),
                                popuTemp.get(popu), gdpTemp.get(gdp)));
                    }
                }
            }
        }
        
        return countryList;
    }
    
    public static void writeToXml(List<Country> countries, String countryFile) throws JDOMException, IOException {
        
        String myxml = "<countries>";
        
        for (Country c : countries) {
            myxml += "<country>";
            myxml += String.format("<name>%s</name>", c.getName());
            myxml += String.format("<area>%.1f</area>", c.getArea());
            myxml += String.format("<population>%d</population>", c.getPopulation());
            myxml += String.format("<gdp>%.1f</gdp>", c.getGdp());
            myxml += "</country>";
        }
        
        myxml += "</countries>";
        
        FileOutputStream fos = new FileOutputStream(countryFile);
        SAXBuilder sb = new SAXBuilder();
        Document doc = sb.build(new StringReader(myxml));
        XMLOutputter xout = new XMLOutputter(Format.getPrettyFormat());
        xout.output(doc, fos);
    }
}
