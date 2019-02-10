package ca.sheridancollege.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Review {

	@Id
	@GeneratedValue
	private int reviewId;
	private String userName;
	private String comment;
	

	public Review(String userName,String comment) {
		super();
		this.userName = userName;
		this.comment = comment;
	}

}
