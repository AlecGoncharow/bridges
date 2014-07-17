package edu.uncc.cs.bridges;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class AbstractEdge implements Comparable<AbstractEdge> {

	AbstractVertex source, destination;
	/**
	 * Visualization properties for this Link
	 * This could be made private.
	 */
	Map<String, String> properties = new HashMap<>();
	String randWeight = "randWeight";
	String weight = "weight";
	
	//Map<String, AbstractVertex> eOutgoing;
	public List<AbstractVertex> eOutgoing;
	
	String identifier;
	
	public AbstractEdge(AbstractVertex source, AbstractVertex destination, String identifier){
		this.source = source;
		this.destination = destination;
		this.identifier = identifier;
		
	}
	
	public AbstractEdge(double weight){
		/*this.source = source;
		this.destination = destination;
		this.identifier = identifier;*/
		setWeight(weight);
		
	}
	
	public AbstractEdge(String randomWeight){
		/*this.source = source;
		this.destination = destination;
		this.identifier = identifier;*/
		setWeight(source, destination, randomWeight);
		
	}
	
	public String getIdentifier(){
		return identifier;
	}
	
	/**
	 * Get the width in pixels
	 * @returns Width in pixels, in range [0.0, 50.0]
	 */
	public double getWeight() {
		String weight = properties.get(this.weight);
		if (weight == null) {
			return 1.0;
		} else {
			return Double.parseDouble(weight);
		}
	}
	
	/**
	 * Sets the weight of the edge
	 * Default width is provided in the following format
	 * For weights less than 4 the width is 1 pixel
	 * For weights less than 7 the width is 3 pixel
	 * For weights of other values greater than or equal to 7 the width is 5 pixel  
	 * @param weight is a double
	 *  
	 */
	public void setWeight(double weight) {
		
		properties.put(this.weight, Double.toString(weight));
		if (weight<4)
			setWidth(1);
		else if (weight<7)
			setWidth(3);
		else 
			setWidth(5);
	}
	
	/**
	 * Sets a Random value between 0 and 9 to the weight attribute of the edge
	 * Syntax: A.createEdge(B, "randWeight");
	 * Default width is provided in the following format
	 * For weights less than 4 the width is 1 pixel
	 * For weights less than 7 the width is 3 pixel
	 * For weights of other values greater than or equal to 7 the width is 5 pixel  
	 * @param weight is the string "randWeight"
	 * @param source contains the source Vertex
	 * @param source contains the destination Vertex
	 * @param random contains the String value "randWeight" later transformed in random double
	 *  between 0.0-9.0
	 */
	
	public void setWeight(AbstractVertex source, AbstractVertex destination, String random) {
		double weight;
		if (random.equals(randWeight))
			weight = Bridge.getEdgeWeight(source.toString(),destination.toString());
		else
			{
				System.out.println("Syntax error: \"randWeight\" expected. All weights are 1.");
				weight=1;
			}
		properties.put(this.weight, Double.toString(weight));
		if (weight<4)
			setWidth(1);
		else if (weight<7)
			setWidth(3);
		else 
			setWidth(5);
	}
	
	/**
	 * Get the color, according to CSS formats.
	 * By default, the color will be chosen at random.
	 * Setting the color to {@code null} or {@code ""} resets to defaults.
	 * 
	 * @param color Color as a String
	 * @see Validation#validateColor(String)
	 */
	public String getColor() {
		String prop = properties.get("color");
		if (prop == null) {
			return "";
		} else {
			return prop;
		}
	}

	/**
	 * Set the color, according to CSS formats.
	 * By default, the color will be chosen at random.
	 * Setting the color to {@code null} or {@code ""} resets to defaults.
	 * 
	 * @param color Color as a String
	 * @see Validation#validateColor(String)
	 */
	public void setColor(String color) {
		color = color.toLowerCase();
		if (color == null || color.isEmpty()) {
			properties.remove("color");
		} else {
			Validation.validateColor(color);
			properties.put("color", color);
		}
	}
	
	/**
	 * Get the dash array
	 * @return  CSS dash array, for example "5,10,5"
	 */
	public String getDash() {
		String prop = properties.get("dasharray");
		if (prop == null) {
			return "";
		} else {
			return prop;
		}
	}
	
	/**
	 * Set the dash array, effectively choosing patterns of dots and dashes for
	 * edges.
	 * @param dash CSS dash double array, for example "5,10,5"
	 */
	public void setDash(double[] dashes) {
		StringBuilder sb = new StringBuilder("");
		for (double dash : dashes) {
			sb.append(dash);
			sb.append(',');
		}
		sb.deleteCharAt(sb.length()-1);
		properties.put("dasharray", sb.toString());
	}
	
	
	/**
	 * Get the width in pixels
	 * @returns Width in pixels, in range [0.0, 50.0]
	 */
	public double getWidth() {
		String prop = properties.get("width");
		if (prop == null) {
			return 1.0;
		} else {
			return Double.parseDouble(prop);
		}
	}
	
	/**
	 * Set the width in pixels
	 * @param pixels  width in pixels, in range [0.0, 50.0]
	 */
	public void setWidth(double pixels) {
		//TODO: Should we protect against NaN and Inf?
		Validation.validateSize(pixels);
		properties.put("width", Double.toString(pixels));
	}
	
	/**
	 * Get the edge's current opacity
	 * 0.0 is invisible
	 * 1.0 is opaque
	 * @returns  Alpha, in range [0.0, 1.0]
	 */
	public double getOpacity() {
		String prop = properties.get("opacity");
		if (prop == null)
			return 1.0;
		else
			return Double.parseDouble(prop);
	}
	
	/**
	 * Set the edge opacity
	 * 0.0 is invisible
	 * 1.0 is opaque
	 * @param opacity  Alpha, in range [0.0, 1.0]
	 */
	public void setOpacity(double opacity) {
		Validation.validateOpacity(opacity);
		properties.put("opacity", Double.toString(opacity));
	}
	
	/**
	 * Internal code for getting the properties of an Edge.
	 * Vertexes are represented by index in the JSON format. Pass a map to
	 * represent that connection (possibly made by earlier traversal.)
	 * 
	 * It produces (without the spaces or newlines):
	 * <tt>
	 * {
	 *  "source": 8162,
	 *  "destination: 1827,
	 *  "other CSS properties like color": any_JSON_value
	 * }
	 * 
	 * 
	 * @param vertex_to_integer		Vertex->index map (see description)
	 * @returns the encoded JSON string
	 */
	String getRepresentation(Map<AbstractVertex, Integer> vertex_to_index) {
		String json = "{";
		for (Entry<String, String> entry : properties.entrySet()) {
			json += String.format("\"%s\": \"%s\", ", entry.getKey(), entry.getValue());
		}
		json += String.format("\"source\":%s,", vertex_to_index.get(source));
		json += String.format("\"target\":%s", vertex_to_index.get(destination));
		return json + "}";
	}

	@Override
	public int compareTo(AbstractEdge o) {
		if (o == null) {
			return 0;
		} else {
			int s_compare = source.compareTo(o.source);
			if (s_compare == 0)
				return destination.compareTo(o.destination);
			else
				return s_compare;
		}
	}
}