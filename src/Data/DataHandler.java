package Data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

public class DataHandler {

    public String readFile(String pfile) throws Exception {
        BufferedReader br;
        String currentLine;
        String fileData = "";

        br = br = new BufferedReader(new FileReader(pfile));
        while ((currentLine = br.readLine()) != null) {
            fileData += currentLine + "\n";
        }

        return fileData;
    }

    public void writeFile(String pfileSource, String pfileData) throws Exception {
        File file;
        BufferedWriter bw;
        FileWriter writer;

        file = new File(pfileSource);
        writer = new FileWriter(file);
        bw = new BufferedWriter(writer);
        bw.write(pfileData);
        bw.close();
        writer.close();
    }

    public void registerUserFile(String pusername, String ppassword, String pemail) throws Exception {
        Properties prop = new Properties();
        OutputStream output;

        if (!verifyUser(pusername)) {
            String file = "src/Data/Database/Users/" + pusername + ".properties";
            output = new FileOutputStream(file);
            prop.setProperty("Username", pusername);
            prop.setProperty("Password", ppassword);
            prop.setProperty("Email", pemail);
            prop.store(output, null);

        } else {
            System.out.println("User already exists, try another username");
        }
    }

    public boolean verifyUser(String pusername) throws Exception {
        InputStream input = null;
        
        try {
            input = new FileInputStream("src/Data/Database/Users/" + pusername + ".properties");
            return true;
        } catch (FileNotFoundException ex) {
            return false;
        }
    }

    public Iterator<String> loadUsersList() {
        File folder = new File("src/Data/Database/Users");
        File[] listOfFiles = folder.listFiles();
        ArrayList<String> userList = new ArrayList<String>();

        Properties prop = new Properties();
        InputStream input = null;

        try {
            for (File file : listOfFiles) {
                input = new FileInputStream("src/Data/Database/Users/" + file.getName());
                prop.load(input);
                userList.add(prop.getProperty("Username"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return userList.iterator();
    }
}