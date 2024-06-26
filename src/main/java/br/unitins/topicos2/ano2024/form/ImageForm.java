package br.unitins.topicos2.ano2024.form;


import jakarta.ws.rs.FormParam;

public class ImageForm {
    @FormParam("id")
    private Long id;

    @FormParam("nomeImagem")
    private String nomeImagem;

    @FormParam("imagem")
    private byte[] imagem;

    public String getNomeImagem() {
        return nomeImagem;
    }

    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }   
}