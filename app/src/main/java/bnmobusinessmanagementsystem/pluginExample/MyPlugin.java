package bnmobusinessmanagementsystem.pluginExample;

public class MyPlugin implements Plugin {
    public String getName() {
        return "My Plugin";
    }

    public void run() {
        System.out.println("Hello from My Plugin!");
    }
}
