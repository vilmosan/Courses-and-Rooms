package com.example.vaadindemo.view;

import com.example.vaadindemo.component.AppMenuBar;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends AbstractView {

    public MainView() {
        initView();

        Text text=new Text("Helló Bálint!");
        add(text);

        Button btn = new Button();
        btn.setText("Kattants rám az 5-ösért...");
        btn.addClickListener(buttonClickEvent -> Notification.show("KARÓ!"));
        add(btn);

        Button btn2 = new Button();
        btn2.setText("Milka csokiért katt ide...");
        btn2.addClickListener(buttonClickEvent -> Notification.show("BROKKOLI!"));
        add(btn2);



    }
}