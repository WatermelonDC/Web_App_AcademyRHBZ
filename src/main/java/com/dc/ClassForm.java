package com.dc;

import Backend.MainSystem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;

public class ClassForm extends VerticalLayout implements View{

    Label lblTitle;
    Label lblSection;
    TextField tfClassID;
    TextField tfClassName;
    Button btnConfirm;

    FormLayout form;
    HorizontalLayout footer;

    MainSystem main = new MainSystem();

    public ClassForm(){

        //initial setup
        setSpacing(true);
        setMargin(true);

        //UI Components
        lblTitle = new Label("Add Class");
        lblTitle.addStyleName("h1");
        addComponent(lblTitle);

        form = new FormLayout();
        form.setMargin(false);
        form.setWidth("900");
        form.addStyleName("light");
        addComponent(form);

        lblSection = new Label("Class Information");
        lblSection.addStyleName("h2");
        lblSection.addStyleName("colored");
        form.addComponent(lblSection);

        tfClassID = new TextField("Class ID");
        tfClassID.setRequiredIndicatorVisible(true);
        form.addComponent(tfClassID);

        tfClassName = new TextField("Class Name");
        tfClassName.setRequiredIndicatorVisible(true);
        form.addComponent(tfClassName);

        btnConfirm = new Button("Confirm");
        btnConfirm.addStyleName("primary");
        btnConfirm.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                //add class to teacher
                main.addClassToTeacher(tfClassID.getValue(),tfClassName.getValue());
                Notification.show("Class Added");

                //reset UI
                tfClassID.setValue("");
                tfClassName.setValue("");

            }
        });

        //horizontal layout for button
        footer = new HorizontalLayout();
        footer.setMargin(new MarginInfo(true,false,true,false));
        footer.setSpacing(true);
        footer.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        form.addComponent(footer);
        footer.addComponent(btnConfirm);


    }

    public void enter(ViewChangeListener.ViewChangeEvent event){

    }
}
