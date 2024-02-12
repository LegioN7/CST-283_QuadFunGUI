package org.example.quadfungui;

// Delta College - CST 283 - Homework 2
// Name:

// Adjust the program to complete y = ax^2 + bx + c
// Change the m and b fields to a, b, and c
// Add a clear button to clear the fields

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class QuadFunGUI extends Application
{
    private TextField aField;
    private TextField bField;
    private TextField xField;
    private TextField cField;
    private Label functionLabel;

    public static void main(String[] args)
    {
        // Launch the application.
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        // Initialize label objects
        Label funFormLabel = new Label("Function:  y = ax^2 + bx + c");


        // Define and organize function input fields
        Label aLabel = new Label("a");
        aField = new TextField();
        Label bLabel = new Label("b");
        bField = new TextField();
        Label cLabel = new Label("c");
        cField = new TextField();
        HBox aBox = new HBox(10, aLabel, aField);
        HBox bBox = new HBox(10, bLabel,bField);
        HBox cBox = new HBox(10, cLabel,cField);
        HBox funBox = new HBox(30,aBox,bBox,cBox);
        funBox.setAlignment(Pos.CENTER);

        // Define and organize x input fields
        Label xValueLabel = new Label("x");
        xField = new TextField();
        HBox xValueBox = new HBox(10, xValueLabel,xField);
        xValueBox.setAlignment(Pos.CENTER);

        // Define calculate button and register button event handler
        Button calcYbutton = new Button("Calculate");
        calcYbutton.setOnAction(new ConvertButtonClickHandler());

        // Define the clear button and register button event handler
        Button clearButton = new Button("Clear");
        clearButton.setOnAction(new clearButtonClickHandler());

        // Combine buttons in horizontal box.
        HBox buttonBox  = new HBox(10, calcYbutton, clearButton);
        buttonBox.setAlignment(Pos.CENTER);

        // Set up function and y labels - will be "set" via button event listener
        functionLabel = new Label();
        HBox functionBox  = new HBox(functionLabel);
        functionBox.setAlignment(Pos.CENTER);

        // Combine all horizontal rows of interface components in a primary vertical box.
        VBox mainBox = new VBox(10, funFormLabel,funBox,xValueBox,functionLabel,buttonBox);
        mainBox.setAlignment(Pos.CENTER);

        // Set up overall scene
        Scene scene = new Scene(mainBox, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Linear Function Calculator");
        primaryStage.show();
    }

    // Handle "Calculate" button click event.
    class ConvertButtonClickHandler implements EventHandler<ActionEvent>
    {
        // Function:  y = ax^2 + bx + c"
        @Override
        public void handle(ActionEvent event)
        {
            double aValue=0, bValue=0, cValue=0, xValue=0, yValue=0;

            // Get inputs from text fields
            String aSlope = aField.getText();
            String bIntercept = bField.getText();
            String cIntercept = cField.getText();
            String xVariable = xField.getText();

            boolean inputOK = true;

            // Parse inputs and perform input number format check
            try
            {
                aValue = Double.parseDouble(aSlope);
                bValue = Double.parseDouble(bIntercept);
                cValue = Double.parseDouble(cIntercept);
                xValue = Double.parseDouble(xVariable);
            }
            catch (NumberFormatException e)
            {
                inputOK = false;
            }

            // Perform calculations.  Set values in fields.
            if (inputOK)
            {
                yValue = aValue * Math.pow(xValue, 2) + bValue * xValue + cValue;
                functionLabel.setText(Double.toString(yValue) + " = " +
                        Double.toString(aValue) + "(" +Double.toString(xValue) + ")^2 + " +
                        Double.toString(bValue) + "(" +Double.toString(xValue) + ") + " +
                        Double.toString(cValue));
            }
            else   // If any errors,
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Input Error.");
                alert.setContentText("Please check input values.");
                alert.showAndWait();
            }
        }
    }

    class clearButtonClickHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
            aField.clear();
            bField.clear();
            xField.clear();
            cField.clear();
            functionLabel.setText("");
        }
    }
}