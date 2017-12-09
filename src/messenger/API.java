package messenger;

import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.ExecutorCompletionService;

public class API {

    private static Scene msgr;
    private static MessengerController messengerController;

    private static Stage stage;

    public static int x;
    public static int y;
    public static int width = 0;
    public static int length = 0;
    public static String cssStyle;

    private static String destination;
    private static String origin;
    public static String IT = "";

    private String filePath = "/sample/UI/Icons/";



    //@Override
    public void start() throws Exception{

        Stage primaryStage = new Stage();
        primaryStage.initStyle(StageStyle.UNDECORATED);

        FXMLLoader startLoader = new FXMLLoader(getClass().getResource("MessengerUI.fxml"));
        Parent Start = startLoader.load();
        messengerController = startLoader.getController();
        //messengerController.setAPIController(this);
        msgr = new Scene(Start);

        //Parent root = FXMLLoader.load(getClass().getResource("MessengerUI.fxml"));
        primaryStage.setTitle("MessegerAPI");
        primaryStage.setScene(msgr);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.initOwner(stage);
        primaryStage.showAndWait();



    }

    public static void setDestination(String place){
        destination = place;
    }


    public static String getDestination(){
        String place = destination;
        return place;
    }

    public static void setOrigin(String place){
        origin = place;
    }

    public static String getOrigin(){
        String place = origin;
        return place;
    }


    /*public static void genErrorScreen(){
        stage.setScene(genError);
        stage.centerOnScreen();
    }*/

    /*public void run(String[] args) {
        //launch(args);
        try{
            start();
        } catch (Exception e){
            System.out.println("api error : " + e.getMessage());
        }
    }*/

    public void run(int xcoord, int ycoord, int windowWidth, int windowLength, String
            cssPath, String destNodeID, String originNodeID) throws ServiceException{
        if(xcoord >= 0  &&  ycoord >= 0  &&  windowWidth >= 0  && windowLength >= 0) {

            x = xcoord;
            y = ycoord;
            width = windowWidth;
            length = windowLength;
            cssStyle = cssPath;

            setOrigin(originNodeID);
            setDestination(destNodeID);
        }

        else throw new ServiceException();

        try{
            start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void closePopUp(JFXButton back){
        stage = (Stage) back.getScene().getWindow();
        stage.close();
    }
    public void run(int xcoord, int ycoord, int windowWidth, int windowLength, String
            cssPath, String destNodeID, String originNodeID, String it) throws ServiceException{
        if(xcoord >= 0  &&  ycoord >= 0  &&  windowWidth >= 0  && windowLength >= 0) {

            x = xcoord;
            y = ycoord;
            width = windowWidth;
            length = windowLength;
            cssStyle = cssPath;

            setOrigin(originNodeID);
            setDestination(destNodeID);
            //setIT(originNodeID);
            IT = it;
        }

        else throw new ServiceException();
        try{
            start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
