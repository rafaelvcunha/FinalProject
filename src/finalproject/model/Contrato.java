/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject.model;

import java.util.Date;

/**
 *
 * @author rafael
 */
public class Contrato {
    
    private int idContrato;
    private String codContrato;
    private String campoMetragem;
    private String corTinta;
    private String revestimento;
    private String tipoPapelParede;
    private Date dataServicoInicio;
    private Date dataServicoFim;
    private String fotoDoLocal;
    private String formaDePagamento;
    private String codigoServico;
    private String cpfCliente;
    private String statusContrato;
    
    //getters e setters

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public String getCodContrato() {
        return codContrato;
    }

    public void setCodContrato(String codContrato) {
        this.codContrato = codContrato;
    }
    
    public String getCampoMetragem() {
        return campoMetragem;
    }

    public void setCampoMetragem(String campoMetragem) {
        this.campoMetragem = campoMetragem;
    }

    public String getCorTinta() {
        return corTinta;
    }

    public void setCorTinta(String corTinta) {
        this.corTinta = corTinta;
    }

    public String getRevestimento() {
        return revestimento;
    }

    public void setRevestimento(String revestimento) {
        this.revestimento = revestimento;
    }

    public String getTipoPapelParede() {
        return tipoPapelParede;
    }

    public void setTipoPapelParede(String tipoPapelParede) {
        this.tipoPapelParede = tipoPapelParede;
    }

    public Date getDataServicoInicio() {
        return dataServicoInicio;
    }

    public void setDataServicoInicio(Date dataServicoInicio) {
        this.dataServicoInicio = dataServicoInicio;
    }

    public Date getDataServicoFim() {
        return dataServicoFim;
    }

    public void setDataServicoFim(Date dataServicoFim) {
        this.dataServicoFim = dataServicoFim;
    }

    public String getFotoDoLocal() {
        return fotoDoLocal;
    }

    public void setFotoDoLocal(String fotoDoLocal) {
        this.fotoDoLocal = fotoDoLocal;
    }

    public String getFormaDePagamento() {
        return formaDePagamento;
    }

    public void setFormaDePagamento(String formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }

    public String getStatusContrato() {
        return statusContrato;
    }

    public void setStatusContrato(String statusContrato) {
        this.statusContrato = statusContrato;
    }

    public String getCodigoServico() {
        return codigoServico;
    }

    public void setCodigoServico(String codigoServico) {
        this.codigoServico = codigoServico;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }
    
    
    
    
}
