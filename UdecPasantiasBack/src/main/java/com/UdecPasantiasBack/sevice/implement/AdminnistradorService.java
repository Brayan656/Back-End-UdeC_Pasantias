package com.UdecPasantiasBack.sevice.implement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UdecPasantiasBack.dto.Admin_ConvenioDTO;
import com.UdecPasantiasBack.dto.Admin_ISU_DTO;
import com.UdecPasantiasBack.dto.EstudiantesDTO;
import com.UdecPasantiasBack.firebase.FirebaseInitializer;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

@Service
public class AdminnistradorService {
	
	@Autowired//permite una sola instancia
	private FirebaseInitializer firebase;
	
//ADMINISTRADOR CONVENIOS
	public List<Admin_ConvenioDTO> list() {
		// TODO Auto-generated method stub
		List<Admin_ConvenioDTO> response=new ArrayList<>();
		Admin_ConvenioDTO adminConvenio;
		
		ApiFuture<QuerySnapshot> querySnapshotApiFuture = getCollection().get();
		try {
			for (DocumentSnapshot doc : querySnapshotApiFuture.get().getDocuments()) {
				adminConvenio = doc.toObject(Admin_ConvenioDTO.class);
				adminConvenio.setId(doc.getId());
				response.add(adminConvenio);
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

	public Boolean add(Admin_ConvenioDTO adminConvenio) {
		// TODO Auto-generated method stub
		Map<String, Object> docData = getDocData(adminConvenio);
		
		
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
	
	public Boolean edit(String id, Admin_ConvenioDTO adminConvenio) {
		// TODO Auto-generated method stub
		Map<String, Object> docData = getDocData(adminConvenio);
		
		
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
		return firebase.getFirestore().collection("Admin_Convenios");//nombre de la coleccion(tabla de la bd)
	}
	
	private Map<String, Object> getDocData(Admin_ConvenioDTO adminConvenio) {
		Map<String,Object> docData=new HashMap<>();
		docData.put("nombre", adminConvenio.getNombre());
		docData.put("correo", adminConvenio.getCorreo());
		docData.put("clave", adminConvenio.getClave());
		docData.put("imagen", adminConvenio.getImagen());
		return docData;
	}
	
	
//ADMINISTRADOR ISU
/*
 * 
 * 
 * 
 * */
	public List<Admin_ISU_DTO> listISU() {
		// TODO Auto-generated method stub
		List<Admin_ISU_DTO> response=new ArrayList<>();
		Admin_ISU_DTO adminConvenio;
		
		ApiFuture<QuerySnapshot> querySnapshotApiFuture = getCollectionISU().get();
		try {
			for (DocumentSnapshot doc : querySnapshotApiFuture.get().getDocuments()) {
				adminConvenio = doc.toObject(Admin_ISU_DTO.class);
				adminConvenio.setId(doc.getId());
				response.add(adminConvenio);
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

	public Boolean addISU(Admin_ISU_DTO estudiante) {
		// TODO Auto-generated method stub
		Map<String, Object> docData = getDocData(estudiante);
		
		
		CollectionReference posts=getCollectionISU();
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
	
	public Boolean editISU(String id, Admin_ISU_DTO adminConvenio) {
		// TODO Auto-generated method stub
		Map<String, Object> docData = getDocData(adminConvenio);
		
		
		CollectionReference posts=getCollectionISU();
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

	public Boolean deleteISU(String id) {
		// TODO Auto-generated method stub
		
		CollectionReference posts=getCollectionISU();
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
	
	
	
	
	
	
	private CollectionReference getCollectionISU() {
		return firebase.getFirestore().collection("Admin_ISU");//nombre de la coleccion(tabla de la bd)
	}
	
	private Map<String, Object> getDocData(Admin_ISU_DTO adminConvenio) {
		Map<String,Object> docData=new HashMap<>();
		docData.put("nombre", adminConvenio.getNombre());
		docData.put("correo", adminConvenio.getCorreo());
		docData.put("clave", adminConvenio.getClave());
		docData.put("imagen", adminConvenio.getImagen());
		return docData;
	}
	
}
