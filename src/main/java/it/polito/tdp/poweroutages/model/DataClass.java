package it.polito.tdp.poweroutages.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class DataClass {
	
	private int customers_affected; 
	private LocalDateTime date_event_began;
	private LocalDateTime date_event_finished;
	private int anno;
	
	public DataClass(int customers_affected, LocalDateTime date_event_began, LocalDateTime date_event_finished) {
		this.customers_affected = customers_affected;
		this.date_event_began = date_event_began;
		this.date_event_finished = date_event_finished;
		this.anno = this.date_event_began.getYear();
	}

	public int getCustomers_affected() {
		return customers_affected;
	}
	public int getAnno() {
		return this.anno;
	}
	
	public int getDuration() {
		return (int) Duration.between(date_event_began, date_event_finished).toHours();
	}

	@Override
	public String toString() {
		return this.anno + " " + this.date_event_began + " " + this.date_event_finished 
				+ " " + this.getDuration() + " " + this.customers_affected;
	}

}
