package com.UdecPasantiasBack.sevice;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.UdecPasantiasBack.dto.PostDTO;

public interface PostManagementSercice {
	List<PostDTO> list();

    Boolean add(PostDTO post) throws ExecutionException;

    Boolean edit(String id,PostDTO post);

    Boolean delete(String id);
}
