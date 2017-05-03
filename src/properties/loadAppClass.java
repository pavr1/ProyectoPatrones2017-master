/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package properties;

/**
 *
 * @author gpalomox
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class loadAppClass {

  public static void main( String[] args ){

    	Properties prop = new Properties();
    	InputStream input = null;

    	try {

    		String filename = "config.properties";
    		input = loadAppClass.class.getClassLoader().getResourceAsStream(filename);
    		if(input==null){
    	            System.out.println("Sorry, unable to find " + filename);
    		    return;
    		}

    		//load a properties file from class path, inside static method
    		prop.load(input);

                //get the property value and print it out
                System.out.println(prop.getProperty("database"));
    	        System.out.println(prop.getProperty("dbuser"));
    	        System.out.println(prop.getProperty("dbpassword"));

    	} catch (IOException ex) {
    		ex.printStackTrace();
        } finally{
        	if(input!=null){
        		try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        	}
        }

    }
}