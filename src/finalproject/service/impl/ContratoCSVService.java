/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject.service.impl;

import finalproject.model.Contrato;
import finalproject.service.ContratoService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author rafael
 */
public class ContratoCSVService implements ContratoService{
    
    //divisor de colunas no arquivo
    private static final String SEPARADOR = ";";
    
    //O caminho para o arquivo deve ser selecionado aqui
    private static final Path ARQUIVO_SAIDA = Paths.get("./dados_contrato.csv");
    
    // formato de data usado no arquivo
    final SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
    
    //Lista os dados do arquivo
    private List<Contrato> contratos;
    
    public ContratoCSVService() {
        carregaDados();
    }

    @Override
    public void salvar(Contrato contrato) {
        contrato.setIdContrato(ultimoId() +1);
        contratos.add(contrato);
        salvaDados();
    }

    @Override
    public void atualizar(Contrato contrato) {
		Contrato contratoAntigo = buscaPorID(contrato.getIdContrato());
                contratoAntigo.setCodContrato(contrato.getCodContrato());
		contratoAntigo.setCampoMetragem(contrato.getCampoMetragem());
		contratoAntigo.setCorTinta(contrato.getCorTinta());
		contratoAntigo.setRevestimento(contrato.getRevestimento());
		contratoAntigo.setTipoPapelParede(contrato.getTipoPapelParede());
		contratoAntigo.setDataServicoInicio(contrato.getDataServicoInicio());
		contratoAntigo.setDataServicoFim(contrato.getDataServicoFim());
		contratoAntigo.setFotoDoLocal(contrato.getFotoDoLocal());
		contratoAntigo.setFormaDePagamento(contrato.getFormaDePagamento());
		contratoAntigo.setCodigoServico(contrato.getCodigoServico());
		contratoAntigo.setCpfCliente(contrato.getCpfCliente());
		contratoAntigo.setStatusContrato(contrato.getStatusContrato());
		salvaDados();
    }

    @Override
    public List<Contrato> buscarTodas() {
        return contratos;
    }

    @Override
    public void apagar(int id) {
	Contrato contrato = buscaPorID(id);
	contratos.remove(contrato);
	salvaDados();
    }
    
    @Override
    public Contrato buscaPorID(int id) {
        return contratos.stream().filter(c -> c.getIdContrato()== id).findFirst().
        orElseThrow(() -> new Error("Contrato nao encontrado"));
    }

    private void salvaDados() {
        StringBuffer sb = new StringBuffer();

        for (Contrato c : contratos){
            String linha = criaLinha(c);
            sb.append(linha);
            sb.append(System.getProperty("line.separator"));
        }
        try {
            
            //Files.delete(ARQUIVO_SAIDA);
            Files.write(ARQUIVO_SAIDA, sb.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private int ultimoId() {
        return contratos.stream().mapToInt(Contrato::getIdContrato).max().orElse(0);
    }
        
    private void carregaDados() {
        try{
            if(!Files.exists(ARQUIVO_SAIDA)){
                Files.createFile(ARQUIVO_SAIDA);
            }
            contratos = Files.lines(ARQUIVO_SAIDA).map(this::leLinha).collect(Collectors.toList());
        
        }catch(IOException e){
            e.printStackTrace();
            System.exit(0);            
        }
    }

    private Contrato leLinha(String linha){
        String colunas[] = linha.split(SEPARADOR);
        int id = Integer.parseInt(colunas[0]);
	Date dataServicoInicio = null;
        Date dataServicoFim = null;
	try {
		dataServicoInicio = formatoData.parse(colunas[6]);
		dataServicoFim = formatoData.parse(colunas[7]);
	} catch (ParseException e) {
		e.printStackTrace();
		System.exit(0);
	}
        
        

        Contrato contrato = new Contrato();
        contrato.setIdContrato(id);
        contrato.setCodContrato(colunas[1]);
        contrato.setCampoMetragem(colunas[2]);
        contrato.setCorTinta(colunas[3]);
        contrato.setRevestimento(colunas[4]);
        contrato.setTipoPapelParede(colunas[5]);
        contrato.setDataServicoInicio(dataServicoInicio);
        contrato.setDataServicoFim(dataServicoFim);
        contrato.setFotoDoLocal(colunas[8]);
        contrato.setFormaDePagamento(colunas[9]);
        contrato.setCodigoServico(colunas[10]);
        contrato.setCpfCliente(colunas[11]);
        contrato.setStatusContrato(colunas[12]);
        
        return contrato;
    }

    private String criaLinha(Contrato c) {
	String dataServicoInicioStr = formatoData.format(c.getDataServicoInicio());
	String dataServicoFimStr = formatoData.format(c.getDataServicoFim());
        String idStr = String.valueOf(c.getIdContrato());
        String linha = String.join(
                SEPARADOR
                , idStr
                , c.getCodContrato()
                , c.getCampoMetragem()
                , c.getCorTinta()
                , c.getRevestimento()
                , c.getTipoPapelParede()
                , dataServicoInicioStr
                , dataServicoFimStr
                , c.getFotoDoLocal()
                , c.getFormaDePagamento()
                , c.getCodigoServico()
                , c.getCpfCliente()
                , c.getStatusContrato()
                );
        return linha; 
    }

}
