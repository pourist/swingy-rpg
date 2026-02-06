package com.pourist.swingy.view;

public class ViewProvider {

    private View currentView;

    public ViewProvider(View initialView) {
        if (initialView == null) {
            throw new IllegalArgumentException("Initial view cannot be null");
        }
        this.currentView = initialView;
    }

    public View get() {
        return currentView;
    }

    public void set(View newView) {
        if (newView == null) {
            throw new IllegalArgumentException("View cannot be null");
        }
        this.currentView = newView;
    }
}
