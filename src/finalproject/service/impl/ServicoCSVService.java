/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import finalproject.model.Servico;
import finalproject.service.ServicoService;
import javafx.collections.ObservableList;

/**
 *
 * @author rafael
 */
public class ServicoCSVService implements ServicoService{
    
    //divisor de colunas no arquivo
    private static final String SEPARADOR = ";";
    
    //O caminho para o arquivo deve ser selecionado aqui
    private static final Path ARQUIVO_SAIDA = Paths.get("./dados_servico.csv");
    
    //Lista os dados do arquivo
    private List<Servico> servicos;
    
    public ServicoCSVService(){
        carregaDados();
    }

    @Override
    public void salvar(Servico servico) {
        servico.setIdServico(ultimoId() +1);
        servicos.add(servico);
        salvaDados();

    }

    @Override
    public void atualizar(Servico servico) {
        Servico servicoAntigo = buscaPorID(servico.getIdServico());
        servicoAntigo.setCodigo(servico.getCodigo());
        servicoAntigo.setServico(servico.getServico());
        servicoAntigo.setDescricao(servico.getDescricao());
        salvaDados();        
    }    

    @Override
    public List<Servico> buscarTodas() {
        return servicos;
    }

    @Override
    public void apagar(int id) {
        Servico servico = buscaPorID(id);
        servicos.remove(servico);
        salvaDados();
    }    
    
    @Override
    public Servico buscaPorID(int id) {
        return servicos.stream().filter(c -> c.getIdServico()== id).findFirst().
                orElseThrow(() -> new Error("Servico nao encontrada"));
    }
    
    /*@Override
    public ObservableList<String> buscarCodServico() {
        //return servicos.stream().filter(c -> c.getCodigo()== id).findFirst().
        //        orElseThrow(() -> new Error("Servico nao encontrada"));
    }*/
    
    private void salvaDados() {
        StringBuffer sb = new StringBuffer();

        for (Servico c : servicos){
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
        return servicos.stream().mapToInt(Servico::getIdServico).max().orElse(0);
    }

    private void carregaDados() {
        try{
            if(!Files.exists(ARQUIVO_SAIDA)){
                Files.createFile(ARQUIVO_SAIDA);
            }
            servicos = Files.lines(ARQUIVO_SAIDA).map(this::leLinha).collect(Collectors.toList());
        
        }catch(IOException e){
            e.printStackTrace();
            System.exit(0);            
        }
    }

    private Servico leLinha(String linha){
        String colunas[] = linha.split(SEPARADOR);
        int id = Integer.parseInt(colunas[0]);
        
        Servico servico = new Servico();
        servico.setIdServico(id);
        servico.setCodigo(colunas[1]);
        servico.setServico(colunas[2]);
        servico.setDescricao(colunas[3]);
        
        return servico;
    }    
    
    private String criaLinha(Servico c) {
        String idStr = String.valueOf(c.getIdServico());
        String linha = String.join(
                SEPARADOR
                , idStr
                , c.getCodigo()
                , c.getServico()
                , c.getDescricao()
                );
        return linha;                
    }








    
}
