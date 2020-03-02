package app;
import javax.swing.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


class Password {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException{
        
        String[] userInfo = new String[4];
        String[] userInput = new String[2];
        boolean passAllowed = false;
        userInfo = DialogWindow.getUserInfo();
        userInfo = CheckPassword.checkIfPasswordGood(userInfo);
        while (!passAllowed){
            userInput = DialogWindow.logIn();
            passAllowed = HashPassword.validatePassword(userInput[1], userInfo[2], userInfo[3]);
            if (!passAllowed){
                JOptionPane.showMessageDialog(null, "Wrong password!");
            } else {
                JOptionPane.showMessageDialog(null, "Welcome!");  
            }
        }
    }    
}