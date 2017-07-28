package ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import business.AddLibraryMemberException;
import business.Address;
import business.LibraryMember;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddLibraryMember extends Stage implements LibWindow{
	
	public static final AddLibraryMember INSTANCE = new AddLibraryMember();
	
	List<LibraryMember> members = new ArrayList<LibraryMember>();
	
    private boolean isInitialized = false;
	
	public AddLibraryMember() {}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		//this.getIcons().add(new Image(getClass().getResourceAsStream("ui/library-icon.png")));
		this.setTitle("Add New Library Member");
		//JOptionPane.showMessageDialog(null, "i'm here");
		
		
		// Add the text boxes and fields
		
		GridPane grid = new GridPane();
        grid.setId("top-container");
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Add New Member");
        scenetitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20)); //Tahoma
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("Member ID:");
        grid.add(userName, 0, 1);

        TextField idTextField = new TextField();
        grid.add(idTextField, 1, 1);

        Label fnLbl = new Label("First Name:");
        grid.add(fnLbl, 2, 1);
        
        TextField fnTextField=new TextField();
        grid.add(fnTextField, 3, 1);
        
        Label lnLbl = new Label("Last Name:");
        grid.add(lnLbl, 4, 1);
        
        TextField lnTextField=new TextField();
        grid.add(lnTextField, 5, 1);
        
        //next grid line
        
        Label street = new Label("Street:");
        grid.add(street, 0, 2);

        TextField strtTextField = new TextField();
        grid.add(strtTextField, 1, 2);

        Label cityLbl = new Label("City:");
        grid.add(cityLbl, 2, 2);
        
        TextField cityTextField=new TextField();
        grid.add(cityTextField, 3, 2);
        
        Label zipLbl = new Label("Zip:");
        grid.add(zipLbl, 4, 2);
        
        TextField zipTextField=new TextField();
        grid.add(zipTextField, 5, 2);
        
        Label stateLbl = new Label("State:");
        grid.add(stateLbl, 0, 3);
        
        TextField stateTextField=new TextField();
        grid.add(stateTextField, 1, 3);
        
        //next grid line
        
        Label telLbl = new Label("Telephone:");
        grid.add(telLbl, 2, 3);
        
        TextField telTextField=new TextField();
        grid.add(telTextField, 3, 3);
        
        
        Button clearBtn = new Button("Clear");
        HBox clrBtn = new HBox(10);
        clrBtn.setAlignment(Pos.CENTER);
        clrBtn.getChildren().add(clearBtn);
        grid.add(clrBtn, 4, 5);
        
        clearBtn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		try {
        		
        			idTextField.setText("");
        			fnTextField.setText("");
        			lnTextField.setText("");
        			strtTextField.setText("");
        			cityTextField.setText("");
        			stateTextField.setText("");
        			zipTextField.setText("");
        			telTextField.setText("");
                
        		} catch(Exception ex) {
        			
        			JOptionPane.showMessageDialog(null, ex.getMessage());
        		}
        	   
        	}
        });
		
        
        Button submitBtn = new Button("Submit");
        HBox sbBtn = new HBox(10);
        sbBtn.setAlignment(Pos.CENTER);
        sbBtn.getChildren().add(submitBtn);
        grid.add(sbBtn, 5, 5);
        
        //region On submit the button saves it to the storage
        
        submitBtn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		try {
        		
        			List<Address> addresses = new ArrayList<Address>() {
        				
						{
							
        				add(new Address(strtTextField.getText().trim(),
        						cityTextField.getText().trim(), stateTextField.getText().trim()
        						, zipTextField.getText().trim()));
        			}
        		};
        			LibraryMember memberlibrary = new LibraryMember(idTextField.getText().trim()
        					,fnTextField.getText().trim(),lnTextField.getText().trim(),
        					telTextField.getText().trim(),addresses.get(0));
        			
        			DataAccess da=new DataAccessFacade();
        			
        			HashMap<String, LibraryMember> existingMembers=da.readMemberMap();
        			
        			members.addAll(existingMembers.values());
        			members.add(memberlibrary);
        			AddLibraryMemberException.validate(memberlibrary);
        			
        			
        			DataAccessFacade.loadMemberMap(members);
        			JOptionPane.showMessageDialog(null, "Successfully Inserted");
                
        		} catch(AddLibraryMemberException ex) {
        			
        			JOptionPane.showMessageDialog(null, ex.getMessage());
        		}
        	   
        	}
        });
		
        
        
        
        grid.setGridLinesVisible(false) ;
        
       
        Scene scene = new Scene(grid);
        scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
        setScene(scene);
        //this.setAlwaysOnTop(true);
		
	}

	
	
	@Override
	public boolean isInitialized() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void isInitialized(boolean val) {
		// TODO Auto-generated method stub
		
	}

}
