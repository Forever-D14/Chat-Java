/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Ignacio Diaz Romero
 */
public class ItemAgregar extends javax.swing.JPanel {
        ClienteGUI c;
    /**
     * Creates new form ItemAgregar
     */
    public ItemAgregar(String nombre,ClienteGUI cliente) {
        initComponents();
        this.label_nombre.setText(nombre);
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

        Agregarboton = new javax.swing.JButton();
        label_nombre = new javax.swing.JLabel();

        Agregarboton.setText("Agregar");
        Agregarboton.setName("Agregar"); // NOI18N
        Agregarboton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarbotonActionPerformed(evt);
            }
        });

        label_nombre.setText("Nombre");
        label_nombre.setName("Nombre"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(label_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(Agregarboton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Agregarboton)
                    .addComponent(label_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void AgregarbotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarbotonActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
       c.getUser().addAmigo(this.label_nombre.getText());
       c.actualizarListaAmigos();
       c.actualizarListaNoAmigos();
      
       
        
    }//GEN-LAST:event_AgregarbotonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Agregarboton;
    private javax.swing.JLabel label_nombre;
    // End of variables declaration//GEN-END:variables
}