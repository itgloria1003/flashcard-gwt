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
		List<BlobKey> blobKeys = blobs.get("imageFile");
		if (blobKeys == null || blobKeys.isEmpty()) {
			response.sendRedirect("/");
		} else {
			// parse Object
			BlobKey uploadBlobKey = blobKeys.get(0);

			response.setContentType("text/html");
			response.setHeader("Pragma", "No-cache");
			response.setDateHeader("Expires", 0);
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(uploadBlobKey.getKeyString());
		}
	}



}