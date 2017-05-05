package Data;

import games.Interfaces.IGame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Properties;

public class DataHandler {

    private InputStream inputStream;
    private Reader inputStreamReader;
    private ArrayList<String> userList;

    public DataHandler() {
        userList = new ArrayList<String>();
    }

    public StringBuilder readFile(String pfile) throws Exception {
        BufferedReader br;
        String currentLine;
        StringBuilder fileData = new StringBuilder();

        // if (verifyFile(pfile)) {
        br = new BufferedReader(new FileReader("src/properties/" + pfile));
        while ((currentLine = br.readLine()) != null) {
            //System.out.println(currentLine);
            fileData.append(currentLine).append("\n");
        }
        //}

        return fileData;
    }

    public void writeFile(String pfile, String pextension, String pfileData) throws Exception {
        File file;

        //if (!verifyFile(pfile)) {
        file = new File("src/properties/" + pfile + "." + pextension);
        FileWriter writer = new FileWriter(file);
        writer.write(pfileData);
        writer.close();
        // }
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

    /* public boolean verifyFile(String pfile) throws Exception {
        boolean fileExist = false;

        FileReader fr = new FileReader("src/properties/" + pfile);
        
            fileExist = true;
        

        return fileExist;
    }*/
}