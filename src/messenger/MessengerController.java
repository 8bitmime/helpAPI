package messenger;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import javax.sip.*;

import javafx.scene.image.Image;

import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.TooManyListenersException;

public class MessengerController implements Initializable, MessageProcessor{




    @FXML
    private JFXListView chatListView;
    @FXML
    private JFXTextField toField, questionAsked;
    @FXML
    private javafx.scene.control.Label fromLabel;
    @FXML
    private JFXButton sendAsk, backButton;

    Image senderImg = new Image(getClass().getResourceAsStream("/messenger/person-outline-filled.png"));
    Image reciveImg = new Image(getClass().getResourceAsStream("/messenger/person-solid.png"));

    public ObservableList currentChat = FXCollections.observableArrayList();

    private API APIController;

    String ip = InetAddress.getLocalHost().getHostAddress();

    SipLayer sip; //= new SipLayer("TEST", ip, 6000);

    public MessengerController() throws InvalidArgumentException,
            TransportNotSupportedException, TooManyListenersException,
            PeerUnavailableException, ObjectInUseException, UnknownHostException {
        if(sip == null){
            sip = new SipLayer("TEST", ip, 6000);
        }

    }

    //Purpose: to initialize the fxml
    @Override
    public void initialize(URL location, ResourceBundle resources){
        chatListView.setItems(currentChat);
        fromLabel.setText("sip:" + sip.getUsername() + "@" + sip.getHost() + ":" + sip.getPort());
        sip.setMessageProcessor(this);
        toField.setText(API.IT);
        //sip.startIM("TEST", 1010);


        //System.out.println("address: " + sip.getAddr());
        //fromLabel.setText(sip.getAddr());
    }

    //getters and setters
    public void setAPIController(API in){
        APIController = in;
    }

    public ObservableList<String> getCurrentChat(){
        return currentChat;
    }

    public JFXListView getChatListView() {
        return chatListView;
    }

    //other functions

    @FXML
    public void sendQuestion() throws ParseException, SipException, InvalidArgumentException {
        String question = questionAsked.getText();
        questionAsked.setText("");
        HBox hbox = new HBox();
        javafx.scene.image.ImageView img = new javafx.scene.image.ImageView();
        img.setImage(senderImg);
        img.setFitHeight(25);
        img.setFitWidth(25);
        Label label = new Label();
        label.setText(question);
        hbox.getChildren().addAll(label, img);
        hbox.setAlignment(Pos.TOP_RIGHT);
        chatListView.getItems().add(hbox);
        sip.sendMessage(toField.getText(), question);
        //call answering part?

    }

    public void back(){
        sip.end();
        API.closePopUp(backButton);
        clear();
    }

    public void clear(){
        toField.setText("");
        questionAsked.setText("");
        chatListView.getItems().clear();
    }

    //send button
    public void send() throws ParseException, SipException, InvalidArgumentException {
        sip.sendMessage(fromLabel.getText(), questionAsked.getText());
    }

    @Override
    public void processMessage(String sender, String message) {
        questionAsked.setText("");
        HBox hbox = new HBox();
        javafx.scene.image.ImageView img = new javafx.scene.image.ImageView();
        img.setImage(reciveImg);
        img.setFitHeight(25);
        img.setFitWidth(25);
        Label label = new Label();
        label.setText(message);
        hbox.getChildren().addAll(img, label);
        hbox.setAlignment(Pos.TOP_LEFT);
        chatListView.getItems().add(hbox);

        /*HBox hbox = new HBox();
        Label label = new Label();
        javafx.scene.image.ImageView img = new javafx.scene.image.ImageView();
        img.setImage(reciveImg);
        img.setFitHeight(25);
        img.setFitWidth(25);
        label.setText(message);
        hbox.getChildren().addAll(img, label);
        hbox.setAlignment(Pos.TOP_LEFT);
        chatListView.getItems().add(hbox);*/
    }

    @Override
    public void processError(String errorMessage) {
        System.out.println(errorMessage);
    }

    @Override
    public void processInfo(String infoMessage) {
        System.out.println(infoMessage);
    }

    public void setBackButton(){
        sip.end();
    }

}
