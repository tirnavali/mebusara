package datamodel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataSource {
    ///BU sınıf veritabanı bağlantısnı içerir
    public static final String DB_NAME="mebusara.db";
    public static final String DB_CONN_STRING="jdbc:sqlite:C:\\Users\\serca\\IdeaProjects\\HelloWorldFX\\HelloWorldFX\\src\\datamodel\\"+DB_NAME;
    public static final String ORDER_ASC="ORDER BY ASC";
    public static final String ORDER_DESC="ORDER BY DESC";

    public static final String TABLE_MESLEK         ="meslekler";
    public static final String COLUMN_MESLEK_ID     ="id";
    public static final String COLUMN_MESLEK_ADI    ="m_adi";
    public static final String QUERY_MESLEKLER      ="SELECT * FROM "+TABLE_MESLEK+" ORDER BY "+ COLUMN_MESLEK_ADI;
    public static final String QUERY_MESLEK         ="SELECT "+COLUMN_MESLEK_ID+" FROM "+TABLE_MESLEK+" WHERE "+COLUMN_MESLEK_ADI+" = ?";
    public static final String INSERT_MESLEK        ="INSERT INTO "+TABLE_MESLEK+"("+COLUMN_MESLEK_ADI+" ) VALUES (?)";

    public static final String TABLE_DONEMLER       ="donemler";
    public static final String COLUMN_DONEM_ID      ="id";
    public static final String COLUMN_DONEM_ADI     ="donem_adi";
    public static final String COLUMN_DONEM_BAS_T   ="donem_bas_t";
    public static final String COLUMN_DONEM_BIT_T   ="donem_bit_t";
    public static final String QUERY_DONEMLER       ="SELECT * FROM "+TABLE_DONEMLER;
    public static final String QUERY_DONEM          ="SELECT "+COLUMN_DONEM_ID+" FROM "+TABLE_DONEMLER+" WHERE "+COLUMN_DONEM_ADI+" = ?";
    public static final String INSERT_DONEM         ="INSERT INTO "+TABLE_DONEMLER+" ( "+COLUMN_DONEM_ADI+") VALUES (?)";

    public static final String TABLE_PARTILER       ="partiler";
    public static final String COLUMN_PARTI_ID      ="id";
    public static final String COLUMN_PARTI_AD      ="parti_ad";
    public static final String QUERY_PARTILER       ="SELECT * FROM "+ TABLE_PARTILER;
    public static final String QUERY_PARTI          ="SELECT "+COLUMN_PARTI_ID+" FROM "+TABLE_PARTILER+ " WHERE "+COLUMN_PARTI_AD+" = ?";
    public static final String INSERT_PARTI         ="INSERT INTO "+TABLE_PARTILER+"("+COLUMN_PARTI_AD+" ) VALUES (?)";


    private Connection conn;
    private PreparedStatement queryMeslekler;
    private PreparedStatement queryMeslek;
    private PreparedStatement insertIntoMeslekler;

    private PreparedStatement queryDonemler;
    private PreparedStatement queryDonem;
    private PreparedStatement insertIntoDonemler;

    private PreparedStatement queryPartiler;
    private PreparedStatement queryParti;
    private PreparedStatement insertIntoPartiler;

    private static DataSource datasource = new DataSource();

    public boolean open(){
        try {
            conn = DriverManager.getConnection(DB_CONN_STRING);
            System.out.println("Connection success!");
            queryMeslekler      = conn.prepareStatement(QUERY_MESLEKLER);
            queryMeslek         = conn.prepareStatement(QUERY_MESLEK);
            insertIntoMeslekler = conn.prepareStatement(INSERT_MESLEK, Statement.RETURN_GENERATED_KEYS);

            queryDonemler       = conn.prepareStatement(QUERY_DONEMLER);
            queryDonem          = conn.prepareStatement(QUERY_DONEM);
            insertIntoDonemler  = conn.prepareStatement(INSERT_DONEM, Statement.RETURN_GENERATED_KEYS);

            queryPartiler       =conn.prepareStatement(QUERY_PARTILER);
            queryParti          =conn.prepareStatement(QUERY_PARTI);
            insertIntoPartiler  =conn.prepareStatement(INSERT_PARTI, Statement.RETURN_GENERATED_KEYS);


            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldnt connect db : "+DB_NAME);
            return false;
        }
    }

    public void close() {
        try {
            if (queryMeslekler != null) {
                queryMeslekler.close();
            }
            if(queryMeslek != null) {
                queryMeslek.close();
            }
            if(insertIntoMeslekler != null){
                insertIntoMeslekler.close();
            }
            if(queryDonemler != null) {
                queryDonemler.close();
            }
            if(queryDonem != null) {
                queryDonem.close();
            }
            if(insertIntoDonemler != null) {
                insertIntoDonemler.close();
            }
            if(queryParti != null) {
                queryParti.close();
            }
            if(queryPartiler != null) {
                queryPartiler.close();
            }
            if(insertIntoPartiler != null) {
                insertIntoPartiler.close();
            }
            if(conn != null){
                conn.close();
            }
        } catch (Exception e) {
            System.out.println("DB kapanmadı");
        }
    }

    private DataSource(){}

    public static DataSource getInstance(){
        return datasource;
    }

    public List<Parti> queryPartiler(){
        try(ResultSet resultSet = queryPartiler.executeQuery()){
            List<Parti> partiler = new ArrayList<>();
            // sorgu sonucuna gelene kadar tüm sonucu dolaş
            while(resultSet.next()){
                Parti parti = new Parti();
                parti.setId(resultSet.getInt(COLUMN_PARTI_ID));
                parti.setParti_adi(resultSet.getString(COLUMN_PARTI_AD));
                partiler.add(parti);
            }
            return partiler;

        } catch(SQLException e) {
            System.out.println("Partiler tablosunda  sorun oluştu");
            e.printStackTrace();
            return null;
        }
    }

    public List<Donem> queryDonemler() {
        try (ResultSet resultSet = queryDonemler.executeQuery()){
            List<Donem> donemler = new ArrayList<>();
            while(resultSet.next()){
                Donem donem = new Donem();
                donem.setId(resultSet.getInt(COLUMN_DONEM_ID));
                donem.setDonem_adi(resultSet.getString(COLUMN_DONEM_ADI));
                donem.setDonem_bas_t(resultSet.getString(COLUMN_DONEM_BAS_T));
                donem.setDonem_bit_t(resultSet.getString(COLUMN_DONEM_BIT_T));
                donemler.add(donem);
            }
            return donemler;
        }catch (SQLException e){
            System.out.println("Dönemler tablosunda problem oluştu");
            e.printStackTrace();
            return null;
        }
    }
    public List<Meslek> queryMeslekler(){
//        Statement statement = null;
//        ResultSet resultSet = null;

        try(ResultSet resultSet = queryMeslekler.executeQuery()){
            List<Meslek> meslekler = new ArrayList<Meslek>();


            while(resultSet.next()){
                Meslek meslek = new Meslek();
                meslek.setId(resultSet.getInt(COLUMN_MESLEK_ID));
                meslek.setMeslek_adi(resultSet.getString(COLUMN_MESLEK_ADI));
                meslekler.add(meslek);
            }
            return meslekler;
        }catch (SQLException e) {
            System.out.println("meslek tablosuna bağlanamadık");
            e.printStackTrace();
            return null;
        }
    }

    public int addMeslek(String meslekAdi) throws SQLException {
        queryMeslek.setString(1,meslekAdi);
        ResultSet result = queryMeslek.executeQuery();
        //bu isimde bir meslek varsa tabloya yazmayacağız
        if (result.next()) {
            //var olan mesleğin id'sini döndür
            return result.getInt(1);

        } else {
            //tabloya yazılacak
            insertIntoMeslekler.setString(1,meslekAdi);
            int effectedRows = insertIntoMeslekler.executeUpdate();
            //eğer birden fazla ya da eksik satır etkilendiyse hata ver. Normalde 1 satır veri yazıyoruz.
            if (effectedRows != 1) {
                throw new SQLException("MEslek eklenemedi");
            }
            // eğer oluşmuş bir id varsa o id yi döndür.
            ResultSet generatedKeys = insertIntoMeslekler.getGeneratedKeys();
            if(generatedKeys.next()) {
                return  generatedKeys.getInt(1);
            } else {
                throw new SQLException("Couldn't get id from meslekler");
            }
        }


    }
}
