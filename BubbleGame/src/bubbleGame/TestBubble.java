package bubbleGame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TestBubble extends Application{
	double start = System.currentTimeMillis();
	public static void main(String[] args) {
		Application.launch(args);

	}

	@Override
	public void start(Stage s) throws Exception {
		Image img = new Image("back.jpg");
		ImageView imgV = new ImageView(img);
		Pane pane = new Pane();
		pane.getChildren().add(imgV);
		
		
		
		Label l = new Label("Use your mouse to POP these bubbles!\n\n");
		l.setFont(Font.font("Bizzak",FontWeight.BOLD,FontPosture.ITALIC,30));
		l.setTextFill(Color.ORANGE);
		StackPane label = new StackPane();
		label.setPadding(new Insets(20));
		label.getChildren().add(l);
		
		Circle []circles = new Circle[32];
		Line []lines = new Line[4];
		PathTransition []paths = new PathTransition[32];
		int x1 = 20,y1=150,	x2 = 600,y2=150;
		//Creating lines
		for(int j=0;j<4;j++)
		{
			lines[j] = new Line(x1,y1,x2,y2);
			y1+=50; y2+=50;
		}
		int j=0;
		//Creating circles + move them on lines
		for(int i =0 ; i<32 ; i++)
			{
			circles[i] = new Circle(20);
			circles[i].setFill(Color.color(Math.random(), Math.random(), Math.random()).brighter());
			}
		for(int i =0 ; i<16; i++)
		{
		double d =(Math.random()*100)%4;
		paths[i] = new PathTransition();
		paths[i].setPath(lines[i%4]);
		paths[i].setNode(circles[i]);
		paths[i].setAutoReverse(true);
		paths[i].setDuration(Duration.seconds(d+1));
		paths[i].setCycleCount(Timeline.INDEFINITE);
		paths[i].play();
		}
		//Bubbles on Circles & Rectangle
		Circle c2 = new Circle(100,400,70);
		Circle c3 = new Circle(500,400,70);
		Rectangle r = new Rectangle(210,330,180,60);
		for(int i =16 ; i<22; i++)
		{
		double d =(Math.random()*100)%4;
		paths[i] = new PathTransition();
		paths[i].setPath(c2);
		paths[i].setNode(circles[i]);
		paths[i].setAutoReverse(true);
		paths[i].setDuration(Duration.seconds(d+1));
		paths[i].setCycleCount(Timeline.INDEFINITE);
		paths[i].play();
		}
		for(int i =22 ; i<28; i++)
		{
		double d =(Math.random()*100)%4;
		paths[i] = new PathTransition();
		paths[i].setPath(c3);
		paths[i].setNode(circles[i]);
		paths[i].setAutoReverse(true);
		paths[i].setDuration(Duration.seconds(d+1));
		paths[i].setCycleCount(Timeline.INDEFINITE);
		paths[i].play();
		}
		for(int i =28 ; i<32; i++)
		{
		double d =(Math.random()*100)%4;
		paths[i] = new PathTransition();
		paths[i].setPath(r);
		paths[i].setNode(circles[i]);
//		paths[i].setAutoReverse(true);
		paths[i].setDuration(Duration.seconds(d+1));
		paths[i].setCycleCount(Timeline.INDEFINITE);
		paths[i].play();
		}
		
		
		pane.getChildren().addAll(label);
		
		//Actions
		int i=0;
		for(i =0 ; i<32 ; i++)
		{int z=i;
			circles[i].setOnMousePressed(e->{
				
				if(e.getButton() == MouseButton.SECONDARY || e.getButton() == MouseButton.PRIMARY)
				{
					pane.getChildren().remove(circles[z]); 
					if(pane.getChildren().size()==2)
					{
						String ss = "" + timeCalculator(System.currentTimeMillis());
						Label time = new Label("\n\tYour time : "+ss+" Seconds\t\t \n\n");
						time.setFont(Font.font("Bizzak",FontWeight.BOLD,FontPosture.ITALIC,30));
						time.setTextFill(Color.TOMATO);
						Stage s2 = new Stage();
						Scene c = new Scene(time);
						s2.setScene(c);
						s2.show();
						s2.setTitle("Result");
						s2.setResizable(false);
						
					}
				}
				
			});
		}
		
//		for(int k=0;k<4;k++)
//		{
//			pane.getChildren().add(lines[k]);
//			
//		}
		for(i =0 ; i<32 ; i++)
		{
			pane.getChildren().add(circles[i]);
		}
		Scene scene = new Scene(pane,600,400);
		s.setScene(scene);
		s.show();
		s.setTitle("Bubbles v1.0");
		s.setResizable(false);
	}
	
	public int timeCalculator(double time){
		int result = (int) (time - start);
		result /= 1000;
		return result;
	}

}
