package diego.com.github.controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import diego.com.github.dao.EleicaoDAO;
import diego.com.github.dao.UsuarioDAO;
import diego.com.github.modelo.Eleicao;
import diego.com.github.modelo.TelefoneTipo;
import diego.com.github.modelo.Usuario;

/**
 * Servlet implementa��o da classe controladora EleicaoControlador
 * Diego Cardoso
 */
@WebServlet(description = "Administra as partiçoes para a tabela eleicao", urlPatterns = { "/eleicao" })
public class EleicaoControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EleicaoControlador() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String opcao = request.getParameter("opcao");
		
		
		if (opcao.equals("criar_cargo")) {
			
			List<TelefoneTipo> lista5 = new ArrayList<>();

			try {
				

				for (TelefoneTipo telefoneTipo : lista5) {
					System.out.println(telefoneTipo);
				}
				
				request.setAttribute("lista", lista5);
				System.out.println("Pressionou a opção de criar");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/cadastrar_cargo.jsp");
				requestDispatcher.forward(request, response);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
			
		if (opcao.equals("votar")) {
			
			List<TelefoneTipo> lista6 = new ArrayList<>();

			try {
				lista6 = null;
				
				request.setAttribute("lista", lista6);
				System.out.println("Pressionou a opção de criar");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/votar.jsp");
				requestDispatcher.forward(request, response);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		
			
			if (opcao.equals("criar_candidato")) {
				
				List<TelefoneTipo> lista3 = new ArrayList<>();

				try {
					
					
					request.setAttribute("lista", lista3);
					System.out.println("Pressionou a opção de criar");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/cadastrar_candidato.jsp");
					requestDispatcher.forward(request, response);
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			}
			
		if (opcao.equals("criar")) {
			
			List<TelefoneTipo> lista = new ArrayList<>();

			try {
				
				request.setAttribute("lista", lista);
				System.out.println("Pressionou a opção de criar");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/criar.jsp");
				requestDispatcher.forward(request, response);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			
		} else if (opcao.equals("criar_eleicao")) {
				EleicaoDAO eleicaoDAO = new EleicaoDAO();
				

				try {
					
					
					
					System.out.println("Pressionou a opção de criar");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/cadastrar_eleicao.jsp");
					requestDispatcher.forward(request, response);
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
			
		} else if (opcao.equals("listar")) {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			List<Usuario> arrayUsuario = new ArrayList<>();
			
			
			try {

				arrayUsuario = usuarioDAO.listarUsuarios();

				
				

				request.setAttribute("arrayUsuario", arrayUsuario);
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/listar.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			
			System.out.println("Pressionou a opção de listar");
		} else if(opcao.equals("meditar")) {
			int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
			System.out.println("Editar id: "+ id_usuario);

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			Usuario u = new Usuario();
			
			

			
			try {
				u = usuarioDAO.listarUsuario(id_usuario);
				
				request.setAttribute("usuario", u);
				
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/editar.jsp");
				requestDispatcher.forward(request, response);
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		} else if (opcao.equals("deletar")) {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			

			int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
			try {
				usuarioDAO.deletarUsuario(id_usuario);
				System.out.println("Exclusão do id " + request.getParameter("id_usuario") + " realizado com sucesso!");
				System.out.println("Exclusão de todos os números do id " + request.getParameter("id_usuario") + " realizado com sucesso!");

				HttpSession session=request.getSession();  
				session.setAttribute("msgAviso", "Exclusão realizada com sucesso!");
				session.setAttribute("msgAvisoCor", "green");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/principal.jsp");
				requestDispatcher.forward(request, response);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(opcao.equals("voltar")) {
			String url = "";
			HttpSession session = request.getSession();
			if(session.getAttribute("usuario") == null || session.getAttribute("usuario") == "") {
				url = "index.jsp";
			}
			else {
				String view = request.getParameter("view");

				url = "views/"+view;
			}
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
			requestDispatcher.forward(request, response);
		}

		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String opcao = request.getParameter("opcao");
		
		if (opcao.equals("guardar_eleicao")) {
			EleicaoDAO eleicaoDAO = new EleicaoDAO();
			
			Eleicao eleicao = new Eleicao();
			eleicao.setNome(request.getParameter("nome"));
			HttpSession session=request.getSession();  
			 Usuario usuario = (Usuario) session.getAttribute("usuario");
			
            
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	        String dataInicio = request.getParameter("dataInicio");
	        String dataFim = request.getParameter("dataFim");
	        
	        try {
	        	eleicao.setDataInicio(new java.sql.Date( formatter.parse(dataInicio).getTime()));
	        	eleicao.setDataFim(new java.sql.Date( formatter.parse(dataFim).getTime()));
	           
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
			try {
				int id_usuario = (int) eleicaoDAO.inserirEleicao(eleicao);


				session.setAttribute("msgAviso", "Elei��o Cadastrada com Sucesso!");
				session.setAttribute("msgAvisoCor", "green");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/principal.jsp");					
					requestDispatcher.forward(request, response);					
				}
			catch (SQLException e) {
				e.printStackTrace();
			}
		
		
		
		
		
		//doGet(request, response);
	}

}}
