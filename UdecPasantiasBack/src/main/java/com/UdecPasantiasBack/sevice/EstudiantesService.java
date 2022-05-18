package com.UdecPasantiasBack.sevice;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.UdecPasantiasBack.dto.EstudiantesDTO;

public interface EstudiantesService{
	List<EstudiantesDTO> list();

    Boolean add(EstudiantesDTO estudiante) throws ExecutionException;

    Boolean edit(String id,EstudiantesDTO estudiante);

    Boolean delete(String id);
    
    EstudiantesDTO estudiante(String id) ;

	EstudiantesDTO estudiante(int id);
}
