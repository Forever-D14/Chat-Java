import java.io.*;
import java.net.*;
import java.security.SecureRandom;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Base64;
import java.io.UnsupportedEncodingException;

public class Servidor {  
	//Lista de Usuarios
	private ArrayList<Usuario> usuarios = new ArrayList<>();
	private static ArrayList<GestorPeticion> hilos = new ArrayList<>();

	/*
	 * Main
	 */
	public static void main(String[] args) {
		// Variables auxiliares
		Servidor aux = new Servidor();
		LectorArchivo lec = new LectorArchivo();
		// Socket Servidor
		ServerSocket socketServer = null;
		//Socket Cliente
		Socket socketDespachador = null;

		// Lee la base de datos de los usuarios
		// Si no exsite, entonces sale
		if ((aux.usuarios = lec.procesaArchivo("usuarios.dat"))== null) {
			System.out.println("Error al leer usuarios.dat");
			System.exit(-1);
		}
		
		// imprime a los usuarios
		aux.imprimeUsuarios();

		// Intenta la coneccion con el socker servidor
		try {
			//                             Puerto
			socketServer = new ServerSocket(9999);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		// Inicia el servicio de escucha		
		System.out.println("Escuchando: " + socketServer);
		
		// Inicia el proceso de atender peticiones
		while(true){
			try {
				// Se crea un socket despachador
				socketDespachador = socketServer.accept();
				System.out.println("Nueva conexion aceptada: " + socketDespachador);
				// Se crea un Hilo para esa peticion
				GestorPeticion nuevo = new GestorPeticion(socketDespachador,aux.usuarios);
				hilos.add( nuevo ) ;
				nuevo.start();
				socketDespachador = null;
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
	}

	/**
	 * funcion que imprime usuarios de la Base de datos
	 */
	private void imprimeUsuarios(){
		System.out.println("Usuarios Registrados\n<------------------------>");
		for (Usuario usuario : usuarios) {
			System.out.println(usuario.toString());
		}
		System.out.println("<------------------------>\n");
	}
}

/*
 * Clase Gestor de peticiones
 * Se crea un hilo por cada enlace nuevo
 */
class GestorPeticion extends Thread {
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	BufferedReader entrada = null;
	PrintWriter salida = null;
	Socket socket;

	// Constructor sin nombre
	public GestorPeticion(Socket socket,ArrayList<Usuario> usuarios){
		this.socket = socket;
		this.usuarios = usuarios;
	}

	// Constructor con nombre
	public GestorPeticion(String nombre, Socket socket,ArrayList<Usuario> usuarios){
		super(nombre);
		this.socket = socket;
		this.usuarios = usuarios;
	}

	/*
	 * Instruccion de ejecucion
	 */
	public void run(){
		//System.out.print("\033[H\033[2J");  
		//System.out.flush();
		System.out.println("\n\n<----------------->"); 
		Usuario user = null;
		int indexUser = 0;
		String textoAleatorio = "";
		String textoMezclado  = "";
		boolean cambiosBD = false;
		try{
			entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			while (true){
				System.out.print("\033[H\033[2J");  
				System.out.flush();
				System.out.println("\n\n<----------------->"); 
				//Lee lo que se reciba en el Socket
				String str = desencriptar( entrada.readLine() );
				System.out.println("-> " + str);
				//Separa lo que se lee
				String aux[] = str.split(",");
				//
				if (aux[0].startsWith("us")) {//Recibe Usuaro
					indexUser = buscaUsuario(aux[1]);

					if (indexUser == -1) {//Si no se encuentra el usuario
						System.out.println("Usuario no Encontrado");
						salida.println(encriptar("un,"+"null"));
					}else{//Si lo encuentra
						Mezclador mes = new Mezclador();
						user = usuarios.get(indexUser);
						System.out.println("Usuario Encontrado");

						//Gerenando texto Aleatorio
						textoAleatorio = generaTexto();
						System.out.println("Texto Generado:\n"+textoAleatorio+"\n");
						salida.println(encriptar("ms,"+textoAleatorio));

						//Texto aletorio mezclado
						textoMezclado = mes.mezcla(textoAleatorio, user.getPass());
						System.out.println("Texto Mezclado:\n"+textoMezclado+"\n");
					}
				}else if (aux[0].startsWith("md")) {//Recibe md
					MD5 gen = new MD5();
					String md5cli = aux[1];
					String md5ser = gen.getMD5(textoMezclado);
					System.out.println("MD5Cli:\n"+md5cli+"\n");
					System.out.println("MD5Ser:\n"+md5ser+"\n");
					
					if (md5ser.equals(md5cli)) {
						System.out.println("Contraseña Correcta");
						salida.println(encriptar("cn"));
						// Si la contraseña es correcta
						// SE cambia el nombre del hilo al del usuario
						try {
							this.setName(user.getName());
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}else{
						System.out.println("Contraseña Incorrecta");
						salida.println( encriptar("nn") );
					}
				}else if (aux[0].startsWith("ls")) {//Lista de Amigos
					//Se obtiene lista de amigos                    
					ArrayList<String> aux3 = new ArrayList<>();
					for(int i=0;i<user.getAmigos().size();i++)
						aux3.add(user.getAmigos().get(i));
					//Se cambia el formato
					String aux2 = aux3.toString();
					aux2 = aux2.replace("[", "");
					aux2 = aux2.replace("]", "");
					System.out.println("Amigos: "+aux2);
					salida.println( encriptar(aux2) );
                                        
					aux3 = new ArrayList<String>();
					//Se envia la lista de usuarips
					for(int i=0;i<this.usuarios.size();i++)
						aux3.add(this.usuarios.get(i).getName());
					//Se quita el formato
					String aux4 = aux3.toString();
					aux4 = aux4.replace("[", "");
					aux4 = aux4.replace("]", "");
					System.out.println("Usuarios: "+aux4);
					salida.println( encriptar(aux4) );
				
				}else if (aux[0].startsWith("ac")) {//Actualizar lista amigos
					String listaNueva = aux[1];
					System.out.println("Recibiendo lista nueva: \n"+aux[1]);
					
					String strss[] = listaNueva.split(",");
					List<String> al = new ArrayList<>();
					al =  Arrays.asList(strss);
					ArrayList<String> aux3 = new ArrayList<String>(al);

					usuarios.get(indexUser).setAmigos(aux3);
					cambiosBD = true;
				}
				// Cierra Coneccion
				if(str.equals("fn")){
					System.out.println("Cerrando Coneccion");
					System.out.println("<----------------->\n\n"); 
					break;
				}
			}//Fin While
			if (cambiosBD) {
				System.out.println("Actualizando Base de Datos");
				LectorArchivo lec = new LectorArchivo();
				lec.escribeArchivo("usuarios.dat", usuarios);
			}
			//Cierra la tuberia
			salida.close();
			entrada.close();
		}
		catch(Exception e){
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	/*
	 * Encriptador
	 */
	private String encriptar(String s) throws UnsupportedEncodingException{
        return Base64.getEncoder().encodeToString(s.getBytes("utf-8"));
    }
	
	/*
	 * Desincriptador
	 */
    private String desencriptar(String s) throws UnsupportedEncodingException{
        byte[] decode = Base64.getDecoder().decode(s.getBytes());
        return new String(decode, "utf-8");
    }

	/*
	 * Busca ujsuarios en Base de Datos
	 */
	private int buscaUsuario(String userName){
		int posicion = -1;
		for (int i = 0; i < usuarios.size(); i++) {
			Usuario user = usuarios.get(i);
			String nombre = user.getName();
			if (nombre.equals(userName)) {
				posicion = i;
				break;
			}
		}
		return posicion;
	}

	/*
	 * Generador de texto aleatorio
	 */
	private String generaTexto(){
		SecureRandom random = new SecureRandom();
 		String text = new BigInteger(586, random).toString(32);
 		return text;
	}
}

/*
 * Clase Lector Archivo
 */
class LectorArchivo {
    private ArrayList<String> contenidoArchivo = new ArrayList<String>();
    public LectorArchivo() { };
    /*
     * LeerArchivo Guarda en forma de String todas las lineas
     * leidas del archivo en el atributo contenidoArchivo
     * recibe:
     * * String fileName
     */
    private boolean leerArchivo(String fileName){
        File archivo = new File (fileName);
        FileReader fr = null;
        BufferedReader br = null;
        String linea;

        // Lectura del fichero
        try {
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            // hacer una lectura comoda (disponer del metodo readLine()).
            while ((linea = br.readLine()) != null){
                this.contenidoArchivo.add(linea);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try{                    
               if( null != fr ){   
                  fr.close();     
               }                  
            }catch (Exception e2){ 
               e2.printStackTrace();
               return false;
            }
        }//Fin lectura archivo
        return true;
    }

    /*
     * procesaArchivo 
     * convietiendo los campos a floats
     * Retorna:
     * ArrayList<Float[]>
     */
    public ArrayList<Usuario> procesaArchivo(String fileName){
        if (leerArchivo(fileName) == false) {
            return null;
        }
        if (contenidoArchivo.isEmpty()) {
            return null;
        }
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        for (int i = 0; i < contenidoArchivo.size(); i++) {
            String aux = contenidoArchivo.get(i);
            String aux2[] = aux.split(",");
            usuarios.add(new Usuario(aux2[0], aux2[1], aux2[2] ));
        }
        contenidoArchivo = null;
        return usuarios;
    }

	public boolean escribeArchivo(String fileName,ArrayList<Usuario> contendido){
		File archivo = new File (fileName);
		FileWriter fw = null;
        PrintWriter pw = null;

		// Lectura del fichero
		try {
			fw = new FileWriter(archivo);
			pw = new PrintWriter(fw);
			// hacer una lectura comoda (disponer del metodo readLine()).
			for (Usuario usuario : contendido) {
				pw.println(usuario.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{                    
				if( null != fw ){   
					fw.close();     
				}                  
			}catch (Exception e2){ 
				e2.printStackTrace();
				return false;
			}
		}//Fin lectura archivo
		return true;
	}
}
