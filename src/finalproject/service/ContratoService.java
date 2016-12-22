/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject.service;

import finalproject.model.Contrato;
import finalproject.service.impl.ContratoCSVService;
import java.util.List;

/**
 *
 * @author rafael
 */
public interface ContratoService {

    //Create    
    public void salvar(Contrato contrato);

    //Retrieve 
    public List<Contrato> buscarTodas();
    public Contrato buscaPorID(int id);
    
    //Delete
    public void apagar(int id);
    
    //Update
    public void atualizar(Contrato contrato);
    
    // retorna a implementação ContratoCSVService, 
    public static ContratoService getNewInstance(){
        return new ContratoCSVService();
    }    
    
    
}
