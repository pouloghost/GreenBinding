package org.gt.greenbinding;

import android.util.AttributeSet;
import android.view.View;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.gt.greenbinding.Constants.ATTR_GET_FIELD;
import static org.gt.greenbinding.Constants.ATTR_ON_CLICK;
import static org.gt.greenbinding.Constants.ATTR_SET_FIELD;

/**
 * Created by pouloghost on 2017/3/28.
 */
public class BindingContext {
    private static final Set<String> sAttrNames = new HashSet<>();

    static {
        Collections.addAll(sAttrNames, ATTR_ON_CLICK, ATTR_GET_FIELD, ATTR_SET_FIELD);
    }

    private List<ViewAttributes> mAttributes = new LinkedList<>();

    public void update(View view, AttributeSet attrs) {
        ViewAttributes map = null;
        for (int i = 0; i < attrs.getAttributeCount(); i++) {
            String name = attrs.getAttributeName(i);
            if (sAttrNames.contains(name)) {
                if (null == map) {
                    map = new ViewAttributes();
                }
                map.newAttrs(name, attrs.getAttributeValue(i));
            }
        }
        if (null != map) {
            map.newView(view);
            mAttributes.add(map);
        }
    }

    public Iterator<ViewAttributes> iterator() {
        return mAttributes.iterator();
    }
}
