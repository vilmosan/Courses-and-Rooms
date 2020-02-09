package com.example.vaadindemo.view;

import com.example.vaadindemo.entity.Course;
import com.example.vaadindemo.repository.CourseRepository;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "course/view")
public class CoursePageView extends VerticalLayout implements HasUrlParameter<String> {

    @Autowired
    private CourseRepository repository;

    @Override
    public void setParameter(BeforeEvent beforeEvent, String s) {
        try {
            Course course = repository.findById(Long.parseLong(s));

            add(new Label("Course: "+course.getName()));
            if(course.getRoom()!=null){
                add(new Label("Room: "+course.getRoom().getName()));
                add(new Label("Floor: "+course.getRoom().getLevel()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}