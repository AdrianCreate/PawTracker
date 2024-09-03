package DatabaseConnection;

import java.io.IOException;
import java.sql.Date;

import com.google.zxing.WriterException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class PetRegisterServlet
 */
public class PetRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		String name = StringEscapeUtils.escapeHtml4(request.getParameter("petName"));
//		String sexString = StringEscapeUtils.escapeHtml4(request.getParameter("petSex"));
//		char sex = sexString.charAt(0);
//		String type = StringEscapeUtils.escapeHtml4(request.getParameter("petType"));
//		String breed = StringEscapeUtils.escapeHtml4(request.getParameter("breed"));
//		String colour = StringEscapeUtils.escapeHtml4(request.getParameter("colour"));
//		String date_of_birth_pet_str = StringEscapeUtils.escapeHtml4(request.getParameter("petBirthDate"));
//		Date date_of_birth_pet = Date.valueOf(date_of_birth_pet_str);
//		String distinctive_signs = StringEscapeUtils.escapeHtml4(request.getParameter("distinctiveSigns"));
//		String medication = StringEscapeUtils.escapeHtml4(request.getParameter("medication"));
//		
//		Pet newPet = new Pet(name, sex, type, breed, colour, date_of_birth_pet, distinctive_signs, medication);
//		PetHandler petHandler = new PetHandler();
//		petHandler.insertPet(newPet);

		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");

		String name = request.getParameter("petName");
		String sexString = request.getParameter("petSex");
		char sex = sexString.charAt(0);
		String type = request.getParameter("petType");
		String breed = request.getParameter("breed");
		String colour = request.getParameter("colour");
		String date_of_birth_pet_str = request.getParameter("petBirthDate");
		Date date_of_birth_pet = Date.valueOf(date_of_birth_pet_str);
		String distinctive_signs = request.getParameter("distinctiveSigns");
		String medication = request.getParameter("medication");

		Pet newPet = new Pet(name, sex, type, breed, colour, date_of_birth_pet, distinctive_signs, medication);
		PetHandler petHandler = new PetHandler();
		UserHandler userHandler = new UserHandler();
		User owner = userHandler.getUserById(userId);
		int petId = petHandler.insertPet(newPet);
		petHandler.createOwnership(userId, petId);
		try {
			QRCodeGenerator.generateQrContactInfo(petId, userId);
			System.out.println("Pet QR Code created successfully.");
		} catch (WriterException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Failed to create the pet QR Code.");
		}
		response.sendRedirect("Dashboard.jsp");
	}

}
