package ca.sheridancollege.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Movie {

	@Id
	@GeneratedValue
	private int movieId;
	private String title;
	private String genre;
	private String director;
	private int star;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<Review> reviewList = new ArrayList<Review>();
	
	public Movie(String title, String genre, String director,int star) {
		super();
		this.title = title;
		this.genre = genre;
		this.director = director;
		this.star = star;
	}

}
