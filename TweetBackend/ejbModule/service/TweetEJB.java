package service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.TweetEntity;

/**
 * Session Bean implementation class TweetEJB
 */
@Stateless
@LocalBean
public class TweetEJB {

	@PersistenceContext
	private EntityManager em;

    public TweetEJB() {
        // TODO Auto-generated constructor stub
    }

    public void addNewTweet(TweetEntity tweetEntity)
    {
    	System.out.println(" adding data ");
    	em.persist(tweetEntity);  /*save data TO DB*/
    	
    }
    
    public List<TweetEntity> findTweets() {
    	List<TweetEntity> tweet = em.createQuery("Select e from  tweet_tbl e"
    			,TweetEntity.class).getResultList();
    	return tweet;
    }
}
