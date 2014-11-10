package database.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import database.dto.Rekvisition;

public class RekvisitionDaoImplExt extends RekvisitionDaoImpl {
	
	private static final String ADV_SEARCH = "SELECT * FROM rekvisition NATURAL JOIN patient NATURAL JOIN modalitet NATURAL JOIN undersoegelses_type WHERE ";
	private static final String GET_ALL_REKV =  "SELECT * FROM rekvisition NATURAL JOIN patient NATURAL JOIN modalitet NATURAL JOIN undersoegelses_type";

	public RekvisitionDaoImplExt(Connection conn) {
		super(conn);
	}

	public Rekvisition[] findByAdvSearch(String cpr, String name, String modality, Rekvisition.Status status,Timestamp date, String department){ 	
//		String[] columns = SELECT_COLUMNS.split(",");
//		for(int i = 0; i < columns.length; i++){
//			columns[i] = columns[i].replace(" ", "");
//		}
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Rekvisition> rekv = new ArrayList<>();
		String query = ADV_SEARCH;
		boolean first = false;

		if (cpr != null){
			query = query + "patient_cpr=? ";
			first = true;
		}
		if(name != null){			
			query = query + (first ? " AND patient_navn=? " : "patient_navn=? ");
			first=true;
		}
		if(modality != null){
			query = query + (first ? " AND modalitet_navn=? " : " modalitet_navn=? ");
			first = true;
		}
		if(status != null){
			query = query + (first ? " AND status=? " : " status=? ");
			first = true;
		}
		if(date != null){
			query = query + (first ? " AND afsendt_dato=? " : "afsendt_dato=? ");
			first = true;
		}
		if(department != null){
			query = query + (first ? " AND stamafdeling=? " : "stamafdeling=? ");
		}
		
		if(!false){
			query = GET_ALL_REKV;
		
		System.out.println("GET ALL");
		}
		//    	modalitet_navn
		//    	patient_cpr
		//    	patient_navn
		//    	stamafdeling
		//    	status
		//    	afsendt_dato

				try {
					stmt = conn.prepareStatement(query);
					int i = 1;
					if (cpr != null){
						stmt.setString(i, cpr);
						i++;
					}
					if(name != null){			
						stmt.setString(i, name);
						i++;
					}
					if(modality != null){
						stmt.setString(i, modality);
						i++;
					}
					if(status != null){
						stmt.setShort( i, (short) (status.ordinal() + 1) );
						i++;
					}
					if(date != null){
						stmt.setTimestamp(i, date);
						i++;
					}
					if(department != null){
						stmt.setString(i, department);
					}					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					rs = stmt.executeQuery();
					
					while (rs.next()){
						System.out.println("##### TEST: " + rs.getInt("rekvisition_id"));
						rekv.add(findByPrimaryKey(rs.getInt("rekvisition_id")));
						System.out.println(rekv.size());
					}
				} catch (SQLException e) {
					System.err.println(e.getSQLState());
				}
				Rekvisition[] ret = new Rekvisition[rekv.size()];
				for(int i = 0; i < rekv.size(); i++){
					ret[i] = rekv.get(i);
				}
		return ret;
	}
}
