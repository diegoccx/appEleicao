package diego.com.github.controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import diego.com.github.dao.CandidatoDAO;
import diego.com.github.dao.CandidatoVotoDAO;
import diego.com.github.dao.CargoEleicaoDAO;
import diego.com.github.dao.EleicaoDAO;
import diego.com.github.dao.UsuarioDAO;
import diego.com.github.form.CargoEleicaoForm;
import diego.com.github.form.EleicaoCargoCandidatosForm;
import diego.com.github.modelo.Candidato;
import diego.com.github.modelo.CandidatoVoto;
import diego.com.github.modelo.Cargo;
import diego.com.github.modelo.Eleicao;
import diego.com.github.modelo.TelefoneTipo;
import diego.com.github.modelo.Usuario;
import diego.com.github.modelo.UsuarioTelefone;

/**
 * Servlet implementação da classe controladora CandidatoControlador
 * Diego Cardoso
 */
@WebServlet(description = "Administra as partiÃ§oes para a tabela candidato", urlPatterns = { "/candidatoVoto" })
public class CandidatoVotoControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Integer totalVotos = 0; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CandidatoVotoControlador() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String opcao = request.getParameter("opcao");
		
		CargoEleicaoForm cargoForm;
		
		if (opcao.equals("criar_voto")) {
		    EleicaoDAO eleicaoDAO = new EleicaoDAO();
		    CargoEleicaoDAO cargoDAO = new CargoEleicaoDAO();
		    CandidatoDAO  candidatoDAO = new CandidatoDAO();
		    CandidatoVotoDAO  candidatoVotoDAO = new CandidatoVotoDAO();
		    
		    HttpSession session=request.getSession();  
			Usuario usuario = (Usuario) session.getAttribute("usuario");
		   
			
			try {
				if(candidatoVotoDAO.jaVotou(usuario.getCpf())){
					session.setAttribute("msgAviso", "Você já realizou a votação!");
					session.setAttribute("msgAvisoCor", "green");
					
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/principal.jsp");					
						requestDispatcher.forward(request, response);	
				}
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		    
		    
			List<Cargo> cargos = new ArrayList<Cargo>();
			List<Eleicao> eleicoes = new ArrayList<Eleicao>();
			List<CargoEleicaoForm> listaCargos = new ArrayList<CargoEleicaoForm>();
			List<EleicaoCargoCandidatosForm> listaEleicaoCargos = new ArrayList<EleicaoCargoCandidatosForm>();
			
			EleicaoCargoCandidatosForm eleicaoCandidato = new EleicaoCargoCandidatosForm();
			try {
				eleicoes = eleicaoDAO.listarEleicoes();
				
				totalVotos = 0;
				for (Eleicao eleicao : eleicoes) {
					
					cargos = cargoDAO.listarCargosEleicao(eleicao.getIdEleicao());
					
					for (Cargo cargo : cargos) {
				    	System.out.println("CARGO:"+cargo.getNome());

						
						eleicaoCandidato = new EleicaoCargoCandidatosForm();
						eleicaoCandidato.setCandidatos(candidatoDAO.listarCandidatosCargo(cargo.getIdCargoEleicao()));
					    eleicaoCandidato.setIdEleicao(eleicao.getIdEleicao());
					    eleicaoCandidato.setNomeEleicao(eleicao.getNome());
					    eleicaoCandidato.setIdCargo(cargo.getIdCargoEleicao());
					    eleicaoCandidato.setNomeCargo(cargo.getNome());
					    if(eleicaoCandidato.getCandidatos() != null && eleicaoCandidato.getCandidatos().size()>0) {
					    	listaEleicaoCargos.add(eleicaoCandidato);
					    	totalVotos = totalVotos + 1;
					    	System.out.println("QUANTIDADE DE CANDIDATOS ELEICAO:"+eleicaoCandidato.getCandidatos().size());
					    }
					}
					
					
					
					
				}
				
				request.setAttribute("listaCargosVotoForm", listaEleicaoCargos);
				System.out.println("lista de cargos tamanho"+listaEleicaoCargos.size());
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/votar.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		if (opcao.equals("resultado_votacao")) {
		    EleicaoDAO eleicaoDAO = new EleicaoDAO();
		    CargoEleicaoDAO cargoDAO = new CargoEleicaoDAO();
		    CandidatoDAO  candidatoDAO = new CandidatoDAO();
		    
		    
			List<Cargo> cargos = new ArrayList<Cargo>();
			List<Eleicao> eleicoes = new ArrayList<Eleicao>();
			List<CargoEleicaoForm> listaCargos = new ArrayList<CargoEleicaoForm>();
			
			List<EleicaoCargoCandidatosForm> listaEleicaoParcial = new ArrayList<EleicaoCargoCandidatosForm>();
			List<EleicaoCargoCandidatosForm> listaEleicaoFinal = new ArrayList<EleicaoCargoCandidatosForm>();

			EleicaoCargoCandidatosForm eleicaoCandidato = new EleicaoCargoCandidatosForm();
			try {
				eleicoes = eleicaoDAO.listarEleicoes();
				

				for (Eleicao eleicao : eleicoes) {
					
					cargos = cargoDAO.listarCargosEleicao(eleicao.getIdEleicao());
					
					for (Cargo cargo : cargos) {
				    	System.out.println("CARGO:"+cargo.getNome());
				    	
				    	
				    	
						
						eleicaoCandidato = new EleicaoCargoCandidatosForm();
						eleicaoCandidato.setCandidatos(candidatoDAO.listarCandidatosCargo(cargo.getIdCargoEleicao()));
					    eleicaoCandidato.setIdEleicao(eleicao.getIdEleicao());
					    eleicaoCandidato.setNomeEleicao(eleicao.getNome());
					    eleicaoCandidato.setIdCargo(cargo.getIdCargoEleicao());
					    eleicaoCandidato.setNomeCargo(cargo.getNome());
					    
					    for(Candidato candidato:eleicaoCandidato.getCandidatos()) {
					    	String encode = Base64.getEncoder().encodeToString(candidato.getFotoImagem());
				            System.out.println("STRING DA IMAGEM:"+encode);
				            candidato.setCodigoFoto(encode);
					    	request.setAttribute("imgBase", encode);
				           
					    	
					    }
					    
					    if(eleicaoCandidato.getCandidatos() != null) {
					    	if(eleicao.getDataFim().before(new Date())) {
					    		listaEleicaoFinal.add(eleicaoCandidato);
					    	}else {
					    		listaEleicaoParcial.add(eleicaoCandidato);
					    	}
					    		
					    	
					    	
					    	System.out.println("QUANTIDADE DE CANDIDATOS ELEICAO-RESULTADO FINAL:"+eleicaoCandidato.getCandidatos().size());
					    }
					}
					
					
					
					
				}
				
				request.setAttribute("listaVotosFinal", listaEleicaoFinal);
				request.setAttribute("listaVotosParcial", listaEleicaoParcial);
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/resultado_eleicao.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		
		
			
			
		 if (opcao.equals("criar_eleicao")) {
				EleicaoDAO eleicaoDAO = new EleicaoDAO();
				

				try {
					
					
					
					System.out.println("Pressionou a opÃ§Ã£o de criar");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/cadastrar_eleicao.jsp");
					requestDispatcher.forward(request, response);
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
			
		} 
			
			
			System.out.println("Pressionou a opÃ§Ã£o de listar");
		} 


	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String opcao = request.getParameter("opcao");
		String protocolo = "";
		if (opcao.equals("guardar_voto")) {
			CandidatoVotoDAO candidatoVotoDAO = new CandidatoVotoDAO();
			CandidatoDAO candidatoDAO = new CandidatoDAO();
			
			CandidatoVoto candidatoVoto = null;
			
			HttpSession session=request.getSession();  
			Usuario usuario = (Usuario) session.getAttribute("usuario");
			
			
			System.out.println("USUARIO CPF:"+usuario.getCpf());
			
			int min = 1000;
		      int max = 9999;
		        
		     
		     
		      protocolo = "";
		      for(int i=0;i<4;i++) {
		      int protocolo_int = (int)Math.floor(Math.random()*(max-min+1)+min);
		      
		      if(i==3) {
		      protocolo = protocolo + protocolo_int;
		      } else {
		    	  protocolo = protocolo + protocolo_int+"-";
		      }
		      
		      }
			
			for(int i=0;i<totalVotos;i++) {
			if (request.getParameter("id_cargoForm"+i) != "") {
				candidatoVoto = new CandidatoVoto();
				candidatoVoto.setNome(usuario.getNome());
				candidatoVoto.setCpf(usuario.getCpf());
				candidatoVoto.setProtocolo(protocolo);
				
				
				candidatoVoto.setIdCandidatoCargo(new Integer(request.getParameter("id_cargoForm"+i)));
				System.out.println("ID DO CARGO DO CANDIDATO VOTADO+"+request.getParameter("id_cargoForm"+i));
				
			
				try {
					candidatoDAO.somarVotoEleicao(candidatoVoto.getIdCandidatoCargo());
					int id_usuario = (int) candidatoVotoDAO.inserirCandidatoVoto(candidatoVoto);


									
					}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			session.setAttribute("msgAviso", "Voto realizado com  Sucesso! seu número de protocolo é:"+protocolo);
			session.setAttribute("msgAvisoCor", "green"); 
			
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/principal.jsp");					
				requestDispatcher.forward(request, response);	
				
			
		
			}
			
		}
		
		
		
	}

}
