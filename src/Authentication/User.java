package Authentication;

import Data.DataHandler;

public class User {

    public User createPlayer(String pusername, String ppassword, String pemail) throws Exception{
        
        DataHandler dt = new DataHandler();
        
        if(!dt.verifyUser(pusername)){
            dt.registerUserFile(pusername, ppassword,pemail);
        }

        User gPlayer = new User();

        return gPlayer;
    }

    public void loadPlayer() {

    }

    public void verifyPlayer() {

    }

}