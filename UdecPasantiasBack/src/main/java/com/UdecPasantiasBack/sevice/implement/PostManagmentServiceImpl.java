package com.UdecPasantiasBack.sevice.implement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scripting.bsh.BshScriptUtils.BshExecutionException;
import org.springframework.stereotype.Service;

import com.UdecPasantiasBack.dto.PostDTO;
import com.UdecPasantiasBack.firebase.FirebaseInitializer;
import com.UdecPasantiasBack.sevice.PostManagementSercice;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firestore.v1.WriteResult;
import com.google.common.util.concurrent.ExecutionSequencer;

@Service
public class PostManagmentServiceImpl implements PostManagementSercice {

	@Autowired
	private FirebaseInitializer firebase;
	
	@Override
	public List<PostDTO> list() {
		// TODO Auto-generated method stub
		List<PostDTO> response=new ArrayList<>();
		PostDTO post;
		
		ApiFuture<QuerySnapshot> querySnapshotApiFuture = getCollection().get();
		try {
			for (DocumentSnapshot doc : querySnapshotApiFuture.get().getDocuments()) {
				post = doc.toObject(PostDTO.class);
				post.setId(doc.getId());
				response.add(post);
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
	public Boolean add(PostDTO post) {
		// TODO Auto-generated method stub
		Map<String, Object> docData = getDocData(post);
		
		
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
	public Boolean edit(String id, PostDTO post) {
		// TODO Auto-generated method stub
		Map<String, Object> docData = getDocData(post);
		
		
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
		return firebase.getFirestore().collection("post");
	}
	
	private Map<String, Object> getDocData(PostDTO post) {
		Map<String,Object> docData=new HashMap<>();
		docData.put("title", post.getTitle());
		docData.put("content", post.getContent());
		return docData;
	}

}
