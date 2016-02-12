package utilities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class xmlStringtoCollection {

	public static List<Integer> xmlStringtoIntegerCollection(String xmlString) {
		List<Integer> list = new ArrayList<Integer>();
		String[] var = xmlString.split("\\s+");
		for (String s : var) {
			list.add(Integer.parseInt(s));
		}
		return list;
	}
	
	public static List<String> xmlStringtoStringCollection(String xmlString) {
		List<String> list = new ArrayList<String>();
		String[] var = xmlString.split("\\s+");
		for (String s : var) {
			list.add(s);
		}
		return list;
	}
	
	public static List<Double> xmlStringtoDoubleCollection(String xmlString) {
		List<Double> list = new ArrayList<Double>();
		String[] var = xmlString.split("\\s+");
		for (String s : var) {
			list.add(Double.parseDouble(s));
		}
		return list;
	}
}
