package com.koushikdutta.widgets;

import com.koushikdutta.widgets.ActivityBaseFragment.ActivityBaseFragmentListener;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;



public class ActivityBase extends FragmentActivity implements ActivityBaseFragmentListener {
    Class<? extends ActivityBaseFragment> clazz;
    public ActivityBase(Class<? extends ActivityBaseFragment> clazz) {
        super();
        this.clazz = clazz;
    }
    
    public ActivityBase() {
        super();
        this.clazz = ActivityBaseFragment.class;
    }
    
    public ActivityBaseFragment getFragment() {
        return fragment;        
    }
    
    public View getView() {
        return fragment.getView();
    }
    
    ActivityBaseFragment fragment;
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        try {
            fragment = (ActivityBaseFragment)clazz.getConstructors()[0].newInstance();
            fragment.setArguments(getIntent().getExtras());
            fragment.setListener(this);
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, fragment).commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState, View view) {
    }
    

    protected ListItem addItem(int sectionName, ListItem item) {
        return getFragment().addItem(getString(sectionName), item);
    }

    protected ListItem addItem(int sectionName, ListItem item, int index) {
        return getFragment().addItem(getString(sectionName), item, index);
    }
    
    protected ListItem addItem(String sectionName, ListItem item) {
        return getFragment().addItem(sectionName, item, -1);
    }
    
    public void setEmpty(int res) {
        getFragment().setEmpty(res);
    }
}
