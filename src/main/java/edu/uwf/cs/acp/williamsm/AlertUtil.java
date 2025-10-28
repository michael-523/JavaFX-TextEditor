package edu.uwf.cs.acp.williamsm;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

public class AlertUtil
{
	private static Alert createAlert(Alert.AlertType alertType, String title,
			String header, String content)
	{
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		
		return alert;
	}
	
	private static void showAlertWithOkButton(Alert.AlertType alertType, String title,
			String header, String content)
	{
		Alert alert = createAlert(alertType, title, header, content);
		ButtonType okButton = new ButtonType("OK", ButtonData.OK_DONE);
		alert.getButtonTypes().setAll(okButton);
		alert.showAndWait();
	}
	
	public static void showInfoAlertWithOkButton(String title, String header, String content)
	{
		showAlertWithOkButton(Alert.AlertType.INFORMATION, title, header, content);
	}
	
	public static void showErrorAlertWithOkButton(String title, String header, String content)
	{
		showAlertWithOkButton(Alert.AlertType.ERROR, title, header, content);
	}
}
