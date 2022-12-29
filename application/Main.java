package application;
	
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sun.prism.paint.Color;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	static final String path = "src/";
	static final String airportsAllFileName = path + "airports.txt";
	static final File airportsAllFile = new File(airportsAllFileName);

	protected Button btnAirportInfo;
	protected Button btnDistanceBetween;
	protected Button btnAirportClosestTo;
	protected Button btnAirportsWithin;
	protected Button btnAirportsSorted;
	protected Label lbl, label1;
	protected TextField txfName, distanceField;
	protected TextArea txaMessage;
	protected ListView<String> airportList;
	protected ListView<String> airportList1;
	protected RadioButton rbCity, rbState;
	protected ToggleGroup compChoice;
	private Pane buildOptionsButtons() {
		btnAirportInfo = new Button("Airport info");
		btnAirportInfo.setOnAction(new infoEventHandler());
		
		
		btnDistanceBetween = new Button("Distance between");
		btnDistanceBetween.setOnAction(new DistanceEventHandler());

		btnAirportClosestTo = new Button("Airport Closest to");
		btnAirportClosestTo.setOnAction(new ClosestToEventHandler());

		btnAirportsWithin = new Button("Airports Within");
		btnAirportsWithin.setOnAction(new WithinEventHandler());


		btnAirportsSorted = new Button("Airports Sorted");
		compChoice = new ToggleGroup();
		rbCity= new RadioButton("City");
		rbCity.setToggleGroup(compChoice);
		
		rbState= new RadioButton("State");
		rbState.setToggleGroup(compChoice);
		btnAirportsSorted.setOnAction(new SortedEventHandler());
		
		VBox vBoxName = new VBox();
		vBoxName.getStyleClass().add("h_or_v_box");
		vBoxName.getChildren().addAll(btnAirportInfo, btnDistanceBetween, btnAirportClosestTo, btnAirportsWithin, btnAirportsSorted, rbCity, rbState);

		return vBoxName;
	}
	private Pane buildAirportList() {
		airportList = new ListView<>();
		airportList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		airportList.getItems().addAll(getList());
		airportList.setPrefHeight(150);
		airportList.setPrefWidth(120);
		
		airportList1 = new ListView<>();
		airportList1.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		airportList1.getItems().addAll(getList());
		airportList1.setPrefHeight(150);
		airportList1.setPrefWidth(120);
		
		
		
		HBox hBox = new HBox();
		hBox.getStyleClass().add("h_or_v_box");	
		hBox.getChildren().addAll(airportList,airportList1);
		return hBox;
	}
	private Pane buildUpperLeft() {
		VBox vBox = new VBox();
		vBox.getStyleClass().add("h_or_v_box");
		vBox.getChildren().addAll(buildOptionsButtons());

		vBox.setPrefSize(200, 50);
		return vBox;
	}
	private Pane buildUpperRight() {
		VBox vBox = new VBox();
		label1 = new Label("Airport list");
		vBox.getStyleClass().add("h_or_v_box");
		vBox.getChildren().addAll(buildAirportList());
	
		return vBox;
	}
	private Pane buildGui() {
		GridPane root = new GridPane();
		
		Pane p = buildUpperLeft();
		root.add(p, 0, 1);
		
		Pane p2 = buildUpperRight();
		root.add(p2, 1, 1);

		// Create other controls
		txaMessage = new TextArea();
		txaMessage.setPrefColumnCount(30);
	    txaMessage.setPrefRowCount(7);
		txaMessage.setEditable(false);
		root.add(txaMessage, 0, 3);
		
		label1 = new Label("Distance:");
		distanceField = new TextField();
		HBox hb = new HBox();
		hb.getChildren().addAll(label1, distanceField);
		hb.setSpacing(10);
		root.add(hb, 0, 2);
		return root;
	}
	@Override
	public void start(Stage primaryStage) {
		try {
			// Create root container for controls
			Pane root = buildGui();
			// Add GridPane (root) to Scene.
			Scene scene = new Scene(root,700,450);		// Set the title for the window
			primaryStage.setTitle("Airport");
			// Code to display the Gui
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();



		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	 public List<String> getList(){
		 String AirportCode = "";
			Map<String,Airport> airports = AirportLoader.getAirportMap(airportsAllFile);
			List<String> codes = new ArrayList<>(airports.keySet());
			return codes;
	 }
	private class infoEventHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			AirportManager manager = AirportManagerTest.getAirportManager(airportsAllFile);
			Airport a = new Airport();
			List<String> allCodes = airportList.getSelectionModel().getSelectedItems();
			for(String code : allCodes) {
				a = manager.getAirport(code);
	
		}
			
			txaMessage.setText("the airport with code " + a.getCode() + " is " + a.getCity() + " Airport "
					+ "\nin the state of " + a.getState() + " with coordinates Lat: "+ a.getLatitude()+ " and lon: " + a.getLongitude());
	}
		
}
	private class DistanceEventHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			AirportManager manager = AirportManagerTest.getAirportManager(airportsAllFile);
			double a;
			List<String> allCodes = airportList.getSelectionModel().getSelectedItems();
			List<String> allCodes1 = airportList1.getSelectionModel().getSelectedItems();

			for(String code : allCodes) {
				for(String code1 : allCodes1) {
				a = (int)manager.getDistanceBetween(code, code1);
				txaMessage.setText("the distance between " + code + " and " + code1 + " is: " + a + " miles");
				
		}
	}
			
}
		}
	private class ClosestToEventHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			AirportManager manager = AirportManagerTest.getAirportManager(airportsAllFile);
			Airport a = new Airport();
			List<String> allCodes = airportList.getSelectionModel().getSelectedItems();
			for(String code : allCodes) {
				a = manager.getAirportClosestTo(code);
	
		}
			txaMessage.setText("\n"+ a.toString());
	}
		
	}
	private class SortedEventHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			
			RadioButton rad = (RadioButton)compChoice.getSelectedToggle();
			String choice = rad.getText();
			Map<String,Airport> airportMap = AirportLoader.getAirportMap(airportsAllFile);
			AirportManager manager = AirportManagerTest.getAirportManager(airportsAllFile);
			List<Airport> a = new ArrayList<>();
			List<String> allCodes = airportList.getSelectionModel().getSelectedItems();
			switch(choice) {
			case "City" : 
				for(String code : allCodes) {
					Airport b = airportMap.get(code);
					a = manager.getAirportsSortedBy(b.getCity());
					txaMessage.setText(a.toString());}
				break;
				
			case "State" : 	
				for(Airport p : airportMap.values()) {
					if(p.getCode().equals(allCodes.get(0))) {
						a = manager.getAirportsSortedBy(p.getState());
						txaMessage.setText(a.toString());
					}
				
					}
				break;
			}
		
			
			
			
			
		}
		
		}
		
	private class WithinEventHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			AirportManager manager = AirportManagerTest.getAirportManager(airportsAllFile);

			String s = distanceField.getText();
			List<String> allCodes = airportList.getSelectionModel().getSelectedItems();
			List<String> allCodes1 = airportList1.getSelectionModel().getSelectedItems();
			double distance = Integer.parseInt(s);
			for(String code : allCodes) {
				for(String code1 : allCodes1) {
				List<Airport>  a = manager.getAirportsWithin(code, code1, distance);	
				txaMessage.setText(a.toString());
		}
			
			}}
		


	}}
