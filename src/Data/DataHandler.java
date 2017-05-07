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
import java.util.Properties;

public class DataHandler {

    public DataHandler() {

    }

    public StringBuilder readFile(String pfile) throws Exception {
        BufferedReader br;
        String currentLine;
        StringBuilder fileData = new StringBuilder();

        try {
            br = br = new BufferedReader(new FileReader(pfile));
            while ((currentLine = br.readLine()) != null) {
                System.out.println(currentLine);
                fileData.append(currentLine).append("\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist");
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

    public void registerUserFile(String pusername, String ppassword) throws Exception {
        Properties prop = new Properties();
        OutputStream output;

        if (!verifyUser(pusername)) {
            String file = "src/Data/Database/Users/" + pusername + ".properties";
            output = new FileOutputStream(file);
            prop.setProperty("Username", pusername);
            prop.setProperty("Password", ppassword);
            prop.store(output, null);

        } else {
            System.out.println("User already exists, try another username");
        }
    }

    public boolean verifyUser(String pusername) throws Exception {
        InputStream input = null;
        Properties prop = new Properties();

        try {
            input = new FileInputStream("src/Data/Database/Users/" + pusername + ".properties");
            prop.load(input);
            return true;
        } catch (FileNotFoundException ex) {
            return false;
        }
    }

    public boolean verifyFile(String pfile) {
        boolean fileExists = false;

        // File f = new File(pfile).isFile();
        //  if (new File(pfile).isFile()) {
        //fileExists = true;
        // }
        return new File(pfile).exists();
    }
}
