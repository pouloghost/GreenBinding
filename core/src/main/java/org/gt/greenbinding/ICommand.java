package org.gt.greenbinding;

/**
 * Created by pouloghost on 2017/3/23.
 */
public interface ICommand {
    Object invoke(AbsViewModel model, Object... params);
}
