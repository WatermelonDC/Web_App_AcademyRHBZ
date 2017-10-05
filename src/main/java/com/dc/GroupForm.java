package com.dc;

import Backend.Class;
import Backend.MainSystem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;

import java.util.ArrayList;

public class GroupForm extends VerticalLayout implements View {

    MainSystem main = new MainSystem();

    //Layouts
    FormLayout formMain;
    HorizontalLayout footer;

    //UI Components
    Label lblTitle;
    Label lblSelection;
    ComboBox cbClassID;
    TextField tfGroupID;
    Button btnConfirm;

    public GroupForm(){

        //Inital Setup
        setSpacing(true);
        setMargin(true);

        //Title
        lblTitle = new Label("Join Group");
        lblTitle.setStyleName("h1");
        addComponent(lblTitle);

        //main form layout
        formMain = new FormLayout();
        formMain.setMargin(false);
        formMain.setWidth("900");
        formMain.addStyleName("light");
        addComponent(formMain);

        lblSelection = new Label("Class Information");
        lblSelection.addStyleName("h2");
        lblSelection.addStyleName("colored");
        formMain.addComponent(lblSelection);

        //DATA load - Backend
        ArrayList<Backend.Class> classList = main.getAllClasses();
        ArrayList<String> classNameList = new ArrayList<>();
        for(Backend.Class class1: classList){
            classNameList.add(class1.getClassID());
        }

        //Add data to combobox
        cbClassID = new ComboBox("Class ID", classNameList);
        cbClassID.setEmptySelectionAllowed(false);
        formMain.addComponent(cbClassID);

        tfGroupID = new TextField("Group ID");
        tfGroupID.setRequiredIndicatorVisible(true);
        formMain.addComponent(tfGroupID);

        btnConfirm = new Button("Confirm");
        btnConfirm.addStyleName("primary");
        btnConfirm.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                //add student to group
                main.addStudentToGroup(tfGroupID.getValue(), cbClassID.getValue().toString());

                //reset textfield
                tfGroupID.setValue("");

                Notification.show("Group Joined");

            }
        });

        formMain.addComponent(btnConfirm);

        //Footer Layout
        footer = new HorizontalLayout();
        footer.setMargin(new MarginInfo(true,false,true,false));
        footer.setSpacing(true);
        footer.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        footer.addComponent(btnConfirm);
        formMain.addComponent(footer);


    }

    public void enter(ViewChangeListener.ViewChangeEvent event){

    }
}
