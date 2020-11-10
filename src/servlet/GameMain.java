package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Dealer;
import model.Deck;
import model.Main;
import model.Player;


//デッキを少数にしたデッキのクリエイトメソッド、デッキの新しいコンストラクタ、
//必ず1を引くdealerのdraw, それを使ったMain

/**
 * Servlet implementation class GameMain
 */
@WebServlet("/GameMain")
public class GameMain extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Main main = new Main();

		HttpSession session = request.getSession();
		Player player = (Player) session.getAttribute("player");
		Dealer dealer = (Dealer) session.getAttribute("dealer");
		Deck deck = (Deck) session.getAttribute("deck");
		Deck graveDeck = (Deck) session.getAttribute("graveDeck");

		if(dealer == null || deck == null || graveDeck == null) {
			dealer = new Dealer();
			deck = new Deck();
			graveDeck = new Deck();

			deck.create();
			deck.shuffle();
		}


		main.start(player, dealer, deck, graveDeck);

		session.setAttribute("player", player);
		session.setAttribute("dealer", dealer);
		session.setAttribute("deck", deck);
		session.setAttribute("graveDeck", graveDeck);

		response.sendRedirect("/gameMain.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		Main main = new Main();

		HttpSession session = request.getSession();
		Player player = (Player) session.getAttribute("player");
		Dealer dealer = (Dealer) session.getAttribute("dealer");
		Deck deck = (Deck) session.getAttribute("deck");
		Deck graveDeck = (Deck) session.getAttribute("graveDeck");


		boolean finish = main.action(action, player, deck, graveDeck);

		String redirectPath;
		if(finish) {
			if(player.getHandSum() < 22 && !action.equals("fold")) {
				while(dealer.getHandSum() < 17) {
					dealer.draw(deck, graveDeck);
				}
			}
			String result = main.result(player, dealer);
			session.setAttribute("result", result);
			redirectPath = "/gameResult.jsp";
		}else {
			redirectPath = "/gameMain.jsp";
		}

		session.setAttribute("player", player);
		session.setAttribute("dealer", dealer);
		session.setAttribute("deck", deck);
		session.setAttribute("graveDeck", graveDeck);

		response.sendRedirect(redirectPath);
	}

}
