package org.mlp.apps.photo;

import org.springframework.stereotype.Service;

@Service
public interface PhotoService {
	
	public void preparePhoto(Photo p, byte[] file) throws Exception;
}
