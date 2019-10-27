package model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity(name="tweet_tbl")
public class TweetEntity {
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private long    id;
		private String message;
		
		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		
		public long getId() {
			return id;
		}
		public Timestamp getTimeStamp() {
			
			return timeStamp;
		}
		public String getMessage() {
			return message;
		}
		public void setId(long id) {
			this.id = id;
		}
		public void setTimeStamp( Timestamp timeStamp ) {
			this.timeStamp = timeStamp;
		}
		public void setMessage(String message) {
			this.message = message;
		}

}
