
package analizadorteam.a;

/*
* Tecnológico Nacional de México campus Pachuca
* Asignatura: Lenguajes y Autómatas I
* Catedrático: Ing. Roberto Hernandéz Pérez
* Equipo: A
*   Lugo Martinez Saul Isui
*   Tomás Hernández Dulce Alejandra
*
* Analizador léxico minimo con 3 tokens
*/
    
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Analizador extends javax.swing.JFrame {
    //Varibles globales
    ArrayList<String> Cadenas = new ArrayList<String>();
    ArrayList<String> Tokens = new ArrayList<String>();
    ArrayList<Integer> Linea = new ArrayList<Integer>();
    String Token1 = null;
    String Token2 = null;
    String Token3 = null;
    String TokenNoAlfa = null;
    String TokenNingun = null;

       
    public Analizador() {
        initComponents();              
    }
    
/*INICIAN LOS AUTOMATAS COMO TOKENS*/ 
/*PRIMER AUTOMATA DFA QUE ACEPTA CADENAS CON LA SUBCADENA 101*/    
    //Funcion de transición iterativa para Delta
    public String Delta(String q, char a){
        String p = null;        
        //Compara cada estado con la entrada que recibe
        if(q.equals("q0") && a == '0'){
            p = "q0";            
        } else if(q.equals("q0") && a == '1'){
            p = "q1";
        } else if(q.equals("q1") && a == '0'){
            p = "q2";           
        } else if(q.equals("q1") && a == '1'){
            p = "q1";            
        } else if(q.equals("q2") && a == '0'){
            p = "q0";            
        } else if(q.equals("q2") && a == '1'){
            p = "q3";            
        } else if(q.equals("q3") && a == '0'){
            p = "q0";            
        } else if(q.equals("q3") && a == '1'){
            p = "q1";            
        }
        return p;
    }    
    //Funcion iterativa para Delta
    public String deltaIterativa(String q, String w){
        String p = "";
        int i = 0;
        while (i<w.length()){
            //Llama al metodo delta para que obtengamos
            //el estado con el simbolo actual de la cadena
            p = Delta(q, w.charAt(i));
            q = p;
            i++;
        }
        return p;
    }
    //Valida que el ultimo simbolo de la cadena se encuetre
    //en el estado final
    public boolean Valida(String w){
        boolean val = false;
        String p = deltaIterativa("q0",w);
    //q3 es el estado final
        if (p.equals("q3")){
            val = true;
        }
        return val;
    }        
    //Si la cadena es válida lo guarda, de lo contrario pasa al siguiente DFA
    public void Comprueba(String w){
        
        if (Valida(w)){
            Token1 = "Token1";
            GuardaToken(Token1);
        } else {
            Comprueba2(w);
        }
    }    
/*SEGUNDO AUTOMATA DFA QUE ACEPTA CADENAS CON SECUENCIAS DE 000 */
    //Funcion de transición iterativa para Delta DFA 2 TOKEN 2
    public String Delta2(String q, char a){
        String p = null;        
        //Compara cada estado con la entrada que recibe
        if(q.equals("q0") && a == '0'){
            p = "q1";            
        } else if(q.equals("q0") && a == '1'){
            p = "q0";
        } else if(q.equals("q1") && a == '0'){
            p = "q2";           
        } else if(q.equals("q1") && a == '1'){
            p = "q0";            
        } else if(q.equals("q2") && a == '0'){
            p = "q3";            
        } else if(q.equals("q2") && a == '1'){
            p = "q0";            
        } else if(q.equals("q3") && a == '0'){
            p = "q3";            
        } else if(q.equals("q3") && a == '1'){
            p = "q3";            
        }
        return p;
    }    
    //Funcion iterativa para Delta
    public String deltaIterativa2(String q, String w){
        String p = "";
        int i = 0;
        while (i<w.length()){
            //Llama al metodo delta para que obtengamos
            //el estado con el simbolo actual de la cadena
            p = Delta2(q, w.charAt(i));
            q = p;
            i++;
        }
        return p;
    }
    //Valida que el ultimo simbolo de la cadena se encuetre
    //en el estado final
    public boolean Valida2(String w){
        boolean val = false;
        String p = deltaIterativa2("q0",w);
    //q3 es el estado final
        if (p.equals("q3")){
            val = true;
        }
        return val;
    }    
 //Si la cadena es válida lo guarda, de lo contrario pasa al siguiente DFA
    public void Comprueba2(String w){        
        if (Valida2(w)){
            Token2 = "Token2";
            GuardaToken(Token2);
        } else {
            Comprueba3(w);
        }
    }
/*INICA EL TERCER AUTOMATA DFA QUE ACEPTA CADENAS CON PARES DE 1'S*/    
     //Funcion de transición iterativa para Delta DFA 3 TOKEN 3
    public String Delta3(String q, char a){
        String p = null;        
        //Compara cada estado con la entrada que recibe
        if(q.equals("q0") && a == '0'){
            p = "q0";            
        } else if(q.equals("q0") && a == '1'){
            p = "q1";
        } else if(q.equals("q1") && a == '0'){
            p = "q0";           
        } else if(q.equals("q1") && a == '1'){
            p = "q2";            
        } else if(q.equals("q2") && a == '0'){
            p = "q2";            
        } else if(q.equals("q2") && a == '1'){
            p = "q2";            
        }
        return p;
    }    
    //Funcion iterativa para Delta
    public String deltaIterativa3(String q, String w){
        String p = "";
        int i = 0;
        while (i<w.length()){
            //Llama al metodo delta para que obtengamos
            //el estado con el simbolo actual de la cadena
            p = Delta3(q, w.charAt(i));
            q = p;
            i++;
        }
        return p;
    }
    //Valida que el ultimo simbolo de la cadena se encuetre
    //en el estado final
    public boolean Valida3 (String w){
        boolean val = false;
        String p = deltaIterativa3("q0",w);
    //q2 es el estado final
        if (p.equals("q2")){
            val = true;
        }
        return val;
    }    
//Si la cadena es válida lo guarda, de lo contrario pasa al siguiente DFA
    public void Comprueba3(String w){
        
        if (Valida3(w)){
            Token3 = "Token3";
            GuardaToken(Token3);
        } else {
            TokenNingun = "TokenNingun";
            GuardaToken(TokenNingun);
        }
    } 
/*TERMINAN LOS AUTOMATAS*/    
    
    //Metodo para validar que los simbolos pertenescan al alfabeto
    public void ValidaAlfa(String w) {
        boolean valida = false;
        //variable para que para el simbolo que no pertence al alfabeto
        String p = null;
        for (int i = 0; i < w.length(); i++) {
            char a = w.charAt(i);
            String q = String.valueOf(a);
            //Comparamos cada simbolo
            if (q.equals("0") || q.equals("1")) {
                valida = true;
            } else {
                valida = false;
                p = q;
                /*break termina las iteraciones
                * puesto que la cadena no podra ser leida
                * por contener un simbolo que no pertence al alfabeto*/
                break;
            }
        }
        if (valida) {
            //Si todos los simbolos son aceptados puede entrar al automata
            Comprueba(w);
        } else {
            //La cadena contiene simbolos que no son aceptados para el alfabeto
            //p es el simbolo que no pertenece al alfabeto            
            TokenNoAlfa = "TokenNoAlfa";
            GuardaToken(TokenNoAlfa);
        }
    }       
    
    //LECTOR DE ARCHIVO
    public void LeerArchivo(){         
        String cadena="";
        int fila = 0;
        String ruta = "C:\\Users\\hp\\Documents\\NetBeansProjects\\AnalizadorTeam-A\\Documento.txt";
        try{
            FileReader lector = new FileReader(ruta);
            BufferedReader lectura = new BufferedReader(lector);
            //Lee cada linea del archivo
            cadena = lectura.readLine();
            int linea = 0;
            while (cadena != null){
                this.Guarda(cadena, linea);             
                linea ++;               
                cadena = lectura.readLine();             
            }
            jTextField1.setText("Cadenas leidas correctamente del archivo TXT");            
        } catch (FileNotFoundException ex){
            System.out.println("Error : "+ex);            
        } catch (IOException ex){
            System.out.println("Error : "+ex);
        } 
    }
    
    //Llama de manera iterativa a los Automatas
    public void Procesa() {
        try {
            for (int i = 0; i < Cadenas.size(); i++) {
                String cadena = Cadenas.get(i);                                
                ValidaAlfa(cadena);
            }
            jTextField1.setText("Cadenas procesadas");
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
    }     
    
    //IMPRIMIR EN TABLA
    public void Imprime() {
        try {
            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
            int i;
            jTextField1.setText("");
            for (i = 0; i < Cadenas.size(); i++) {
                //Imprime en cada columna, i es la fila
                jTable1.setValueAt(Tokens.get(i), i, 0);
                jTable1.setValueAt(Cadenas.get(i), i, 1);
                jTable1.setValueAt(Linea.get(i), i, 2);                                
            }
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
    }
    
    //Metodo para guardar cadenas   
    public void Guarda(String cadena, int linea){  
        Cadenas.add(cadena);
        Linea.add(linea);
    }
    //Metodo para guardar tokens
    public void GuardaToken(String token){
        Tokens.add(token);
    }
    
    //Metodo para limpiar la tabla
    public void limpiarTabla(JTable tabla) {
        try {
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            int filas = tabla.getRowCount();
            for (int i = 0; filas > i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 255, 255));

        jTable1.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "TOKEN", "LEXEMA", "LINEA"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/upload.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/printer.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/play.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/cleaning.png"))); // NOI18N
        jButton4.setText("LIMPIAR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/encabezado.png"))); // NOI18N

        jLabel2.setText("Cargar archivo");

        jLabel3.setText("Procesar cadenas");

        jLabel4.setText("Ver resultados");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Analizador léxico equipo A - V.1.0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTextField1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE))
                .addGap(21, 21, 21))
            .addGroup(layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(23, 23, 23)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        LeerArchivo();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Imprime();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Procesa(); 
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       this.limpiarTabla(jTable1);
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Analizador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Analizador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Analizador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Analizador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Analizador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
