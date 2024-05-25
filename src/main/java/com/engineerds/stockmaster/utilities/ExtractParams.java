package main.java.com.engineerds.stockmaster.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ExtractParams {

	private static int findIndexParams(String cadena, char param) {
    	int index = -1;
		for(int i = 0; i < cadena.length(); i++) {
			if(cadena.charAt(i) == param) {
				return i;
			}
		}
		return index;
	}
	
	private static String extractAndReplace(String cad, int index, ArrayList<String> listParams) {
		String params = cad;
		String sub = params.substring(0, index);
		listParams.add(sub);
		params = params.replace(sub, "");
		params = params.replaceFirst("&", "");
		return params;
	}
    
	private static ArrayList<String> getListParams(String params_) {
		String params = params_;
    	ArrayList<String> listParams = new ArrayList<String>();
    	listParams.clear();
    	while(params.contains(String.valueOf('&'))) {
			params = extractAndReplace(params, findIndexParams(params, '&'), listParams);
		}
    	return listParams;
    }
    
    public static Map<String, String> getParams(String urlParams) {
    	String params = urlParams.concat("&");
    	String key;
    	String value;
    	ArrayList<String> listParams = getListParams(params); 
    	HashMap<String, String> HashParams = new HashMap<String, String>();
    	for(String param: listParams) {
    		int index = findIndexParams(param, '=');
    		key = param.substring(0, index);
    		value = param.substring(index + 1, param.length());
    		HashParams.put(key, value);
		}
    	return HashParams;
    }
    
}
