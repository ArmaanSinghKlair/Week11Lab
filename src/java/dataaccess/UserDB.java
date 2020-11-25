package dataaccess;

import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.User;


public class UserDB {
    public User get(String email) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            User user = em.find(User.class, email);
            return user;
        } finally {
            em.close();
        }
    }
    
    public String addUUID(String email, String uuid){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            User user = em.find(User.class, email);            
            trans.begin();
            user.setResetpasswordUUID(uuid);
            trans.commit();
            return "Success";
        } catch(Exception e){
            return "Error: "+e.getMessage();
        }finally {
            em.close();
        }
    }
    
    public String deleteUUID(String email){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            User user = em.find(User.class, email);
            
            trans.begin();
            user.setResetpasswordUUID(null);
            trans.commit();
            return "Success";
        } catch(Exception e){
            return "Error: "+e.getMessage();
        }finally {
            em.close();
        }
    }
    
    public boolean isUuidValid(String email, String uuid){
         EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            User user = em.find(User.class, email);
            if(user.getResetpasswordUUID() == null)
                return false;
            return user.getResetpasswordUUID().equals(uuid);
        } finally {
            em.close();
        }

    }
    
    public String setPassword(String email, String password){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            User user = em.find(User.class, email);
            
            trans.begin();
            user.setPassword(password);
            trans.commit();
            return "Success";
        } catch(Exception e){
            return "Error: "+e.getMessage();
        }finally {
            em.close();
        }
    }
}
