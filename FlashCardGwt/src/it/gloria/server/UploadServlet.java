package it.gloria.server;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;

@SuppressWarnings("serial")
public class UploadServlet extends HttpServlet {
	private static final Logger log = Logger.getLogger(UploadServlet.class
			.getName());

	private BlobstoreService blobstoreService = BlobstoreServiceFactory
			.getBlobstoreService();

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// // checks if the request actually contains upload file
		Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(request);
		List<BlobKey> blobKeys = blobs.get("myFile");
		FlashCardDao dao = new FlashCardDao();
		if (blobKeys == null || blobKeys.isEmpty()) {
			response.sendRedirect("/");
		} else {
			// parse Object
			BlobKey uploadBlobKey = blobKeys.get(0);

			// save the record
			dao.createFile(uploadBlobKey);
			ImagesService imagesService = ImagesServiceFactory
                    .getImagesService();
 
            response.sendRedirect("/upload?key=" + uploadBlobKey.getKeyString());

		}
	}
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
 
        String imageUrl = req.getParameter("key");
        resp.setHeader("Content-Type", "text/html");
 
        // This is a bit hacky, but it'll work. We'll use this key in an Async
        // service to
        // fetch the image and image information
        resp.getWriter().println(imageUrl);
 
    }

}