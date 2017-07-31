package ui;

import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

import business.ControllerInterface;
import business.LoginException;
import business.SystemController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

@SuppressWarnings("unused")

public class ControlWindow extends Stage implements LibWindow {

	public static final ControlWindow INSTANCE = new ControlWindow();

	private boolean isInitialized = false;

	public ControlWindow() {
	}

	public void init() {

		// this.getIcons().add(new
		// Image(getClass().getResourceAsStream("ui/library-icon.png")));
		this.setTitle("Control Window");

		VBox topContainer = new VBox();
		topContainer.setId("top-container");
		MenuBar mainMenu = new MenuBar();
		VBox imageHolder = new VBox();
		Image image = new Image("ui/32.jpg", 700, 600, false, false);

		ImageView iv = new ImageView();
		iv.setImage(image);
		imageHolder.getChildren().add(iv);
		imageHolder.setAlignment(Pos.CENTER);
		HBox splashBox = new HBox();
		Label splashLabel = new Label("Welcome" + SystemController.currentAuth.toString());
		// splashLabel.setFont(Font.font("Trajan Pro", FontWeight.BOLD, 30));
		// splashBox.getChildren().add(splashLabel);
		// splashBox.setAlignment(Pos.CENTER);

		topContainer.getChildren().add(mainMenu);
		topContainer.getChildren().add(splashBox);
		topContainer.getChildren().add(imageHolder);

		Menu optionsMenu = new Menu("File");
		MenuItem viewMember = new MenuItem("View Member");

		viewMember.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				// hideAllWindows();
				if (!AllMembersWindow.INSTANCE.isInitialized()) {
					AllMembersWindow.INSTANCE.init();
				}
				ControllerInterface ci = new SystemController();
				List<String> ids = ci.allMemberIds();
				Collections.sort(ids);
				System.out.println(ids);
				StringBuilder sb = new StringBuilder();
				for (String s : ids) {
					sb.append(s + "\n");
				}
				System.out.println(sb.toString());
				AllMembersWindow.INSTANCE.setData(sb.toString());
				AllMembersWindow.INSTANCE.show();
			}
		});

		MenuItem viewBook = new MenuItem("View Book");

		viewBook.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				// hideAllWindows();
				if (!AllBooksWindow.INSTANCE.isInitialized()) {
					AllBooksWindow.INSTANCE.init();
				}
				ControllerInterface ci = new SystemController();
				List<String> ids = ci.allBookIds();
				Collections.sort(ids);
				StringBuilder sb = new StringBuilder();
				for (String s : ids) {
					sb.append(s + "\n");
				}
				AllBooksWindow.INSTANCE.setData(sb.toString());
				AllBooksWindow.INSTANCE.show();
			}
		});

		optionsMenu.getItems().addAll(viewMember, viewBook);

		Menu addMenu = new Menu("Add");
		MenuItem addNewBook = new MenuItem("New Book");
		MenuItem addCopyBook = new MenuItem("Copy Book");

		addCopyBook.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				try {

					if (!NewCopyBook.INSTANCE.isInitialized()) {
						NewCopyBook.INSTANCE.init();
					}
					// ControlWindow.INSTANCE.clear();
					NewCopyBook.INSTANCE.show();

				} catch (Exception ex) {

					JOptionPane.showMessageDialog(null, ex.getMessage());
				}

			}
		});

		MenuItem addMember = new MenuItem("New Member");

		addMember.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				try {

					if (!AddLibraryMember.INSTANCE.isInitialized()) {
						AddLibraryMember.INSTANCE.init();
					}
					// ControlWindow.INSTANCE.clear();
					AddLibraryMember.INSTANCE.show();

				} catch (Exception ex) {

					JOptionPane.showMessageDialog(null, ex.getMessage());
				}

			}
		});
		if(LoginWindow.INSTANCE.getAuthorization()== "ADMIN") {

		addMenu.getItems().addAll(addNewBook, addCopyBook, addMember);
		}
		else {
			
		}

		Menu checkMenu = new Menu("Chek Menu");
		MenuItem checkIn = new MenuItem("Check In");
		MenuItem checkOut = new MenuItem("Check Out");
		
		checkOut.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				try {

					if (!CheckOutBookWindow.INSTANCE.isInitialized()) {
						CheckOutBookWindow.INSTANCE.init();
					}
					// ControlWindow.INSTANCE.clear();
					CheckOutBookWindow.INSTANCE.show();

				} catch (Exception ex) {

					JOptionPane.showMessageDialog(null, ex.getMessage());
				}

			}
		});

		checkMenu.getItems().addAll(checkIn, checkOut);

		Button backBtn = new Button("Log Out");
		
		backBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
			
				Start.primStage().show();
				ControlWindow.INSTANCE.close();
			}
		});

		GridPane grid = new GridPane();

		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		VBox hBack = new VBox(5);
		backBtn.setPrefWidth(200);
		//backBtn.setPrefWidth(100);
		hBack.setAlignment(Pos.CENTER);
		hBack.getChildren().add(backBtn);
		grid.add(hBack, 0, 1,2,2);

		mainMenu.getMenus().addAll(optionsMenu, addMenu, checkMenu);

		StackPane stackPane = new StackPane();
		stackPane.getChildren().addAll(imageHolder, hBack);
		
		topContainer.getChildren().add(stackPane);

		Scene scene = new Scene(topContainer, 700, 600);
		this.setScene(scene);

		scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
		this.show();
		// this.setAlwaysOnTop(true);

	}

	@Override
	public boolean isInitialized() {
		// TODO Auto-generated method stub
		return isInitialized;
	}

	@Override
	public void isInitialized(boolean val) {
		// TODO Auto-generated method stub
		isInitialized = val;
	}

}
