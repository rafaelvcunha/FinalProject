/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject.controller;

import finalproject.model.Servico;
import finalproject.service.ServicoService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author rafael
 */
public class FXMLCadastroServicoController implements Initializable {

    @FXML
    private TableView<Servico> tblServicos; 
    @FXML
    private TableColumn<Servico, String> clCodigo;
    @FXML
    private TableColumn<Servico, String> clServico;
    @FXML
    private TableColumn<Servico, String> clDescricao;
    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtServico;
    @FXML
    private TextField txtDescricao;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnAtualizar;
    @FXML
    private Button btnApagar;
    @FXML
    private Button btnLimpar;

    private ServicoService service;
            
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        service = ServicoService.getNewInstance();
        configuraColunas();
        configuraBindings();
        atualizaDadosTabela();
    }  

    public void salvar() {
	Servico s = new Servico();
	pegaValores(s);
	service.salvar(s);
	atualizaDadosTabela();
    }

    public void atualizar() {
	Servico s = tblServicos.getSelectionModel().getSelectedItem();
	pegaValores(s);
	service.atualizar(s);
	atualizaDadosTabela();
    }    

    public void apagar() {
	Servico c = tblServicos.getSelectionModel().getSelectedItem();
	service.apagar(c.getIdServico());
	atualizaDadosTabela();
    }    

    public void limpar() {
    	tblServicos.getSelectionModel().select(null);
	txtCodigo.setText("");
	txtServico.setText("");
        txtDescricao.setText("");

    }        

    private void pegaValores(Servico s) {
        s.setCodigo(txtCodigo.getText());
	s.setServico(txtServico.getText());
        s.setDescricao(txtDescricao.getText());
    }

    private void atualizaDadosTabela() {
        tblServicos.getItems().setAll(service.buscarTodas());
    }    
    
    private void configuraColunas() {
       clCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
       clServico.setCellValueFactory(new PropertyValueFactory<>("servico"));
       clDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
    }
    
    private void configuraBindings() {
	// esse binding só e false quando os campos da tela estão preenchidos
	BooleanBinding camposPreenchidos = 
                        txtCodigo.textProperty().isEmpty().
                        or(txtServico.textProperty().isEmpty()).
                        or(txtDescricao.textProperty().isEmpty())
                        ;
	// indica se há algo selecionado na tabela
	BooleanBinding algoSelecionado = tblServicos.getSelectionModel().selectedItemProperty().isNull();
	// alguns botões só são habilitados se algo foi selecionado na tabela
	btnApagar.disableProperty().bind(algoSelecionado);
	btnAtualizar.disableProperty().bind(algoSelecionado);
	btnLimpar.disableProperty().bind(algoSelecionado);
	// o botão salvar só é habilitado se as informações foram preenchidas e não tem nada na tela
	btnSalvar.disableProperty().bind(algoSelecionado.not().or(camposPreenchidos));
	// quando algo é selecionado na tabela, preenchemos os campos de entrada com os valores para o 
	// usuário editar
	tblServicos.getSelectionModel().selectedItemProperty().addListener((b, o, n) -> {
		if (n != null) {
                       	txtCodigo.setText(n.getCodigo());
                        txtServico.setText(n.getServico());
                        txtDescricao.setText(n.getDescricao());
		}
	});
    }    
}
