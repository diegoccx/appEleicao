package diego.com.github.controlador;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import diego.com.github.dao.CandidatoDAO;
import diego.com.github.dao.CargoEleicaoDAO;
import diego.com.github.dao.EleicaoDAO;

import diego.com.github.dao.UsuarioDAO;

import diego.com.github.form.CargoEleicaoForm;
import diego.com.github.modelo.Candidato;
import diego.com.github.modelo.Cargo;
import diego.com.github.modelo.Eleicao;
import diego.com.github.modelo.TelefoneTipo;
import diego.com.github.modelo.Usuario;
import diego.com.github.modelo.UsuarioTelefone;

/**
 * Servlet implementaÁ„o da classe controladora CandidatoControlador
 * Diego Cardoso
 */
@WebServlet(description = "Administra as parti√ßoes para a tabela candidato", urlPatterns = { "/candidato" })
@MultipartConfig
public class CandidatoControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CandidatoControlador() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String opcao = request.getParameter("opcao");
		Eleicao eleicao = new Eleicao();
		CargoEleicaoForm cargoForm;
		
		if (opcao.equals("criar_candidato")) {
		    EleicaoDAO eleicaoDAO = new EleicaoDAO();
		    CargoEleicaoDAO cargoDAO = new CargoEleicaoDAO();
		    
			List<Cargo> cargos = new ArrayList<Cargo>();
			List<CargoEleicaoForm> listaCargos = new ArrayList<CargoEleicaoForm>();
			
			List<Integer> listaCargosTam = new ArrayList<Integer>();
			listaCargosTam.add(1);
			listaCargosTam.add(2);
			listaCargosTam.add(3);
			listaCargosTam.add(4);
          
			try {
				cargos = cargoDAO.listarCargos();

				for (Cargo cargo : cargos) {
					System.out.println("NOME DO CARGO"+cargo.getNome());
					System.out.println("ID DA ELEICAO"+cargo.getIdEleicao());
					cargoForm = new CargoEleicaoForm();
					eleicao = eleicaoDAO.obterEleicao(cargo.getIdEleicao());
					
					System.out.println("NOME DA ELEICAO"+eleicao.getNome());
					
					cargoForm.setIdCargoEleicao(cargo.getIdCargoEleicao());
					cargoForm.setIdEleicao(eleicao.getIdEleicao());
					cargoForm.setNomeCargo(cargo.getNome());
					cargoForm.setNomeEleicao(eleicao.getNome());
					
					listaCargos.add(cargoForm);
				}
				
				request.setAttribute("listaCargosForm", listaCargos);
				
				request.setAttribute("listaCargosTam", listaCargosTam);
				System.out.println("lista de cargos tamanho"+listaCargos.size());
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/cadastrar_candidato.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
			
	
		
			
			
			
		 if (opcao.equals("criar_eleicao")) {
				EleicaoDAO eleicaoDAO = new EleicaoDAO();
				

				try {
					
					
					
					System.out.println("Pressionou a op√ß√£o de criar");
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
		
		if (opcao.equals("guardar_candidato")) {
			CandidatoDAO candidatoDAO = new CandidatoDAO();
			
			 HttpSession session=request.getSession();  
			 Usuario usuario = (Usuario) session.getAttribute("usuario");
			Candidato candidato = new Candidato();
			candidato.setNome(request.getParameter("nome"));
			
			if (request.getParameter("id_cargoForm0") != "") {
				System.out.println("ID DO CARGO DO CANDIDATO"+request.getParameter("id_cargoForm"));
				
				
				candidato.setIdCargoEleicao(new Integer(request.getParameter("id_cargoForm0")));
			}
					
			candidato.setQuantidadeVotos(0);
			
			
			Part filePart = request.getPart("photo");
			InputStream inputStream = null; 
	        if (filePart != null) {
	            
	            System.out.println(filePart.getName());
	            System.out.println(filePart.getSize());
	            System.out.println(filePart.getContentType());

	            
	            inputStream = filePart.getInputStream();
	            candidato.setFoto(inputStream);
	        }
			
			try {
				int id_usuario = (int) candidatoDAO.inserirCandidato(candidato);


				
				
				session.setAttribute("msgAviso", "Candidato Cadastrado com Sucesso!");
				session.setAttribute("msgAvisoCor", "green");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/principal.jsp");					
					requestDispatcher.forward(request, response);					
				}
			catch (SQLException e) {
				e.printStackTrace();
			}
		
		
		
		
		
		
	}

}}
