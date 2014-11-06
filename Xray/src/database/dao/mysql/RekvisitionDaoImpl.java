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
import java.sql.Timestamp;
import java.sql.Types;

import java.util.ArrayList;

import com.spoledge.audao.db.dao.AbstractDaoImpl;
import com.spoledge.audao.db.dao.DBException;
import com.spoledge.audao.db.dao.DaoException;


import database.dao.RekvisitionDao;
import database.dto.Rekvisition;


/**
 * This is the DAO imlementation class.
 *
 * @author generated
 */
public class RekvisitionDaoImpl extends AbstractDaoImpl<Rekvisition> implements RekvisitionDao {

    private static final String TABLE_NAME = "rekvisition";

    protected static final String SELECT_COLUMNS = "rekvisition_id, MR_kontrolskema_id, PETCT_kontrolskema_id, CT_kontrast_kontrolskema_id, invasiv_UL_kontrolskema_id, undersoegelses_type_id, rekvirent_id, visitator_id, patient_id, henvist_til, hospital_oenske, prioritering, udf_indlagt, ambulant_koersel, indlaeggelse_transport, status, samtykke, patient_foedselsdag, ambulant, dato_forslag, graviditet, graviditet_uge, cave, hoerehaemmet, synshaemmet, amputeret, kan_ikke_staa, ilt_liter_prmin, tolk_sprog, dement, afasi, isolation, cytostatika_dato, tidl_billed_diagnostik, klinisk_problemstilling, triage, henv_laege, henv_afd, kontakt_tlf, visitator_prioritering, afsendt_dato";

    protected static final String PK_CONDITION = "rekvisition_id=?";

    private static final String SQL_INSERT = "INSERT INTO rekvisition (rekvisition_id,MR_kontrolskema_id,PETCT_kontrolskema_id,CT_kontrast_kontrolskema_id,invasiv_UL_kontrolskema_id,undersoegelses_type_id,rekvirent_id,visitator_id,patient_id,henvist_til,hospital_oenske,prioritering,udf_indlagt,ambulant_koersel,indlaeggelse_transport,status,samtykke,patient_foedselsdag,ambulant,dato_forslag,graviditet,graviditet_uge,cave,hoerehaemmet,synshaemmet,amputeret,kan_ikke_staa,ilt_liter_prmin,tolk_sprog,dement,afasi,isolation,cytostatika_dato,tidl_billed_diagnostik,klinisk_problemstilling,triage,henv_laege,henv_afd,kontakt_tlf,visitator_prioritering,afsendt_dato) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final Rekvisition.HenvistTil[] _Rekvisition_HenvistTils = { null, Rekvisition.HenvistTil.RADIOLOGISK, Rekvisition.HenvistTil.KLINISK };
    private static final Rekvisition.HospitalOenske[] _Rekvisition_HospitalOenskes = { null, Rekvisition.HospitalOenske.HILLEROED, Rekvisition.HospitalOenske.FREDERIKSSUND, Rekvisition.HospitalOenske.HELSINGOER };
    private static final Rekvisition.Prioritering[] _Rekvisition_Prioriterings = { null, Rekvisition.Prioritering.HASTE, Rekvisition.Prioritering.RUTINE, Rekvisition.Prioritering.FREMSKYNDET, Rekvisition.Prioritering.PAKKEFORLOEB };
    private static final Rekvisition.AmbulantKoersel[] _Rekvisition_AmbulantKoersels = { null, Rekvisition.AmbulantKoersel.INGEN, Rekvisition.AmbulantKoersel.SIDDENDE, Rekvisition.AmbulantKoersel.LIGGENDE };
    private static final Rekvisition.IndlaeggelseTransport[] _Rekvisition_IndlaeggelseTransports = { null, Rekvisition.IndlaeggelseTransport.GAA_UDEN_PORTOER, Rekvisition.IndlaeggelseTransport.GAA_MED_PORTOER, Rekvisition.IndlaeggelseTransport.KOERESTOL, Rekvisition.IndlaeggelseTransport.SENG };
    private static final Rekvisition.Status[] _Rekvisition_Statuss = { null, Rekvisition.Status.PENDING, Rekvisition.Status.CANCELED, Rekvisition.Status.APPROVED, Rekvisition.Status.DECLINED, Rekvisition.Status.BOOKED };
    private static final Rekvisition.Samtykke[] _Rekvisition_Samtykkes = { null, Rekvisition.Samtykke.JA, Rekvisition.Samtykke.NEJ, Rekvisition.Samtykke.UDEN_SAMTYKKE };

    public RekvisitionDaoImpl( Connection conn ) {
        super( conn );
    }

    /**
     * Finds a record identified by its primary key.
     * @return the record found or null
     */
    public Rekvisition findByPrimaryKey( int rekvisitionId ) {
        return findOne( PK_CONDITION, rekvisitionId);
    }

    /**
     * Inserts a new record.
     */
    public void insert( Rekvisition dto ) throws DaoException {
        PreparedStatement stmt = null;

        debugSql( SQL_INSERT, dto );

        try {
            stmt = conn.prepareStatement( SQL_INSERT );

            if ( dto.getRekvisitionId() == null ) {
                throw new DaoException("Value of column 'rekvisition_id' cannot be null");
            }
            stmt.setInt( 1, dto.getRekvisitionId() );

            if ( dto.getMRKontrolskemaId() == null ) {
                stmt.setNull( 2, Types.INTEGER );
            }
            else {
                stmt.setInt( 2, dto.getMRKontrolskemaId() );
            }

            if ( dto.getPETCTKontrolskemaId() == null ) {
                stmt.setNull( 3, Types.INTEGER );
            }
            else {
                stmt.setInt( 3, dto.getPETCTKontrolskemaId() );
            }

            if ( dto.getCTKontrastKontrolskemaId() == null ) {
                stmt.setNull( 4, Types.INTEGER );
            }
            else {
                stmt.setInt( 4, dto.getCTKontrastKontrolskemaId() );
            }

            if ( dto.getInvasivULKontrolskemaId() == null ) {
                stmt.setNull( 5, Types.INTEGER );
            }
            else {
                stmt.setInt( 5, dto.getInvasivULKontrolskemaId() );
            }

            if ( dto.getUndersoegelsesTypeId() == null ) {
                throw new DaoException("Value of column 'undersoegelses_type_id' cannot be null");
            }
            stmt.setInt( 6, dto.getUndersoegelsesTypeId() );

            if ( dto.getRekvirentId() == null ) {
                throw new DaoException("Value of column 'rekvirent_id' cannot be null");
            }
            stmt.setInt( 7, dto.getRekvirentId() );

            if ( dto.getVisitatorId() == null ) {
                throw new DaoException("Value of column 'visitator_id' cannot be null");
            }
            stmt.setInt( 8, dto.getVisitatorId() );

            if ( dto.getPatientId() == null ) {
                throw new DaoException("Value of column 'patient_id' cannot be null");
            }
            stmt.setInt( 9, dto.getPatientId() );

            if ( dto.getHenvistTil() == null ) {
                throw new DaoException("Value of column 'henvist_til' cannot be null");
            }
            stmt.setShort( 10, (short) (dto.getHenvistTil().ordinal() + 1) );

            if ( dto.getHospitalOenske() == null ) {
                stmt.setNull( 11, Types.SMALLINT );
            }
            else {
                stmt.setShort( 11, (short) (dto.getHospitalOenske().ordinal() + 1) );
            }

            if ( dto.getPrioritering() == null ) {
                throw new DaoException("Value of column 'prioritering' cannot be null");
            }
            stmt.setShort( 12, (short) (dto.getPrioritering().ordinal() + 1) );

            if ( dto.getUdfIndlagt() == null ) {
                throw new DaoException("Value of column 'udf_indlagt' cannot be null");
            }
            stmt.setByte( 13, dto.getUdfIndlagt() ? ((byte)1) : ((byte)0) );

            if ( dto.getAmbulantKoersel() == null ) {
                stmt.setNull( 14, Types.SMALLINT );
            }
            else {
                stmt.setShort( 14, (short) (dto.getAmbulantKoersel().ordinal() + 1) );
            }

            if ( dto.getIndlaeggelseTransport() == null ) {
                stmt.setNull( 15, Types.SMALLINT );
            }
            else {
                stmt.setShort( 15, (short) (dto.getIndlaeggelseTransport().ordinal() + 1) );
            }

            if ( dto.getStatus() == null ) {
                throw new DaoException("Value of column 'status' cannot be null");
            }
            stmt.setShort( 16, (short) (dto.getStatus().ordinal() + 1) );

            if ( dto.getSamtykke() == null ) {
                stmt.setNull( 17, Types.SMALLINT );
            }
            else {
                stmt.setShort( 17, (short) (dto.getSamtykke().ordinal() + 1) );
            }

            if ( dto.getPatientFoedselsdag() == null ) {
                throw new DaoException("Value of column 'patient_foedselsdag' cannot be null");
            }
            stmt.setTimestamp( 18, dto.getPatientFoedselsdag() );

            if ( dto.getAmbulant() == null ) {
                throw new DaoException("Value of column 'ambulant' cannot be null");
            }
            stmt.setByte( 19, dto.getAmbulant() ? ((byte)1) : ((byte)0) );

            if ( dto.getDatoForslag() != null ) {
                checkMaxLength( "dato_forslag", dto.getDatoForslag(), 50 );
            }
            stmt.setString( 20, dto.getDatoForslag() );

            if ( dto.getGraviditet() == null ) {
                stmt.setNull( 21, Types.TINYINT );
            }
            else {
                stmt.setByte( 21, dto.getGraviditet() ? ((byte)1) : ((byte)0) );
            }

            if ( dto.getGraviditetUge() == null ) {
                stmt.setNull( 22, Types.INTEGER );
            }
            else {
                stmt.setInt( 22, dto.getGraviditetUge() );
            }

            if ( dto.getCave() == null ) {
                throw new DaoException("Value of column 'cave' cannot be null");
            }
            checkMaxLength( "cave", dto.getCave(), 500 );
            stmt.setString( 23, dto.getCave() );

            if ( dto.getHoerehaemmet() == null ) {
                throw new DaoException("Value of column 'hoerehaemmet' cannot be null");
            }
            stmt.setByte( 24, dto.getHoerehaemmet() ? ((byte)1) : ((byte)0) );

            if ( dto.getSynshaemmet() == null ) {
                throw new DaoException("Value of column 'synshaemmet' cannot be null");
            }
            stmt.setByte( 25, dto.getSynshaemmet() ? ((byte)1) : ((byte)0) );

            if ( dto.getAmputeret() == null ) {
                throw new DaoException("Value of column 'amputeret' cannot be null");
            }
            stmt.setByte( 26, dto.getAmputeret() ? ((byte)1) : ((byte)0) );

            if ( dto.getKanIkkeStaa() == null ) {
                throw new DaoException("Value of column 'kan_ikke_staa' cannot be null");
            }
            stmt.setByte( 27, dto.getKanIkkeStaa() ? ((byte)1) : ((byte)0) );

            if ( dto.getIltLiterPrmin() == null ) {
                stmt.setNull( 28, Types.SMALLINT );
            }
            else {
                stmt.setShort( 28, dto.getIltLiterPrmin() );
            }

            if ( dto.getTolkSprog() != null ) {
                checkMaxLength( "tolk_sprog", dto.getTolkSprog(), 50 );
            }
            stmt.setString( 29, dto.getTolkSprog() );

            if ( dto.getDement() == null ) {
                throw new DaoException("Value of column 'dement' cannot be null");
            }
            stmt.setByte( 30, dto.getDement() ? ((byte)1) : ((byte)0) );

            if ( dto.getAfasi() == null ) {
                throw new DaoException("Value of column 'afasi' cannot be null");
            }
            stmt.setByte( 31, dto.getAfasi() ? ((byte)1) : ((byte)0) );

            if ( dto.getIsolation() == null ) {
                throw new DaoException("Value of column 'isolation' cannot be null");
            }
            checkMaxLength( "isolation", dto.getIsolation(), 50 );
            stmt.setString( 32, dto.getIsolation() );
            stmt.setTimestamp( 33, dto.getCytostatikaDato() );

            if ( dto.getTidlBilledDiagnostik() != null ) {
                checkMaxLength( "tidl_billed_diagnostik", dto.getTidlBilledDiagnostik(), 50 );
            }
            stmt.setString( 34, dto.getTidlBilledDiagnostik() );

            if ( dto.getKliniskProblemstilling() == null ) {
                throw new DaoException("Value of column 'klinisk_problemstilling' cannot be null");
            }
            checkMaxLength( "klinisk_problemstilling", dto.getKliniskProblemstilling(), 1000 );
            stmt.setString( 35, dto.getKliniskProblemstilling() );

            if ( dto.getTriage() != null ) {
                checkMaxLength( "triage", dto.getTriage(), 100 );
            }
            stmt.setString( 36, dto.getTriage() );

            if ( dto.getHenvLaege() == null ) {
                throw new DaoException("Value of column 'henv_laege' cannot be null");
            }
            checkMaxLength( "henv_laege", dto.getHenvLaege(), 100 );
            stmt.setString( 37, dto.getHenvLaege() );

            if ( dto.getHenvAfd() == null ) {
                throw new DaoException("Value of column 'henv_afd' cannot be null");
            }
            checkMaxLength( "henv_afd", dto.getHenvAfd(), 100 );
            stmt.setString( 38, dto.getHenvAfd() );

            if ( dto.getKontaktTlf() == null ) {
                throw new DaoException("Value of column 'kontakt_tlf' cannot be null");
            }
            checkMaxLength( "kontakt_tlf", dto.getKontaktTlf(), 50 );
            stmt.setString( 39, dto.getKontaktTlf() );

            if ( dto.getVisitatorPrioritering() != null ) {
                checkMaxLength( "visitator_prioritering", dto.getVisitatorPrioritering(), 100 );
            }
            stmt.setString( 40, dto.getVisitatorPrioritering() );

            if ( dto.getAfsendtDato() == null ) {
                throw new DaoException("Value of column 'afsendt_dato' cannot be null");
            }
            stmt.setTimestamp( 41, dto.getAfsendtDato() );

            int n = stmt.executeUpdate();
        }
        catch (SQLException e) {
            errorSql( e, SQL_INSERT, dto );
            handleException( e );
            throw new DBException( e );
        }
        finally {
            if (stmt != null) try { stmt.close(); } catch (SQLException e) {}
        }
    }

    /**
     * Updates one record found by primary key.
     * @return true iff the record was really updated (=found and any change was really saved)
     */
    public boolean update( int rekvisitionId, Rekvisition dto ) throws DaoException {
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

    protected Rekvisition fetch( ResultSet rs ) throws SQLException {
        Rekvisition dto = new Rekvisition();
        dto.setRekvisitionId( rs.getInt( 1 ));
        dto.setMRKontrolskemaId( rs.getInt( 2 ));

        if ( rs.wasNull()) {
            dto.setMRKontrolskemaId( null );
        }

        dto.setPETCTKontrolskemaId( rs.getInt( 3 ));

        if ( rs.wasNull()) {
            dto.setPETCTKontrolskemaId( null );
        }

        dto.setCTKontrastKontrolskemaId( rs.getInt( 4 ));

        if ( rs.wasNull()) {
            dto.setCTKontrastKontrolskemaId( null );
        }

        dto.setInvasivULKontrolskemaId( rs.getInt( 5 ));

        if ( rs.wasNull()) {
            dto.setInvasivULKontrolskemaId( null );
        }

        dto.setUndersoegelsesTypeId( rs.getInt( 6 ));
        dto.setRekvirentId( rs.getInt( 7 ));
        dto.setVisitatorId( rs.getInt( 8 ));
        dto.setPatientId( rs.getInt( 9 ));
        dto.setHenvistTil( _Rekvisition_HenvistTils[ rs.getShort( 10 ) ]);
        dto.setHospitalOenske( _Rekvisition_HospitalOenskes[ rs.getShort( 11 ) ]);

        if ( rs.wasNull()) {
            dto.setHospitalOenske( null );
        }

        dto.setPrioritering( _Rekvisition_Prioriterings[ rs.getShort( 12 ) ]);
        dto.setUdfIndlagt( rs.getBoolean( 13 ) ? Boolean.TRUE : Boolean.FALSE );
        dto.setAmbulantKoersel( _Rekvisition_AmbulantKoersels[ rs.getShort( 14 ) ]);

        if ( rs.wasNull()) {
            dto.setAmbulantKoersel( null );
        }

        dto.setIndlaeggelseTransport( _Rekvisition_IndlaeggelseTransports[ rs.getShort( 15 ) ]);

        if ( rs.wasNull()) {
            dto.setIndlaeggelseTransport( null );
        }

        dto.setStatus( _Rekvisition_Statuss[ rs.getShort( 16 ) ]);
        dto.setSamtykke( _Rekvisition_Samtykkes[ rs.getShort( 17 ) ]);

        if ( rs.wasNull()) {
            dto.setSamtykke( null );
        }

        dto.setPatientFoedselsdag( rs.getTimestamp( 18 ));
        dto.setAmbulant( rs.getBoolean( 19 ) ? Boolean.TRUE : Boolean.FALSE );
        dto.setDatoForslag( rs.getString( 20 ));
        dto.setGraviditet( rs.getBoolean( 21 ) ? Boolean.TRUE : Boolean.FALSE );

        if ( rs.wasNull()) {
            dto.setGraviditet( null );
        }

        dto.setGraviditetUge( rs.getInt( 22 ));

        if ( rs.wasNull()) {
            dto.setGraviditetUge( null );
        }

        dto.setCave( rs.getString( 23 ));
        dto.setHoerehaemmet( rs.getBoolean( 24 ) ? Boolean.TRUE : Boolean.FALSE );
        dto.setSynshaemmet( rs.getBoolean( 25 ) ? Boolean.TRUE : Boolean.FALSE );
        dto.setAmputeret( rs.getBoolean( 26 ) ? Boolean.TRUE : Boolean.FALSE );
        dto.setKanIkkeStaa( rs.getBoolean( 27 ) ? Boolean.TRUE : Boolean.FALSE );
        dto.setIltLiterPrmin( rs.getShort( 28 ));

        if ( rs.wasNull()) {
            dto.setIltLiterPrmin( null );
        }

        dto.setTolkSprog( rs.getString( 29 ));
        dto.setDement( rs.getBoolean( 30 ) ? Boolean.TRUE : Boolean.FALSE );
        dto.setAfasi( rs.getBoolean( 31 ) ? Boolean.TRUE : Boolean.FALSE );
        dto.setIsolation( rs.getString( 32 ));
        dto.setCytostatikaDato( rs.getTimestamp( 33 ));
        dto.setTidlBilledDiagnostik( rs.getString( 34 ));
        dto.setKliniskProblemstilling( rs.getString( 35 ));
        dto.setTriage( rs.getString( 36 ));
        dto.setHenvLaege( rs.getString( 37 ));
        dto.setHenvAfd( rs.getString( 38 ));
        dto.setKontaktTlf( rs.getString( 39 ));
        dto.setVisitatorPrioritering( rs.getString( 40 ));
        dto.setAfsendtDato( rs.getTimestamp( 41 ));

        return dto;
    }

    protected Rekvisition[] toArray(ArrayList<Rekvisition> list ) {
        Rekvisition[] ret = new Rekvisition[ list.size() ];
        return list.toArray( ret );
    }

}