package org.gt.greenbinding.handler;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import org.gt.greenbinding.AbsViewModel;
import org.gt.greenbinding.IObserver;
import org.gt.greenbinding.ViewAttributes;

import static org.gt.greenbinding.Constants.ATTR_GET_FIELD;
import static org.gt.greenbinding.Constants.ATTR_SET_FIELD;

/**
 * Created by pouloghost on 2017/3/28.
 */
public class TextViewBindHandler implements IBindHandler {
    @Override
    public void bind(AbsViewModel model, ViewAttributes viewAttributes) {
        bindGetFieldSetText(model, viewAttributes);
        bindSetFieldTextWatcher(model, viewAttributes);
    }

    public static void bindGetFieldSetText(AbsViewModel model, ViewAttributes viewAttributes) {
        String field = viewAttributes.getAttr(ATTR_GET_FIELD);
        if (TextUtils.isEmpty(field)) {
            return;
        }
        final View view = viewAttributes.getView();
        if (null == view || !(view instanceof TextView)) {
            return;
        }
        model.observe(field, new IObserver() {
            @Override
            public void onChange(String field, Object value) {
                ((TextView) view).setText(String.valueOf(value));
            }
        });
    }

    public static void bindSetFieldTextWatcher(final AbsViewModel model, ViewAttributes viewAttributes) {
        final String field = viewAttributes.getAttr(ATTR_SET_FIELD);
        if (TextUtils.isEmpty(field)) {
            return;
        }
        View view = viewAttributes.getView();
        if (null == view || !(view instanceof TextView)) {
            return;
        }
        ((TextView) view).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                model.set(field, s.toString());
            }
        });
    }
}
