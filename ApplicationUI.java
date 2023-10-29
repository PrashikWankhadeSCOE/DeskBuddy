
import java.net.InetAddress;
import java.net.UnknownHostException;

import ClientEnd.StartClient;
import Server.Start;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ApplicationUI extends Application {
    private HostServices hostServices;
    public static Stage pStage = null;

    @Override
    public void init() {
        hostServices = getHostServices();
    }

    @Override
    public void start(Stage primaryStage) {
        pStage = primaryStage;
        Image backgroundImage = new Image("Deskbuddy1.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);


        Image rotatingImage = new Image("deskbuddyrobo.png");
        ImageView rotatingImageView = new ImageView(rotatingImage);
        rotatingImageView.setRotate(30);
        rotatingImageView.setRotationAxis(Rotate.Y_AXIS);

        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(5), rotatingImageView);
        rotateTransition.setByAngle(360);
        rotateTransition.setCycleCount(RotateTransition.INDEFINITE);
        rotateTransition.setAutoReverse(false);
        rotateTransition.play();

        StackPane root = new StackPane(backgroundImageView, rotatingImageView);
        StackPane.setMargin(rotatingImageView, new Insets(300, 900, 300, 200));
        //declaration of buttons and set their size and coordinates
        Button button1 = new Button("Client");
        Button button2 = new Button("Server");
        Button button3 = new Button("About Us");
        button1.setMinSize(250, 60);
        button2.setMinSize(250, 60);
        button3.setMinSize(80, 40);
        button3.setOpacity(10);
        StackPane.setMargin(button1, new Insets(550, -300, 150, 750));
        StackPane.setMargin(button2, new Insets(550, 100, 150, 1200));
        StackPane.setMargin(button3, new Insets(700, 650, 50, 70));

        //add a new stackpane on button click
        button3.setTranslateX(-300);
        TranslateTransition transition3 = new TranslateTransition(Duration.seconds(1.5), button3);
        transition3.setToX(1);
        transition3.play();

        button3.setOnAction(event -> {

            Stage aboutUsStage = new Stage();
            aboutUsStage.setTitle("About Us");
            Image aboutUsImage = new Image("AboutUs.png");
            ImageView aboutUsImageView = new ImageView(aboutUsImage);
        
            aboutUsImageView.setFitWidth(850);
            aboutUsImageView.setFitHeight(600);
        
            Button backButton = new Button("Back");
            backButton.setOnAction(e -> aboutUsStage.close());
        
            HBox bottomBox = new HBox(backButton);
            bottomBox.setAlignment(Pos.CENTER);
        
            VBox aboutUsLayout = new VBox();
            aboutUsLayout.getChildren().addAll(aboutUsImageView, bottomBox);
        
            aboutUsLayout.setSpacing(0); 
            aboutUsLayout.setPadding(new Insets(-25));
            Scene aboutUsScene = new Scene(aboutUsLayout, 800, 600);
            
            aboutUsStage.setScene(aboutUsScene);
            aboutUsStage.show();
            
        });

        //Core2web logoooooooooo
        
         Image image2 = new Image("c2w.png");
        ImageView core = new ImageView(image2);
        core.setX(100);
        core.setY(500);
        core.setFitHeight(110);
        core.setFitWidth(350);
        core.setTranslateX(-480);
        core.setTranslateY(-290);

        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setSaturation(0.7);
        core.setEffect(colorAdjust);

        Image logoImage1 = new Image("youtube.png");
        ImageView logoImageView1 = new ImageView(logoImage1);
        Button youtubeButton = createImageButton(logoImageView1, 100, 80);
        StackPane.setMargin(youtubeButton, new Insets(700, 850, 50, 70));

        logoImageView1.setTranslateX(-300);        
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1.5), logoImageView1);
        transition.setToX(1); 
        transition.play();

        Image logoImage2 = new Image("instagram.png"); 
        ImageView logoImageView2 = new ImageView(logoImage2);
        Button instagramButton = createImageButton(logoImageView2,100,80);
        StackPane.setMargin(instagramButton, new Insets(700, 950, 50, 10));

        logoImageView2.setTranslateX(-300);
        TranslateTransition transition2 = new TranslateTransition(Duration.seconds(1.5), logoImageView2);
        transition2.setToX(1);
        transition2.play();

        // Set actions for both buttons to open the Instagram & youtube link
        youtubeButton.setOnAction(event -> hostServices.showDocument("https://www.youtube.com/results?search_query=core2web"));
        instagramButton.setOnAction(event -> hostServices.showDocument("https://instagram.com/core2web?igshid=MzRlODBiNWFlZA=="));
        button1.setOnAction(e ->{
            StartClient.connectionClient();
            //primaryStage.close();
        });
        button2.setOnAction(e ->{
            Start.connectionUI();
            try{
                InetAddress localhost = InetAddress.getLocalHost();
                System.out.println("IP Address : "+ localhost.getHostAddress());
            }catch(UnknownHostException ie){
                ie.printStackTrace();
            }
        });
        root.getChildren().addAll(core,button1, button2, button3, youtubeButton, instagramButton);
        Scene scene = new Scene(root, backgroundImage.getWidth(), backgroundImage.getHeight());

        primaryStage.setTitle("Desk Buddy");
        primaryStage.getIcons().add(new Image("deskbuddyrobo.png"));
        primaryStage.setScene(scene);
        primaryStage.show();

        slideIn(button1, Duration.seconds(1), -100, -400);
        slideIn(button2, Duration.seconds(1), 400, -100);
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void slideIn(Button button, Duration duration, double fromX, double toX) {
        TranslateTransition slideTransition = new TranslateTransition(duration, button);
        slideTransition.setFromX(fromX);
        slideTransition.setToX(toX);
        slideTransition.play();
    }

    private Button createImageButton(ImageView imageView, double width, double height) {
        Button imageButton = new Button();
        imageButton.setGraphic(imageView);
        imageButton.setMinSize(width, height);
        imageButton.setStyle("-fx-background-color: transparent");
        return imageButton;
    }
}
