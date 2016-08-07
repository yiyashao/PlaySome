package com.techosoft.idea.playsome.models;

import java.util.Date;

/**
 * Created by davidsss on 16-08-06.
 * store a single want item
 */
public class WantItem {

    //initial values
    public String title = "";
    public String detail = "";
    public int ownerId = 0;
    public Date expireDate = new Date();
    public String itemCloudId = "";

}
