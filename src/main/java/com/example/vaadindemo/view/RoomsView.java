package com.example.vaadindemo.view;

import com.example.vaadindemo.component.RoomForm;
import com.example.vaadindemo.entity.Room;
import com.example.vaadindemo.repository.RoomRepository;
import com.example.vaadindemo.util.RefreshAware;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route
public class RoomsView extends VerticalLayout implements RefreshAware {

    @Autowired
    private RoomRepository repository;
    @Autowired
    private RoomForm roomForm;
    private Grid<Room> grid;

    @PostConstruct
    private void init() {
        try {
            grid = new Grid<>();
            grid.setItems(repository.findAll());
            grid.addColumn(Room::getId).setHeader("Id");
            grid.addColumn(Room::getName).setHeader("Name");
            grid.addColumn(Room::getLevel).setHeader("Floor");

            grid.asSingleSelect().addValueChangeListener(e -> {
                if (e.getValue() != null) {
                    roomForm.initEdit(e.getValue().getId());
                }
            });
            Button newBtn = new Button("New", VaadinIcon.PLUS.create());
            newBtn.addClickListener(event -> roomForm.initSave());
            add(newBtn);
            add(grid);
            add(roomForm);
            roomForm.setRefreshAware(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void processRefresh() {
        try {
            grid.setItems(repository.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}