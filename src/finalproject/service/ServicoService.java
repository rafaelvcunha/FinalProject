/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject.service;

import finalproject.model.Servico;
import finalproject.service.impl.ServicoCSVService;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author rafael
 */
public interface ServicoService {
    
        //Create
    public void salvar(Servico servico);
    
    //Retrieve 
    public List<Servico> buscarTodas();
    public Servico buscaPorID(int id);
    //public ObservableList<String> buscarCodServico();
    
    //Delete
    public void apagar(int id);
    
    //Update
    public void atualizar(Servico servico);
    
    // retorna a implementação ClienteCSVService, 
    public static ServicoService getNewInstance(){
        return new ServicoCSVService();
    }
    
}
