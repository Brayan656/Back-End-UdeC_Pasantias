package com.UdecPasantiasBack.sevice.implement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UdecPasantiasBack.dto.EmpresaDTO;
import com.UdecPasantiasBack.firebase.FirebaseInitializer;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

@Service
public class EmpresaService {
	@Autowired//permite una sola instancia
	private FirebaseInitializer firebase;
	
	
	public List<EmpresaDTO> list() {
		// TODO Auto-generated method stub
		List<EmpresaDTO> response=new ArrayList<>();
		EmpresaDTO empresa;
		
		ApiFuture<QuerySnapshot> querySnapshotApiFuture = getCollection().get();
		try {
			for (DocumentSnapshot doc : querySnapshotApiFuture.get().getDocuments()) {
				empresa = doc.toObject(EmpresaDTO.class);
				empresa.setId(doc.getId());
				response.add(empresa);
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

	public Boolean add(EmpresaDTO empresa) {
		// TODO Auto-generated method stub
		Map<String, Object> docData = getDocData(empresa);
		
		
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
	
	public Boolean edit(String id, EmpresaDTO empresa) {
		// TODO Auto-generated method stub
		Map<String, Object> docData = getDocData(empresa);
		
		
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
		return firebase.getFirestore().collection("Empresa");//nombre de la coleccion(tabla de la bd)
	}
	
	private Map<String, Object> getDocData(EmpresaDTO empresa) {
		Map<String,Object> docData=new HashMap<>();
		docData.put("nombre", empresa.getNombre());
		docData.put("correo", empresa.getCorreo());
		docData.put("clave", empresa.getClave());
		docData.put("imagen", empresa.getImagen());
		return docData;
	}
}
