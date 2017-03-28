package org.gt.greenbinding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by pouloghost on 2017/3/27.
 */
public class Binders {
    public static View inflateAndBind(Context context, int layout, AbsViewModel model, ViewGroup viewGroup, boolean attach) {
        LayoutInflater inflater = LayoutInflater.from(context).cloneInContext(context);

        LayoutHook hook = LayoutHook.hook(inflater);
        View view = inflater.inflate(layout, viewGroup, attach);
        Binder binder = new Binder(model, hook.getBindingContext());
        binder.bind();
        return view;
    }
}
