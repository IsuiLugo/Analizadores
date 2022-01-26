
package analizadorteam.a;
   /*
* Tecnológico Nacional de México campus Pachuca
* Asignatura: Lenguajes y Autómatas I
* Catedrático: Ing. Roberto Hernandéz Pérez
* Equipo: A
*   Lugo Martinez Saul Isui
*   Tomás Hernández Dulce Alejandra
*
* Analizador léxico versión 2 y versión 3 con tokens
*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
public class ProyectoAsignatura extends javax.swing.JFrame {
//Varibles globales
    ArrayList<String> Entradas = new ArrayList<String>();
    ArrayList<String> Lexemas = new ArrayList<String>();    
    ArrayList<String> LexemasUnicos = new ArrayList<String>();
    ArrayList<String> Tokens = new ArrayList<String>();
    ArrayList<String> Identificadores = new ArrayList<String>();
    ArrayList<String> Comentarios = new ArrayList<String>();
    ArrayList<Integer> Linea = new ArrayList<Integer>();
    StringBuilder agrega = new StringBuilder();
    String Token1 = null;
    String Token2 = null;
    String Token3 = null;
    String TokenSemiColon = null;
    String TokenNoAlfa = null;
    String TokenNingun = null;
    String Identifica = null;
    public ProyectoAsignatura() {
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
    public void Comprueba(String w, int linea){
        
        if (Valida(w)){
            GuardaLexema(w);
            Token1 = "TokenInicia";
            
            GuardaToken(Token1);
            String Token1A = BinarioDecimal(w);
            GuardaUnicos(Token1A);            
            GuardaLinea(linea);
            Identifica = "Numero";
            GuardaIdentificador(Identifica);
        } else {
            Comprueba2(w, linea);
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
    public void Comprueba2(String w, int linea){        
        if (Valida2(w)){
            GuardaLexema(w);
            Token2 = "Token2";
            GuardaToken(Token2);
            String Token2A = BinarioDecimal(w);
            GuardaUnicos(Token2A);
            GuardaLinea(linea);
            Identifica = "Numero";
            GuardaIdentificador(Identifica);
            
        } else {
            Comprueba3(w, linea);
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
    public void Comprueba3(String w, int linea){
        
        if (Valida3(w)){
            Token3 = "Token3";
            GuardaToken(Token3);
            GuardaUnicos(w);
            GuardaLinea(linea);
            GuardaLexema(w);
            Identifica = "String";
            GuardaIdentificador(Identifica);
        } else {
            TokenNingun = "TokenTermina";
            GuardaToken(TokenNingun);
            GuardaUnicos(w);
            GuardaLinea(linea);
            GuardaLexema(w);
            Identifica = "Termina";
            GuardaIdentificador(Identifica);
        }
    } 
/*TERMINAN LOS AUTOMATAS*/    
    
    //Metodo para validar que los simbolos pertenescan al alfabeto
    public void ValidaAlfa(String w, int linea) {
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
            Comprueba(w,linea);
        } else if(p.equals(";")){
            GuardaLexema(w);
            TokenSemiColon = "TokenSemiColon";
            GuardaToken(TokenSemiColon);
            GuardaUnicos(p);
            GuardaLinea(linea);
            Identifica = "Delimitador";
            GuardaIdentificador(Identifica);
          
        } else if(p.equals("/")){
            GuardaComentarios(w);
        } else if(p.equals("*") || p.equals("+")){
            TokenNoAlfa = "TokenOpera";
            GuardaToken(TokenNoAlfa);
            GuardaUnicos(p);    
            GuardaLinea(linea);
            GuardaLexema(w);
            Identifica = "Operador";
            GuardaIdentificador(Identifica);
        }else{
            //La cadena contiene simbolos que no son aceptados para el alfabeto
            //p es el simbolo que no pertenece al alfabeto            
            TokenNoAlfa = "TokenNoAlfa";
            GuardaToken(TokenNoAlfa);
            GuardaUnicos(p);    
            GuardaLinea(linea);
            GuardaLexema(w);
            Identifica = "Caracter";
            GuardaIdentificador(Identifica);
        }
    }       
    
    //Método para converti de binario a decimal
    public String BinarioDecimal(String binario) {
        double decimal = 0f;
        String valor;
        int j = (binario.length()) - 1;
        for (double i = 0; i <= (binario.length()) - 1; i++) {
            double valorActual = Character.getNumericValue(binario.charAt(j));
            j--; //Itera en cada valor del número
            double suma = (Math.pow(2, i)) * valorActual;
            decimal = decimal + suma;
        }
        int Resultado = (int) decimal; //Elimina el cero del valor double
        valor = String.valueOf(Resultado);
        return valor;
    }     
    
    
    //LECTOR DE ARCHIVO
    public void LeerArchivo(){         
        String cadena = "";
        String ruta = "C:\\Users\\hp\\Documents\\NetBeansProjects\\AnalizadorTeam-A\\Documento.txt";
        try{
            FileReader lector = new FileReader(ruta);
            BufferedReader lectura = new BufferedReader(lector);
            //Lee cada linea del archivo
            cadena = lectura.readLine();           
            while (cadena != null){
                this.GuardaEntradas(cadena);
                cadena = lectura.readLine();             
            }
            jTextField1.setText("Cadenas leidas correctamente del archivo TXT");            
        } catch (FileNotFoundException ex){
            System.out.println("Error1 en leer: "+ex);            
        } catch (IOException ex){
            System.out.println("Error2 en leer: "+ex);
        } 
    }
           
    //Llama de manera iterativa a los Automatas
    public void Procesa() {
        try {
            for (int i = 0; i < Entradas.size(); i++) {
                String cadena = Entradas.get(i);                
                ValidaAlfa(cadena,i);                
            }         
            jTextField1.setText("Cadenas procesadas");
        } catch (Exception e) {
            System.out.println("Error en procesa = " + e);
        }
    }     
    
    //IMPRIMIR EN TABLA DE RESULTADOS
    public void Imprime() {
        try {
            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
            int i;
            jTextField1.setText("");
            for (i = 0; i < Lexemas.size(); i++) {
                //Imprime en cada columna, i es la fila
                jTable1.setValueAt(Tokens.get(i), i, 0);
                jTable1.setValueAt(Lexemas.get(i), i, 1);
                jTable1.setValueAt(Linea.get(i), i, 2);                                
            }
                        
        } catch (Exception e) {
            System.out.println("Error en imprime: = " + e);
        }
    }
    
    //IMPRIMIR EN TABLA
    public void Imprime2() {
        try {
            DefaultTableModel modelo = (DefaultTableModel) jTable2.getModel();
            int i;
            jTextField1.setText("");
            for (i = 0; i < Lexemas.size(); i++) {
                //Imprime en cada columna, i es la fila
                jTable2.setValueAt(Identificadores.get(i), i, 0);
                jTable2.setValueAt(LexemasUnicos.get(i), i, 1);
                jTable2.setValueAt(Linea.get(i), i, 2);                                
            }
        } catch (Exception e) {
            System.out.println("Error en imprime2: = " + e);
        }
    }
    
    //Método para imprimir en TextArea los comentarios
    public void ImprimeArea(){
        for (int i = 0; i < Comentarios.size(); i++) {
            jTextArea1.append(Comentarios.get(i)+"\n");

        }
    }
    
    
    //Método para guardar los datos de entrada
    public void GuardaEntradas(String cadena){
        Entradas.add(cadena);
    }
    
    //Metodo para guardar lexemas   
    public void GuardaLexema(String cadena){  
        Lexemas.add(cadena);        
    }
    
    //Metodo para guardar lexemas únicos   
    public void GuardaUnicos(String cadena){  
        LexemasUnicos.add(cadena);        
    }
    //Metodo para guardar tokens
    public void GuardaToken(String token){
        Tokens.add(token);
    }
    
    //Metodo para guardar el no. de linea encontrada
    public void GuardaLinea(int linea){
        Linea.add(linea);
    }
    
    //Método para guardar los comentarios
    public void GuardaComentarios(String comentario){
        Comentarios.add(comentario);
    }
    
    //Método para guardar los comentarios
    public void GuardaIdentificador(String comentario){
        Identificadores.add(comentario);
    }
            
    //Metodo para limpiar la tabla
    public void limpiarTabla(JTable tabla) {
        try {
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            int filas = tabla.getRowCount();
            for (int i = 0; filas > i; i++) {
                modelo.removeRow(0);
            }
           jTextArea1.setText("");
        } catch (Exception e) {
            System.out.println("Error en limpiar tabla: = " + e);
        }
    }

 
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setAutoCreateRowSorter(true);
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
                "TOKEN", "LEXEMA", "No. LINEA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setCellSelectionEnabled(true);
        jTable1.setColumnSelectionAllowed(true);
        jTable1.setGridColor(new java.awt.Color(204, 255, 255));
        jTable1.setOpaque(false);
        jTable1.setShowGrid(true);
        jScrollPane1.setViewportView(jTable1);

        jTable2.setAutoCreateRowSorter(true);
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
                "TOKEN", "LEXEMA ÚNICO", "No. Linea"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setGridColor(new java.awt.Color(204, 255, 255));
        jScrollPane2.setViewportView(jTable2);

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 8)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/upload.png"))); // NOI18N
        jButton1.setText("cargar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 8)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/play.png"))); // NOI18N
        jButton2.setText("procesar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 5)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/printer.png"))); // NOI18N
        jButton3.setText("resultados");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 8)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/layout.png"))); // NOI18N
        jButton4.setText("simbolos");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/cleaning.png"))); // NOI18N
        jButton5.setText("limpiar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Lectura del archivo fuente");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setBorder(javax.swing.BorderFactory.createTitledBorder("Comentarios"));
        jScrollPane3.setViewportView(jTextArea1);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Tabla de símbolos");

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 8)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/essay.png"))); // NOI18N
        jButton6.setText("Comentarios");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/encabezado.png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Proyecto de asignatura Analizador Léxico");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel5.setText("Equipo A");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(101, 101, 101)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(169, 169, 169))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton6)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(98, 98, 98)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(59, 59, 59)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.Imprime2();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       this.LeerArchivo();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.Procesa();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.Imprime();
    }//GEN-LAST:event_jButton3ActionPerformed
     
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.limpiarTabla(jTable1);
        this.limpiarTabla(jTable2);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        this.ImprimeArea();
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(ProyectoAsignatura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProyectoAsignatura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProyectoAsignatura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProyectoAsignatura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProyectoAsignatura().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
