package com.hyq.listener;

import org.hibernate.HibernateException;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.hibernate.event.spi.SaveOrUpdateEventListener;

/**
 * Created by genius on 2017/3/11.
 */
public class MySaveListener implements SaveOrUpdateEventListener{
    public void onSaveOrUpdate(SaveOrUpdateEvent saveOrUpdateEvent) throws HibernateException {
        System.out.println(saveOrUpdateEvent.getObject());
        System.out.println("自定义保存事件监听");
    }
}
