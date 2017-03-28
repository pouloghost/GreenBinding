package org.gt.greenbinding;

/**
 * Created by pouloghost on 2017/3/28.
 */
public interface IObserver {
    void onChange(String field, Object value);
}
