package com.example.sbpd_app;

import android.content.Context;
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
                        "`PSS_id` varchar(50), "+
                        "`PSS_name` varchar(50), "+
                        "`section_name` varchar(50), "+
                        "`sub_division_name` varchar(50), "+
                        "PRIMARY KEY (`PSS_id`));" ;
            db.execSQL(tblPSS);
            
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
                    "  `Type` INTEGER, " +
                    "  FOREIGN KEY (`ins_id`) REFERENCES INSPECTION(`ins_id`));";
            db.execSQL(tblMeter);
            
            tblMeteringUnit="CREATE TABLE `MeteringUnit` ( " +
                    "  `ins_id` TEXT, " +
                    "  `Total` INTEGER, " +
                    "  `Working` INTEGER, " +
                    "  `Defective` INTEGER, " +
                    "  `Type` INTEGER, " +
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
                    "  `Type` INTEGER, " +
                    "  FOREIGN KEY (`ins_id`) REFERENCES INSPECTION(`ins_id`));";
            db.execSQL(tblIsolator);
            
            tblVCB="CREATE TABLE `VCB` ( " +
                    "  `ins_id` TEXT, " +
                    "  `Type` INTEGER, " +
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
                    "  `MfgYear` DATE, " +
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
                    "  `Type` INTEGER, " +
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

    public void saveForm1(String value[],String table)
    {
        String sql = "INSERT INTO " +table+ " VALUES (?,?,?,?)";
        SQLiteStatement statement = ourDatabase.compileStatement(sql);
        statement.bindAllArgsAsStrings(value);
        try {
            long rowId = statement.executeInsert();
            Log.w("insert success","Insert success");
        } catch (SQLiteException e) {
            Log.w("insert fail",e.getMessage());
        }
    }
}