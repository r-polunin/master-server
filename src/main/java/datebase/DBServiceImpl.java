package datebase;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	private Connection connection;

	public DBServiceImpl(MessageSystem msgSystem){
		address=new Address();
		messageSystem = msgSystem;
		messageSystem.addService(address,"DBService");
	}

	public MessageSystem getMessageSystem(){
		return messageSystem;
	}

	public Address getAddress(){
		return address;
	}

	public UserDataSet getUDS(final String login, String password){
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String HQL_QUERY = "from UserModel users where users.nickname = :nickname, users.password = :password";
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
        int id = userModel.getId();
        String login = userModel.getNickname();
        int rating = userModel.getRating();
        int winQuantity = userModel.getWinQuantity();
        int loseQuantity = userModel.getLoseQuantity();
        return new UserDataSet(id,login,rating,winQuantity,loseQuantity);
    }

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
    /*
	public void updateUsers(List<UserDataSet> users){
		ListIterator<UserDataSet> li = users.listIterator();
		while(li.hasNext()){
			UserDataSet user = li.next();
			String login = user.getNick();
			int rating = user.getRating();
			int winQuantity = user.getWinQuantity();
			int loseQuantity = user.getLoseQuantity();
			TExecutor.updateUser(connection, login, rating, winQuantity, loseQuantity);
		}
	}
	*/

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

    public void run(){
		try{
			Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
			DriverManager.registerDriver(driver);
		}
		catch(Exception e){
			System.err.println("\nError");
			System.err.println("DBServiceImpl, run1");
			System.err.println(e.getMessage());
			System.exit(-1);
		}
		String url="jdbc:mysql://localhost:3306/checkers?user=root&password=";
		try{
			connection = DriverManager.getConnection(url);
		}
		catch(Exception e){
			System.err.println("\nError");
			System.err.println("DBServiceImpl, run2");
			System.err.println(e.getMessage());
			System.exit(-1);
		}
		while(true){
			messageSystem.execForAbonent(address);
			TimeHelper.sleep(200);
		}
	}
}
