package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;

import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * Classe che gestisce il database.
 * @author giampaolo
 *
 */
public class DBManager {
	
	public static final DBManager getInstance() {
		DBManager INSTANCE = null;
		if(INSTANCE == null) {
			try {
				INSTANCE = new DBManager();
			} catch (DBManagerException e) {
				e.getMessage();
			}
		}
		return INSTANCE;
	}
	
	private Connection conn;
	
	protected final String config = "dbconfig.properties";
	
	/**
	 * Metodo costruttore privato.
	 * Cattura la connessione.
	 * @throws DBManagerException 
	 */
	private DBManager() throws DBManagerException {
		this.conn = connect();
		if(conn == null) {
			throw new DBManagerException("Connessione non riuscita.");
		}
	}

	/**
	 * Instaura una connessione al database.
	 * @return
	 */
	public Connection connect() {
		Properties prop = new Properties();
		FileInputStream file = null;
		try {
			file = new FileInputStream(this.config);
			prop.load(file);
		} catch (IOException e) {
			e.getMessage();
		}
		finally {
			if(file != null) {
				try {
					file.close();
				} catch (IOException e) {
					e.getMessage();
				}
			}
		}
		
		Connection connDb = null;
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setServerName(prop.getProperty("hostname"));
		dataSource.setUser(prop.getProperty("username"));
		dataSource.setPassword(prop.getProperty("password"));
		dataSource.setDatabaseName(prop.getProperty("dbname"));
		try {
			connDb = dataSource.getConnection();
		} catch (SQLException e) {
			e.getMessage();
		}
		return connDb;
	}
	
	public Connection getDbConnession() {
		return this.conn;
	}

}
