package diego.com.github.controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import diego.com.github.dao.CargoEleicaoDAO;
import diego.com.github.dao.EleicaoDAO;
import diego.com.github.modelo.Cargo;
import diego.com.github.modelo.Eleicao;
import diego.com.github.modelo.Usuario;

/**
 * Servlet implementa��o da classe controladora CargoControlador
 * Diego Cardoso
 */
@WebServlet(description = "Administra as partiçoes para a tabela cargo", urlPatterns = { "/cargo" })
public class CargoControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CargoControlador() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String opcao = request.getParameter("opcao");
		
		
		if (opcao.equals("criar_cargo")) {
		    EleicaoDAO eleicaoDAO = new EleicaoDAO();
			List<Eleicao> eleicoes = new ArrayList<Eleicao>();

			try {
				eleicoes = eleicaoDAO.listarEleicoes();

				for (Eleicao eleicao : eleicoes) {
					System.out.println(eleicao.getNome());
				}
				
				request.setAttribute("listaEleicoes", eleicoes);
				System.out.println("Pressionou a opção de criar");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/cadastrar_cargo.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
			
		
			
			
			
		 if (opcao.equals("criar_eleicao")) {
				EleicaoDAO eleicaoDAO = new EleicaoDAO();
				

				try {
					
					
					
					System.out.println("Pressionou a opção de criar");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/cadastrar_eleicao.jsp");
					requestDispatcher.forward(request, response);
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
			
		} 
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String opcao = request.getParameter("opcao");
		
		if (opcao.equals("guardar_cargo")) {
			CargoEleicaoDAO cargoEleicaoDAO = new CargoEleicaoDAO();

			HttpSession session=request.getSession();  
			 Usuario usuario = (Usuario) session.getAttribute("usuario");
			Cargo cargo = new Cargo();
			cargo.setNome(request.getParameter("nome"));
			
			if (request.getParameter("id_eleicao") != "") {
				System.out.println("ID DA ELEICAO"+request.getParameter("id_eleicao"));
				cargo.setIdEleicao(new Integer(request.getParameter("id_eleicao")));
			}
			
			try {
				int id_usuario = (int) cargoEleicaoDAO.inserirCargoEleicao(cargo);

				
				session.setAttribute("msgAviso", "Cargo Cadastrado com Sucesso!");
				session.setAttribute("msgAvisoCor", "green");
				
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/principal.jsp");					
					requestDispatcher.forward(request, response);					
				}
			catch (SQLException e) {
				e.printStackTrace();
			}
		
		}

}}
