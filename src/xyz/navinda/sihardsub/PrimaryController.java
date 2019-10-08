package xyz.navinda.sihardsub;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import java.nio.file.Paths;

public class PrimaryController {
	private String videoFile, subFile, outputPath = "";
	private String siHardSubPath = Paths.get(".").toAbsolutePath().normalize().toString() + "/scripts/si_hardsub";
	public TextField txtVidFile = null;
	public TextField txtSubFile = null;
	public TextField txtOutPath = null;
	public TextField txtSiHardsubPath = null;
	public TextArea txtOutLog = null;
	public Label lblStatus = null;
	
	
	@FXML
	public void btnSelectVideoClick(Event e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Open File");
		File file = chooser.showOpenDialog(null);

		if (file != null) {
			videoFile = file.getAbsolutePath();
			txtVidFile.setText(videoFile);
			outputPath = file.getParent() + "/";
			txtOutPath.setText(outputPath);
		}
	}

	@FXML
	public void btnSelectSubClick(Event e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Open File");
		File file = chooser.showOpenDialog(null);
		if (file != null) {
			subFile = file.getAbsolutePath();
			txtSubFile.setText(subFile);
		}

	}

	@FXML
	public void btnSelectOutPathClick(Event e) {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle("Open File");
		File selectedDirectory = directoryChooser.showDialog(null);
		if (selectedDirectory != null) {
			outputPath = selectedDirectory.getAbsolutePath() + "/";
			txtOutPath.setText(outputPath);
		}
	}

	@FXML
	public void btnStartClick(Event e) {
		lblStatus.setText("Running.");
		Thread FFMpeg = new Thread(() -> {
			String[] arguments = new String[] { siHardSubPath, "-i", videoFile, "-s", subFile, "-o",
					outputPath };
			try {
				Process proc = new ProcessBuilder(arguments).redirectErrorStream(true).start();

				BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));

				String line = "";

				while ((line = reader.readLine()) != null) {
					final String ln = line;
					Platform.runLater(() -> txtOutLog.appendText(ln + "\n"));
				}

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} finally {
				Platform.runLater(() -> {
					lblStatus.setText("Stopped.");
				});
			}
		});

		FFMpeg.start();
	}
	
	@FXML
	public void btnSetSiHardsubClick(Event e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Open File");
		File file = chooser.showOpenDialog(null);
		if (file != null) {
			siHardSubPath = file.getAbsolutePath();
			txtSiHardsubPath.setText(siHardSubPath);
		}
	}
}