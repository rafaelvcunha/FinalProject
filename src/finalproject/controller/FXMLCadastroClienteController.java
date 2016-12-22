/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject.controller;

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

import finalproject.model.Cliente;
import finalproject.service.ClienteService;


/**
 * FXML Controller class
 *
 * @author rafael
 */
public class FXMLCadastroClienteController implements Initializable {
    
    @FXML
    private TableView<Cliente> tblClientes; 
    @FXML
    private TableColumn<Cliente, String> clNome;
    @FXML
    private TableColumn<Cliente, String> clSobreNome;
    @FXML
    private TableColumn<Cliente, String> clCpf;
    @FXML
    private TableColumn<Cliente, String> clLogradouro;
    @FXML
    private TableColumn<Cliente, String> clNumero;
    @FXML
    private TableColumn<Cliente, String> clBairro;
    @FXML
    private TableColumn<Cliente, String> clCep;
    @FXML
    private TableColumn<Cliente, String> clEstado;
    @FXML
    private TableColumn<Cliente, String> clPais;
    
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtSobreNome;
    @FXML
    private TextField txtCpf;
    @FXML
    private TextField txtLogradouro;
    @FXML
    private TextField txtNumero;
    @FXML
    private TextField txtBairro;
    @FXML
    private TextField txtCep;
    @FXML
    private TextField txtEstado;
    @FXML
    private TextField txtPais;
    
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnAtualizar;
    @FXML
    private Button btnApagar;
    @FXML
    private Button btnLimpar;

    private ClienteService service;
    
              
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    //public void initialize(URL location, ResourceBundle resources) {
        service = ClienteService.getNewInstance();
        configuraColunas();
        configuraBindings();
        atualizaDadosTabela();
    }

    // métodos públicos chamados quando o botão é clicado
	
    public void salvar() {
	Cliente c = new Cliente();
	pegaValores(c);
	service.salvar(c);
	atualizaDadosTabela();
    }

    public void atualizar() {
	Cliente c = tblClientes.getSelectionModel().getSelectedItem();
	pegaValores(c);
	service.atualizar(c);
	atualizaDadosTabela();
	}

    public void apagar() {
	Cliente c = tblClientes.getSelectionModel().getSelectedItem();
	service.apagar(c.getId());
	atualizaDadosTabela();
	}

    public void limpar() {
    	tblClientes.getSelectionModel().select(null);
	txtNome.setText("");
	txtSobreNome.setText("");
        txtCpf.setText("");
        txtLogradouro.setText("");
        txtNumero.setText("");
        txtBairro.setText("");
        txtCep.setText("");
        txtEstado.setText("");
        txtPais.setText("");

    }    

    // métodos privados do controller
    // pega os valores entrados pelo usuário e adiciona no objeto cliente
    
    private void pegaValores(Cliente c) {
        c.setNome(txtNome.getText());
	c.setSobreNome(txtSobreNome.getText());
        c.setCpf(txtCpf.getText());
        c.setLogradouro(txtLogradouro.getText());
        c.setNumero(txtNumero.getText());
        c.setBairro(txtBairro.getText());
        c.setCep(txtCep.getText());
        c.setEstado(txtEstado.getText());
        c.setPais(txtPais.getText());
    }
    
    // chamado quando acontece alguma operação de atualização dos dados
    private void atualizaDadosTabela() {
        tblClientes.getItems().setAll(service.buscarTodas());
    }
    
    // configura as colunas para mostrar as propriedades da classe Conta
    private void configuraColunas() {
       clNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
       clSobreNome.setCellValueFactory(new PropertyValueFactory<>("sobreNome"));
       clCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
       clLogradouro.setCellValueFactory(new PropertyValueFactory<>("logradouro"));
       clNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
       clBairro.setCellValueFactory(new PropertyValueFactory<>("bairro"));
       clCep.setCellValueFactory(new PropertyValueFactory<>("cep"));
       clEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
       clPais.setCellValueFactory(new PropertyValueFactory<>("pais"));
              
    }

    // configura a lógica da tela
    private void configuraBindings() {
	// esse binding só e false quando os campos da tela estão preenchidos
	BooleanBinding camposPreenchidos = 
                        txtNome.textProperty().isEmpty().
                        or(txtSobreNome.textProperty().isEmpty()).
                        or(txtCpf.textProperty().isEmpty()).
                        or(txtLogradouro.textProperty().isEmpty()).
                        or(txtNumero.textProperty().isEmpty()).
                        or(txtBairro.textProperty().isEmpty()).
                        or(txtCep.textProperty().isEmpty()).
                        or(txtEstado.textProperty().isEmpty()).
                        or(txtPais.textProperty().isEmpty())
                        ;
	// indica se há algo selecionado na tabela
	BooleanBinding algoSelecionado = tblClientes.getSelectionModel().selectedItemProperty().isNull();
	// alguns botões só são habilitados se algo foi selecionado na tabela
	btnApagar.disableProperty().bind(algoSelecionado);
	btnAtualizar.disableProperty().bind(algoSelecionado);
	btnLimpar.disableProperty().bind(algoSelecionado);
	// o botão salvar só é habilitado se as informações foram preenchidas e não tem nada na tela
	btnSalvar.disableProperty().bind(algoSelecionado.not().or(camposPreenchidos));
	// quando algo é selecionado na tabela, preenchemos os campos de entrada com os valores para o 
	// usuário editar
	tblClientes.getSelectionModel().selectedItemProperty().addListener((b, o, n) -> {
		if (n != null) {
                       	txtNome.setText(n.getNome());
                        txtSobreNome.setText(n.getSobreNome());
                        txtCpf.setText(n.getCpf());
                        txtLogradouro.setText(n.getLogradouro());
                        txtNumero.setText(n.getNumero());
                        txtBairro.setText(n.getBairro());
                        txtCep.setText(n.getCep());
                        txtEstado.setText(n.getEstado());
                        txtPais.setText(n.getPais());
		}
	});
    }

}
    
    

