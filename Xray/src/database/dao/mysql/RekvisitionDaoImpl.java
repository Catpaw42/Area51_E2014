/*
 * This file was generated - do not edit it directly !!
 * Generated by AuDAO tool, a product of Spolecne s.r.o.
 * For more information please visit http://www.spoledge.com
 */
package database.dao.mysql;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;

import com.spoledge.audao.db.dao.AbstractDaoImpl;
import com.spoledge.audao.db.dao.DBException;
import com.spoledge.audao.db.dao.DaoException;

import database.dao.BrugerDao;
import database.dao.CtKontrastKontrolskemaDao;
import database.dao.MRKontrolskemaDao;
import database.dao.ModalitetDao;
import database.dao.PETCTKontrolskemaDao;
import database.dao.PatientDao;
import database.dao.RekvisitionDao;
import database.dao.UlInvKontrolskemaDao;
import database.dao.UndersoegelsesTypeDao;
import database.dto.Modalitet;
import database.dto.Patient;
import database.dto.RekvisitionExtended;
import database.dto.UlInvKontrolskema;
import database.dto.RekvisitionExtended.Status;


/**
 * This is the DAO imlementation class.
 *
 * @author generated
 */
public class RekvisitionDaoImpl extends AbstractDaoImpl<RekvisitionExtended> implements RekvisitionDao {
	
	private static final String ADV_SEARCH = "SELECT * FROM rekvisition NATURAL JOIN patient NATURAL JOIN modalitet NATURAL JOIN undersoegelses_type WHERE ";
	private static final String GET_ALL_REKV =  "SELECT * FROM rekvisition NATURAL JOIN patient NATURAL JOIN modalitet NATURAL JOIN undersoegelses_type";
	

	private static final String TABLE_NAME = "rekvisition";

	protected static final String SELECT_COLUMNS = "rekvisition_id, MR_kontrolskema_id, PETCT_kontrolskema_id, CT_kontrast_kontrolskema_id, invasiv_UL_kontrolskema_id, undersoegelses_type_id, rekvirent_id, visitator_id, patient_id, henvist_til, hospital_oenske, prioritering, udf_indlagt, ambulant_koersel, indlaeggelse_transport, status, samtykke, paaroerende, ambulant, dato_forslag, graviditet, graviditet_uge, cave, hoerehaemmet, synshaemmet, amputeret, kan_ikke_staa, ilt_liter_prmin, tolk_sprog, dement, afasi, isolation, cytostatika_dato, tidl_billed_diagnostik, klinisk_problemstilling, triage, henv_laege, henv_afd, kontakt_tlf, visitator_prioritering, visitator_bemaerkning, afsendt_dato";

	protected static final String PK_CONDITION = "rekvisition_id=?";

	private static final String SQL_INSERT = "INSERT INTO rekvisition (MR_kontrolskema_id,PETCT_kontrolskema_id,CT_kontrast_kontrolskema_id,invasiv_UL_kontrolskema_id,undersoegelses_type_id,rekvirent_id,visitator_id,patient_id,henvist_til,hospital_oenske,prioritering,udf_indlagt,ambulant_koersel,indlaeggelse_transport,status,samtykke,paaroerende,ambulant,dato_forslag,graviditet,graviditet_uge,cave,hoerehaemmet,synshaemmet,amputeret,kan_ikke_staa,ilt_liter_prmin,tolk_sprog,dement,afasi,isolation,cytostatika_dato,tidl_billed_diagnostik,klinisk_problemstilling,triage,henv_laege,henv_afd,kontakt_tlf,visitator_prioritering,visitator_bemaerkning,afsendt_dato) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final RekvisitionExtended.HenvistTil[] _Rekvisition_HenvistTils = { null, RekvisitionExtended.HenvistTil.RADIOLOGISK, RekvisitionExtended.HenvistTil.KLINISK };
	private static final RekvisitionExtended.HospitalOenske[] _Rekvisition_HospitalOenskes = { null, RekvisitionExtended.HospitalOenske.HILLEROED, RekvisitionExtended.HospitalOenske.FREDERIKSSUND, RekvisitionExtended.HospitalOenske.HELSINGOER };
	private static final RekvisitionExtended.Prioritering[] _Rekvisition_Prioriterings = { null, RekvisitionExtended.Prioritering.HASTE, RekvisitionExtended.Prioritering.RUTINE, RekvisitionExtended.Prioritering.FREMSKYNDET, RekvisitionExtended.Prioritering.PAKKEFORLOEB };
	private static final RekvisitionExtended.AmbulantKoersel[] _Rekvisition_AmbulantKoersels = { null, RekvisitionExtended.AmbulantKoersel.INGEN, RekvisitionExtended.AmbulantKoersel.SIDDENDE, RekvisitionExtended.AmbulantKoersel.LIGGENDE };
	private static final RekvisitionExtended.IndlaeggelseTransport[] _Rekvisition_IndlaeggelseTransports = { null, RekvisitionExtended.IndlaeggelseTransport.GAA_UDEN_PORTOER, RekvisitionExtended.IndlaeggelseTransport.GAA_MED_PORTOER, RekvisitionExtended.IndlaeggelseTransport.KOERESTOL, RekvisitionExtended.IndlaeggelseTransport.SENG };
	protected static final RekvisitionExtended.Status[] _Rekvisition_Statuss = { null, RekvisitionExtended.Status.PENDING, RekvisitionExtended.Status.CANCELED, RekvisitionExtended.Status.APPROVED, RekvisitionExtended.Status.DECLINED, RekvisitionExtended.Status.BOOKED };
	private static final RekvisitionExtended.Samtykke[] _Rekvisition_Samtykkes = { null, RekvisitionExtended.Samtykke.JA, RekvisitionExtended.Samtykke.NEJ, RekvisitionExtended.Samtykke.UDEN_SAMTYKKE };


	public RekvisitionDaoImpl( Connection conn ) {
		super( conn );
	}
	
	
	public RekvisitionExtended[] findByAdvSearch(String cpr, String name, String modality, RekvisitionExtended.Status status,Timestamp date, String department, int rekvirentId) {
		Timestamp lowBound = null;
		Timestamp upperBound = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<RekvisitionExtended> rekv = new ArrayList<RekvisitionExtended>();
		String query = ADV_SEARCH;
		boolean first = false;

		if (cpr != null && !cpr.equals("")){
			query = query + "patient_cpr=? ";
			first = true;
		}
		if(name != null && !name.equals("")){			
			query = query + (first ? " AND " : " ") + "patient_navn=? ";
			first=true;
		}
		if(modality != null && !modality.equals("")){
			// query = query + (first ? " AND modalitet_navn=? " : " modalitet_navn=? ");
			query = query + (first ? " AND " : " ") + "modalitet_id=? ";
			first = true;
		}
		if(status != null){
			query = query + (first ? " AND " : " ") + "status=? ";
			first = true;
		}
		if(date != null){
			long day = 60*60*24*1000-1;
			lowBound = new Timestamp(date.getTime());
			upperBound = new Timestamp(date.getTime()+day);
			query = query + (first ? " AND  " : " ") + "afsendt_dato>? AND afsendt_dato<? ";
			first = true;
		}
		if(department != null && !department.equals("") && !department.equalsIgnoreCase("alle")){
			query = query + (first ? " AND " : " ") + "stamafdeling=? ";
			first = true;
		}
		if(rekvirentId != -1){
			query = query + (first ? " AND " : " ") + "rekvirent_id=? ";
			first = true;
		}
		
		if(!first){
			query = GET_ALL_REKV;
		}
		//    	modalitet_navn
		//    	patient_cpr
		//    	patient_navn
		//    	stamafdeling
		//    	status
		//    	afsendt_dato

		System.out.println("Query: " + query);
				try {
					stmt = conn.prepareStatement(query);
					int i = 1;
					if (cpr != null && !cpr.equals("")){
						stmt.setString(i, cpr);
						i++;
					}
					if(name != null && !name.equals("")){			
						stmt.setString(i, name);
						i++;
					}
					if(modality != null && !modality.equals("")){
						stmt.setInt(i, Integer.valueOf(modality)+1);
						i++;
					}
					if(status != null){
						stmt.setShort( i, (short) (status.ordinal() + 1));
						i++;
					}
					if(date != null){
						stmt.setTimestamp(i, lowBound);
						i++;
						stmt.setTimestamp(i, upperBound);
						i++;
					}
					if(department != null && !department.equals("") && !department.equalsIgnoreCase("alle")){
						stmt.setString(i, department);
						i++;
					}
					if(rekvirentId != -1){
						stmt.setInt(i, rekvirentId);
						i++; // not necessary but if more where to be added it is
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
			
					rs = stmt.executeQuery();
					String pt_cpr = "patient_cpr";
					String pt_name = "patient_navn";
					String mod_name = "modalitet_navn";
					String stam_afdeling = "stamafdeling";
					String afs_dato = "afsendt_dato";
					String rekv_status = "status";
					String rekv_id = "rekvisition_id";


					
					while (rs.next()){
						RekvisitionExtended rekvRsRow = new RekvisitionExtended();
						Patient dummyP = new Patient();
						Modalitet dummyM = new Modalitet();
						
						rekvRsRow.setPatient(dummyP);
						rekvRsRow.setModalitet(dummyM);
						rekvRsRow.getPatient().setPatientCpr(rs.getString(pt_cpr));
						rekvRsRow.getPatient().setPatientNavn(rs.getString(pt_name));
						rekvRsRow.getModalitet().setModalitetNavn(rs.getString(mod_name));
						rekvRsRow.getPatient().setStamafdeling(rs.getString(stam_afdeling));
						rekvRsRow.setAfsendtDato(rs.getTimestamp(afs_dato));
						rekvRsRow.setStatus(_Rekvisition_Statuss[ rs.getShort( rekv_status) ]);
						rekvRsRow.setRekvisitionId(rs.getInt(rekv_id));
						rekv.add(rekvRsRow);
						
						//to get rekvisition with all attributes
//						rekvRsRow = fetch(rs);
//						rekvRsRow.setPatient(ptDao.fetch(rs));
//						rekvRsRow.setModalitet(modDao.fetch(rs));
//						rekvRsRow.setUndersoegelsesType(undDao.fetch(rs));
//						rekv.add(rekvRsRow);
//						rekv.add(findByPrimaryKey(rs.getInt("rekvisition_id")));
					}
				} catch (SQLException e) {
					System.err.println(e.getSQLState());
				}
						rekv.toArray();
				RekvisitionExtended[] ret = new RekvisitionExtended[rekv.size()];
				for(int i = 0; i < rekv.size(); i++){
					ret[i] = rekv.get(i);
				}
				
				
				// to add all attributes
//		return addObjectsToRekvisition(ret);
				return ret;
	}

	public RekvisitionExtended[] findByAdvSearch(String cpr, String name, String modality, RekvisitionExtended.Status status,Timestamp date, String department){ 	
		return findByAdvSearch(cpr, name, modality, status, date, department, -1);
		
		
	}
	
	

	/**
	 * Finds a record identified by its primary key.
	 * @return the record found or null
	 */
	public RekvisitionExtended findByPrimaryKey( int rekvisitionId ) {
		RekvisitionExtended find = findOne( PK_CONDITION, rekvisitionId);
		if(find == null) return null;
		RekvisitionExtended[] rekv = new RekvisitionExtended[]{find};
		rekv = addObjectsToRekvisition(rekv);
		return rekv[0];
	}

	private RekvisitionExtended[] addObjectsToRekvisition(RekvisitionExtended[] rekv){
		if(rekv == null || rekv.length <= 0) return null;
		MRKontrolskemaDao mrDao = new MRKontrolskemaDaoImpl(conn);
		PETCTKontrolskemaDao petctDao = new PETCTKontrolskemaDaoImpl(conn);
		CtKontrastKontrolskemaDao ctKontrDao = new CtKontrastKontrolskemaDaoImpl(conn);
		BrugerDao brugerDao = new BrugerDaoImpl(conn);
		PatientDao ptDao = new PatientDaoImpl(conn);
		ModalitetDao modDao = new ModalitetDaoImpl(conn);
		UndersoegelsesTypeDao undDao = new UndersoegelsesTypeDaoImpl(conn);
		UlInvKontrolskemaDao ulInvDao = new UlInvKontrolskemaDaoImpl(conn);

		
		for(int i = 0; i < rekv.length; i++){
			rekv[i].setPatient(ptDao.findByPrimaryKey(rekv[i].getPatientId() != null ? rekv[i].getPatientId() : -1));
			rekv[i].setUndersoegelsesType(undDao.findByPrimaryKey(rekv[i].getUndersoegelsesTypeId() != null ? rekv[i].getUndersoegelsesTypeId() : -1));
			rekv[i].setModalitet(modDao.findByPrimaryKey(rekv[i].getUndersoegelsesType() != null ? rekv[i].getUndersoegelsesType().getModalitetId() : -1));
			rekv[i].setMRKontrolskema(mrDao.findByPrimaryKey(rekv[i].getMRKontrolskemaId() != null ? rekv[i].getMRKontrolskemaId() : -1));
			rekv[i].setPetctKontrolskema(petctDao.findByPrimaryKey(rekv[i].getPETCTKontrolskemaId() != null ? rekv[i].getPETCTKontrolskemaId() : -1));
			rekv[i].setCtKontrastKontrolskema(ctKontrDao.findByPrimaryKey(rekv[i].getCTKontrastKontrolskemaId() != null ? rekv[i].getCTKontrastKontrolskemaId() : -1));
			rekv[i].setUlInvKontrolskema(ulInvDao.findByPrimaryKey(rekv[i].getInvasivULKontrolskemaId() != null ? rekv[i].getInvasivULKontrolskemaId() : -1));
			rekv[i].setRekvirent(brugerDao.findByPrimaryKey(rekv[i].getRekvirentId() != null ? rekv[i].getRekvirentId() : -1));
			rekv[i].setVisitator(brugerDao.findByPrimaryKey(rekv[i].getVisitatorId() != null ? rekv[i].getVisitatorId() : -1));
		}
		return rekv;
	}

	/**
	 * Finds records.
	 */
	public RekvisitionExtended[] findDynamic( String cond, int offset, int count, Object... params ) {
		for(int i = 0; i < params.length; i++){
			if(params[i].getClass().isEnum()){
				params[i] = ((Enum<?>) params[i]).ordinal() +1;
			}
		}	
		RekvisitionExtended[] rekv = findManyArray( cond, offset, count, params);
		return addObjectsToRekvisition(rekv);

	}

	/**
	 * Inserts a new record.
	 * @return the generated primary key - rekvisitionId
	 */
	public int insert( RekvisitionExtended dto ) throws DaoException {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		debugSql( SQL_INSERT, dto );

		try {
			stmt = conn.prepareStatement( SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS );

			if ( dto.getMRKontrolskemaId() == null ) {
				stmt.setNull( 1, Types.INTEGER );
			}
			else {
				stmt.setInt( 1, dto.getMRKontrolskemaId() );
			}

			if ( dto.getPETCTKontrolskemaId() == null ) {
				stmt.setNull( 2, Types.INTEGER );
			}
			else {
				stmt.setInt( 2, dto.getPETCTKontrolskemaId() );
			}

			if ( dto.getCTKontrastKontrolskemaId() == null ) {
				stmt.setNull( 3, Types.INTEGER );
			}
			else {
				stmt.setInt( 3, dto.getCTKontrastKontrolskemaId() );
			}

			if ( dto.getInvasivULKontrolskemaId() == null ) {
				stmt.setNull( 4, Types.INTEGER );
			}
			else {
				stmt.setInt( 4, dto.getInvasivULKontrolskemaId() );
			}

			if ( dto.getUndersoegelsesTypeId() == null ) {
				throw new DaoException("Value of column 'undersoegelses_type_id' cannot be null");
			}
			stmt.setInt( 5, dto.getUndersoegelsesTypeId() );

			if ( dto.getRekvirentId() == null ) {
				throw new DaoException("Value of column 'rekvirent_id' cannot be null");
			}
			stmt.setInt( 6, dto.getRekvirentId() );

			if ( dto.getVisitatorId() == null ) {
				stmt.setNull( 7, Types.INTEGER );
			} else {
				stmt.setInt( 7, dto.getVisitatorId() );
			}

			if ( dto.getPatientId() == null ) {
				throw new DaoException("Value of column 'patient_id' cannot be null");
			}
			stmt.setInt( 8, dto.getPatientId() );

			if ( dto.getHenvistTil() == null ) {
				throw new DaoException("Value of column 'henvist_til' cannot be null");
			}
			stmt.setShort( 9, (short) (dto.getHenvistTil().ordinal() + 1) );

			if ( dto.getHospitalOenske() == null ) {
				stmt.setNull( 10, Types.SMALLINT );
			}
			else {
				stmt.setShort( 10, (short) (dto.getHospitalOenske().ordinal() + 1) );
			}

			if ( dto.getPrioritering() == null ) {
				throw new DaoException("Value of column 'prioritering' cannot be null");
			}
			stmt.setShort( 11, (short) (dto.getPrioritering().ordinal() + 1) );

			if ( dto.getUdfIndlagt() == null ) {
				throw new DaoException("Value of column 'udf_indlagt' cannot be null");
			}
			stmt.setByte( 12, dto.getUdfIndlagt() ? ((byte)1) : ((byte)0) );

			if ( dto.getAmbulantKoersel() == null ) {
				stmt.setNull( 13, Types.SMALLINT );
			}
			else {
				stmt.setShort( 13, (short) (dto.getAmbulantKoersel().ordinal() + 1) );
			}

			if ( dto.getIndlaeggelseTransport() == null ) {
				stmt.setNull( 14, Types.SMALLINT );
			}
			else {
				stmt.setShort( 14, (short) (dto.getIndlaeggelseTransport().ordinal() + 1) );
			}

			if ( dto.getStatus() == null ) {
				throw new DaoException("Value of column 'status' cannot be null");
			}
			stmt.setShort( 15, (short) (dto.getStatus().ordinal() + 1) );

			if ( dto.getSamtykke() == null ) {
				stmt.setNull( 16, Types.SMALLINT );
			}
			else {
				stmt.setShort( 16, (short) (dto.getSamtykke().ordinal() + 1) );
			}

			if ( dto.getPaaroerende() != null ) {
				checkMaxLength( "paaroerende", dto.getPaaroerende(), 100 );
			}
			stmt.setString( 17, dto.getPaaroerende() );

			if ( dto.getAmbulant() == null ) {
				throw new DaoException("Value of column 'ambulant' cannot be null");
			}
			stmt.setByte( 18, dto.getAmbulant() ? ((byte)1) : ((byte)0) );

			if ( dto.getDatoForslag() != null ) {
				checkMaxLength( "dato_forslag", dto.getDatoForslag(), 50 );
			}
			stmt.setString( 19, dto.getDatoForslag() );

			if ( dto.getGraviditet() == null ) {
				stmt.setNull( 20, Types.TINYINT );
			}
			else {
				stmt.setByte( 20, dto.getGraviditet() ? ((byte)1) : ((byte)0) );
			}

			if ( dto.getGraviditetUge() == null ) {
				stmt.setNull( 21, Types.INTEGER );
			}
			else {
				stmt.setInt( 21, dto.getGraviditetUge() );
			}

			if ( dto.getCave() == null ) {
				throw new DaoException("Value of column 'cave' cannot be null");
			}
			checkMaxLength( "cave", dto.getCave(), 500 );
			stmt.setString( 22, dto.getCave() );

			if ( dto.getHoerehaemmet() == null ) {
				throw new DaoException("Value of column 'hoerehaemmet' cannot be null");
			}
			stmt.setByte( 23, dto.getHoerehaemmet() ? ((byte)1) : ((byte)0) );

			if ( dto.getSynshaemmet() == null ) {
				throw new DaoException("Value of column 'synshaemmet' cannot be null");
			}
			stmt.setByte( 24, dto.getSynshaemmet() ? ((byte)1) : ((byte)0) );

			if ( dto.getAmputeret() == null ) {
				throw new DaoException("Value of column 'amputeret' cannot be null");
			}
			stmt.setByte( 25, dto.getAmputeret() ? ((byte)1) : ((byte)0) );

			if ( dto.getKanIkkeStaa() == null ) {
				throw new DaoException("Value of column 'kan_ikke_staa' cannot be null");
			}
			stmt.setByte( 26, dto.getKanIkkeStaa() ? ((byte)1) : ((byte)0) );

			if ( dto.getIltLiterPrmin() == null ) {
				stmt.setNull( 27, Types.SMALLINT );
			}
			else {
				stmt.setShort( 27, dto.getIltLiterPrmin() );
			}

			if ( dto.getTolkSprog() != null ) {
				checkMaxLength( "tolk_sprog", dto.getTolkSprog(), 50 );
			}
			stmt.setString( 28, dto.getTolkSprog() );

			if ( dto.getDement() == null ) {
				throw new DaoException("Value of column 'dement' cannot be null");
			}
			stmt.setByte( 29, dto.getDement() ? ((byte)1) : ((byte)0) );

			if ( dto.getAfasi() == null ) {
				throw new DaoException("Value of column 'afasi' cannot be null");
			}
			stmt.setByte( 30, dto.getAfasi() ? ((byte)1) : ((byte)0) );

			if ( dto.getIsolation() == null ) {
				throw new DaoException("Value of column 'isolation' cannot be null");
			}
			checkMaxLength( "isolation", dto.getIsolation(), 50 );
			stmt.setString( 31, dto.getIsolation() );
			stmt.setTimestamp( 32, dto.getCytostatikaDato() );

			if ( dto.getTidlBilledDiagnostik() != null ) {
				checkMaxLength( "tidl_billed_diagnostik", dto.getTidlBilledDiagnostik(), 50 );
			}
			stmt.setString( 33, dto.getTidlBilledDiagnostik() );

			if ( dto.getKliniskProblemstilling() == null ) {
				throw new DaoException("Value of column 'klinisk_problemstilling' cannot be null");
			}
			checkMaxLength( "klinisk_problemstilling", dto.getKliniskProblemstilling(), 1000 );
			stmt.setString( 34, dto.getKliniskProblemstilling() );

			if ( dto.getTriage() != null ) {
				checkMaxLength( "triage", dto.getTriage(), 100 );
			}
			stmt.setString( 35, dto.getTriage() );

			if ( dto.getHenvLaege() == null ) {
				throw new DaoException("Value of column 'henv_laege' cannot be null");
			}
			checkMaxLength( "henv_laege", dto.getHenvLaege(), 100 );
			stmt.setString( 36, dto.getHenvLaege() );

			if ( dto.getHenvAfd() == null ) {
				throw new DaoException("Value of column 'henv_afd' cannot be null");
			}
			checkMaxLength( "henv_afd", dto.getHenvAfd(), 100 );
			stmt.setString( 37, dto.getHenvAfd() );

			if ( dto.getKontaktTlf() == null ) {
				throw new DaoException("Value of column 'kontakt_tlf' cannot be null");
			}
			checkMaxLength( "kontakt_tlf", dto.getKontaktTlf(), 50 );
			stmt.setString( 38, dto.getKontaktTlf() );

			if ( dto.getVisitatorPrioritering() != null ) {
				checkMaxLength( "visitator_prioritering", dto.getVisitatorPrioritering(), 100 );
			}
			stmt.setString( 39, dto.getVisitatorPrioritering() );

			if ( dto.getVisitatorBemaerkning() != null ) {
				checkMaxLength( "visitator_bemaerkning", dto.getVisitatorBemaerkning(), 100 );
			}
			stmt.setString( 40, dto.getVisitatorBemaerkning() );

			if ( dto.getAfsendtDato() == null ) {
				throw new DaoException("Value of column 'afsendt_dato' cannot be null");
			}
			stmt.setTimestamp( 41, dto.getAfsendtDato() );

			int n = stmt.executeUpdate();

			rs = stmt.getGeneratedKeys();
			rs.next();

			dto.setRekvisitionId( rs.getInt( 1 ));

			return dto.getRekvisitionId();
		}
		catch (SQLException e) {
			errorSql( e, SQL_INSERT, dto );
			handleException( e );
			throw new DBException( e );
		}
		finally {
			if (rs != null) try { rs.close(); } catch (SQLException e) {}
			if (stmt != null) try { stmt.close(); } catch (SQLException e) {}
		}
	}

	/**
	 * Updates one record found by primary key.
	 * @return true iff the record was really updated (=found and any change was really saved)
	 */
	public boolean update( int rekvisitionId, RekvisitionExtended dto ) throws DaoException {
		StringBuffer sb = new StringBuffer();
		ArrayList<Object> params = new ArrayList<Object>();

		if ( dto.isMRKontrolskemaIdModified()) {
			if ( dto.getMRKontrolskemaId() == null ) {
				sb.append( "MR_kontrolskema_id=NULL" );
			}
			else {
				sb.append( "MR_kontrolskema_id=?" );
				params.add( dto.getMRKontrolskemaId());
			}
		}

		if ( dto.isPETCTKontrolskemaIdModified()) {
			if (sb.length() > 0) {
				sb.append( ", " );
			}

			if ( dto.getPETCTKontrolskemaId() == null ) {
				sb.append( "PETCT_kontrolskema_id=NULL" );
			}
			else {
				sb.append( "PETCT_kontrolskema_id=?" );
				params.add( dto.getPETCTKontrolskemaId());
			}
		}

		if ( dto.isCTKontrastKontrolskemaIdModified()) {
			if (sb.length() > 0) {
				sb.append( ", " );
			}

			if ( dto.getCTKontrastKontrolskemaId() == null ) {
				sb.append( "CT_kontrast_kontrolskema_id=NULL" );
			}
			else {
				sb.append( "CT_kontrast_kontrolskema_id=?" );
				params.add( dto.getCTKontrastKontrolskemaId());
			}
		}

		if ( dto.isInvasivULKontrolskemaIdModified()) {
			if (sb.length() > 0) {
				sb.append( ", " );
			}

			if ( dto.getInvasivULKontrolskemaId() == null ) {
				sb.append( "invasiv_UL_kontrolskema_id=NULL" );
			}
			else {
				sb.append( "invasiv_UL_kontrolskema_id=?" );
				params.add( dto.getInvasivULKontrolskemaId());
			}
		}
		
		if ( dto.isVisitatorIdModified()) {
			if (sb.length() > 0) {
				sb.append( ", " );
			}

			if ( dto.getVisitatorId() == null ) {
				sb.append( "visitator_id=NULL" );
			}
			else {
				sb.append( "visitator_id=?" );
				params.add( dto.getVisitatorId());
			}
		}

		if ( dto.getUndersoegelsesTypeId() != null ) {
			if (sb.length() > 0) {
				sb.append( ", " );
			}

			sb.append( "undersoegelses_type_id=?" );
			params.add( dto.getUndersoegelsesTypeId());
		}

		if ( dto.getHenvistTil() != null ) {
			if (sb.length() > 0) {
				sb.append( ", " );
			}

			sb.append( "henvist_til=?" );
			params.add( dto.getHenvistTil().ordinal() + 1);
		}

		if ( dto.isHospitalOenskeModified()) {
			if (sb.length() > 0) {
				sb.append( ", " );
			}

			if ( dto.getHospitalOenske() == null ) {
				sb.append( "hospital_oenske=NULL" );
			}
			else {
				sb.append( "hospital_oenske=?" );
				params.add( dto.getHospitalOenske().ordinal() + 1);
			}
		}

		if ( dto.getPrioritering() != null ) {
			if (sb.length() > 0) {
				sb.append( ", " );
			}

			sb.append( "prioritering=?" );
			params.add( dto.getPrioritering().ordinal() + 1);
		}

		if ( dto.getUdfIndlagt() != null ) {
			if (sb.length() > 0) {
				sb.append( ", " );
			}

			sb.append( "udf_indlagt=?" );
			params.add( (dto.getUdfIndlagt() ? ((byte)1) : ((byte)0)));
		}

		if ( dto.isAmbulantKoerselModified()) {
			if (sb.length() > 0) {
				sb.append( ", " );
			}

			if ( dto.getAmbulantKoersel() == null ) {
				sb.append( "ambulant_koersel=NULL" );
			}
			else {
				sb.append( "ambulant_koersel=?" );
				params.add( dto.getAmbulantKoersel().ordinal() + 1);
			}
		}

		if ( dto.isIndlaeggelseTransportModified()) {
			if (sb.length() > 0) {
				sb.append( ", " );
			}

			if ( dto.getIndlaeggelseTransport() == null ) {
				sb.append( "indlaeggelse_transport=NULL" );
			}
			else {
				sb.append( "indlaeggelse_transport=?" );
				params.add( dto.getIndlaeggelseTransport().ordinal() + 1);
			}
		}

		if ( dto.getStatus() != null ) {
			if (sb.length() > 0) {
				sb.append( ", " );
			}

			sb.append( "status=?" );
			params.add( dto.getStatus().ordinal() + 1);
		}

		if ( dto.isSamtykkeModified()) {
			if (sb.length() > 0) {
				sb.append( ", " );
			}

			if ( dto.getSamtykke() == null ) {
				sb.append( "samtykke=NULL" );
			}
			else {
				sb.append( "samtykke=?" );
				params.add( dto.getSamtykke().ordinal() + 1);
			}
		}

		if ( dto.getAmbulant() != null ) {
			if (sb.length() > 0) {
				sb.append( ", " );
			}

			sb.append( "ambulant=?" );
			params.add( (dto.getAmbulant() ? ((byte)1) : ((byte)0)));
		}

		if ( dto.isDatoForslagModified()) {
			if (sb.length() > 0) {
				sb.append( ", " );
			}

			if ( dto.getDatoForslag() == null ) {
				sb.append( "dato_forslag=NULL" );
			}
			else {
				checkMaxLength( "dato_forslag", dto.getDatoForslag(), 50 );
				sb.append( "dato_forslag=?" );
				params.add( dto.getDatoForslag());
			}
		}

		if ( dto.isGraviditetModified()) {
			if (sb.length() > 0) {
				sb.append( ", " );
			}

			if ( dto.getGraviditet() == null ) {
				sb.append( "graviditet=NULL" );
			}
			else {
				sb.append( "graviditet=?" );
				params.add( (dto.getGraviditet() ? ((byte)1) : ((byte)0)));
			}
		}

		if ( dto.isGraviditetUgeModified()) {
			if (sb.length() > 0) {
				sb.append( ", " );
			}

			if ( dto.getGraviditetUge() == null ) {
				sb.append( "graviditet_uge=NULL" );
			}
			else {
				sb.append( "graviditet_uge=?" );
				params.add( dto.getGraviditetUge());
			}
		}

		if ( dto.getCave() != null ) {
			if (sb.length() > 0) {
				sb.append( ", " );
			}

			checkMaxLength( "cave", dto.getCave(), 500 );
			sb.append( "cave=?" );
			params.add( dto.getCave());
		}

		if ( dto.getHoerehaemmet() != null ) {
			if (sb.length() > 0) {
				sb.append( ", " );
			}

			sb.append( "hoerehaemmet=?" );
			params.add( (dto.getHoerehaemmet() ? ((byte)1) : ((byte)0)));
		}

		if ( dto.getSynshaemmet() != null ) {
			if (sb.length() > 0) {
				sb.append( ", " );
			}

			sb.append( "synshaemmet=?" );
			params.add( (dto.getSynshaemmet() ? ((byte)1) : ((byte)0)));
		}

		if ( dto.getAmputeret() != null ) {
			if (sb.length() > 0) {
				sb.append( ", " );
			}

			sb.append( "amputeret=?" );
			params.add( (dto.getAmputeret() ? ((byte)1) : ((byte)0)));
		}

		if ( dto.getKanIkkeStaa() != null ) {
			if (sb.length() > 0) {
				sb.append( ", " );
			}

			sb.append( "kan_ikke_staa=?" );
			params.add( (dto.getKanIkkeStaa() ? ((byte)1) : ((byte)0)));
		}

		if ( dto.isIltLiterPrminModified()) {
			if (sb.length() > 0) {
				sb.append( ", " );
			}

			if ( dto.getIltLiterPrmin() == null ) {
				sb.append( "ilt_liter_prmin=NULL" );
			}
			else {
				sb.append( "ilt_liter_prmin=?" );
				params.add( dto.getIltLiterPrmin());
			}
		}

		if ( dto.isTolkSprogModified()) {
			if (sb.length() > 0) {
				sb.append( ", " );
			}

			if ( dto.getTolkSprog() == null ) {
				sb.append( "tolk_sprog=NULL" );
			}
			else {
				checkMaxLength( "tolk_sprog", dto.getTolkSprog(), 50 );
				sb.append( "tolk_sprog=?" );
				params.add( dto.getTolkSprog());
			}
		}

		if ( dto.getDement() != null ) {
			if (sb.length() > 0) {
				sb.append( ", " );
			}

			sb.append( "dement=?" );
			params.add( (dto.getDement() ? ((byte)1) : ((byte)0)));
		}

		if ( dto.getAfasi() != null ) {
			if (sb.length() > 0) {
				sb.append( ", " );
			}

			sb.append( "afasi=?" );
			params.add( (dto.getAfasi() ? ((byte)1) : ((byte)0)));
		}

		if ( dto.getIsolation() != null ) {
			if (sb.length() > 0) {
				sb.append( ", " );
			}

			checkMaxLength( "isolation", dto.getIsolation(), 50 );
			sb.append( "isolation=?" );
			params.add( dto.getIsolation());
		}

		if ( dto.isCytostatikaDatoModified()) {
			if (sb.length() > 0) {
				sb.append( ", " );
			}

			if ( dto.getCytostatikaDato() == null ) {
				sb.append( "cytostatika_dato=NULL" );
			}
			else {
				sb.append( "cytostatika_dato=?" );
				params.add( dto.getCytostatikaDato());
			}
		}

		if ( dto.isTidlBilledDiagnostikModified()) {
			if (sb.length() > 0) {
				sb.append( ", " );
			}

			if ( dto.getTidlBilledDiagnostik() == null ) {
				sb.append( "tidl_billed_diagnostik=NULL" );
			}
			else {
				checkMaxLength( "tidl_billed_diagnostik", dto.getTidlBilledDiagnostik(), 50 );
				sb.append( "tidl_billed_diagnostik=?" );
				params.add( dto.getTidlBilledDiagnostik());
			}
		}

		if ( dto.getKliniskProblemstilling() != null ) {
			if (sb.length() > 0) {
				sb.append( ", " );
			}

			checkMaxLength( "klinisk_problemstilling", dto.getKliniskProblemstilling(), 1000 );
			sb.append( "klinisk_problemstilling=?" );
			params.add( dto.getKliniskProblemstilling());
		}

		if ( dto.isTriageModified()) {
			if (sb.length() > 0) {
				sb.append( ", " );
			}

			if ( dto.getTriage() == null ) {
				sb.append( "triage=NULL" );
			}
			else {
				checkMaxLength( "triage", dto.getTriage(), 100 );
				sb.append( "triage=?" );
				params.add( dto.getTriage());
			}
		}

		if ( dto.getHenvLaege() != null ) {
			if (sb.length() > 0) {
				sb.append( ", " );
			}

			checkMaxLength( "henv_laege", dto.getHenvLaege(), 100 );
			sb.append( "henv_laege=?" );
			params.add( dto.getHenvLaege());
		}

		if ( dto.getHenvAfd() != null ) {
			if (sb.length() > 0) {
				sb.append( ", " );
			}

			checkMaxLength( "henv_afd", dto.getHenvAfd(), 100 );
			sb.append( "henv_afd=?" );
			params.add( dto.getHenvAfd());
		}

		if ( dto.getKontaktTlf() != null ) {
			if (sb.length() > 0) {
				sb.append( ", " );
			}

			checkMaxLength( "kontakt_tlf", dto.getKontaktTlf(), 50 );
			sb.append( "kontakt_tlf=?" );
			params.add( dto.getKontaktTlf());
		}

		if ( dto.isVisitatorPrioriteringModified()) {
			if (sb.length() > 0) {
				sb.append( ", " );
			}

			if ( dto.getVisitatorPrioritering() == null ) {
				sb.append( "visitator_prioritering=NULL" );
			}
			else {
				checkMaxLength( "visitator_prioritering", dto.getVisitatorPrioritering(), 100 );
				sb.append( "visitator_prioritering=?" );
				params.add( dto.getVisitatorPrioritering());
			}
		}

		if ( dto.isVisitatorBemaerkningModified()) {
			if (sb.length() > 0) {
				sb.append( ", " );
			}

			if ( dto.getVisitatorBemaerkning() == null ) {
				sb.append( "visitator_bemaerkning=NULL" );
			}
			else {
				checkMaxLength( "visitator_bemaerkning", dto.getVisitatorBemaerkning(), 100 );
				sb.append( "visitator_bemaerkning=?" );
				params.add( dto.getVisitatorBemaerkning());
			}
		}

		if ( dto.getAfsendtDato() != null ) {
			if (sb.length() > 0) {
				sb.append( ", " );
			}

			sb.append( "afsendt_dato=?" );
			params.add( dto.getAfsendtDato());
		}

		if (sb.length() == 0) {
			return false;
		}

		params.add( rekvisitionId );

		Object[] oparams = new Object[ params.size() ];

		return updateOne( sb.toString(), PK_CONDITION, params.toArray( oparams ));
	}

	/**
	 * Returns the table name.
	 */
	public String getTableName() {
		return TABLE_NAME;
	}

	protected String getSelectColumns() {
		return SELECT_COLUMNS;
	}

	protected RekvisitionExtended fetch( ResultSet rs ) throws SQLException {
		String[] colNames = SELECT_COLUMNS.split(", ");
		String[] cn = new String[colNames.length+1];	
		int i = 1;
		for(String s : colNames){
			cn[i] = s;
			i++;
		}
		RekvisitionExtended dto = new RekvisitionExtended();
		dto.setRekvisitionId( rs.getInt( cn[1] ));
		dto.setMRKontrolskemaId( rs.getInt( cn[2] ));

		if ( rs.wasNull()) {
			dto.setMRKontrolskemaId( null );
		}

		dto.setPETCTKontrolskemaId( rs.getInt( cn[3]));

		if ( rs.wasNull()) {
			dto.setPETCTKontrolskemaId( null );
		}

		dto.setCTKontrastKontrolskemaId( rs.getInt( cn[4] ));

		if ( rs.wasNull()) {
			dto.setCTKontrastKontrolskemaId( null );
		}

		dto.setInvasivULKontrolskemaId( rs.getInt( cn[5] ));

		if ( rs.wasNull()) {
			dto.setInvasivULKontrolskemaId( null );
		}

		dto.setUndersoegelsesTypeId( rs.getInt( cn[6] ));
		dto.setRekvirentId( rs.getInt( cn[7] ));
		dto.setVisitatorId( rs.getInt( cn[8] ));
		dto.setPatientId( rs.getInt( cn[9] ));
		dto.setHenvistTil( _Rekvisition_HenvistTils[ rs.getShort( cn[10] ) ]);
		dto.setHospitalOenske( _Rekvisition_HospitalOenskes[ rs.getShort( cn[11] ) ]);

		if ( rs.wasNull()) {
			dto.setHospitalOenske( null );
		}

		dto.setPrioritering( _Rekvisition_Prioriterings[ rs.getShort( cn[12] ) ]);
		dto.setUdfIndlagt( rs.getBoolean( cn[13] ) ? Boolean.TRUE : Boolean.FALSE );
		dto.setAmbulantKoersel( _Rekvisition_AmbulantKoersels[ rs.getShort( cn[14] ) ]);

		if ( rs.wasNull()) {
			dto.setAmbulantKoersel( null );
		}

		dto.setIndlaeggelseTransport( _Rekvisition_IndlaeggelseTransports[ rs.getShort( cn[15] ) ]);

		if ( rs.wasNull()) {
			dto.setIndlaeggelseTransport( null );
		}

		dto.setStatus( _Rekvisition_Statuss[ rs.getShort( cn[16] ) ]);
		dto.setSamtykke( _Rekvisition_Samtykkes[ rs.getShort( cn[17] ) ]);

		if ( rs.wasNull()) {
			dto.setSamtykke( null );
		}

		dto.setPaaroerende( rs.getString( cn[18] ));
		dto.setAmbulant( rs.getBoolean( cn[19] ) ? Boolean.TRUE : Boolean.FALSE );
		dto.setDatoForslag( rs.getString( cn[20] ));
		dto.setGraviditet( rs.getBoolean( cn[21] ) ? Boolean.TRUE : Boolean.FALSE );

		if ( rs.wasNull()) {
			dto.setGraviditet( null );
		}

		dto.setGraviditetUge( rs.getInt( cn[22] ));

		if ( rs.wasNull()) {
			dto.setGraviditetUge( null );
		}

		dto.setCave( rs.getString( cn[23] ));
		dto.setHoerehaemmet( rs.getBoolean( cn[24] ) ? Boolean.TRUE : Boolean.FALSE );
		dto.setSynshaemmet( rs.getBoolean( cn[25] ) ? Boolean.TRUE : Boolean.FALSE );
		dto.setAmputeret( rs.getBoolean( cn[26] ) ? Boolean.TRUE : Boolean.FALSE );
		dto.setKanIkkeStaa( rs.getBoolean( cn[27] ) ? Boolean.TRUE : Boolean.FALSE );
		dto.setIltLiterPrmin( rs.getShort( cn[28] ));

		if ( rs.wasNull()) {
			dto.setIltLiterPrmin( null );
		}

		dto.setTolkSprog( rs.getString( cn[29] ));
		dto.setDement( rs.getBoolean( cn[30] ) ? Boolean.TRUE : Boolean.FALSE );
		dto.setAfasi( rs.getBoolean( cn[31] ) ? Boolean.TRUE : Boolean.FALSE );
		dto.setIsolation( rs.getString( cn[32] ));
		dto.setCytostatikaDato( rs.getTimestamp( cn[33] ));
		dto.setTidlBilledDiagnostik( rs.getString( cn[34] ));
		dto.setKliniskProblemstilling( rs.getString( cn[35] ));
		dto.setTriage( rs.getString( cn[36] ));
		dto.setHenvLaege( rs.getString( cn[37] ));
		dto.setHenvAfd( rs.getString( cn[38] ));
		dto.setKontaktTlf( rs.getString( cn[39] ));
		dto.setVisitatorPrioritering( rs.getString( cn[40] ));
		dto.setVisitatorBemaerkning( rs.getString( cn[41] ));
		dto.setAfsendtDato( rs.getTimestamp( cn[42] ));

		return dto;
	}

	protected RekvisitionExtended[] toArray(ArrayList<RekvisitionExtended> list ) {
		RekvisitionExtended[] ret = new RekvisitionExtended[ list.size() ];
		return list.toArray( ret );
	}



}
