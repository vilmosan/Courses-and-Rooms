package com.example.vaadindemo.component;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class AppMenuBar extends HorizontalLayout {

    public AppMenuBar()
    {
        buildAnchor("Main", "/");
        buildAnchor("Course List", "/courses");
        buildAnchor("Room List", "/rooms");
    }

    private void buildAnchor(String text, String href)
    {
        Anchor anchor = new Anchor();
        anchor.setText(text);
        anchor.setHref(href);
        add(anchor);
    }
}
