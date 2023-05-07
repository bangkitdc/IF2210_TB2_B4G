package bnmobusinessmanagementsystem.pluginsStorage.pluginsInput;

public interface Plugin<T> {
    public String getName();
    public void run(T parameter);

    public boolean isNeedPage();
}
