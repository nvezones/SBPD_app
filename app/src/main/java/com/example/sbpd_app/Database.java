package com.example.sbpd_app;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import android.widget.Toast;

public class Database {
    private final String DATABASE_NAME="SBPDdb";
    private final int DATABASE_VERSION=1;

    private DBHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;

    public Database (Context context)
    {
        ourContext=context;
    }

    private class DBHelper extends SQLiteOpenHelper
    {

        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION );
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String tblPSS,tblGeneralReport,tblBatteryCharger,tblMaterials,tblMeter;
            String tblMeteringUnit,tblBattery,tblIsolator,tblVCB,tblPowerTransformer,tblLA,tblInspection, tblSync_details;

            tblPSS="CREATE TABLE `PSS` ("+
                        "`PSS_id` TEXT, "+
                        "`PSS_name` TEXT, "+
                        "`section_name`TEXT, "+
                        "`sub_division_name` TEXT, "+
                        "`District` TEXT, "+
                        "`numPT` INTEGER, "+
                        "PRIMARY KEY (`PSS_id`));" ;
            db.execSQL(tblPSS);
            //db.savePSS();
            tblInspection="CREATE TABLE `INSPECTION` (`ins_id` TEXT PRIMARY KEY, `user_id` TEXT,`PSS_id` TEXT,`date` DATE,\n" +
                    "FOREIGN KEY (`PSS_id`) REFERENCES PSS(`PSS_id`)\n" +
                    ");";
            db.execSQL(tblInspection);
            
            tblGeneralReport="CREATE TABLE `GeneralReport` ( " +
                    "  `ins_id` TEXT, " +
                    "  `A` INTEGER, " +
                    "  `B` INTEGER, " +
                    "  `C1` INTEGER, " +
                    "  `C2` INTEGER, " +
                    "  `D1` INTEGER, " +
                    "  `D2` INTEGER, " +
                    "  `E` INTEGER, " +
                    "  `F1` INTEGER, " +
                    "  `F2` INTEGER, " +
                    "  `G` INTEGER, " +
                    "  `H` INTEGER, " +
                    "  `I` INTEGER, " +
                    "  `J` INTEGER, " +
                    "  `K` INTEGER, " +
                    "  `L` INTEGER, " +
                    "  `M` INTEGER, " +
                    "  `N` INTEGER, " +
                    "  `O` INTEGER, " +
                    "  `P` INTEGER, " +
                    "  `Q` INTEGER, " +
                    "  `R` INTEGER, " +
                    "  `S` INTEGER, " +
                    "  FOREIGN KEY (`ins_id`) REFERENCES INSPECTION(`ins_id`));";
            db.execSQL(tblGeneralReport);
            
            tblBatteryCharger="CREATE TABLE `BatteryCharger` ( " +
                    "  `ins_id` TEXT, " +
                    "  `SupplyVoltageAC` INTEGER, " +
                    "  `SupplyVoltageDC` INTEGER, " +
                    "  `ChargingCurrent` INTEGER, " +
                    "  FOREIGN KEY (`ins_id`) REFERENCES INSPECTION(`ins_id`));";
            db.execSQL(tblBatteryCharger);
            
            tblMaterials="CREATE TABLE `Materials` ( " +
                    "  `ins_id` TEXT, " +
                    "  `Name` TEXT, " +
                    "  `for33KV` INTEGER, " +
                    "  `for11KV` INTEGER, " +
                    "  FOREIGN KEY (`ins_id`) REFERENCES INSPECTION(`ins_id`));";
            db.execSQL(tblMaterials);
            
            tblMeter="CREATE TABLE `Meter` ( " +
                    "  `ins_id` TEXT, " +
                    "  `Total` INTEGER, " +
                    "  `Working` INTEGER, " +
                    "  `Defective` INTEGER, " +
                    "  `Type` TEXT, " +
                    "  FOREIGN KEY (`ins_id`) REFERENCES INSPECTION(`ins_id`));";
            db.execSQL(tblMeter);
            
            tblMeteringUnit="CREATE TABLE `MeteringUnit` ( " +
                    "  `ins_id` TEXT, " +
                    "  `Total` INTEGER, " +
                    "  `Working` INTEGER, " +
                    "  `Defective` INTEGER, " +
                    "  `Type` TEXT, " +
                    "  FOREIGN KEY (`ins_id`) REFERENCES INSPECTION(`ins_id`));";
            db.execSQL(tblMeteringUnit);
            
            tblBattery="CREATE TABLE `Battery` ( " +
                    "  `ins_id` TEXT, " +
                    "  `Water_level` INTEGER, " +
                    "  `Terminal` INTEGER, " +
                    "  `Voltage` INTEGER, " +
                    "  `Gravity` INTEGER, " +
                    "  FOREIGN KEY (`ins_id`) REFERENCES INSPECTION(`ins_id`));";
            db.execSQL(tblBattery);
            
            tblIsolator="CREATE TABLE `Isolator` ( " +
                    "  `ins_id` TEXT, " +
                    "  `Total` INTEGER, " +
                    "  `Working` INTEGER, " +
                    "  `Defective` INTEGER, " +
                    "  `Type` TEXT, " +
                    "  FOREIGN KEY (`ins_id`) REFERENCES INSPECTION(`ins_id`));";
            db.execSQL(tblIsolator);
            
            tblVCB="CREATE TABLE `VCB` ( " +
                    "  `ins_id` TEXT, " +
                    "  `Type` TEXT, " +
                    "  `Name` TEXT, " +
                    "  `CT` INTEGER, " +
                    "  `Interrupter` INTEGER, " +
                    "  `Mechanism` INTEGER, " +
                    "  `Motor` INTEGER, " +
                    "  `TrippingCoil` INTEGER, " +
                    "  `closingCoil` INTEGER, " +
                    "  `EarthingPanel` INTEGER, " +
                    "  `RelayOC` INTEGER, " +
                    "  `MasterTripRelay` INTEGER, " +
                    "  `SL_no` TEXT, " +
                    "  `MfgYear` TEXT, " +
                    "  `Make` TEXT, " +
                    "  FOREIGN KEY (`ins_id`) REFERENCES INSPECTION(`ins_id`));";
            db.execSQL(tblVCB);
            
            tblPowerTransformer="CREATE TABLE `PowerTransformer` ( " +
                    "  `PSS_id` TEXT, " +
                    "  `ins_id` TEXT, " +
                    "  `PT_id` TEXT, " +
                    "  `SL_no` TEXT, " +
                    "  `MfgYear` DATE, " +
                    "  `OilLevel` TEXT, " +
                    "  `BDVTop` TEXT, " +
                    "  `BDVBottom` TEXT, " +
                    "  `BuchholzRelay` INTEGER, " +
                    "  `OilLeakage` TEXT, " +
                    "  `TempMeterWinding` INTEGER, " +
                    "  `TempMeterOil` INTEGER, " +
                    "  `SilicaGel` INTEGER, " +
                    "  `EarthResistNeutral` INTEGER, " +
                    "  `EarthResistBody` INTEGER, " +
                    "  `HTBrush` INTEGER, " +
                    "  `LTBrush` INTEGER, " +
                    "  `BrushConnectors` INTEGER, " +
                    "  `Plinth` TEXT, " +
                    "  `EarthPit&connector` TEXT, " +
                    "  PRIMARY KEY (`PSS_id`, `PT_id`), " +
                    "  FOREIGN KEY (`PSS_id`) REFERENCES PSS(`PSS_id`), " +
                    "  FOREIGN KEY (`ins_id`) REFERENCES INSPECTION(`ins_id`) );";
            db.execSQL(tblPowerTransformer);
            
            tblLA="CREATE TABLE `LA` ( " +
                    "  `ins_id` TEXT, " +
                    "  `Total` INTEGER, " +
                    "  `Working` INTEGER, " +
                    "  `Defective` INTEGER, " +
                    "  `Required` INTEGER, " +
                    "  `Remarks TEXT`, "+
                    "  `Type` TEXT, " +
                    "   FOREIGN KEY (`ins_id`) REFERENCES INSPECTION(`ins_id`));";
            db.execSQL(tblLA);
            
            tblSync_details="CREATE TABLE `SYNC_DETAILS` ( " +
                    "    `ins_id` TEXT, " +
                    "    `isSynced`  INTEGER, " +
                    "    `syncTime`  TIMESTAMP, " +
                    "    FOREIGN KEY (`ins_id`) REFERENCES INSPECTION(`ins_id`));";
            db.execSQL(tblSync_details);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS student;");
            onCreate(db);
        }
    }

    public Database open() throws SQLException
    {
        ourHelper =new DBHelper(ourContext);
        ourDatabase=ourHelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        ourHelper.close();
    }

    public void savePSS(String value[])      //PSS Table insertion
    {
        String sql = "INSERT INTO `PSS` VALUES (?,?,?,?)";
        SQLiteStatement statement = ourDatabase.compileStatement(sql);
        statement.bindAllArgsAsStrings(value);

        try {
            long rowId = statement.executeInsert();
            Log.w("insert success","Insert success");
        } catch (SQLiteException e) {
            Log.w("insert fail",e.getMessage());
        }
    }

    public void savePositionBattery(String value[])      //Position of Battery Table insertion
    {
        String sql = "INSERT INTO `Battery` VALUES (?,?,?,?,?)";
        SQLiteStatement statement = ourDatabase.compileStatement(sql);
        statement.bindString(1,"ins123");
        for(int i=0;i<4;i++)
        {
            statement.bindString(i+2,value[i]);
        }
        try {
            long rowId = statement.executeInsert();
            Log.w("insert success","Insert success");
        } catch (SQLiteException e) {
            Log.w("insert fail",e.getMessage());
        }
    }

    public void savePositionBatteryCharger(Integer value[])      //Position of Battery Charger Table insertion
    {
        String sql = "INSERT INTO `BatteryCharger` VALUES (?,?,?,?)";
        SQLiteStatement statement = ourDatabase.compileStatement(sql);
        statement.bindString(1,"ins123");
        for(int i=0;i<3;i++)
        {
            statement.bindLong(i+2,value[i]);
        }
        try {
            long rowId = statement.executeInsert();
            Log.w("insert success","Insert success");
        } catch (SQLiteException e) {
            Log.w("insert fail",e.getMessage());
        }
    }

    public void saveVCB(String value[],String type)      //VCB Table insertion
    {
        String sql = "INSERT INTO `VCB` VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        SQLiteStatement statement = ourDatabase.compileStatement(sql);
        statement.bindString(1,"1ns123");
        statement.bindString(2,type);
        for(int i=0;i<13;i++)
        {
            statement.bindString(i+3,value[i]);
        }
        try {
            long rowId = statement.executeInsert();
            Log.w("insert success","Insert success");
        } catch (SQLiteException e) {
            Log.w("insert fail",e.getMessage());
        }
    }

    public void savePowerTransformer(String value[])      //Power Transformer table insertion
    {
        String sql = "INSERT INTO `PowerTransformer` VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        SQLiteStatement statement=ourDatabase.compileStatement(sql);
        statement.bindString(1,"pss123");
        statement.bindString(2,"ins123");

        for(int i=0;i<18;i++)
            statement.bindString(i+3,value[i]);

        try {
            long rowId=statement.executeInsert();
            Log.w("insert success","Insert success");
        } catch (SQLiteException e) {
            Log.w("insert fail",e.getMessage());
        }
    }

    public void saveIsolator(Integer value[], String type)
    {
        String sql="INSERT INTO `Isolator` VALUES (?,?,?,?,?)";
        SQLiteStatement statement=ourDatabase.compileStatement(sql);
        statement.bindString(1,"ins123");
        for(int i=0;i<3;i++)
            statement.bindLong(i+2,value[i]);
        statement.bindString(5,type);

        try{
            long rowId=statement.executeInsert();
            Log.w("insert success","Insert success");
        } catch (SQLiteException e) {
            Log.w("insert fail",e.getMessage());
        }
    }

    public void saveMaterialsReq(String value[])
    {
        String sql="INSERT INTO `Materials` VALUES (?,?,?,?)";
        SQLiteStatement statement=ourDatabase.compileStatement(sql);
        statement.bindString(1,"ins123");
        statement.bindString(2,value[0]);
        int for33=0,for11=0;
        try{
            for33=Integer.parseInt(value[1]);
            for11=Integer.parseInt(value[2]);
        }catch (NumberFormatException e)
        {
            for33=-1;
            for11=-1;
        }finally {
            statement.bindLong(3,for33);
            statement.bindLong(4,for11);
        }

        try{
            long rowId=statement.executeInsert();
            Log.w("insert success","Insert success");
        } catch (SQLiteException e) {
            Log.w("insert fail",e.getMessage());
        }
    }

    public void saveLA(Integer value[],String type,String remarks)
    {
        String sql="INSERT INTO `LA` VALUES (?,?,?,?,?,?,?)";
        SQLiteStatement statement=ourDatabase.compileStatement(sql);
        statement.bindString(1,"ins123");
        for(int i=0;i<4;i++)
            statement.bindLong(i+2,value[i]);
        statement.bindString(6,remarks);
        statement.bindString(7,type);

        try{
            long rowId=statement.executeInsert();
            Log.w("insert success","Insert success");
        } catch (SQLiteException e) {
            Log.w("insert fail",e.getMessage());
        }
    }

    public void saveMetering(Integer value[],String type)
    {
        String sql="INSERT INTO `MeteringUnit` VALUES (?,?,?,?,?)";
        SQLiteStatement statement=ourDatabase.compileStatement(sql);
        statement.bindString(1,"ins123");
        for(int i=0;i<3;i++)
            statement.bindLong(i+2,value[i]);
        statement.bindString(5,type);

        try{
            long rowId=statement.executeInsert();
            Log.w("insert success","Insert success");
        } catch (SQLiteException e) {
            Log.w("insert fail",e.getMessage());
        }
    }

    public void saveMeter(Integer value[],String type)
    {
        String sql="INSERT INTO `Meter` VALUES (?,?,?,?,?)";
        SQLiteStatement statement=ourDatabase.compileStatement(sql);
        statement.bindString(1,"ins123");
        for(int i=0;i<3;i++)
            statement.bindLong(i+2,value[i]);
        statement.bindString(5,type);

        try{
            long rowId=statement.executeInsert();
            Log.w("insert success","Insert success");
        } catch (SQLiteException e) {
            Log.w("insert fail",e.getMessage());
        }
    }

    public void saveGeneralReport(Integer value[])
    {
        String sql="INSERT INTO `GeneralReport` VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        SQLiteStatement statement=ourDatabase.compileStatement(sql);
        statement.bindString(1,"ins123");
        for(int i=0;i<22;i++)
            statement.bindLong(i+2,value[i]);

        try{
            long rowId=statement.executeInsert();
            Log.w("insert success","Insert success");
        } catch (SQLiteException e) {
            Log.w("insert fail", e.getMessage());
        }
    }

    public void saveSyncDetails(String value[])
    {
        String sql="INSERT INTO `SYNC_DETAILS` VALUES ( ?,?,?)";
        SQLiteStatement statement=ourDatabase.compileStatement(sql);
        statement.bindString(1,value[0]);
        statement.bindLong(2,Long.parseLong(value[1]));
        statement.bindString(3,value[2]);
        try{
            long rowId=statement.executeInsert();
            Log.w("insert success","Insert success");
        } catch (SQLiteException e) {
            Log.w("insert fail", e.getMessage());
        }
    }

    /*------------------------------Methods for extracting data from SQLite db-----------------------------*/



    public String[][] getBatteryData(String ins_id[]){
        Cursor cursor;
        cursor=ourDatabase.query("Battery",null,"ins_id=?",ins_id,null,null,null);
        int rowCount=cursor.getCount();
        String result[][]=new String[rowCount][5];
        if(cursor!=null) {
            for(int i=0;i<rowCount;i++)
            {
                cursor.moveToPosition(i);
                result[i][0]=cursor.getString(0);  //ins_id
                result[i][1]=Long.toString(cursor.getLong(1)); //water_level
                result[i][2]=Long.toString(cursor.getLong(2)); //terminal
                result[i][3]=Long.toString(cursor.getLong(3)); //voltage
                result[i][4]=Long.toString(cursor.getLong(4)); //gravity
            }
        }
        cursor.close();
        return result;
    }

    public String[][] getBatteryChargerData(String ins_id[]){
        Cursor cursor;
        cursor=ourDatabase.query("BatteryCharger",null,"ins_id=?",ins_id,null,null,null);
        int rowCount=cursor.getCount();
        String result[][]=new String[rowCount][4];
        if(cursor!=null) {
            for(int i=0;i<rowCount;i++)
            {
                cursor.moveToPosition(i);
                result[i][0]=cursor.getString(0);  //ins_id
                result[i][1]=Long.toString(cursor.getLong(1)); //supplyVoltageAC
                result[i][2]=Long.toString(cursor.getLong(2)); //supplyVoltageDC
                result[i][3]=Long.toString(cursor.getLong(3)); //ChargingCurrent
            }
        }
        cursor.close();
        return result;
    }

    public String[][] getPowerTransformerData(String ins_id[]){
        Cursor cursor;
        cursor=ourDatabase.query("PowerTransformer",null,"ins_id=?",ins_id,null,null,null);
        int rowCount=cursor.getCount();
        String result[][]=new String[rowCount][20];
        if(cursor!=null)
        {

            for (int i = 0; i < rowCount; i++) {
                cursor.moveToPosition(i);
                for (int j = 0; j < 7; j++)
                    result[i][j] = cursor.getString(j); //pss_id to BDVBottom
                result[i][8] = Long.toString(cursor.getLong(8)); //BuchholzRelay
                result[i][9] = cursor.getString(9);  //OilLeakage
                for (int j = 10; j < 18; j++)
                    result[i][j] = Long.toString(cursor.getLong(j)); //TempMeterWinding to BrushConnectors
                result[i][18] = cursor.getString(18);    //Plinth
                result[i][19] = cursor.getString(19);    //EarthPit&connector
                //move to next record
            }
        }
        cursor.close();
        return result;
    }


    public String[][] getVCBData(String ins_id[]){
        Cursor cursor;
        cursor=ourDatabase.query("VCB",null,"ins_id=?",ins_id,null,null,null);
        int rowCount=cursor.getCount();
        String result[][]=new String[rowCount][15];
        if(cursor!=null)
        {
            for(int i=0;i<rowCount;i++)
            {
                cursor.moveToPosition(i);
                for(int j=0;j<3;j++)
                    result[i][j]=cursor.getString(j);  //ins_id to Name
                for(int j=3;j<12;j++)
                    result[i][j]=Long.toString(cursor.getLong(j)); //CT to MasterTripRelay
                for(int j=12;j<15;j++)
                    result[i][j]=cursor.getString(j);  //SL_no to Make
            }
        }
        cursor.close();
        return result;
    }

    public String[][] getIsolatorData(String ins_id[]){
        Cursor cursor;
        cursor=ourDatabase.query("Isolator",null,"ins_id=?",ins_id,null,null,null);
        int rowCount=cursor.getCount();
        String result[][]=new String[rowCount][5];
        if(cursor!=null)
        {
            for(int i=0;i<rowCount;i++)
            {
                cursor.moveToPosition(i);
                result[i][0]=cursor.getString(0);  //ins_id
                for(int j=1;j<4;j++)
                    result[i][j]=Long.toString(cursor.getLong(j)); //Total, working, defective
                result[i][4]=cursor.getString(4);  //Type
            }
        }
        cursor.close();
        return result;
    }

    public String[][] getMaterialsData(String ins_id[]){
        Cursor cursor;
        cursor=ourDatabase.query("Materials",null,"ins_id=?",ins_id,null,null,null);
        int rowCount=cursor.getCount();
        String result[][]=new String[rowCount][4];
        if(cursor!=null)
        {
            for(int i=0;i<rowCount;i++)
            {
                cursor.moveToPosition(i);
                result[i][0]=cursor.getString(0);  //ins_id
                result[i][1]=cursor.getString(1);  //Name
                result[i][2]=Long.toString(cursor.getLong(2)); //for 33KV
                result[i][3]=Long.toString(cursor.getLong(3));  //for 11KV
            }
        }
        cursor.close();
        return result;
    }

    public String[][] getLAData(String ins_id[]){
        Cursor cursor;
        cursor=ourDatabase.query("LA",null,"ins_id=?",ins_id,null,null,null);
        int rowCount=cursor.getCount();
        String result[][]=new String[rowCount][7];
        if(cursor!=null)
        {
            for(int i=0;i<rowCount;i++)
            {
                cursor.moveToPosition(i);
                result[i][0]=cursor.getString(0);  //ins_id
                for(int j=1;j<5;j++)
                    result[i][j]=Long.toString(cursor.getLong(j)); //Total, working, defective, required
                result[i][5]=cursor.getString(4);  //Remarks
                result[i][6]=cursor.getString(4);  //Type
            }
        }
        cursor.close();
        return result;
    }

    public String[][] getMeterData(String ins_id[]) {
        Cursor cursor;
        cursor = ourDatabase.query("Meter", null, "ins_id=?", ins_id, null, null, null);
        int rowCount=cursor.getCount();
        String result[][] = new String[rowCount][5];
        if(cursor!=null)
        {
            for (int i=0;i<rowCount;i++)
            {
                cursor.moveToPosition(i);
                result[i][0]=cursor.getString(0);  //ins_id
                result[i][1]=Long.toString(cursor.getLong(1)); //total
                result[i][2]=Long.toString(cursor.getLong(2)); //working
                result[i][3]=Long.toString(cursor.getLong(3)); //defective
                result[i][4]=cursor.getString(4);  //type
            }

        }
        cursor.close();
        return result;
    }

    public String[][] getMeteringUnitData(String ins_id[]){
        Cursor cursor;
        cursor=ourDatabase.query("MeteringUnit",null,"ins_id=?",ins_id,null,null,null);
        int rowCount=cursor.getCount();
        String result[][]=new String[rowCount][7];
        if(cursor!=null)
        {
            for(int i=0;i<rowCount;i++)
            {
                cursor.moveToPosition(i);
                result[i][0]=cursor.getString(0);  //ins_id
                result[i][1]=Long.toString(cursor.getLong(1)); //total
                result[i][2]=Long.toString(cursor.getLong(2)); //working
                result[i][3]=Long.toString(cursor.getLong(3)); //defective
                result[i][4]=cursor.getString(4);  //type
            }
        }
        cursor.close();
        return result;
    }

    public String[][] getGeneralReportData(String ins_id[]){
        Cursor cursor;
        cursor=ourDatabase.query("GeneralReport",null,"ins_id=?",ins_id,null,null,null);
        int rowCount=cursor.getCount();
        String result[][]=new String[rowCount][23];
        if(cursor!=null)
        {
            for(int i=0;i<rowCount;i++)
            {
                cursor.moveToPosition(i);
                result[i][0]=cursor.getString(0);  //ins_id
                for(int j=1;j<23;j++)
                    result[i][j]=Long.toString(cursor.getLong(j)); //A to S
            }
        }
        cursor.close();
        return result;
    }

    public Cursor getSyncDetailsData()
    {
        Cursor cursor;
        cursor=ourDatabase.query("SYNC_DETAILS",null,null,null,null,null,null);
        return cursor;
    }
}
