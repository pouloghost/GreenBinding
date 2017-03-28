package org.gt.greenbinding.handler;

import android.text.TextUtils;
import android.view.View;

import org.gt.greenbinding.AbsViewModel;
import org.gt.greenbinding.ViewAttributes;

import static org.gt.greenbinding.Constants.ATTR_ON_CLICK;

/**
 * Created by pouloghost on 2017/3/28.
 */
public class ViewBindHandler implements IBindHandler {
    @Override
    public void bind(AbsViewModel model, ViewAttributes viewAttributes) {
        bindOnClick(model, viewAttributes);
    }

    public static void bindOnClick(final AbsViewModel model, ViewAttributes viewAttributes) {
        final String onClick = viewAttributes.getAttr(ATTR_ON_CLICK);
        if (TextUtils.isEmpty(onClick)) {
            return;
        }
        View view = viewAttributes.getView();
        if (null == view) {
            return;
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.invoke(onClick, v);
            }
        });
    }
}
