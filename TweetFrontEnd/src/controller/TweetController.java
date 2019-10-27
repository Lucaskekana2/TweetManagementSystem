package controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;

import model.Tweet;
import model.TweetEntity;
import service.TweetEJB;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@ManagedBean(name="tweetcontroller")
@SessionScoped 
@Path("/MyTweetRestService")
@ApplicationPath("/resource")
public class TweetController {
	
	private final static String CONSUMER_KEY = "uLeQx9PdGAGTOIS4ttzkucuc6";
    private final static String CONSUMER_KEY_SECRET = "PmqCQiz0rRQe2DlEeEGjpUFcsXtxjOMPBUlhKyoEchFEDSfiuf";
    private final static String ACCESS_TOKEN = "1187381810570956801-IAi62jKxnKYo2ljcyqOD21HKFl0Yzc";
    private final static String ACCESS_TOKEN_SECRET = "VDTQGv8KufoYfOPREClOwjB1Iv7GuKPQi8AsvfU7uD939";
    
	@EJB
	TweetEJB tweetservice;
	
	@ManagedProperty(value="#{tweet}") 
	private Tweet tweet;
	
	private List<TweetEntity> tweetList = new ArrayList<>();
	
	public Tweet getTweet() {
		return tweet;
	}

	public void setTweet(Tweet tweet) {
		this.tweet = tweet;
	}

	public String redirecttoAdd() {
		return "index.xhtml";
	}
	public String redirecttoViewTweets() {
		return "Tweets.xhtml";
	}

	public void addTweet()
	{
		System.out.println("this is in use");
		tweetservice.addNewTweet(tweet.getEntity());
	}
	public static Twitter getTwitterinstance() {

		ConfigurationBuilder cb = new ConfigurationBuilder();
	    cb.setDebugEnabled(true)
	      .setOAuthConsumerKey(CONSUMER_KEY)
	      .setOAuthConsumerSecret(CONSUMER_KEY_SECRET)
	      .setOAuthAccessToken(ACCESS_TOKEN)
	      .setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);
	    TwitterFactory tf = new TwitterFactory(cb.build());
	    Twitter twitter = tf.getInstance();
	   
	    System.out.println("lucask testing instance =" + twitter);
		return twitter;
	}

	public String postTweet() throws TwitterException {
	    
		addTweet();
		
		Twitter twitter = getTwitterinstance();
	    Status status = twitter.updateStatus(tweet.getEntity().getMessage());
	    System.out.println(status.getText());
	   
	     System.out.println("lucask testing instance to update status" + twitter);
	   return status.getText();
	}
	
    public List<TweetEntity> getTweetList() {
		System.out.println("getting details");
		tweetList = tweetservice.findTweets();
	    return tweetList;
	}



}
