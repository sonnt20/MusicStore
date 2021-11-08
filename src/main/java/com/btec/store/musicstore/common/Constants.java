package com.btec.store.musicstore.common;

import org.springframework.beans.factory.annotation.Value;

public class Constants {
    //    -----------------------FILE PROPERTIES---------------------------------
    @Value("${wb.address}")
    public static String webAddressOrigAllow;

    //    -----------------------CONSTANT----------------------------------------
    //-------------Status User-----------
    public static final Integer ACTIVE = 1;
    public static final Integer INACTIVE = 0;

    //--------------ROLE-----------------
    public static final Integer ADMIN = 1;
    public static final Integer USER = 2;

    public static class SONG_STATUS {
        public static final Integer ACTIVE = 1;
        public static final Integer INACTIVE = 0;
    }
    public static class ENABLE {
        public static final Long ACTIVE = 1l;
        public static final Long INACTIVE = 0l;
    }


}
