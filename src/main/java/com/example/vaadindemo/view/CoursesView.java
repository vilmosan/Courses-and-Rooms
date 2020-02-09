package com.example.vaadindemo.view;

import com.example.vaadindemo.component.CourseForm;
import com.example.vaadindemo.entity.Course;
import com.example.vaadindemo.repository.CourseRepository;
import com.example.vaadindemo.util.RefreshAware;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route
public class CoursesView extends VerticalLayout implements RefreshAware {

    @Autowired
    private CourseRepository repository;
    @Autowired
    private CourseForm courseForm;
    private Grid<Course> grid;


    @PostConstruct
    private void init() {
        try {
            /*if (repository.findAll().isEmpty()) {
                for (int i = 0; i < 20; i++) {
                    Course course = new Course();
                    course.setName("Kurzus #" + i);
                    repository.save(course);
                }
            }*/

            grid = new Grid<>();
            grid.setItems(repository.findAll());
            grid.addColumn(Course::getId).setHeader("Id");
            grid.addColumn(Course::getName).setHeader("Name");
            grid.asSingleSelect().addValueChangeListener(e -> {
                if (e.getValue() != null) {
                    courseForm.initEdit(e.getValue().getId());
                }
            });
            Button newBtn = new Button("New", VaadinIcon.PLUS.create());
            newBtn.addClickListener(event -> courseForm.initSave());
            add(newBtn);
            add(grid);
            add(courseForm);
            courseForm.setRefreshAware(this);
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