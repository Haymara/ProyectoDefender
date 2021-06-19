/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.defender.controller.clases;

/**
 *
 * @author Haymara
 */
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class DragAndDrop {

    
	public void EstablishtTarget(ImageView _AND,AnchorPane Target) {
		Target.setOnDragOver(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				if (event.getGestureSource() != Target && event.getDragboard().hasImage() && event.getSceneX() > 100) {
					event.acceptTransferModes(TransferMode.COPY);

				}
				event.consume();
			}
		});
		Target.setOnDragDropped(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {

				if (event.getGestureSource() == _AND) {
					Image ANDimage = new Image("AND.png", 100, 100, true, true, true);
					ImageView _AND = new ImageView(ANDimage);
					_AND.setTranslateX(event.getX() - 55);
					_AND.setTranslateY(event.getY() - 25);
					Target.getChildren().add(_AND);
				}
				event.setDropCompleted(true);
				event.consume();
			}

		});
		_AND.setOnDragDetected(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				Dragboard db = _AND.startDragAndDrop(TransferMode.ANY);
				ClipboardContent content = new ClipboardContent();
				content.putImage(_AND.getImage());
				db.setContent(content);
				event.consume();
			}
		});
	}
}
