/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject.controller;

import finalproject.model.Contrato;
import finalproject.service.ContratoService;
//import finalproject.service.ServicoService;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
//import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author rafael
 */
public class FXMLGerarContratoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Contrato> tblContratos; 
    @FXML
    private TableColumn<Contrato, String> clCodContrato;
    @FXML
    private TableColumn<Contrato, String> clCampoMetragem;
    @FXML
    private TableColumn<Contrato, String> clCorTinta;
    @FXML
    private TableColumn<Contrato, String> clRevestimento;
    @FXML
    private TableColumn<Contrato, String> clTipoPapelParede;
    @FXML
    private TableColumn<Contrato, Date> clDataServicoInicio;
    @FXML
    private TableColumn<Contrato, Date> clDataServicoFim;
    @FXML
    private TableColumn<Contrato, String> clFotoDoLocal;
    @FXML
    private TableColumn<Contrato, String> clFormaDePagamento;
    @FXML
    private TableColumn<Contrato, String> clCodigoServico;    
    @FXML
    private TableColumn<Contrato, String> clCpfCliente; 
    @FXML
    private TableColumn<Contrato, String> clStatusContrato; 

    @FXML
    private TextField txtCodContrato;
    @FXML
    private TextField txtCampoMetragem;
    @FXML
    private TextField txtCorTinta;
    @FXML
    private TextField txtRevestimento;
    @FXML
    private TextField txtTipoPapelParede;
    @FXML
    private DatePicker dpDataServicoInicio;
    @FXML
    private DatePicker dpDataServicoFim;
    @FXML
    private TextField txtFotoDoLocal;
    @FXML
    private ComboBox cbFormaDePagamento;
    @FXML
    private ComboBox cbCodigoServico;
    @FXML
    private ComboBox cbCpfCliente;
    @FXML
    private ComboBox cbStatusContrato;
    
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnAtualizar;
    @FXML
    private Button btnApagar;
    @FXML
    private Button btnLimpar;

    private ContratoService service;    
    //private ServicoService serviceServico;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        service = ContratoService.getNewInstance();
        //serviceServico = ServicoService.getNewInstance();
        carregaTodosComboBox();
        configuraColunas();
        configuraBindings();
        atualizaDadosTabela();
    } 

    // métodos públicos chamados quando o botão é clicado
	
    public void salvar() {
	Contrato c = new Contrato();
	pegaValores(c);
	service.salvar(c);
	atualizaDadosTabela();
    }

    public void atualizar() {
	Contrato c = tblContratos.getSelectionModel().getSelectedItem();
	pegaValores(c);
	service.atualizar(c);
	atualizaDadosTabela();
	}

    public void apagar() {
	Contrato c = tblContratos.getSelectionModel().getSelectedItem();
	service.apagar(c.getIdContrato());
	atualizaDadosTabela();
	}

    public void limpar() {
    	tblContratos.getSelectionModel().select(null);
        txtCodContrato.setText("");
	txtCampoMetragem.setText("");
	txtCorTinta.setText("");
        txtRevestimento.setText("");
        txtTipoPapelParede.setText("");
        dpDataServicoInicio.setValue(null);
        dpDataServicoFim.setValue(null);
        txtFotoDoLocal.setText("");
        cbFormaDePagamento.setValue(null);
        cbCodigoServico.setValue(null);
        cbCpfCliente.setValue(null);
        cbStatusContrato.setValue(null);
    }

    // métodos privados do controller
    // pega os valores entrados pelo usuário e adiciona no objeto contrato
    
    //invoca todos os metodos de carregar comboBox
    private void carregaTodosComboBox(){
        carregarComboBoxFormaDePagamento();
        carregarComboBoxCodigoServico();
        carregarComboBoxCpfCliente();
        carregarComboBoxStatusContrato();
    }
    
    //Popula o ComboBox cbFormaDePagamento
    private void carregarComboBoxFormaDePagamento(){
        ObservableList<String> listFormaDePagamento = 
                FXCollections.observableArrayList(
                "Dinheiro"
                ,"Débito"
                ,"Crédito à vista"
                ,"Crédito Parcelado"
                ); 
        cbFormaDePagamento.setItems(listFormaDePagamento);
    }
    
    //Popula o ComboBox Codigo Servico
    private void carregarComboBoxCodigoServico(){
        //ObservableList<String> listCodigoServico = serviceServico.buscarCodServico();
        ObservableList<String> listCodigoServico = 
                FXCollections.observableArrayList(
                 "SERV0001"
                ,"SERV0002"
                ,"SERV0003"
                ,"SERV0004"
                ,"SERV0005"
                ,"SERV0006"
                ,"SERV0007"
                ,"SERV0008"
                ,"SERV0009"
                ,"SERV0010"
                ,"SERV0011"
                ,"SERV0012"
                ,"SERV0013"
                ,"SERV0014"
                ,"SERV0015"
                ,"SERV0016"
                ,"SERV0017"
                ,"SERV0018"
                ,"SERV0019"
                ,"SERV0020"
                ,"SERV0021"
                ,"SERV0022"
                ,"SERV0023"
                ,"SERV0024"
                ,"SERV0025"
                ,"SERV0026"
                ,"SERV0027"
                ,"SERV0028"
                ,"SERV0029"
                ,"SERV0030"
                ,"SERV0031"
                ,"SERV0032"
                ,"SERV0033"
                ,"SERV0034"
                ,"SERV0035"
                ,"SERV0036"
                ,"SERV0037"
                ,"SERV0038"
                ,"SERV0039"
                ,"SERV0040"
                ,"SERV0041"
                ,"SERV0042"
                ,"SERV0043"
                ,"SERV0044"
                ,"SERV0045"
                ,"SERV0046"
                ,"SERV0047"
                ,"SERV0048"
                ,"SERV0049"
                ,"SERV0050"
                ,"SERV0051"
                ,"SERV0052"
                ,"SERV0053"
                ,"SERV0054"
                ,"SERV0055"
                ,"SERV0056"
                ,"SERV0057"
                ,"SERV0058"
                ,"SERV0059"
                ,"SERV0060"
                ,"SERV0061"
                ,"SERV0063"
                ,"SERV0064"
                ,"SERV0065"
                ,"SERV0066"
                ,"SERV0067"
                ,"SERV0068"
                ,"SERV0069"
                ,"SERV0070"
                ,"SERV0062"
                ,"SERV0099"
                //serviceServico.buscaPorID(1).toString()
                ); 
        cbCodigoServico.setItems(listCodigoServico);
    }
    

    //Popula o ComboBox CpfCliente
    private void carregarComboBoxCpfCliente(){
        //serviceServico.buscarTodas().get(1);
        ObservableList<String> listClientes = 
                FXCollections.observableArrayList(
                "111.111.111-99"
                ,"111.111.111-98"
                ,"111.111.111-97"
                ,"111.111.111-96"
                ,"111.111.111-95"
                ,"111.111.111-94"
                ,"111.111.111-93"
                ,"111.111.111-92"
                ,"111.111.111-91"
                ,"111.111.111-90"
                ,"111.111.111-89"
                ,"111.111.111-88"
                ,"111.111.111-87"
                ,"111.111.111-86"
                ,"111.111.111-85"
                ,"111.111.111-84"
                ,"111.111.111-83"
                ,"111.111.111-82"
                ,"111.111.111-81"
                ,"111.111.111-80"
                ,"111.111.111-79"
                ,"111.111.111-78"
                ,"111.111.111-77"
                ,"111.111.111-76"
                ,"111.111.111-75"
                ,"111.111.111-74"
                ,"111.111.111-73"
                ,"111.111.111-72"
                ,"111.111.111-71"
                ,"111.111.111-70"
                ,"111.111.111-69"
                ,"111.111.111-68"
                ,"111.111.111-67"
                ,"111.111.111-66"
                ,"111.111.111-65"
                ,"111.111.111-64"
                ,"111.111.111-63"
                ,"111.111.111-62"
                ,"111.111.111-61"
                ,"111.111.111-60"
                ,"111.111.111-59"
                ,"111.111.111-58"
                ,"111.111.111-57"
                ,"111.111.111-56"
                ,"111.111.111-55"
                ,"111.111.111-54"
                //serviceServico.buscaPorID(1).toString()
                ); 
        cbCpfCliente.setItems(listClientes);
    }
    
    //Popula o ComboBox cbFormaDePagamento
    private void carregarComboBoxStatusContrato(){
        ObservableList<String> listStatus = 
                FXCollections.observableArrayList(
                "Aberto"
                ,"Em andamento"
                ,"Finalizado"
                ,"Encerrado"
                ); 
        cbStatusContrato.setItems(listStatus);
    }
    
    private void pegaValores(Contrato c) {
        c.setCodContrato(txtCodContrato.getText());
        c.setCampoMetragem(txtCampoMetragem.getText());
	c.setCorTinta(txtCorTinta.getText());
        c.setRevestimento(txtRevestimento.getText());
        c.setTipoPapelParede(txtTipoPapelParede.getText());
        c.setDataServicoInicio(dataServicoInicioSelecionada());
        c.setDataServicoFim(dataServicoFimSelecionada());
        c.setFotoDoLocal(txtFotoDoLocal.getText());
        c.setFormaDePagamento(cbFormaDePagamento.getSelectionModel().getSelectedItem().toString());
        c.setCodigoServico(cbCodigoServico.getSelectionModel().getSelectedItem().toString());
        c.setCpfCliente(cbCpfCliente.getSelectionModel().getSelectedItem().toString());
        c.setStatusContrato(cbStatusContrato.getSelectionModel().getSelectedItem().toString());
    }    
    
    // método utilitário para pega a data que foi selecionada (que usa o tipo novo do java 8 LocalDateTime)
    private Date dataServicoInicioSelecionada() {
    	LocalDateTime time = dpDataServicoInicio.getValue().atStartOfDay();
    	return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }    

    // método utilitário para pega a data que foi selecionada (que usa o tipo novo do java 8 LocalDateTime)    
    private Date dataServicoFimSelecionada() {
    	LocalDateTime time = dpDataServicoFim.getValue().atStartOfDay();
    	return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    } 
    
    // configura as colunas para mostrar as propriedades da classe Contrato
    private void atualizaDadosTabela() {
	tblContratos.getItems().setAll(service.buscarTodas());
	limpar();
    }
    
    private void configuraColunas() {
        clCodContrato.setCellValueFactory(new PropertyValueFactory<>("codContrato"));
	clCampoMetragem.setCellValueFactory(new PropertyValueFactory<>("campoMetragem"));
	clCorTinta.setCellValueFactory(new PropertyValueFactory<>("corTinta"));
	clRevestimento.setCellValueFactory(new PropertyValueFactory<>("revestimento"));
	clTipoPapelParede.setCellValueFactory(new PropertyValueFactory<>("tipoPapelParede"));
	clDataServicoInicio.setCellValueFactory(new PropertyValueFactory<>("dataServicoInicio"));
	clDataServicoFim.setCellValueFactory(new PropertyValueFactory<>("dataServicoFim"));
	clFotoDoLocal.setCellValueFactory(new PropertyValueFactory<>("fotoDoLocal"));
	clFormaDePagamento.setCellValueFactory(new PropertyValueFactory<>("formaDePagamento"));
	clCodigoServico.setCellValueFactory(new PropertyValueFactory<>("codigoServico"));
	clCpfCliente.setCellValueFactory(new PropertyValueFactory<>("cpfCliente"));
	clStatusContrato.setCellValueFactory(new PropertyValueFactory<>("statusContrato"));
    }
    
    // configura a lógica da tela
    private void configuraBindings() {
        // esse binding só e false quando os campos da tela estão preenchidos
	BooleanBinding camposPreenchidos = 
                        txtCodContrato.textProperty().isEmpty().
                        or(txtCampoMetragem.textProperty().isEmpty()).
                        or(txtCorTinta.textProperty().isEmpty()).
                        or(txtRevestimento.textProperty().isEmpty()).
                        or(txtTipoPapelParede.textProperty().isEmpty()).
                        or(dpDataServicoInicio.valueProperty().isNull()).
                        or(dpDataServicoFim.valueProperty().isNull()).
                        or(txtFotoDoLocal.textProperty().isEmpty()).
                        or(cbFormaDePagamento.valueProperty().isNull()).
                        or(cbCodigoServico.valueProperty().isNull()).        
                        or(cbCpfCliente.valueProperty().isNull()).       
                        or(cbStatusContrato.valueProperty().isNull());
	// indica se há algo selecionado na tabela
	BooleanBinding algoSelecionado = tblContratos.getSelectionModel().selectedItemProperty().isNull();
	// alguns botões só são habilitados se algo foi selecionado na tabela
	btnApagar.disableProperty().bind(algoSelecionado);
	btnAtualizar.disableProperty().bind(algoSelecionado);
	btnLimpar.disableProperty().bind(algoSelecionado);
	// o botão salvar só é habilitado se as informações foram preenchidas e não tem nada na tela
	btnSalvar.disableProperty().bind(algoSelecionado.not().or(camposPreenchidos));
	// quando algo é selecionado na tabela, preenchemos os campos de entrada com os valores para o 
	// usuário editar
	tblContratos.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Contrato> b, Contrato o, Contrato n) -> {
		if (n != null) {
			LocalDate dtServicoInicio = n.getDataServicoInicio().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate dtServicoFim = n.getDataServicoFim().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        //SingleSelectionModel CpfCliente = n.getFormaDePagamento()
			//cbFormaDePagamento.getSelectionModel().getSelectedItem().toString()
                        txtCodContrato.setText(n.getCodContrato());
                        txtCampoMetragem.setText(n.getCampoMetragem());
			txtCorTinta.setText(n.getCorTinta());
			txtRevestimento.setText(n.getCorTinta());
			txtTipoPapelParede.setText(n.getCorTinta());
                        dpDataServicoInicio.setValue(dtServicoInicio);
                        dpDataServicoFim.setValue(dtServicoFim);
			txtFotoDoLocal.setText(n.getFotoDoLocal());
                        cbFormaDePagamento.setValue(n.getFormaDePagamento());//(n.getFormaDePagamento().toString());
                        cbCodigoServico.setValue(n.getCodigoServico());
                        cbCpfCliente.setValue(n.getCpfCliente());
                        cbStatusContrato.setValue(n.getStatusContrato());
                        
		}
	});
    }
}
