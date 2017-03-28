package org.gt.greenbinding;

import android.view.View;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pouloghost on 2017/3/28.
 */
public class ViewAttributes {
    private WeakReference<View> mView;
    private Map<String, String> mAttrs = new HashMap<>();

    public View getView() {
        return mView.get();
    }

    public String getAttr(String name) {
        return mAttrs.get(name);
    }

    public void newAttrs(String name, String value) {
        mAttrs.put(name, value);
    }

    public void newView(View v) {
        mView = new WeakReference<>(v);
    }
}
