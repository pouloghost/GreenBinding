package org.gt.greenbinding;

import org.gt.greenbinding.handler.Handlers;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by pouloghost on 2017/3/27.
 */
public class Binder {
    private AbsViewModel mViewModel;
    private BindingContext mContext;

    public Binder(AbsViewModel model, BindingContext context) {
        mViewModel = model;
        mContext = context;
    }

    public void bind() {
        Iterator<ViewAttributes> iterator = mContext.iterator();
        while (iterator.hasNext()) {
            ViewAttributes viewAttributes = iterator.next();
            List<String> supers = getSuperClasses(viewAttributes.getView());
            if (null == supers) {
                continue;
            }
            for (String clazz : supers) {
                Handlers.bind(clazz, mViewModel, viewAttributes);
            }
        }
    }

    public static List<String> getSuperClasses(Object o) {
        if (null == o) {
            return null;
        }
        Class clazz = o.getClass();
        List<String> supers = new LinkedList<>();
        while (clazz != Object.class) {
            supers.add(clazz.getName());
            clazz = clazz.getSuperclass();
        }
        return supers;
    }
}
