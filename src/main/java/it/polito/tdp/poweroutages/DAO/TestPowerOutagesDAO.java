package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;

import it.polito.tdp.poweroutages.model.DataClass;

public class TestPowerOutagesDAO {

	public static void main(String[] args) {
		
		try {
			Connection connection = ConnectDB.getConnection();
			connection.close();
			System.out.println("Connection Test PASSED");
			
			PowerOutageDAO dao = new PowerOutageDAO() ;
			
			for(DataClass x: dao.nome(4))
				System.out.println(x.toString()+"\n") ;

		} catch (Exception e) {
			System.err.println("Test FAILED");
		}

	}

}
