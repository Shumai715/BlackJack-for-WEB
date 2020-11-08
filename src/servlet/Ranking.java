package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.RecordDAO;
import model.PlayRecord;

/**
 * Servlet implementation class Ranking
 */
@WebServlet("/Ranking")
public class Ranking extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ranking() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RecordDAO recordDAO = new RecordDAO();

		List<PlayRecord> rankingList = recordDAO.getRankingRecord();
		HttpSession session = request.getSession();
		session.setAttribute("rankingList", rankingList);

		PlayRecord nowRecord = (PlayRecord)session.getAttribute("nowRecord");
		for(PlayRecord record: rankingList) {
			if(record.equals(nowRecord)) {
				System.out.println("true");
			}else {
				System.out.println("false");
			}
		}

		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/ranking.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RecordDAO recordDAO = new RecordDAO();

		List<PlayRecord> rankingList = recordDAO.getRankingRecord();
		HttpSession session = request.getSession();
		session.setAttribute("rankingList", rankingList);

		PlayRecord nowRecord = (PlayRecord)session.getAttribute("nowRecord");
		for(PlayRecord record: rankingList) {
			if(record.equals(nowRecord)) {
				System.out.println("true");
			}else {
				System.out.println("false");
			}
		}

		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/ranking.jsp");
		dispatcher.forward(request, response);
	}

}
