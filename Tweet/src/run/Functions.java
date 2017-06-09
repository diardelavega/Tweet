package run;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import Object.SimpleTweet;
import connect.Conn;

public class Functions {

	Conn conn = new Conn();

	public List<SimpleTweet> getLastNTweets(int n)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		String q = "SELECT ID, Created_At, Text, From_User_Name, XML_EXTRACT(URLs,'//expanded_url',',')as url, "
				+ "XML_EXTRACT(Hashtags,'//text',',')as hash   FROM Tweets   limit " + n + ";";
		ResultSet rs = conn.getConn().createStatement().executeQuery(q);
		SimpleTweet st;
		List<SimpleTweet> stl = new ArrayList<>();
		while (rs.next()) {
			st = new SimpleTweet();
			st.setCreated(strToLocDate(rs.getString("Created_At")));
			st.setID(rs.getLong("ID"));
			st.setFromUser(rs.getString("From_User_Name"));
			st.setText(rs.getString("Text"));
			st.setUrls(rs.getString("url"));
			st.setHashtags(rs.getString("hash"));
			stl.add(st);
		}
		conn.close();
		return stl;
	}

	public List<SimpleTweet> getLastTweetsBySubject(String s)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		String q = "SELECT ID, Created_At, Text, From_User_Name, XML_EXTRACT(URLs,'//expanded_url',',')as url, "
				+ "XML_EXTRACT(Hashtags,'//text',',')as hash   FROM Tweets  WHERE SearchTerms like '" + s
				+ "%' limit 5;";
		ResultSet rs = conn.getConn().createStatement().executeQuery(q);
		SimpleTweet st;
		List<SimpleTweet> stl = new ArrayList<>();
		while (rs.next()) {
			st = new SimpleTweet();
			st.setCreated(strToLocDate(rs.getString("Created_At")));
			st.setID(rs.getLong("ID"));
			st.setFromUser(rs.getString("From_User_Name"));
			st.setText(rs.getString("Text"));
			st.setUrls(rs.getString("url"));
			st.setHashtags(rs.getString("hash"));
			stl.add(st);
		}
		conn.close();
		return stl;
	}

	public String writeTweet(String txt)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		String q = "INSERT INTO Tweets (Text) VALUES (?)";
		PreparedStatement ps = conn.getConn().prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, txt);
		ps.executeUpdate();
		ResultSet s = ps.getGeneratedKeys();
		conn.close();
		return s.getString("ID");
	}

	public int deleteTweet(String tweetId)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		String dq = "Delete FROM Tweets where ID=?";
		PreparedStatement ps = conn.getConn().prepareStatement(dq);
		ps.setString(1, tweetId);
		int count = ps.executeUpdate();
		System.out.println(count + " rows deleted");
		conn.close();
		return count;
	}

	private LocalDateTime strToLocDate(String sd) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddHH:mm:ss");
		formatter = formatter.withLocale(Locale.ITALY);
		String[] temp = sd.split("[T/.]");
		LocalDateTime date = LocalDateTime.parse(temp[0] + temp[1], formatter);
		return date;
	}
}
