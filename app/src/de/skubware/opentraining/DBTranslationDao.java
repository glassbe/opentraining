package de.skubware.opentraining;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

import de.skubware.opentraining.DBTranslation;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table DBTRANSLATION.
*/
public class DBTranslationDao extends AbstractDao<DBTranslation, Void> {

    public static final String TABLENAME = "DBTRANSLATION";

    /**
     * Properties of entity DBTranslation.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Locale = new Property(0, String.class, "locale", false, "LOCALE");
        public final static Property Translated_name = new Property(1, String.class, "translated_name", false, "TRANSLATED_NAME");
        public final static Property Primary_name = new Property(2, String.class, "primary_name", true, "PRIMARY_NAME");
    };

    private DaoSession daoSession;

    private Query<DBTranslation> dBMuscle_DBTranslationListQuery;

    public DBTranslationDao(DaoConfig config) {
        super(config);
    }
    
    public DBTranslationDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'DBTRANSLATION' (" + //
                "'LOCALE' TEXT," + // 0: locale
                "'TRANSLATED_NAME' TEXT," + // 1: translated_name
                "'PRIMARY_NAME' TEXT PRIMARY KEY NOT NULL );"); // 2: primary_name
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'DBTRANSLATION'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, DBTranslation entity) {
        stmt.clearBindings();
 
        String locale = entity.getLocale();
        if (locale != null) {
            stmt.bindString(1, locale);
        }
 
        String translated_name = entity.getTranslated_name();
        if (translated_name != null) {
            stmt.bindString(2, translated_name);
        }
    }

    @Override
    protected void attachEntity(DBTranslation entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    /** @inheritdoc */
    @Override
    public DBTranslation readEntity(Cursor cursor, int offset) {
        DBTranslation entity = new DBTranslation( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // locale
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1) // translated_name
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, DBTranslation entity, int offset) {
        entity.setLocale(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setTranslated_name(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
     }
    
    /** @inheritdoc */
    @Override
    protected Void updateKeyAfterInsert(DBTranslation entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    /** @inheritdoc */
    @Override
    public Void getKey(DBTranslation entity) {
        return null;
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "dBTranslationList" to-many relationship of DBMuscle. */
    public List<DBTranslation> _queryDBMuscle_DBTranslationList(String locale) {
        synchronized (this) {
            if (dBMuscle_DBTranslationListQuery == null) {
                QueryBuilder<DBTranslation> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.Locale.eq(null));
                dBMuscle_DBTranslationListQuery = queryBuilder.build();
            }
        }
        Query<DBTranslation> query = dBMuscle_DBTranslationListQuery.forCurrentThread();
        query.setParameter(0, locale);
        return query.list();
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getDBMuscleDao().getAllColumns());
            builder.append(" FROM DBTRANSLATION T");
            builder.append(" LEFT JOIN DBMUSCLE T0 ON T.'PRIMARY_NAME'=T0.'PRIMARY_NAME'");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected DBTranslation loadCurrentDeep(Cursor cursor, boolean lock) {
        DBTranslation entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        DBMuscle dBMuscle = loadCurrentOther(daoSession.getDBMuscleDao(), cursor, offset);
        entity.setDBMuscle(dBMuscle);

        return entity;    
    }

    public DBTranslation loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<DBTranslation> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<DBTranslation> list = new ArrayList<DBTranslation>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<DBTranslation> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<DBTranslation> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
