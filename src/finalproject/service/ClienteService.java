/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject.service;

import java.util.List;
import finalproject.model.Cliente;
import finalproject.service.impl.ClienteCSVService;

/**
 *
 * @author rafael
 */
public interface ClienteService {
    
    //Create
    public void salvar(Cliente cliente);
    
    //Retrieve 
    public List<Cliente> buscarTodas();
    public Cliente buscaPorID(int id);
    
    //Delete
    public void apagar(int id);
    
    //Update
    public void atualizar(Cliente cliente);
    
    // retorna a implementação ClienteCSVService, 
    public static ClienteService getNewInstance(){
        return new ClienteCSVService();
    }
       
}
