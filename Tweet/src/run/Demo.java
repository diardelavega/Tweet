package run;

import java.sql.SQLException;
import java.util.List;

import Object.SimpleTweet;

public class Demo {

	public static void main(String[] args)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Functions f = new Functions();
		List<SimpleTweet> tweetList = f.getLastNTweets(10);
		for (int i = 0; i < tweetList.size(); i++) {
			tweetList.get(i).printTweet();
		}

		tweetList = f.getLastTweetsBySubject("tenis");
		for (int i = 0; i < tweetList.size(); i++) {
			tweetList.get(i).printTweet();
		}

		String tweetId = f.writeTweet("This is a Tweet!!");
		System.out.println(tweetId);

		f.deleteTweet(tweetId);

	}

}
