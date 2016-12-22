/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author rafael
 */
public class FXMLVBoxMainController implements Initializable {
    
    @FXML
    private Menu menuCadastros;
    @FXML
    private MenuItem menuItemCadastroCliente;
    @FXML
    private MenuItem menuItemCadastroServicos;
    @FXML
    private Menu menuContratos;
    @FXML
    private MenuItem menuItemGerarContrato;
    @FXML
    private MenuItem menuItemBuscarContrato;

    
    @FXML
    private AnchorPane anchorPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    @FXML
    public void handleMenuCadastroCliente() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/finalproject/view/FXMLCadastroCliente.fxml"));
        anchorPane.getChildren().setAll(a) ;
    }
    
    @FXML
    public void handleMenuCadastroServico() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/finalproject/view/FXMLCadastroServico.fxml"));
        anchorPane.getChildren().setAll(a) ;
    }
    
    @FXML
    public void handleMenuGerarContrato() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/finalproject/view/FXMLGerarContrato.fxml"));
        anchorPane.getChildren().setAll(a) ;
    }
    
    @FXML
    public void handleMenuBuscarContrato() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/finalproject/view/FXMLBuscarContrato.fxml"));
        anchorPane.getChildren().setAll(a) ;
    }
    
}
