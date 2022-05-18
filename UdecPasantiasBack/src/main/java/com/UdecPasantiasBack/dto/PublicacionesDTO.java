package com.UdecPasantiasBack.dto;

import lombok.Data;

@Data
public class PublicacionesDTO {

	private String id;
	private String titulo;
	private String url;
	private String tipoOferta;
	private String descripcion;
	private int cantidad_Vacantes;
	private String id_Empresa;
}
