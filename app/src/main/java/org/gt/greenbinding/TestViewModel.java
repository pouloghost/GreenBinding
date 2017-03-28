package org.gt.greenbinding;

public class TestViewModel extends AbsViewModel {
    public TestViewModel() {
        mCommands.put("click", new TestClick());
    }

    public String getFoo() {
        return (String) get("Foo");
    }

    public void setFoo(String Foo) {
        set("Foo", Foo);
    }

    public Object click(Object... params) {
        ICommand command = mCommands.get("click");
        if (null == command) {
            return null;
        }
        return command.invoke(this, params);
    }

    private class TestClick implements ICommand {
        @Override
        public Object invoke(AbsViewModel model, Object... params) {
            setFoo("clicked");
            return null;
        }
    }
}