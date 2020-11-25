package services;

import dataaccess.UserDB;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;

public class AccountService {
    
    public User login(String email, String password, String path) {
        UserDB userDB = new UserDB();
        
        try {
            User user = userDB.get(email);
            if (password.equals(user.getPassword())) {
				// Log activity
                Logger.getLogger(AccountService.class.getName()).log(Level.INFO, "Successful login by {0}", email);
                
				// Send E-mail
				
                /*String to = user.getEmail();
                String subject = "Notes App Login";
                String template = path + "/emailtemplates/login.html";
                
                HashMap<String, String> tags = new HashMap<>();
                tags.put("firstname", user.getFirstName());
                tags.put("lastname", user.getLastName());
                tags.put("date", (new java.util.Date()).toString());
                
                GmailService.sendMail(to, subject, template, tags);
				
*/
                return user;
            }
        } catch (Exception e) {
        }
        
        return null;
    }
    
    public String resetPassword(String email, String path, String url){
        if(email == null || email.trim().isEmpty())
            return "Error: Email cannot be empty";
        String uuid = UUID.randomUUID().toString();
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        System.out.println("---------------------------------------------------------------------------------------URL ="+url +" and path = "+path);
        if( user != null){
            userDB.addUUID(email, uuid);
            String to = user.getEmail();
            String subject = "Notes App Reset Password";

            String template = path + "/emailtemplates/resetpassword.html";
            HashMap<String, String> tags = new HashMap<>();
            tags.put("firstname", user.getFirstName());
            tags.put("lastname", user.getLastName());
            tags.put("email", email);
            tags.put("link", url+"?uuid="+uuid);

            try {
                GmailService.sendMail(to, subject, template, tags);
                return "We sent a reset password link to your email";
            } catch (Exception ex) {
                Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
                return "Error: "+ex.getMessage();
            }
        } else{
            return "Error: Specified email address doesn't exist";
        }
    }
    
    public boolean isUuidValid(String email, String uuid){
        return new UserDB().isUuidValid(email, uuid);
    }
    
    public String setPassword(String email, String password){
        if(email == null || email.trim().length() == 0)
            return "Error: Password cannot be empty";
        return new UserDB().setPassword(email, password);
    }
}
