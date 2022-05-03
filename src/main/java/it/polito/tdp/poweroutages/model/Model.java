package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	private PowerOutageDAO podao;
	private List<DataClass> dataList;
	private int maxYears;
	private int maxHours;
	private List<DataClass> soluzione;
	private int maxPeople;
	
	public Model() {
		this.podao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	public String worstCaseAnalysis(int id, int maxY, int maxH) {
		this.dataList = this.podao.nome(id);
		this.maxYears = maxY;
		this.maxHours = maxH;
		this.maxPeople = 0;
		this.soluzione = new ArrayList<>();
		List<DataClass> parziale = new ArrayList<>();
		this.wcaRicorsivo(parziale);
		int totH = this.totalHours(soluzione);
		String stringa = "Tot people affected: " + this.maxPeople + "\n"
							+ "Tot hours of outage: " + totH + "\n";
		for(DataClass d: this.soluzione) {
			stringa += d.toString() + "\n";
		}
		return stringa;
	}
	
	public void wcaRicorsivo(List<DataClass> parziale) {

		// condizione di terminazione ?
		if(this.totalHours(parziale)>this.maxHours)
			return;
	
		if(this.totalPeople(parziale)>this.maxPeople) {
			this.maxPeople = this.totalPeople(parziale);
			this.soluzione = new ArrayList<>(parziale);
		}
		
		for(DataClass d: this.dataList) {
			if(this.controlloValidita(d, parziale)) {
				parziale.add(d);
				this.wcaRicorsivo(parziale);
				parziale.remove(parziale.size()-1);
			}
		}
		
	}
	private boolean controlloValidita(DataClass d, List<DataClass> parziale) {
		if(parziale.isEmpty()) {
			return true;
		}
		if(parziale.contains(d))
			return false;
		if(d.getAnno()-parziale.get(0).getAnno()>this.maxYears)
			return false;
		return true;
	}
	
	private int totalHours(List<DataClass> parziale) {
		int totH = 0;
		for(DataClass d: parziale) {
			totH += d.getDuration();
		}
		return totH;
	}
	
	private int totalPeople(List<DataClass> parziale) {
		int totP = 0;
		for(DataClass d: parziale) {
			totP += d.getCustomers_affected();
		}
		return totP;
	}

}
