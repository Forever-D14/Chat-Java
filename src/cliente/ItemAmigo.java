package cliente;

/**
 *
 * @author pace_
 */
public class ItemAmigo extends javax.swing.JPanel {
        ClienteGUI c;
    /**
     * Creates new form itemAmigo
     */
    public ItemAmigo(String nombre,ClienteGUI cliente) {
        initComponents();
        this.labelNombreAmigo.setText(nombre);
        this.c=cliente;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelNombreAmigo = new javax.swing.JLabel();
        botonMensajear = new javax.swing.JButton();

        labelNombreAmigo.setText("Nombre Amigo");

        botonMensajear.setText("Mensajear");
        botonMensajear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMensajearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelNombreAmigo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(botonMensajear)
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNombreAmigo)
                    .addComponent(botonMensajear))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void botonMensajearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonMensajearActionPerformed

        Conversacion conv = new Conversacion(this.labelNombreAmigo.getText());
        conv.setBounds(conv.getBounds());
        conv.setVisible(true);
        


        // TODO add your handling code here:
    }//GEN-LAST:event_botonMensajearActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonMensajear;
    private javax.swing.JLabel labelNombreAmigo;
    // End of variables declaration//GEN-END:variables
}
