package com.hyq.listener;

import org.hibernate.HibernateException;
import org.hibernate.event.internal.DefaultLoadEventListener;
import org.hibernate.event.spi.LoadEvent;

/**
 * Created by genius on 2017/3/11.
 */
public class MyLoadListener extends DefaultLoadEventListener{

    @Override
    public void onLoad(LoadEvent event, LoadType loadType) throws HibernateException {
        System.out.println("自定义的load事件");
        System.out.println(event.getEntityClassName()+"======"+event.getEntityId());
        super.onLoad(event,loadType);
    }


}
