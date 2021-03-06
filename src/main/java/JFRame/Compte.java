/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JFRame;

import DAO.CompteDAO;
import javax.swing.JOptionPane;

/**
 *
 * @author luigi
 */
public class Compte extends javax.swing.JFrame {
    
    private int IDCompte;
    private float Solde;

    public int getIDCompte() {
        return IDCompte;
    }

    public void setIDCompte(int IDCompte) {
        this.IDCompte = IDCompte;
    }

    public float getSolde() {
        return Solde;
    }

    public void setSolde(float Solde) {
        this.Solde = Solde;
    }
    
    /**
     * Creates new form Compte
     */
    public Compte(int idCompte, float Solde) {
        this.setIDCompte(idCompte);
        this.setSolde(Solde);
        initComponents();
        txtSolde.setText(String.valueOf(Solde));
        new CompteDAO().tableUpdate(this.tableHistory, idCompte);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableHistory = new javax.swing.JTable();
        txtSolde = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        Operation = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        Logout = new javax.swing.JMenuItem();
        Quitter = new javax.swing.JMenuItem();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setText("Solde :");

        jLabel3.setText("Historique des transactions :");

        tableHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "n??Operation", "nom", "Montant"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tableHistory);

        txtSolde.setEditable(false);

        jMenu2.setText("Options");

        Operation.setText("Virement");
        Operation.setActionCommand("Operation");
        Operation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OperationActionPerformed(evt);
            }
        });
        jMenu2.add(Operation);

        jMenuBar1.add(jMenu2);

        jMenu1.setText("Exit");

        Logout.setText("D??connection");
        Logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutActionPerformed(evt);
            }
        });
        jMenu1.add(Logout);

        Quitter.setText("Quitter");
        Quitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QuitterActionPerformed(evt);
            }
        });
        jMenu1.add(Quitter);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(txtSolde, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtSolde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Se d??connecter de l'application.
    private void LogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutActionPerformed
        // Affiche un ??cran de confirmation.
        int choix = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment vous d??connecter ?", "D??connection", JOptionPane.YES_NO_OPTION);
        if(choix == JOptionPane.YES_OPTION){
            new Login().setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_LogoutActionPerformed

    //Quitte l'application.
    private void QuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QuitterActionPerformed
        // TODO add your handling code here:
        int choix = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter l'application ?", "Quitter ?", JOptionPane.YES_NO_OPTION);
        if(choix == JOptionPane.YES_OPTION){
            this.dispose();
        }
    }//GEN-LAST:event_QuitterActionPerformed

    private void OperationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OperationActionPerformed
        // TODO add your handling code here:
        new Operation(this.getIDCompte(), this.getSolde()).setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_OperationActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Logout;
    private javax.swing.JMenuItem Operation;
    private javax.swing.JMenuItem Quitter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableHistory;
    private javax.swing.JTextField txtSolde;
    // End of variables declaration//GEN-END:variables
}
