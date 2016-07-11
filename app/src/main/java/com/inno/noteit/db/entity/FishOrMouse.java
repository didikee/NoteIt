package com.inno.noteit.db.entity;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by didik on 2016/7/11.
 */

@Table(name = "FishOrMouses")
public class FishOrMouse extends Model {

    @Column(name = "Haha")
    public String haha;
}
