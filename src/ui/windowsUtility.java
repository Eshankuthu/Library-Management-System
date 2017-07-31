package ui;

import javafx.stage.Stage;

public class windowsUtility {

	private static Stage[] allWindows = { 
			LoginWindow.INSTANCE,
			AllMembersWindow.INSTANCE,	
			AllBooksWindow.INSTANCE,
			ControlWindow.INSTANCE,
		};
		
		public static void hideAllWindows(Stage obj) {
			//primStage.hide();
			for(Stage st: allWindows) {
				if(st==obj)
				{}
				st.hide();
			}
		}
}

