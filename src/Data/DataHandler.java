package Data;

import games.Interfaces.IGame;
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

    public StringBuilder readFile(String pfile, IGame pgameType) throws Exception {
        BufferedReader br;
        String currentLine;
        StringBuilder fileData = new StringBuilder();

//        String extension = "";
//        int i = pfile.lastIndexOf('.');
//        if (i > 0) {
//            extension = pfile.substring(i + 1);
//        }
        try {
            // br = new BufferedReader(new FileReader(pgameType.getSourcePackage() + pfile));
            br = br = new BufferedReader(new FileReader(pfile));
            while ((currentLine = br.readLine()) != null) {
                System.out.println(currentLine);
                fileData.append(currentLine).append("\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File does not exists");
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
        boolean fileExists = false;

        // File f = new File(pfile).isFile();
        //  if (new File(pfile).isFile()) {
        //fileExists = true;
        // }
        return new File(pfile).exists();
    }
}
