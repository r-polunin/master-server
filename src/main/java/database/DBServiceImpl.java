package database;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.hibernate.Session;
import utils.TimeHelper;
import messageSystem.Address;
import messageSystem.MessageSystem;

public class DBServiceImpl implements DataAccessObject{
	private final MessageSystem messageSystem;
	private final Address address;

	public DBServiceImpl(MessageSystem msgSystem){
		address=new Address();
		messageSystem = msgSystem;
		messageSystem.addService(address,"DBService");
	}

	public MessageSystem getMessageSystem(){
		return messageSystem;
	}

    @Override
	public Address getAddress(){
		return address;
	}

	public UserDataSet getUDS(final String login, String password){
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String HQL_QUERY = "from UserModel users where users.nickname = :nickname and users.password = :password";
            org.hibernate.Query query = session.createQuery(HQL_QUERY);
            query.setParameter("nickname", login);
            query.setParameter("password", password);

            for (Iterator it = query.iterate(); it.hasNext(); ) {
                UserModel user = (UserModel) it.next();
                return getUserDataSetByUserModel(user);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            //Debug mode on
            System.out.println("DBServiceImpl something went wrong: " + e.getMessage());
        } finally {
            if (session != null) {
                session.flush();
                session.close();
            }
        }
        return null;
	}

    private UserDataSet getUserDataSetByUserModel(UserModel userModel) {
        return new UserDataSet(userModel);
    }

    @Override
	public boolean addUDS(final String login,String password){
        Session session = null;
        //TODO: tmp block, solution: unique database nickname field and exception handling;
        try {
            if (readUserByLogin(login) != null) {
                //TODO: custom exception
                throw new Exception((new StringBuilder("User ")).append(login).append(" is already exist").toString());
            }
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            UserModel user = new UserModel();
            user.setNickname(login);
            user.setPassword(password);
            user.setLastVisit(new Timestamp(TimeHelper.getCurrentTime()));
            user.setRegistrationDate(new Timestamp(TimeHelper.getCurrentTime()));

            session.save(user);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            //Debug mode on
            System.out.println("DBServiceImpl something went wrong: " + e.getMessage());
            return false;
        } finally {
            if (session != null) {
                session.flush();
                session.close();
            }
        }
	}

    //TODO: getUds refactoring
    private UserModel readUserByLogin(String login) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String HQL_QUERY = "from UserModel users where users.nickname = :nickname";
            org.hibernate.Query query = session.createQuery(HQL_QUERY);
            query.setParameter("nickname", login);

            for (Iterator it = query.iterate(); it.hasNext(); ) {
                UserModel user = (UserModel) it.next();
                return user;
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            //Debug mode on
            System.out.println("DBServiceImpl something went wrong: " + e.getMessage());
        } finally {
            if (session != null) {
                session.flush();
                session.close();
            }
        }
        return null;
    }

    @Override
	public void updateUsers(List<UserDataSet> users){
		ListIterator<UserDataSet> li = users.listIterator();
		while(li.hasNext()){
			UserDataSet user = li.next();
            updateUser(user.getModel());
		}
	}

    private void updateUser(UserModel user) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            //Debug mode on
            System.out.println("DBServiceImpl something went wrong: " + e.getMessage());
        } finally {
            if (session != null) {
                session.flush();
                session.close();
            }
        }

    }

    public void deleteUser(UserModel user) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            //Debug mode on
            System.out.println("DBServiceImpl something went wrong: " + e.getMessage());
        } finally {
            if (session != null) {
                session.flush();
                session.close();
            }
        }
    }

    @Override
    public void run(){
		while(true){
			messageSystem.execForAbonent(this);
			TimeHelper.sleep(200);
		}
	}
}
