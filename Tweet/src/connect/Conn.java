package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {
	private Connection conn;

	public void open() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class.forName("cdata.jdbc.twitter.TwitterDriver").newInstance();
		conn = DriverManager
				.getConnection("jdbc:twitter:OAuth Access Token Secret=Ara5Z4hpLTyvPPreBoKJUjGtTFQJhKxJJ6jNJ2Vn3hso5;"
						+ "OAuth Access Token=818154606-Ujpz2FsT6F7OMI5YCWNqRytRNj1SZMGkQhWE7CvK;"
						+ "OAuth Client Id=qe2uCIUaZHYWiKuum3pyugvGD;OAuth Client Secret=3iSd6y8gufZVkqzNl4RB0GNAKNsr4VMIoIytVnSJyM8AcyU1LQ;");
	}

	public Connection getConn() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (conn == null || (!conn.isValid(6)))
			open();
		return conn;
	}

	public void close() throws SQLException {
		if (conn != null)
			conn.close();
	}

}
