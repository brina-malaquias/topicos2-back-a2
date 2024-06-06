package br.unitins.topicos2.ano2024.model.usuario;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoUsuario {

    USER(1, "Cliente"),
    ADMIN(2, "Admin");

    private final Integer id;
    private final String label;

    TipoUsuario(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static TipoUsuario valueOf(Integer id) throws IllegalArgumentException {
        
        if (id == null)
            return null;

        for (TipoUsuario tipoUsuario : TipoUsuario.values()) {

            if (tipoUsuario.getId().equals(id))
                return tipoUsuario;
        }
        throw new IllegalArgumentException("Id inválido" + id);
    }
    
}
