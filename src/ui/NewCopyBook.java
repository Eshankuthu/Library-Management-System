package ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//import java.util.List;

import javax.swing.JOptionPane;

import business.Author;
import business.Book;
import business.ControllerInterface;
import business.LibraryMember;
//import business.LoginException;
import business.NoSuchBookException;
import business.SystemController;
import business.bookID;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NewCopyBook extends Stage implements LibWindow {

	public static final NewCopyBook INSTANCE = new NewCopyBook();

	private boolean isInitialized = false;

	private Scene scene = null;
	private final ObservableList<bookID> tvObservableList = FXCollections.observableArrayList();
	private TableColumn<bookID, String> isbnCol;
	private TableColumn<bookID, String> booktitleCol;

	public NewCopyBook() {
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		//this.getIcons().add(new Image(getClass().getResourceAsStream("ui/library-icon.png")));
		
		this.setTitle("Add a New Copy Book");

		GridPane grid = new GridPane();
		grid.setId("top-container");
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("Search Book by ISBN");
		scenetitle.setFont(Font.font("Harlow Solid", FontWeight.NORMAL, 20)); // Tahoma
		grid.add(scenetitle, 0, 0, 2, 1);

		Label userName = new Label("ISBN:");
		grid.add(userName, 0, 1);

		TextField idTextField = new TextField();
		grid.add(idTextField, 1, 1);

		Button srchBtn = new Button("Search");
		HBox clrBtn = new HBox(10);
		clrBtn.setAlignment(Pos.CENTER);
		clrBtn.getChildren().add(srchBtn);
		grid.add(clrBtn, 2, 1,1,1);

		srchBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				ControllerInterface c = new SystemController();

				try {
					
					TableView<bookID> table = new TableView<>();
					
					table.setEditable(false);
					
					table.setMaxHeight(100.0);
					
					
					String title = c.bookID(idTextField.getText().trim());
					
					bookID bID = new bookID(idTextField.getText().trim(), title);
					//JOptionPane.showMessageDialog(null, bID.getId() +"-"+bID.getName());
					tvObservableList.addAll(bID);
					table.setItems(tvObservableList);
					
					
					isbnCol = new TableColumn<bookID, String>("ISBN");
					isbnCol.setCellValueFactory(new PropertyValueFactory<bookID,String>("id"));
					
					booktitleCol = new TableColumn<bookID, String>("Book Title");
					booktitleCol.setCellValueFactory(new PropertyValueFactory<bookID,String>("booktitle"));
					
					table.getColumns().addAll(isbnCol,booktitleCol);
					
					final VBox vbox = new VBox();
					vbox.setSpacing(5);
					vbox.setPadding(new Insets(10, 0, 0, 10));
					vbox.getChildren().addAll(table);
					grid.add(vbox, 1, 2);
					
					
					
					
					// Add new button that creates copy 
					
					Button cpyBtn = new Button("Copy Book");
					HBox clrBtn = new HBox(10);
					cpyBtn.setAlignment(Pos.CENTER);
					clrBtn.getChildren().add(cpyBtn);
					grid.add(clrBtn, 1, 3,3,1);
					
				// on click the button reads the already saved books in file add the new copy into it
					cpyBtn.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent e) {
							SystemController c = new SystemController();

							try {
								
								List<Book> retval = new ArrayList<Book>();
								List<Book> bookList = new ArrayList<Book>();
								
								retval =c.allBookIds(idTextField.getText().trim());
								
								System.out.println(retval.get(0).getIsbn());
								
								DataAccess da=new DataAccessFacade();
			        			
			        			HashMap<String, Book> existingBooks=da.readBooksMap();
			        			
			        					
			        			bookList.addAll(existingBooks.values());
			        			       			
			        			
			        			bookList.addAll(retval);
								System.out.println( bookList.size());
								
								
								DataAccessFacade.loadBookMap(bookList);
													
								
								JOptionPane.showMessageDialog(null, "Successfully made copy");
									
								
							}catch(Exception ex) {
								
								JOptionPane.showMessageDialog(null, ex.getMessage());
							}
						}
					});
					

				} catch (NoSuchBookException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}

			}
		});

//		Label invisible = new Label("");
//		grid.add(invisible, 0, 20,10,1);

		 scene = new Scene(grid,450,400);
		
		scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
		setScene(scene);

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

