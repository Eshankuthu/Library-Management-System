package ui;

import business.ControllerInterface;
import business.SystemController;
import business.bookID;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CheckOutBookWindow extends Stage implements LibWindow{
	
    public static final CheckOutBookWindow INSTANCE = new CheckOutBookWindow();
	
	private boolean isInitialized = false;
	public boolean isInitialized() {
		return isInitialized;
	}
	public void isInitialized(boolean val) {
		isInitialized = val;
	}
	private TextArea ta;
	public void setData(String data) {
		ta.setText(data);
	}
	private CheckOutBookWindow() {}
	
	public void init() {
		
		GridPane grid = new GridPane();
		grid.setId("top-container");
		grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Text scenetitle = new Text("CheckOut Book");
        scenetitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20)); //Tahoma
        grid.add(scenetitle, 0, 0, 2, 1);

        Label memIDLbl = new Label("Member ID:");
        grid.add(memIDLbl, 0, 1);

        TextField idTextField = new TextField();
        grid.add(idTextField, 1, 1);

        Label isbnLbl = new Label("ISBN:");
        grid.add(isbnLbl, 2, 1);
        
        TextField isbnTextField=new TextField();
        grid.add(isbnTextField, 3, 1);
        
        Button srchBtn = new Button("Search");
        HBox clrBtn = new HBox(10);
        clrBtn.setAlignment(Pos.CENTER);
        clrBtn.getChildren().add(srchBtn);
        grid.add(clrBtn, 0, 4,3,1);
        
        
        srchBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				ControllerInterface c = new SystemController();

				try {
					
					TableView<bookID> table = new TableView<>();
					
					table.setEditable(false);
					
					table.setMaxHeight(100.0);
					
				}catch(Exception ex) {
					
				}
			}
        });
        
        grid.setGridLinesVisible(false) ;     
        Scene scene = new Scene(grid);
        scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
        setScene(scene);
        
	
	}

}
