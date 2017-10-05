package com.dc;

import Backend.MainSystem;
import com.vaadin.navigator.View;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

public class SignUpForm extends VerticalLayout implements View{

    MainSystem main = new MainSystem();//Connect to backend


    Label lblTitle;
    Label lblHeader;
    TextField tfID;
    TextField tfName;
    PasswordField tfPassword;
    DateField dfDOB;
    ComboBox cbType;
    Button btnConfirm;
    Button btnCancel;
    RadioButtonGroup<String> opGender;

    public SignUpForm(){

        //INITIAL SETUP
        setSpacing(true);
        setMargin(true);

        //UI COMPONENTS
        lblTitle = new Label("Sign Up Form");
        lblTitle.addStyleName("h1");
        addComponent(lblTitle);

        //Set up form
        FormLayout formLayout = new FormLayout();
        formLayout.setMargin(false);
        formLayout.setWidth("700");
        formLayout.addStyleName("light");
        addComponent(formLayout);

        lblHeader = new Label("Personal Information");
        lblHeader.addStyleName("h2");
        lblHeader.addStyleName("colored");
        formLayout.addComponent(lblHeader);

        tfID = new TextField("zID");
        tfID.setRequiredIndicatorVisible(true);
        formLayout.addComponent(tfID);

        tfName = new TextField ("Name");
        tfName.setRequiredIndicatorVisible(true);
        formLayout.addComponent(tfName);

        tfPassword = new PasswordField("Password");
        tfPassword.setRequiredIndicatorVisible(true);
        formLayout.addComponent(tfPassword);

        dfDOB = new DateField("Date of Birthday");
        dfDOB.setDateFormat("dd-MM-yyyy");
        dfDOB.setValue(LocalDate.now());
        formLayout.addComponent(dfDOB);



        opGender = new RadioButtonGroup<>("Gender");
        opGender.setItems("Male","Female");
        opGender.setStyleName("horizontal");
        formLayout.addComponent(opGender);

        //Create combobox with arraylist
        ArrayList<String> listType = new ArrayList<>();
        listType.add("Staff");
        listType.add("Student");

        cbType = new ComboBox("Type", listType);
        cbType.setRequiredIndicatorVisible(true);
        formLayout.addComponent(cbType);

        btnConfirm = new Button("Confirm");
        btnConfirm.addStyleName("primary");

        btnConfirm.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                //sign up user
                main.signUp(tfID.getValue(),tfName.getValue(), tfPassword.getValue(), cbType.getValue().toString(), Date.valueOf(dfDOB.getValue()), opGender.getCaption());

                //Show notification
                Notification.show("You signed up succesfully");

                //Navigate back to login screen
                getUI().getNavigator().navigateTo("login");
            }
        });

        btnCancel = new Button("Cancel");
        btnCancel.addStyleName("danger");

        btnCancel.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {


                //Navigate back to login
                getUI().getNavigator().navigateTo("login");
            }
        });

        //Horizontal footer for buttons
        HorizontalLayout footer = new HorizontalLayout();
        footer.setMargin(new MarginInfo(true, false, true, false));//set margin for top and bottom
        footer.setSpacing(true);
        footer.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        formLayout.addComponent(footer);
        footer.addComponent(btnConfirm);
        footer.addComponent(btnCancel);

    }
}
