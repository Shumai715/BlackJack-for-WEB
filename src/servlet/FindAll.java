package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RecordDAO;
import dao.UserDAO;
import model.PlayRecord;
import model.User;

/**
 * Servlet implementation class FindAll
 */
@WebServlet("/FindAll")
public class FindAll extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindAll() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDAO userDAO = new UserDAO();
		RecordDAO recordDAO = new RecordDAO();
		List<User> userList = null;


		userList = userDAO.findAll();


		System.out.println("セレクト→" + userDAO.select("やまもと").getPass());
		for(User user: userList) {
			System.out.println("名前:" + user.getName());
			System.out.println("パス:" + user.getPass());
		}



		System.out.println("しずえMAX:" + recordDAO.getMaxScore("しずえ"));
		System.out.println("しずえCOUNT" + recordDAO.getPlayCount("しずえ"));

		System.out.println("しずおMAX:" + recordDAO.getMaxScore("しずお"));
		System.out.println("しずおCOUNT" + recordDAO.getPlayCount("しずお"));


		List<PlayRecord> playRecordList = null;
		playRecordList = recordDAO.getUserRecord("しずえ");



		response.sendRedirect("/");
	}

}
