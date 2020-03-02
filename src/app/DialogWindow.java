package app;

import java.awt.*;  
import javax.swing.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

class DialogWindow {
    public static String[] getUserInfo() {
        String[] userInfo = new String[4];
        JTextField userField = new JTextField(30);
        JTextField emailField = new JTextField(30);
        JPasswordField passField = new JPasswordField(30);

        JPanel regPanel = new JPanel();
        regPanel.add(new JLabel("Enter username"));
        regPanel.add(userField);
        regPanel.add(new JLabel("Enter email"));
        regPanel.add(emailField);
        regPanel.add(new JLabel("Enter password"));
        regPanel.add(passField);
        regPanel.setLayout(new GridLayout(6,1));

        JOptionPane.showConfirmDialog(null, regPanel, "User Registration", JOptionPane.OK_CANCEL_OPTION);
        userInfo[0] = userField.getText(); 
        userInfo[1] = emailField.getText();
        userInfo[2] = String.valueOf(passField.getPassword());
        return userInfo;    
    }

    public static String[] logIn(){
        String[] userInput = new String[2];
        JTextField userName = new JTextField(30);
        JPasswordField userPass = new JPasswordField(30);

        JPanel validatePanel = new JPanel();
        validatePanel.add(new JLabel("Enter usrename"));
        validatePanel.add(userName);
        validatePanel.add(new JLabel("Enter password"));
        validatePanel.add(userPass);
        validatePanel.setLayout(new GridLayout(4,1));

        JOptionPane.showConfirmDialog(null, validatePanel, "Login", JOptionPane.OK_CANCEL_OPTION);
        userInput[0] = userName.getText();
        userInput[1] = String.valueOf(userPass.getPassword());
        return userInput;
    }
}
class CheckPassword{
    public static String[] checkIfPasswordGood(String[] userInfo) throws NoSuchAlgorithmException, InvalidKeySpecException{
        String saltHash[] = new String[2];
        HashPassword hash = new HashPassword();
        boolean allowedPass = true;

        while (allowedPass) {
            if (!CheckPassword.checkLength(userInfo[2])) {
                JOptionPane.showMessageDialog(null, "To short!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
            else if(!CheckPassword.checkSpecial(userInfo[2])) {
                JOptionPane.showMessageDialog(null, "You have to use special characters!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if(!CheckPassword.checkUpperLower(userInfo[2])) {
                JOptionPane.showMessageDialog(null, "Use lower and upper case!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Good password!");
                
                saltHash = hash.generateStorngPasswordHash(userInfo[2]);
                userInfo[3] = saltHash[0];
                userInfo[2] = saltHash[1];
                System.out.println(saltHash[0]);
                System.out.println(saltHash[1]);
                allowedPass = false;
            }
        }
        return userInfo;
    }
    public static boolean checkLength(String password) {
        if (password.length() >= 8) {
            return true;
        } else {
            return false;
        }
    }

    public class LoginFrame{
        JFrame dialog;
        LoginFrame() {
            dialog = new JFrame();
        }
    }
    
    public static boolean checkUpperLower(String password) {
        if(!password.equals(password.toLowerCase()) && !password.equals(password.toUpperCase())) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkSpecial(String password) {
        String specialChar = "!#$%&'()*+,-./:;<=>?@[]^_`{|}";
        char[] pass = password.toCharArray();
        for(int i=0; i<pass.length; i++) {
            if(specialChar.contains(Character.toString(pass[i]))) {
                return true;
            } 
            
        }
        return false;
    }
}