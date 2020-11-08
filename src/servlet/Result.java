package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.RecordDAO;
import model.PlayRecord;
import model.Player;

/**
 * Servlet implementation class Result
 */
@WebServlet("/Result")
public class Result extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Player player = (Player)session.getAttribute("player");

		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat( "yyyy/MM/dd" );
		String dateStr = format.format(date);

		PlayRecord record = new PlayRecord(player.getName(), player.getChip(),dateStr);
		RecordDAO recordDAO = new RecordDAO();

		recordDAO.insert(record);
		session.setAttribute("nowRecord", record);

		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/WEB-INF/jsp/playResult.jsp");
		dispatcher.forward(request, response);

	}

}
