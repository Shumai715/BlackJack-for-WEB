package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.User;

/**
 * Servlet implementation class RegisterUser
 */
@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String forwardPath;

		UserDAO userDAO = new UserDAO();

		User user = userDAO.select(name);
		if(user == null) {
			user = new User(name, pass);
			userDAO.userAdd(user);
			forwardPath = "/WEB-INF/jsp/registerOK.jsp";
		}else {
			request.setAttribute("alreadyName", "true");
			forwardPath = "/registerUserForm.jsp";
		}

		RequestDispatcher dispatcher =
				request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}

}
