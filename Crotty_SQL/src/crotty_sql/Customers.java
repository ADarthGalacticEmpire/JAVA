package crotty_sql;
//Amanda Crotty #2262124

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


public class Customers extends Application{
	
	private Connection connect = null;
	
	//create objects for retrieval by user
	private CheckBox cbCustid = new CheckBox();
	private CheckBox cbName = new CheckBox();
	private CheckBox cbAddress = new CheckBox();
	private CheckBox cbCity = new CheckBox();
	private CheckBox cbState = new CheckBox();
	private CheckBox cbZip = new CheckBox();
	
	//where clause
	private TextField tfWhere =  new TextField();
	
	//Output Area
	private TextArea taResult = new TextArea();
	
	//create search, and clear buttons
	private Button btSearch = new Button("Search");
	private Button btClear = new Button("Clear");
	
	//Window for user interface
	GridPane dbSearch = new GridPane();
	
	@Override
	public void start(Stage primaryStage){
		
		//activation to sql connection
		connect();
		
		//GUI settings for search window
		Scene dbScene = new Scene( dbSearch, 800, 600);
		primaryStage.setTitle("Customer Table Search");
		primaryStage.setScene(dbScene);
		primaryStage.show();
		
		dbSearch.setPadding(new Insets(40,40,40,40));
		dbSearch.setVgap(25);
		
		//text above checks
		dbSearch.add(new Label("Customer ID"), 0, 1);
		dbSearch.add(new Label("Name"), 1, 1);
		dbSearch.add(new Label("Address"), 2, 1);
		dbSearch.add(new Label("City"), 3, 1);
		dbSearch.add(new Label("State"), 4, 1);
		dbSearch.add(new Label("Zip Code"), 5, 1);
		
		//checks
		dbSearch.add(cbCustid, 0, 2);
		dbSearch.add(cbName, 1, 2);
		dbSearch.add(cbAddress, 2, 2);
		dbSearch.add(cbCity, 3, 2);
		dbSearch.add(cbState, 4, 2);
		dbSearch.add(cbZip, 5, 2);
		Insets cbInsets = new Insets(10,10,10,10);
		cbCustid.setPadding(cbInsets);
		cbName.setPadding(cbInsets);
		cbAddress.setPadding(cbInsets);
		cbCity.setPadding(cbInsets);
		cbState.setPadding(cbInsets);
		cbZip.setPadding(cbInsets);
		
		dbSearch.add(new Label("Where : "), 0, 3);
		dbSearch.add(tfWhere, 1, 3);
		
		dbSearch.add(taResult, 1, 4);
		
		dbSearch.add(btSearch, 1, 5);
		dbSearch.add(btClear, 2, 5);
		//btSearch.setAlignment(Pos.BOTTOM_LEFT);
		//btClear.setAlignment(Pos.CENTER_RIGHT);

		
		btSearch.setOnAction( e -> {
			
		StringBuilder queryString = new StringBuilder();		
		queryString.length();
		
		//TODO
		//column counter
		
		 
		try {
			
			java.sql.Statement st = connect.createStatement();
			
			int count = 0;

			 if (cbCustid.isSelected()) {
				 queryString.append("custid");
				 count++;
			 }
			 
			 if(cbName.isSelected()) {
				 if(queryString.length() > 0) {
					 queryString.append(",");
					 count++;
				 }
				 queryString.append("name");
			 }
			 
			 if (cbAddress.isSelected()) {
				 if(queryString.length() > 0) {
					 queryString.append(",");
					 count++;
				 }
				 queryString.append("address");
			 }
			 
			 if (cbCity.isSelected()) {
				 if(queryString.length() > 0) {
					 queryString.append(",");
					 count++;
				 }
				 queryString.append("city");
			 }
			 
			 if (cbState.isSelected()) {
				 if(queryString.length() > 0) {
					 queryString.append(",");
					 count++;
				 }
				 queryString.append("state");
			 }
			 
			 if (cbZip.isSelected()) {
				 if(queryString.length() > 0) {
					 queryString.append(",");
					 count++;
				 }
				 queryString.append("zip");
			 }
			 
			 queryString.insert(0,"select ");
			 
			 queryString.append(" from customers");
			 
			 if (tfWhere.getText().length() > 0)
			 {
				 queryString.append(" where ");
				 queryString.append(tfWhere.getText());
			 }
			 
			 queryString.append(";");
			 
			 System.out.println(queryString);
			 java.sql.ResultSet rSet = st.executeQuery(queryString.toString());
			 
			 //return queryString.toString(); 
			 
			 //TODO
			 //handle the TextArea();
			 while (rSet.next()) {
				for (int i = 1; i <= count; i++) {
					taResult.appendText(rSet.getString(i) + " ");
					}
				taResult.appendText("\n");
			 }
			 
			 
			taResult.setPrefColumnCount(200);
			
			//taResult.clear(); //from any previous result
			
			
		}
		
		catch (SQLException ex) {
			// handle the error
			System.out.println("SQL Error: " + ex);
		}

		});
		
		//clear action
		btClear.setOnAction(e -> {
			
			taResult.clear();
			//uncheck the boxes
			
		});	
		
	}//end start
	

	
	//TODO
	private boolean connect() {
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/pcparts", "root", "sesame");
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	

	public static void main(String[] args) {
		launch(args);
	}
}
