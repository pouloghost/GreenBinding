package org.gt.greenbinding.handler;

import org.gt.greenbinding.AbsViewModel;
import org.gt.greenbinding.ViewAttributes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pouloghost on 2017/3/28.
 */
public class Handlers {
    private static final Map<String, IBindHandler> sHandlers = new HashMap<>();

    static {
        sHandlers.put("android.view.View", new ViewBindHandler());
        sHandlers.put("android.widget.TextView", new TextViewBindHandler());
    }

    public static void bind(String view, AbsViewModel model, ViewAttributes viewAttributes) {
        IBindHandler handler = sHandlers.get(view);
        if (null == handler) {
            return;
        }
        handler.bind(model, viewAttributes);
    }
}
