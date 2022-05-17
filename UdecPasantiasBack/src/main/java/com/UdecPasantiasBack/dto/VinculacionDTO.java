package com.UdecPasantiasBack.dto;

import lombok.Data;

@Data
public class VinculacionDTO {
	private String id;
	private String id_Empresa;
	private String id_Estudiante;
	private String oficio_Vinculacion;
	private Boolean estado;
}
