/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dashboard;

import Main.Utility;
import static Main.Utility.conn;
import static Main.Utility.username;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ktouf
 */
public class DashboardController implements Initializable {

    @FXML
    private ImageView imgUser;
    @FXML
    private Button btnChangePic;
    @FXML
    private Label lblName;
    @FXML
    private Label lblUsername;
    @FXML
    private Label lblMail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblName.setText("Name: " + Utility.name);
        lblUsername.setText("Username: " + Utility.username);
        lblMail.setText("Mail: " + Utility.mail);
        imgUser.setImage(Utility.getImage(Utility.username, 200, 200));
    }

    @FXML
    private void btnChangePicOnAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog((Stage)((Node)event.getSource()).getScene().getWindow());
        try {
            FileInputStream imgObj = new FileInputStream(selectedFile);
            Statement delete = Utility.conn.createStatement();
            delete.executeUpdate("delete from `profilepic` where `username` = '" + username + "'");
            String query = "INSERT INTO `profilepic`(`username`, `userImg`) VALUES (?,?)";
            PreparedStatement ps = Utility.conn.prepareStatement(query);
            ps.setString(1, Utility.username);
            ps.setBlob(2, imgObj);
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Connection Error");
        } catch (FileNotFoundException ex) {
            System.out.println("FILE NOT FOUND");
        }
        imgUser.setImage(Utility.getImage(Utility.username, 200, 200));
    }
    
}
