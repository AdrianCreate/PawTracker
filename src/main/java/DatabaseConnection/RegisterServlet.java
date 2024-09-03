package DatabaseConnection;

import java.io.IOException;
import java.sql.Date;

import DatabaseConnection.UserHandler.UserExistsException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//import DatabaseConnection.UserHandler.UserExistsException;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String first_name = request.getParameter("fname");
		String last_name = request.getParameter("lname");
		String password = request.getParameter("password");
		String address = request.getParameter("street_address") + ", " + request.getParameter("building") + ", "
				+ request.getParameter("zipcode");
		String city = request.getParameter("city");
		String email = request.getParameter("email");
		String phone_nr = request.getParameter("phonenr");
		String dateStr = request.getParameter("birthday");
		Date date_of_birth = Date.valueOf(dateStr);
		String sexString = request.getParameter("sex");
		char sex = sexString.charAt(0);

		User newUser = new User(first_name, last_name, password, address, city, email, phone_nr, sex, date_of_birth);
		UserHandler userHandler = new UserHandler();
		try {
			userHandler.insertUser(newUser);
		} catch (UserExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response.sendRedirect("Login.jsp");
	}

}
