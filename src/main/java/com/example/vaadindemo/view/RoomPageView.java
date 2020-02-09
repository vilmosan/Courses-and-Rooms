package com.example.vaadindemo.view;

import com.example.vaadindemo.entity.Course;
import com.example.vaadindemo.entity.Room;
import com.example.vaadindemo.repository.CourseRepository;
import com.example.vaadindemo.repository.RoomRepository;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.OrderedList;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;


@Route(value = "room/view")
public class RoomPageView extends VerticalLayout implements HasUrlParameter<String> {

    @Autowired
    private RoomRepository repository;
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void setParameter(BeforeEvent beforeEvent, String s) {
        try {
            Room room = repository.findById(Long.parseLong(s));

            add(new Label("Room: " + room.getName()));
            Grid<Course> grid=new Grid<>();
            grid.setItems(courseRepository.findAllByRoomId(room.getId()));
            grid.addColumn(Course::getId).setHeader("Id");
            grid.addColumn(Course::getName).setHeader("Name");
            add(grid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
