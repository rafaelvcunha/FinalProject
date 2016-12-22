/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject.controller;

import finalproject.model.Contrato;
import finalproject.service.ContratoService;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author rafael
 */
public class FXMLBuscarContratoController implements Initializable {

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
    private DatePicker dpDataServicoInicio;
    @FXML
    private DatePicker dpDataServicoFim;
    @FXML
    private ComboBox cbStatusContrato;    
    
    //@FXML
    //private Button btnPesquisar;
    @FXML
    private Button btnLimpar;
    
    private ContratoService service;        

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        service = ContratoService.getNewInstance();
        carregarComboBoxStatusContrato();
        configuraColunas();
        configuraBindings();
        atualizaDadosTabela();
        //Adicionando um Listener no txtCodContrato
        
    }   
    
    // métodos públicos chamados quando o botão é clicado

    public void pesquisaStatusContrato(String valorAntigo, String valorNovo){
        //String codContrato = txtCodContrato.getText();
        //codContrato = codContrato.toUpperCase();
        //String statusContrato = cbStatusContrato.getSelectionModel().getSelectedItem().toString();
        txtCodContrato.setText("");
        dpDataServicoInicio.setValue(null);
        dpDataServicoFim.setValue(null);
        
        ObservableList<Contrato> listaFiltrada = FXCollections.observableArrayList();
        
        if(valorNovo == null){
            tblContratos.getItems().setAll(service.buscarTodas());
        }
        else{
            //valorNovo = valorNovo.toUpperCase();
            tblContratos.getItems().setAll(service.buscarTodas());
            for( Contrato contratos: tblContratos.getItems()){
                String filtroStatusContrato = contratos.getStatusContrato();
                                
                if(filtroStatusContrato.contains(valorNovo)){
                        listaFiltrada.add(contratos);
                    }
                }
            tblContratos.getItems().setAll(listaFiltrada);
        } 
    }
    
    public void pesquisaCodContrato(String valorAntigo, String valorNovo){
        //String codContrato = txtCodContrato.getText();
        //codContrato = codContrato.toUpperCase();
        //String statusContrato = cbStatusContrato.getSelectionModel().getSelectedItem().toString();
        dpDataServicoInicio.setValue(null);
        dpDataServicoFim.setValue(null);
        cbStatusContrato.setValue(null);
        
        ObservableList<Contrato> listaFiltrada = FXCollections.observableArrayList();
        
        if(txtCodContrato == null || (valorNovo.length() < valorAntigo.length()) || valorNovo == null){
            tblContratos.getItems().setAll(service.buscarTodas());
        }
        else{
            valorNovo = valorNovo.toUpperCase();
            for( Contrato contratos: tblContratos.getItems()){
                String filtroCodContrato = contratos.getCodContrato();
                                
                if(filtroCodContrato.toUpperCase().contains(valorNovo)){
                        listaFiltrada.add(contratos);
                    }
                }
            tblContratos.getItems().setAll(listaFiltrada);
        } 
    }
    
    
    
    public void pesquisaDataServicoInicio(LocalDate valorAntigo, LocalDate valorNovo){
        //String codContrato = txtCodContrato.getText();
        //codContrato = codContrato.toUpperCase();
        //String statusContrato = cbStatusContrato.getSelectionModel().getSelectedItem().toString();
        txtCodContrato.setText("");
        dpDataServicoFim.setValue(null);
        cbStatusContrato.setValue(null);
        
        ObservableList<Contrato> listaFiltrada = FXCollections.observableArrayList();
        
        if(valorNovo == null){
            tblContratos.getItems().setAll(service.buscarTodas());
        }
        else{
            tblContratos.getItems().setAll(service.buscarTodas());
            for( Contrato contratos: tblContratos.getItems()){
                LocalDate filtroDataServicoInicio = contratos.getDataServicoInicio().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                               
                if(filtroDataServicoInicio.compareTo(valorNovo) == 0){
                        listaFiltrada.add(contratos);
                    }
                }
            tblContratos.getItems().setAll(listaFiltrada);
        } 
    }  
    
    public void pesquisaDataServicoFim(LocalDate valorAntigo, LocalDate valorNovo){
        //String codContrato = txtCodContrato.getText();
        //codContrato = codContrato.toUpperCase();
        //String statusContrato = cbStatusContrato.getSelectionModel().getSelectedItem().toString();
        txtCodContrato.setText("");
        dpDataServicoInicio.setValue(null);
        cbStatusContrato.setValue(null);
        
        ObservableList<Contrato> listaFiltrada = FXCollections.observableArrayList();
        
        if(valorNovo == null){
            tblContratos.getItems().setAll(service.buscarTodas());
        }
        else{
            tblContratos.getItems().setAll(service.buscarTodas());
            for( Contrato contratos: tblContratos.getItems()){
                LocalDate filtroDataServicoFim = contratos.getDataServicoFim().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                               
                if(filtroDataServicoFim.compareTo(valorNovo) == 0){
                        listaFiltrada.add(contratos);
                    }
                }
            tblContratos.getItems().setAll(listaFiltrada);
        } 
    }        
            
/*    public void pesquisarPorData(){
        tblContratos.getItems().setAll(service.buscarTodas());
        String codContrato = txtCodContrato.getText();
        codContrato = codContrato.toUpperCase();
        //String statusContrato = cbStatusContrato.getSelectionModel().getSelectedItem().toString();
        
        ObservableList<Contrato> listaFiltrada = FXCollections.observableArrayList();
        
        
        for( Contrato contratos: tblContratos.getItems()){
            String filtroCodContrato = contratos.getCodContrato();
                                
            if(filtroCodContrato.toUpperCase().contains(codContrato)){
                        listaFiltrada.add(contratos);
                }
            }
        tblContratos.getItems().setAll(listaFiltrada);
         
    }*/
    
    public void limpar() {
    	tblContratos.getSelectionModel().select(null);
        txtCodContrato.setText("");
        dpDataServicoInicio.setValue(null);
        dpDataServicoFim.setValue(null);
        cbStatusContrato.setValue(null);
        tblContratos.getItems().setAll(service.buscarTodas());
    }    
    
    
    // configura as colunas para mostrar as propriedades da classe Contrato
    private void atualizaDadosTabela() {
	tblContratos.getItems().setAll(service.buscarTodas());
	limpar();
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
        txtCodContrato.textProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observable, Object valorAntigo, Object valorNovo){
                pesquisaCodContrato((String) valorAntigo, (String) valorNovo);
            }
        });
        cbStatusContrato.valueProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observable, Object valorAntigo, Object valorNovo){
                pesquisaStatusContrato((String) valorAntigo, (String) valorNovo);
                //System.out.println(observable);
                //System.out.println(valorAntigo);
                //System.out.println(valorNovo);
            }
        });

        dpDataServicoInicio.valueProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observable, Object valorAntigo, Object valorNovo){
                pesquisaDataServicoInicio((LocalDate) valorAntigo, (LocalDate) valorNovo);
            }
        });
        
        dpDataServicoFim.valueProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observable, Object valorAntigo, Object valorNovo){
                pesquisaDataServicoFim((LocalDate) valorAntigo, (LocalDate) valorNovo);
            }
        });
        
        // esse binding só e false quando os campos da tela estão preenchidos
    	/*BooleanBinding camposPreenchidos = 
                        //txtCodContrato.textProperty().isEmpty().
                        //and(dpDataServicoInicio.valueProperty().isNull()).
                        //and(dpDataServicoFim.valueProperty().isNull()).
                        //and(cbStatusContrato.valueProperty().isNull())
                        dpDataServicoInicio.valueProperty().isNull().
                        or(dpDataServicoFim.valueProperty().isNull())
                ;*/
	// indica se há algo selecionado na tabela
	//BooleanBinding algoSelecionado = tblContratos.getSelectionModel().selectedItemProperty().isNull();
	// alguns botões só são habilitados se algo foi selecionado na tabela
	//btnLimpar.disableProperty().bind(algoSelecionado);
	// o botão pesquisar só é habilitado se as informações foram preenchidas e não tem nada na tela
	//btnPesquisar.disableProperty().bind(algoSelecionado.not().or(camposPreenchidos));
	//btnPesquisar.disableProperty().bind(camposPreenchidos);
	
        // quando algo é selecionado na tabela, preenchemos os campos de entrada com os valores para o 
	// usuário editar
	tblContratos.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Contrato> b, Contrato o, Contrato n) -> {
		if (n != null) {
			//LocalDate dtServicoInicio = n.getDataServicoInicio().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			//LocalDate dtServicoFim = n.getDataServicoFim().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

			//txtCodContrato.setText(n.getCodContrato());
                        //dpDataServicoInicio.setValue(dtServicoInicio);
                        //dpDataServicoFim.setValue(dtServicoFim);
                        //cbStatusContrato.setValue(n.getStatusContrato());
                        
		}
	});
    } 
    
}
