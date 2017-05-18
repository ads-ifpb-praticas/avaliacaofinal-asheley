/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.jsf.galeriabeans;

/**
 *
 * @author Asheley
 */
public class Foto {
     private Long id;
    private byte[] imagem;
    private int ordem;
    private String descricao;
    private Evento evento = new Evento();
 
    public Evento getEvento() {
        return evento;
    }
 
    public void setEvento(Evento evento) {
        this.evento = evento;
    }
 
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public byte[] getImagem() {
        return imagem;
    }
 
    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }
 
    public int getOrdem() {
        return ordem;
    }
 
    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }
 
    public String getDescricao() {
        return descricao;
    }
 
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

