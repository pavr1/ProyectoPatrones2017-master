package Data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class DataHandler {

    private InputStream inputStream;
    private Reader inputStreamReader;
    private ArrayList<String> userList;

    public DataHandler() {
        userList = new ArrayList<String>();
    }

    public StringBuilder readFile(String pfile) throws Exception {
//        BufferedReader br;
//        String currentLine;
//        StringBuilder fileData = new StringBuilder();
//        InputStream in = this.getClass().getResourceAsStream(pfile);
//
//        // if (verifyFile(pfile)) {
//        br = new BufferedReader(new FileReader(pfile));
//        while ((currentLine = br.readLine()) != null) {
//            System.out.println(currentLine);
//           // fileData.append(currentLine).append("\n");
//        }
//        //}
//        
        
        StringBuilder result = new StringBuilder("");

	//Get file from resources folder
	ClassLoader classLoader = getClass().getClassLoader();
	File file = new File(classLoader.getResource(pfile).getFile());

	try (Scanner scanner = new Scanner(file)) {

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
                        System.out.println(line);
			result.append(line).append("\n");
		}

		scanner.close();

	} catch (IOException e) {
		e.printStackTrace();
	}
        StringBuilder fileData = new StringBuilder();

        return fileData;
    }

    public void writeFile(String pfile, String pdata) throws Exception {
        File file;
        BufferedWriter bw = null;
        FileWriter writer = null;

        file = new File("src/Main/" + pfile);
        writer = new FileWriter(file);
        bw = new BufferedWriter(writer);
        bw.write(pdata);
        bw.close();
        writer.close();
    }

    public void registerUserFile(String pusername, String ppassword) throws Exception {
        Properties prop = new Properties();
        OutputStream output;

        if (!verifyUser(pusername)) {
            String file = "src/properties/" + "Usr" + pusername + ".properties";
            output = new FileOutputStream(file);
            prop.setProperty("Username", pusername);
            prop.setProperty("Password", ppassword);
            userList.add(file);
            prop.store(output, null);
        }
    }

    public boolean verifyUser(String pusername) throws Exception {
        Properties prop = new Properties();
        InputStream input;
        boolean userExist = false;
        String username;

        for (int i = 0; i < userList.size(); i++) {

            input = new FileInputStream(userList.get(i));
            prop.load(input);
            username = prop.getProperty("Username");

            if (username.equals(pusername)) {
                userExist = true;
            }
        }

        return userExist;
    }

    public boolean verifyFile(String pfile) {
        boolean fileExist = false;

        try {
            FileReader fr = new FileReader(pfile);
            fileExist = true;
        } catch (Exception e) {

        }

        return fileExist;
    }
}
