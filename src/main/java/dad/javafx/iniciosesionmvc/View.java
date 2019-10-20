package dad.javafx.iniciosesionmvc;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class View extends GridPane {
	private Label userLabel;
	private Label passwordLabel;
	private TextField userText;
	private PasswordField passwordText;
	private Button accederButton;
	private Button cancelarButton;
	
	public View() {
		super();
		userLabel = new Label("Usuario: ");
		
		passwordLabel = new Label("Contraseña: ");
		
		userText = new TextField();
		userText.setPromptText("Usuario");
		
		passwordText = new PasswordField();
		userText.setPromptText("Contraseña");
		
		accederButton = new Button("Acceder");
		accederButton.setMaxWidth(Double.MAX_VALUE);
		accederButton.setDefaultButton(true);
		
		cancelarButton = new Button("Cancelar");
		cancelarButton.setMaxWidth(Double.MAX_VALUE);
		
		HBox buttons = new HBox(5, accederButton, cancelarButton);
		buttons.setAlignment(Pos.CENTER);
		
		this.setPadding(new Insets(20));
		this.setHgap(5);
		this.setVgap(5);
		this.addRow(0, userLabel, userText);
		this.addRow(1, passwordLabel, passwordText);
		this.addRow(2, buttons);
		
		GridPane.setRowSpan(buttons, 2);
		GridPane.setFillWidth(userText, true);
		GridPane.setFillWidth(passwordText, true);
	}
	public TextField getUserText() {
		return userText;
	}

	public PasswordField getPasswordText() {
		return passwordText;
	}

	public Button getAccederButton() {
		return accederButton;
	}

	public Button getCancelarButton() {
		return cancelarButton;
	}
	
}
