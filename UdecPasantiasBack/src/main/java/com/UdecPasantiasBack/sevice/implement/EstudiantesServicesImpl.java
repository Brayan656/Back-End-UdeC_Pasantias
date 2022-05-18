package com.UdecPasantiasBack.sevice.implement;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UdecPasantiasBack.dto.EstudiantesDTO;
import com.UdecPasantiasBack.firebase.FirebaseInitializer;
import com.UdecPasantiasBack.sevice.EstudiantesService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

@Service
public class EstudiantesServicesImpl implements EstudiantesService {

	@Autowired//permite una sola instancia
	private FirebaseInitializer firebase;
	
	@Override
	public List<EstudiantesDTO> list() {
		// TODO Auto-generated method stub
		List<EstudiantesDTO> response=new ArrayList<>();
		EstudiantesDTO estudiante;
		
		ApiFuture<QuerySnapshot> querySnapshotApiFuture = getCollection().get();
		try {
			for (DocumentSnapshot doc : querySnapshotApiFuture.get().getDocuments()) {
				estudiante = doc.toObject(EstudiantesDTO.class);
				estudiante.setId(doc.getId());
				response.add(estudiante);
			}
			return response;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Boolean add(EstudiantesDTO estudiante) {
		// TODO Auto-generated method stub
		Map<String, Object> docData = getDocData(estudiante);
		
		
		CollectionReference posts=getCollection();
		ApiFuture<com.google.cloud.firestore.WriteResult> writeResultApiFuture= posts.document().create(docData);
		try {
			if(null!=writeResultApiFuture.get()) {
				return Boolean.TRUE;
			}else return Boolean.FALSE;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Boolean edit(String id, EstudiantesDTO estudiante) {
		// TODO Auto-generated method stub
		Map<String, Object> docData = getDocData(estudiante);
		
		
		CollectionReference posts=getCollection();
		ApiFuture<com.google.cloud.firestore.WriteResult> writeResultApiFuture= posts.document(id).set(docData);
		
		try {
			if(null!=writeResultApiFuture.get()) {
				return Boolean.TRUE;
			}else return Boolean.FALSE;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Boolean delete(String id) {
		// TODO Auto-generated method stub
		
		CollectionReference posts=getCollection();
		ApiFuture<com.google.cloud.firestore.WriteResult> writeResultApiFuture= posts.document(id).delete();
		
		try {
			if(null!=writeResultApiFuture.get()) {
				return Boolean.TRUE;
			}else return Boolean.FALSE;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
	
	private CollectionReference getCollection() {
		return firebase.getFirestore().collection("Estudiante");//nombre de la coleccion(tabla de la bd)
	}
	
	private Map<String, Object> getDocData(EstudiantesDTO estudiante) {
		Map<String,Object> docData=new HashMap<>();
		docData.put("nombre", estudiante.getNombre());
		docData.put("apellido", estudiante.getApellido());
		docData.put("correo", estudiante.getCorreo());
		docData.put("contraseña", estudiante.getContraseña());
		docData.put("imagen", estudiante.getImagen());
		docData.put("hoja_De_Vida", estudiante.getHoja_De_Vida());
		return docData;
	}
}
