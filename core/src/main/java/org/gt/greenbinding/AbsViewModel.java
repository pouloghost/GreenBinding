package org.gt.greenbinding;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbsViewModel {
    private static final List<Class> sPrimaryClasses =
            Arrays.asList(new Class[]{int.class, Integer.class,
                    boolean.class, Boolean.class,
                    short.class, Short.class,
                    long.class, Long.class,
                    float.class, Float.class,
                    double.class, Double.class});

    protected Map<String, Object> mFields = new HashMap<>();
    protected Map<String, ICommand> mCommands = new HashMap<>();
    protected Map<String, IObserver> mObservers = new HashMap<>();

    protected boolean check(Object a, Object b) {
        if (null == a && null == b) {
            return true;
        }
        if (null == a || null == b) {
            return false;
        }
        if (sPrimaryClasses.contains(a.getClass())) {
            return a == b;
        } else {
            return a.equals(b);
        }
    }

    public Object invoke(String name, Object... params) {
        ICommand command = mCommands.get(name);
        if (null == command) {
            return null;
        }
        return command.invoke(this, params);
    }

    public void notifyChange(String field, Object value) {
        IObserver observer = mObservers.get(field);
        if (null == observer) {
            return;
        }
        observer.onChange(field, value);
    }

    public void observe(String field, IObserver observer) {
        mObservers.put(field, observer);
    }


    public void set(String name, Object value) {
        Object v = mFields.get(name);
        if (check(v, value)) {
            return;
        }
        mFields.put(name, value);
        notifyChange(name, value);
    }

    public Object get(String name){
        return mFields.get(name);
    }

    public void dispose() {
        mCommands.clear();
        mObservers.clear();
        mFields.clear();
    }
}
