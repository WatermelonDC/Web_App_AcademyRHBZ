package com.dc;

import Backend.MainSystem;
import com.vaadin.data.HasValue;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import javafx.beans.property.Property;

import java.util.ArrayList;


public class RatingForm extends VerticalLayout implements View{

    MainSystem main = new MainSystem();//link to backend
    //UI Components
    Grid gridRatings;
    Label lblTitle;
    Label lblSection;
    Button btnConfirm;
    ComboBox cbClassID;
    ComboBox cbGroupID;

    //Data - backend
    ArrayList<Backend.Class> classList;
    ArrayList<Backend.Group> groupList;
    Backend.Group chosenGroup;
    Backend.Class chosenClass;

    //Forms
    FormLayout formMain;
    HorizontalLayout footerTable;

    public RatingForm(){
        //Inital setup
        setSpacing(true);
        setMargin(true);

        //UI Components
        lblTitle = new Label("Review Team");
        lblTitle.addStyleName("h1");
        addComponent(lblTitle);

        formMain = new FormLayout();
        formMain.setMargin(false);
        formMain.setWidth("900");
        formMain.addStyleName("light");
        addComponent(formMain);

        lblSection = new Label("Tean Details");
        lblSection.addStyleName("h2");
        lblSection.addStyleName("colored");
        formMain.addComponent(lblSection);

        //Class list
        classList = main.getClassesStudentBelongsTo();
        ArrayList<String> classNameList = new ArrayList<>();
        for(Backend.Class class1: classList){
            classNameList.add(class1.getClassID());
        }

        //add data to combo box
        cbClassID = new ComboBox("ClassID", classNameList);
        cbClassID.setEmptySelectionAllowed(false);

        formMain.addComponent(cbClassID);








    }


    public void valueChange (HasValue.ValueChangeEvent event) {
        chosenClass = main.getClassFromID((String) cbClassID.getValue());
        groupList = main.getGroupsFromClassListAndStudent(classList, (String) cbClassID.getValue());
        for (Backend.Group group : groupList) {

            //add all groups within the selected class to the group combobox
            cbGroupID.setItems(group.getGroupID());
        }
    }


    public void enter(ViewChangeListener.ViewChangeEvent event){

    }
}
