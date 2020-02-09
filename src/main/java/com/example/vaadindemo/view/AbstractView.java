package com.example.vaadindemo.view;

import com.example.vaadindemo.component.AppMenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public abstract class AbstractView extends VerticalLayout {

    public void initView()
    {
        add(new AppMenuBar());
    }

}
