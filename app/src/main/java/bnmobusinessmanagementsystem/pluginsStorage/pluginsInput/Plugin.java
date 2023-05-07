package bnmobusinessmanagementsystem.pluginsStorage.pluginsInput;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.function.Consumer;

public interface Plugin<T> {
    public String getName();
    public void run(T parameter);
    public boolean isNeedPage();
    public String getDBNeeded();
}
