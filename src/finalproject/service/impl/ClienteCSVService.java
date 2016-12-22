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

import finalproject.model.Cliente;
import finalproject.service.ClienteService;

/**
 *
 * @author rafael
 */
public class ClienteCSVService implements ClienteService {
    
    //divisor de colunas no arquivo
    private static final String SEPARADOR = ";";
    
    //O caminho para o arquivo deve ser selecionado aqui
    private static final Path ARQUIVO_SAIDA = Paths.get("./dados_cliente.csv");
    
    //Lista os dados do arquivo
    private List<Cliente> clientes;
    
    public ClienteCSVService(){
        carregaDados();
    }
    
    @Override
    public void salvar(Cliente cliente) {
        cliente.setId(ultimoId() +1);
        clientes.add(cliente);
        salvaDados();

    }

    @Override
    public void atualizar(Cliente cliente) {
        Cliente clienteAntigo = buscaPorID(cliente.getId());
        clienteAntigo.setNome(cliente.getNome());
        clienteAntigo.setSobreNome(cliente.getSobreNome());
        clienteAntigo.setCpf(cliente.getCpf());
        clienteAntigo.setLogradouro(cliente.getLogradouro());
        clienteAntigo.setNumero(cliente.getNumero());
        clienteAntigo.setBairro(cliente.getBairro());
        clienteAntigo.setCep(cliente.getCep());
        clienteAntigo.setEstado(cliente.getEstado());
        clienteAntigo.setPais(cliente.getPais());
        salvaDados();

    }
    
    @Override
    public List<Cliente> buscarTodas() {
        return clientes;
    }

    @Override
    public void apagar(int id) {
        Cliente cliente = buscaPorID(id);
        clientes.remove(cliente);
        salvaDados();

    }
    
    @Override
    public Cliente buscaPorID(int id) {
        return clientes.stream().filter(c -> c.getId() == id).findFirst().
                orElseThrow(() -> new Error("Cliente nao encontrada"));
    }




    
    private void salvaDados() {
        StringBuffer sb = new StringBuffer();

        for (Cliente c : clientes){
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
    
    //O ID mais alto eh retornado aqui para continuarmos contandos os IDs
    private int ultimoId(){
        return clientes.stream().mapToInt(Cliente::getId).max().orElse(0);
    }
    
    //carrega os dados do arquivo para a linta de clientes  
    private void carregaDados() {
        try{
            if(!Files.exists(ARQUIVO_SAIDA)){
                Files.createFile(ARQUIVO_SAIDA);
            }
            clientes = Files.lines(ARQUIVO_SAIDA).map(this::leLinha).collect(Collectors.toList());
        
        }catch(IOException e){
            e.printStackTrace();
            System.exit(0);            
        }
    }
    
    private Cliente leLinha(String linha){
        String colunas[] = linha.split(SEPARADOR);
        int id = Integer.parseInt(colunas[0]);
        
        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setNome(colunas[1]);
        cliente.setSobreNome(colunas[2]);
        cliente.setCpf(colunas[3]);
        cliente.setLogradouro(colunas[4]);
        cliente.setNumero(colunas[5]);
        cliente.setBairro(colunas[6]);
        cliente.setCep(colunas[7]);
        cliente.setEstado(colunas[8]);
        cliente.setPais(colunas[9]);
        
        return cliente;
    }

    private String criaLinha(Cliente c) {
        String idStr = String.valueOf(c.getId());
        String linha = String.join(
                SEPARADOR
                , idStr
                , c.getNome()
                , c.getSobreNome()
                , c.getCpf()
                , c.getLogradouro()
                , c.getNumero()
                , c.getBairro()
                , c.getCep()
                , c.getEstado()
                , c.getPais()
                );
        return linha;
    }
    
    
    
    
    
}
