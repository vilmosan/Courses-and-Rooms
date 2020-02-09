package com.example.vaadindemo.component;

import com.example.vaadindemo.entity.Course;
import com.example.vaadindemo.entity.Room;
import com.example.vaadindemo.repository.CourseRepository;
import com.example.vaadindemo.repository.RoomRepository;
import com.example.vaadindemo.util.RefreshAware;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringComponent
@UIScope
public class CourseForm extends VerticalLayout {

    private Course course;

    @Autowired
    private CourseRepository repository;

    @Autowired
    private RoomRepository roomRepository;



    private Binder<Course> binder;

    private TextField name;
    private ComboBox<Room> room;
    private RefreshAware refreshAware;

    @PostConstruct
    private void init() {
        binder = new Binder<>(Course.class);

        name = new TextField("Course name:");

        room = new ComboBox<>();
        room.setLabel("Room");
        try {
            room.setItems(roomRepository.findAll());
            room.setItemLabelGenerator(Room::getName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        add(name, room);

        HorizontalLayout buttonBar = new HorizontalLayout();

        Button saveBtn = new Button("Save", VaadinIcon.PENCIL.create());
        saveBtn.addClickListener(event -> {
            try {
                if (course.getId() == null) {
                    repository.save(course);

                } else {
                    repository.update(course);

                }
                setVisible(false);
                refreshAware.processRefresh();
                Notification.show("Success!");
            } catch (Exception e) {
                Notification.show(":(");
                e.printStackTrace();
            }
        });

        Button deleteBtn = new Button("Delete", VaadinIcon.TRASH.create());
        deleteBtn.addClickListener(event -> {
            try {
                repository.delete(course.getId());
                setVisible(false);
                refreshAware.processRefresh();
                Notification.show("Success!");
            } catch (Exception e) {
                Notification.show(":(");
                e.printStackTrace();
            }
        });

        Button cancelBtn = new Button("Cancel", VaadinIcon.CLOSE.create());
        cancelBtn.addClickListener(event -> {
            course = null;
            setVisible(false);

        });

        Button viewPage = new Button("About");
        viewPage.addClickListener(e -> {
            UI.getCurrent().navigate("course/view/"+course.getId());
        });
        add(viewPage);

        buttonBar.add(saveBtn, deleteBtn, cancelBtn, viewPage);
        add(buttonBar);
        setVisible(false);
        binder.bindInstanceFields(this);
    }

    public void initSave() {
        course = new Course();
        binder.setBean(course);
        setVisible(true);
    }

    public void initEdit(Long id) {

        try {
            this.course = repository.findById(id);
            binder.setBean(course);
            setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setRefreshAware(RefreshAware refreshAware) {
        this.refreshAware = refreshAware;
    }
}