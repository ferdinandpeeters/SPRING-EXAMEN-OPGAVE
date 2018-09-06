package edu.ap.spring.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Joke {
    @Id
    @GeneratedValue
    private long id;

    @Column
    private int apiID;

    @Column
    private String joke;

    public Joke() {}
    
    public Joke(int apiID, String joke) {
	    	this.apiID = apiID;
	    	this.joke = joke;
    }
    
    public int getApiID() {
		return apiID;
	}

	public void setApiId(int apiID) {
		this.apiID = apiID;
	}

	public String getJoke() {
		return joke;
	}

	public void setJoke(String joke) {
		this.joke = joke;
	}
}
