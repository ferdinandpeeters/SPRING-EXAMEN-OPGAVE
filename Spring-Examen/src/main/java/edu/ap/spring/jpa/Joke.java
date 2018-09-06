package edu.ap.spring.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Joke {
    @Id
    private long databaseId;

    @Column
    private int id;

    @Column
    private String joke;

    public Joke() {}
    
    public Joke(int id, String joke) {
	    	this.id = id;
	    	this.joke = joke;
    }
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJoke() {
		return joke;
	}

	public void setJoke(String joke) {
		this.joke = joke;
	}
}
