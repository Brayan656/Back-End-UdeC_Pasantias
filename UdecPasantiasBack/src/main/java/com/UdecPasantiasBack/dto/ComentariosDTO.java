package com.UdecPasantiasBack.dto;

import lombok.Data;

@Data
public class ComentariosDTO {
	private String id;
	private String id_Usuario;
	private String id_Publicacion;
	private String mensaje;
	private String fecha;
	private int like;
	private int dislike;
}
