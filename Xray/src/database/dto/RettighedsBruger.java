package database.dto;

import database.dao.RettighederDao;



/**
 *  En bruger, der også har sine rettigheder
 * @author Martin
 * TODO test
 */
public class RettighedsBruger extends Bruger {
	
	private Bruger rettighedsBruger;
	private Rettigheder[] rettigheder;
	private RettighederDao retDao;
	protected static final String GET_RETTIGHEDER = "bruger_id=?";
    
	
	public RettighedsBruger(Bruger bruger) {
		this.rettighedsBruger = bruger;
		this.rettigheder = retDao.findDynamic(GET_RETTIGHEDER, 0, -1, new Object[]{bruger.getBrugerId()});
		
	}


	public Bruger getBruger() {
		return rettighedsBruger;
	}


	public void setBruger(Bruger bruger) {
		this.rettighedsBruger = bruger;
	}


	public Rettigheder[] getRettigheder() {
		return rettigheder;
	}


	public void setRettigheder(Rettigheder[] rettigheder) {
		this.rettigheder = rettigheder;
	}
	
	public boolean harRettighed(Rettigheder.Rettighed rettighed){
		for(int i = 0; i<rettigheder.length; i++){
			if(rettighed.equals(rettigheder[i])){
				return true;
			}
		}
		return false;
	}
	

	
	
}
