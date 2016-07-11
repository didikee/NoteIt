package com.inno.noteit.db;

import com.activeandroid.Model;
import com.activeandroid.query.From;
import com.activeandroid.query.Select;
import com.inno.noteit.db.entity.FishOrMouse;

import java.util.List;

/**
 * Created by didikee on 2016/7/3.
 */
public class DBHelper {

    /**
     *  query all table data
     * @param table
     * @return list<model></>
     */
    public static List<Model> queryAll(Class<? extends Model> table){
        List<Model> list= new Select().from(table).execute();
        return  list;
    }


}
