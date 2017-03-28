package org.gt.greenbinding;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import java.lang.reflect.Field;

/**
 * Created by pouloghost on 2017/3/27.
 */
public class LayoutHook implements LayoutInflater.Factory2 {
    private LayoutInflater.Factory mFactory;
    private LayoutInflater.Factory2 mFactory2;
    private LayoutInflater.Factory2 mPrivateFactory;

    private BindingContext mContext;
    private LayoutInflater mInflater;

    public static LayoutHook hook(LayoutInflater inflater) {
        LayoutHook hook = new LayoutHook(inflater);
        hook.setFactory((LayoutInflater.Factory) getFieldValue(LayoutInflater.class, inflater, "mFactory")).
                setFactory2((LayoutInflater.Factory2) getFieldValue(LayoutInflater.class, inflater, "mFactory2")).
                setPrivateFactory((LayoutInflater.Factory2) getFieldValue(LayoutInflater.class, inflater, "mPrivateFactory"));
        inflater.setFactory2(hook);
        return hook;
    }

    private LayoutHook(LayoutInflater inflater) {
        mContext = new BindingContext();
        mInflater = inflater;
    }

    private LayoutHook setFactory(LayoutInflater.Factory factory) {
        mFactory = factory;
        return this;
    }

    private LayoutHook setFactory2(LayoutInflater.Factory2 factory) {
        mFactory2 = factory;
        return this;
    }

    private LayoutHook setPrivateFactory(LayoutInflater.Factory2 factory) {
        mPrivateFactory = factory;
        return this;
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        View view = null;
        if (null == view && null != mFactory2) {
            view = mFactory2.onCreateView(parent, name, context, attrs);
        }
        if (null == view && null != mFactory) {
            view = mFactory.onCreateView(name, context, attrs);
        }
        if (null == view && null != mPrivateFactory) {
            view = mPrivateFactory.onCreateView(parent, name, context, attrs);
        }
        if (null == view && null != mInflater) {
            try {
                view = mInflater.createView(name, null, attrs);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (null != view) {
            mContext.update(view, attrs);
        }
        return view;
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = null;
        if (null == view && null != mFactory2) {
            view = mFactory2.onCreateView(name, context, attrs);
        }
        if (null == view && null != mFactory) {
            view = mFactory.onCreateView(name, context, attrs);
        }
        if (null == view && null != mPrivateFactory) {
            view = mPrivateFactory.onCreateView(name, context, attrs);
        }
        if (null == view && null != mInflater) {
            try {
                view = mInflater.createView(name, null, attrs);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (null != view) {
            mContext.update(view, attrs);
        }
        return view;
    }

    public static Object getFieldValue(Class clazz, Object object, String fieldName) {
        try {
            if (null == clazz) {
                clazz = object.getClass();
            }
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(object);
        } catch (Exception e) {
        }
        return null;
    }

    public BindingContext getBindingContext() {
        return mContext;
    }
}
