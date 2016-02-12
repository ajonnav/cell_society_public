package util;

import java.util.ArrayList;
import java.util.Collection;

public class xmlStringtoCollection {

	public xmlStringtoCollection() {
		// TODO Auto-generated constructor stub
	}
	
	public static Collection<Integer> xmlStringtoIntegerCollection(String xmlString) {
		Collection<Integer> list = new ArrayList<Integer>();
		String[] var = xmlString.split("\\s+");
		for (String s : var) {
			list.add(Integer.parseInt(s));
		}
		return list;
	}
	
	public static Collection<String> xmlStringtoStringCollection(String xmlString) {
		Collection<String> list = new ArrayList<String>();
		String[] var = xmlString.split("\\s+");
		for (String s : var) {
			list.add(s);
		}
		return list;
	}
	
	public static Collection<Double> xmlStringtoDoubleCollection(String xmlString) {
		Collection<Double> list = new ArrayList<Double>();
		String[] var = xmlString.split("\\s+");
		for (String s : var) {
			list.add(Double.parseDouble(s));
		}
		return list;
	}

}
