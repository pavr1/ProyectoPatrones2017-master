package Authentication;

import Data.DataHandler;

public class User {

    public User createPlayer(String pusername, String ppassword, String pemail) throws Exception{
        
        DataHandler dt = new DataHandler();
        
        if(!dt.verifyUser(pusername)){
            dt.registerUserFile(pusername, ppassword,pemail);
        }

        User gPlayer = new User();
        
        String username = pusername;
        String correo = pemail;
        String password = ppassword;
       
       
        dt.registerUserFile(username, correo, password);
       
        System.out.println("**************************************");
        System.out.println("*El usuario se registro exitosamente*");
        System.out.println("**************************************");
        System.out.println();

        return gPlayer;
    }

    public void loadPlayer() {

    }

    public void verifyPlayer() {

    }

}

