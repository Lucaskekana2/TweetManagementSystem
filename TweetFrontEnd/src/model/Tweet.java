package model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="tweet")
@SessionScoped
public class Tweet {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		
		if (message == "") {
			
			message = "blank";
		}
		this.message = message;
	}
	
	public TweetEntity getEntity()
	{
		TweetEntity tweetentity = new TweetEntity();
		tweetentity.setMessage(message);
		
		return tweetentity;
	}
		
	
	
	

}
