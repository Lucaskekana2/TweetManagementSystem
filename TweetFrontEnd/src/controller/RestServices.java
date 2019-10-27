package controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.TweetEntity;
import service.TweetEJB;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

@Path("/MyTweetRestService")
@ApplicationPath("/resources")
public class RestServices extends Application{
	
	private List<TweetEntity> tweetList = new ArrayList<>();
	TweetEntity tweetentity = new TweetEntity();
	TweetController tweetcontroller = new TweetController();

	@EJB
	TweetEJB tweetservice;
	
	@GET
	@Path("/viewTweets")
	@Produces(MediaType.APPLICATION_JSON)
    public Iterable<TweetEntity> getallTweetList() {
	    System.out.println("retriving data");
	    tweetList = tweetservice.findTweets();
      return tweetList;
    }
	
	@POST
	@Path("/addnew")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
	public Response createTweet(@QueryParam("message") String  msg) throws TwitterException {
		
		TweetEntity tweetentity = new TweetEntity();
		System.out.println("am in here plus value of "  + msg );
		tweetentity.setMessage(msg);
		
		tweetservice.addNewTweet(tweetentity);
		
		Twitter twitter = TweetController.getTwitterinstance();
		
		Status status = twitter.updateStatus(msg);
	    System.out.println(status.getText());
		
		return Response.ok("the following message was posted to twitter "+ msg ).build();
	}
		
}
