package dad.javafx.iniciosesionmvc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Controller {
		private Model model = new Model();
		private View view = new View();
		
		public Controller() {
			
			model.userProperty().bindBidirectional(view.getUserText().textProperty());
			
			model.passwordProperty().bindBidirectional(view.getPasswordText().textProperty());
			
			view.getAccederButton().setOnAction(e -> onAccederAction(e));
			view.getCancelarButton().setOnAction(e1 -> onCancelarAction(e1));
		}

		private void onCancelarAction(ActionEvent e1) {
			Platform.exit();
			
		}

		private void noEncontrado(String usuario) {
			Alert sad = new Alert(AlertType.ERROR);
			sad.setTitle("Iniciar sesion");
			sad.setHeaderText("Acceso denegado");
			sad.setContentText("El usuario y/o contraseña no son validos");
			
			sad.showAndWait();
			model.setUser("");
			model.setPassword("");
			
		}

		private void encontrado(String usuario) {
			Alert fine = new Alert(AlertType.INFORMATION);
			fine.setTitle("Iniciar sesion");
			fine.setHeaderText("Acceso permitido");
			fine.setContentText("Las credenciales de acceso son validas");
			
			fine.showAndWait();
			Platform.exit();
		}

		private void onAccederAction(ActionEvent e) {
			String usuario = model.getUser();
			String passNoCod = model.getPassword();
			boolean encontrado = false;
			
			String passCod = DigestUtils.md5Hex(passNoCod).toUpperCase();
			String line = "";
			String[] lineArr;
			
			FileReader reader;
			BufferedReader buffered;
			
			try {
				reader = new FileReader("users.csv");
				buffered = new BufferedReader(reader);
				
				while((line = buffered.readLine()) != null) {
					lineArr = line.split(",");
					if( lineArr[0].contentEquals(usuario) && lineArr[1].equals(passCod)) {
						encontrado = true;
						encontrado(usuario);
					}
				}
				if(!encontrado) {
					noEncontrado(usuario);
				}
				reader.close();
				buffered.close();
				
			}catch(IOException e1) {
				System.out.println("Fallo de lectura");
			}
			
		}
		public View getRoot() {
			return view;
		}
}
