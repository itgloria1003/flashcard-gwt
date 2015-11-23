package it.gloria.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;

/**
 * Servlet implementation class Serve
 */
public class ServeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	ImagesService imageService = ImagesServiceFactory.getImagesService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BlobKey key = new BlobKey(request.getParameter("blobKey"));
		blobstoreService.serve(key, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
