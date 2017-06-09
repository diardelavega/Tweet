package Object;

import java.time.LocalDateTime;

public class SimpleTweet {
	private long ID;
	private LocalDateTime created;
	private String text;
	private String fromUser;
	private String urls;
	private String hashtags;

	public SimpleTweet() {
		super();
	}

	public SimpleTweet(long iD, LocalDateTime created, String text, String fromUser, String urls, String hashtags) {
		super();
		ID = iD;
		this.created = created;
		this.text = text;
		this.fromUser = fromUser;
		this.urls = urls;
		this.hashtags = hashtags;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public String getUrls() {
		return urls;
	}

	public void setUrls(String urls) {
		this.urls = urls;
	}

	public String getHashtags() {
		return hashtags;
	}

	public void setHashtags(String hashtags) {
		this.hashtags = hashtags;
	}

	public void printTweet() {
		System.out.println(text + "; " + ID + ", " + created + ", " + fromUser + ", url: " + urls + ", #:" + hashtags);
	}
}
