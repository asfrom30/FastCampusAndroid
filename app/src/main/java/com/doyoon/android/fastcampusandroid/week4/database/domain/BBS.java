package com.doyoon.android.fastcampusandroid.week4.database.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by DOYOON on 6/9/2017.
 */
@DatabaseTable(tableName = "BBS")
public class BBS {
    @DatabaseField
    String id;
}
