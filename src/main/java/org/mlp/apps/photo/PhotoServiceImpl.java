package org.mlp.apps.photo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.ResponsiveBreakpoint;
import com.cloudinary.utils.ObjectUtils;

@Service
public class PhotoServiceImpl implements PhotoService {
	
	@Override
	public void preparePhoto(Photo photo, byte[] file) throws Exception {
		try {
            Cloudinary cloudinary = new Cloudinary("cloudinary://865793447284318:Hzz6uCahJt-L7k1YEVT2Pv6pRZw@lemursportal");
            List<PhotoBreakpoint> breakpoints = new ArrayList<PhotoBreakpoint>();
            Map cloudinaryResult = cloudinary.uploader().upload(file,
                    ObjectUtils.asMap(
                            "responsive_breakpoints", new ResponsiveBreakpoint().createDerived(false).bytesStep(20000).minWidth(50).maxWidth(1000).maxImages(20),
                            "public_id", photo.getName()));
            photo.setLink((String) cloudinaryResult.get("secure_url"));
            photo.setSize((Integer) cloudinaryResult.get("bytes"));
            List<Map> bp = (List) cloudinaryResult.get("responsive_breakpoints");
            for (Map m : (List<Map>) bp.get(0).get("breakpoints")) {
                PhotoBreakpoint b = new PhotoBreakpoint();
                b.setHeight((Integer) m.get("height"));
                b.setWidth((Integer) m.get("width"));
                b.setSize((Integer) m.get("bytes"));
                b.setLink((String) m.get("secure_url"));
                breakpoints.add(b);
            }
            photo.setBreakpoints(breakpoints);
        } catch (Exception e) {
            throw e;
        }
	}
}
