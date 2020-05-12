package cliente;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Programa Cliente Gui
 * @author Fatake
 */
public class ClienteGUI extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public ClienteGUI() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    //Envia mensaje de Salida
                    salida.println(encriptar("fn"));
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(ClienteGUI.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    //Termina coneccion
                    socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(ClienteGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
               System.exit(0);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        frameLoggin = new javax.swing.JFrame();
        labelIniciarSesion = new javax.swing.JLabel();
        labelUsuario = new javax.swing.JLabel();
        labelPassword = new javax.swing.JLabel();
        campoUsuarioNombre = new javax.swing.JTextField();
        botonLogeo = new javax.swing.JButton();
        campoPassword = new javax.swing.JPasswordField();
        labelError = new javax.swing.JLabel();
        botonAgregarAmigos = new javax.swing.JButton();
        labelListaAmigos = new javax.swing.JLabel();
        botonIniciarSesion = new javax.swing.JButton();
        scrolPanelAmigos = new javax.swing.JScrollPane();
        panelAmigos = new javax.swing.JPanel();
        botonPrueba = new javax.swing.JButton();
        textoNombreUsuario = new javax.swing.JLabel();

        frameLoggin.setTitle("Loggin");
        frameLoggin.setAlwaysOnTop(true);
        frameLoggin.setMinimumSize(new java.awt.Dimension(320, 200));
        frameLoggin.setSize(new java.awt.Dimension(400, 220));

        labelIniciarSesion.setText("iniciar Sesion");

        labelUsuario.setText("Usuario");

        labelPassword.setText("Password");

        botonLogeo.setText("Loggin");
        botonLogeo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonLogeoMouseClicked(evt);
            }
        });

        campoPassword.setText("jPasswordField1");

        javax.swing.GroupLayout frameLogginLayout = new javax.swing.GroupLayout(frameLoggin.getContentPane());
        frameLoggin.getContentPane().setLayout(frameLogginLayout);
        frameLogginLayout.setHorizontalGroup(
            frameLogginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLogginLayout.createSequentialGroup()
                .addGroup(frameLogginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(frameLogginLayout.createSequentialGroup()
                        .addGroup(frameLogginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelIniciarSesion)
                            .addGroup(frameLogginLayout.createSequentialGroup()
                                .addGroup(frameLogginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(frameLogginLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(labelError))
                                    .addGroup(frameLogginLayout.createSequentialGroup()
                                        .addGap(74, 74, 74)
                                        .addGroup(frameLogginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelPassword)
                                            .addComponent(labelUsuario))))
                                .addGap(66, 66, 66)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(frameLogginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(campoUsuarioNombre)
                            .addComponent(campoPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)))
                    .addGroup(frameLogginLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonLogeo)))
                .addGap(78, 78, 78))
        );
        frameLogginLayout.setVerticalGroup(
            frameLogginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameLogginLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelIniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(frameLogginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelUsuario)
                    .addComponent(campoUsuarioNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(frameLogginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPassword)
                    .addComponent(campoPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(labelError)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(botonLogeo)
                .addGap(50, 50, 50))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat Cliente");

        botonAgregarAmigos.setText("Agregar Amigos");
        botonAgregarAmigos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonAgregarAmigosMouseClicked(evt);
            }
        });

        labelListaAmigos.setText("Lista de Amigos de");

        botonIniciarSesion.setText("Iniciar Sesion");
        botonIniciarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonIniciarSesionMouseClicked(evt);
            }
        });
        botonIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonIniciarSesionActionPerformed(evt);
            }
        });

        scrolPanelAmigos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                scrolPanelAmigosMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelAmigosLayout = new javax.swing.GroupLayout(panelAmigos);
        panelAmigos.setLayout(panelAmigosLayout);
        panelAmigosLayout.setHorizontalGroup(
            panelAmigosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 227, Short.MAX_VALUE)
        );
        panelAmigosLayout.setVerticalGroup(
            panelAmigosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 266, Short.MAX_VALUE)
        );

        scrolPanelAmigos.setViewportView(panelAmigos);

        botonPrueba.setText("Prueba");
        botonPrueba.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonPruebaMouseClicked(evt);
            }
        });

        textoNombreUsuario.setText("inicieSesion");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrolPanelAmigos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(botonAgregarAmigos)
                        .addGap(30, 30, 30)
                        .addComponent(botonPrueba))
                    .addComponent(botonIniciarSesion)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelListaAmigos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textoNombreUsuario)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(botonIniciarSesion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelListaAmigos, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoNombreUsuario))
                .addGap(18, 18, 18)
                .addComponent(scrolPanelAmigos, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonAgregarAmigos)
                    .addComponent(botonPrueba))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonIniciarSesionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonIniciarSesionActionPerformed

    private void scrolPanelAmigosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_scrolPanelAmigosMouseClicked
        
    }//GEN-LAST:event_scrolPanelAmigosMouseClicked

    private void botonPruebaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonPruebaMouseClicked
        Conversacion nuevo = new Conversacion();
        nuevo.setVisible(true);
    }//GEN-LAST:event_botonPruebaMouseClicked

    private void botonAgregarAmigosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonAgregarAmigosMouseClicked
        ItemAmigo nuevo = new ItemAmigo();
        panelAmigos.add(nuevo);
        scrolPanelAmigos.updateUI();
    }//GEN-LAST:event_botonAgregarAmigosMouseClicked

    private void botonIniciarSesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonIniciarSesionMouseClicked
        frameLoggin.setVisible(true);
    }//GEN-LAST:event_botonIniciarSesionMouseClicked

    private void botonLogeoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonLogeoMouseClicked
        String nombre = campoUsuarioNombre.getText();
        String password = campoPassword.getText();
        if(nombre.equals("") || password.equals("") ){
            labelError.setText("No ingresó el nombre o contraseña");
        }else{
            labelError.setText("");
            //Envia Nombre Usuario
            try {
                salida.println(encriptar("us,"+nombre));
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(ClienteGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //Recibe mensaje Aleatorio
            String mensajeAleatorio = null;
            try {
                mensajeAleatorio = desencriptar( entrada.readLine() );
            } catch (IOException ex) {
                Logger.getLogger(ClienteGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Separa, donde str[0] tipo de mensaje
            // str[1] mensaje
            String str[] = mensajeAleatorio.split(",");
            if (str[0].equals("un")) {
                    labelError.setText("Usuario no registrado u.u");
            }else{
                mensajeAleatorio = str[1];

                //Mezcla el mensaje
                String textoMezclado = new Mezclador().mezcla(mensajeAleatorio, password);

                //Genera MD5 y envia
                String md5cli = new MD5().getMD5(textoMezclado);
                
                try {
                    salida.println( encriptar("md,"+md5cli) );
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(ClienteGUI.class.getName()).log(Level.SEVERE, null, ex);
                }

                String confirma = "";
                try {
                    // Recibe confirmacion
                    confirma = desencriptar( entrada.readLine() );
                } catch (IOException ex) {
                    Logger.getLogger(ClienteGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                //Si empieza con cn entonces existe coneccion
                if (confirma.equals("cn")) {
                    this.usuario = new Usuario(nombre,password);
                    textoNombreUsuario.setText(this.usuario.getName());
                    frameLoggin.setVisible(false);
                }else{
                     labelError.setText("Usuario no Encontrado"); 
                }
            }
        }
    }//GEN-LAST:event_botonLogeoMouseClicked

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String args[]) throws IOException {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClienteGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        //Inicia el servidord
        try{
            //Abre el socket
            socket = new Socket(SERVIDOR_IP,PUERTO_SERVER);

            //Habilita Escuchadores de entrada y salida
            ClienteGUI.entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ClienteGUI.salida = new PrintWriter( new OutputStreamWriter(socket.getOutputStream() ),true );
        }catch(UnknownHostException e){
            e.printStackTrace();
            System.exit(-1);
        }catch(IOException e){
            e.printStackTrace();
            System.exit(-1);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new ClienteGUI().setVisible(true);
        });
        
      
    }
    
    /**
     * 
     * Metodo Encriptar
     * @param s
     * @return
     * @throws UnsupportedEncodingException 
     */
    private static String encriptar(String s) throws UnsupportedEncodingException{
        return Base64.getEncoder().encodeToString(s.getBytes("utf-8"));
    }
    
    /**
     * Metodo Desencriptar
     * @param s
     * @return
     * @throws UnsupportedEncodingException 
     */
    private static String desencriptar(String s) throws UnsupportedEncodingException{
        byte[] decode = Base64.getDecoder().decode(s.getBytes());
        return new String(decode, "utf-8");
    }
    //Constantes
    public static final String SERVIDOR_IP = "localhost";
    public static final int PUERTO_SERVER = 9999;
    
    //Variables no Graficas
    private Usuario usuario;
    private static BufferedReader entrada;
    private static PrintWriter salida; 
    private static Socket socket;
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAgregarAmigos;
    private javax.swing.JButton botonIniciarSesion;
    private javax.swing.JButton botonLogeo;
    private javax.swing.JButton botonPrueba;
    private javax.swing.JPasswordField campoPassword;
    private javax.swing.JTextField campoUsuarioNombre;
    private javax.swing.JFrame frameLoggin;
    private javax.swing.JLabel labelError;
    private javax.swing.JLabel labelIniciarSesion;
    private javax.swing.JLabel labelListaAmigos;
    private javax.swing.JLabel labelPassword;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JPanel panelAmigos;
    private javax.swing.JScrollPane scrolPanelAmigos;
    private javax.swing.JLabel textoNombreUsuario;
    // End of variables declaration//GEN-END:variables
}
