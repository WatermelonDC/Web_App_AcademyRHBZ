package com.dc;

import Backend.MainSystem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

import java.util.Date;

public class Login extends VerticalLayout implements View {

    MainSystem main = new MainSystem();//link to backend

    TextField tfID;
    PasswordField tfPassword;
    Button btnSignUp;
    Button btnLogin;

    public Login(){

        //UI Elements
        tfID = new TextField("zID");
        tfPassword = new PasswordField("Password");

        //Add horizontal layout for buttons
        HorizontalLayout HLayout = new HorizontalLayout();

        //Hardcode LOGIN
        Date date = new Date();
        main.signUp("z1","John Smith","z1","Student",date,"Male");
        main.signUp("z2","Bob Willians","z2","Student",date,"Male");
        main.signUp("z3","Michael Cahalane","z3","Teacher",date,"Male");
        System.out.println(date);

        //UI Elements - Buttons
        btnLogin = new Button("Login");
        btnLogin.addStyleName("friendly");


        btnLogin.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if(main.Login(tfID.getValue(),tfPassword.getValue())){
                    //logged succesfully
                    getUI().getNavigator().navigateTo("dashboard");

                    //reset UI Component
                    tfID.setValue("");
                    tfPassword.setValue("");
                }else {
                    //incorrect login notification
                    Notification.show("Incorrect Login Details!",Notification.Type.ERROR_MESSAGE);
                }
            }
        });

        btnSignUp = new Button("Sign Up");
        btnSignUp.addStyleName("primary");

        //Navigation
        btnSignUp.addClickListener(new Button.ClickListener(){
            public void buttonClick(Button.ClickEvent event){
            getUI().getNavigator().navigateTo("signup");

            //reset UI Components
            tfID.setValue("");
            tfPassword.setValue("");
            }
        });

        HLayout.addComponent(btnLogin);
        HLayout.addComponent(btnSignUp);

        HLayout.setSpacing(true);

        //Form Layout
        FormLayout formLayout = new FormLayout(tfID,tfPassword, HLayout);
        formLayout.setMargin(true);

        //Panel
        Panel loginPanel = new Panel("Peer Preview System", formLayout);
        loginPanel.setWidth("450");
        loginPanel.setHeight("250");




        //Add Component
        addComponent(loginPanel);
        setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
        setHeight("100%");
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        throw new UnsupportedOperationException("not supported yet");
    }
}
