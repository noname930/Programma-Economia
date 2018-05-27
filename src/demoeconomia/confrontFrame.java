/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demoeconomia;

import java.util.HashMap;
import java.util.Map;
import javax.swing.ButtonGroup;
import Jama.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author alessio spina
 * @description: La classe confrontFrame serve per attuare i confronti tra i 5 attributi dei computer,
 * l'ho progettata in modo tale da modificarne le dimensioni a seconda dei confronti che deve fare, poiché
 * ci sono casi in cui serve un pannello più grandi con tante domande, e casi opposti.
 * Una volta Cliccato il tasto "Fatto", modificherà la matrice presente nella classe mainFrame, inserendo i dati
 * raccolti dai confronti.
 */
public class confrontFrame extends javax.swing.JFrame {
    
    //mi servirà per capire quale confronto ho aperto.
    private int indicatore;
    /**
     * Creates new form confrontFrame
     */
    
       private Map<String,Double> hmap_valutazioni = new HashMap<String, Double>() {
        {

           put("Altissimo",9.0);
           put("Alto", 8.0);
           put("Medio Alto", 7.0);
           put("Discretamente Alto", 6.0);
           put("Nella Media", 1.0);
           put("Discretamente Basso", 5.0);
           put("Medio Basso", 4.0);
           put("Basso", 3.0);
           put("Bassissimo",2.0);
            
            
        }
    };
       
       
    public confrontFrame() {
        initComponents();
        
        //Aggiunta del buttongroup che permette di effettuare la mutua esclusione
        //dei jRadioButton ( se ne premo uno, l'altro di deseleziona)
        ButtonGroup g1 = new ButtonGroup();
        g1.add(jRadioButton3);
        g1.add(jRadioButton4);
        
        ButtonGroup g2 = new ButtonGroup();
        g2.add(jRadioButton11);
        g2.add(jRadioButton12);
        
        ButtonGroup g3 = new ButtonGroup();
        g3.add(jRadioButton13);
        g3.add(jRadioButton14);
        
        ButtonGroup g4 = new ButtonGroup();
        g4.add(jRadioButton15);
        g4.add(jRadioButton16);
        
        
    }
    
    //Al variare della posizione dello slider, cambierà i nomi dei label
    public void jSliderChanger(javax.swing.JSlider e , javax.swing.JLabel l)
    {
       if(e.getValue() >= 0 && e.getValue() <=10)
          l.setText("Bassissimo");
       else if(e.getValue() >= 11 && e.getValue() <= 20)
           l.setText("Basso");
       else if(e.getValue() >= 21 && e.getValue() <= 30)
           l.setText("Medio Basso");
       else if(e.getValue() >= 31 && e.getValue() <= 40)
           l.setText("Discretamente Basso");
       else if(e.getValue() >= 41 && e.getValue() <= 50)
           l.setText("Nella Media");
       else if(e.getValue() >= 51 && e.getValue() <= 60)
           l.setText("Discretamente Alto");
       else if(e.getValue() > 61 && e.getValue() <= 70)
           l.setText("Medio Alto");
       else if(e.getValue() > 71 && e.getValue() <= 80)
           l.setText("Alto");
       else if(e.getValue() > 81 && e.getValue() <= 100)
           l.setText("Altissimo");
    }
    
    //a seconda del parametro n, il frame visualizzerà solo i Panel che servono
    // per effettuare il questionario, ad esempio se n=4, per come ho impostato
    // la matrice (Peso,Prezzo,Comfort...,ecc,) vuol dire che dovrà fare i 4 confronti
    // riguardanti al peso, e quindi si visualizzeranno nel frame i 4 panel contenenti
    //slider, label corretti.
    public void setPanelDisplayed(int n)
    {   
        //in questo modo riesco a determinare l'entità di confrontFrame
        indicatore = n;
        
        if(n==1) {
            //Confronto Estetica - Comfort
            jPanel7.setVisible(false);
            jPanel8.setVisible(false);
            jPanel9.setVisible(false);
                       
            jRadioButton3.setText("Estetica");
            jRadioButton4.setText("Comfort");
            jButton1.setLocation(315,250);
            this.setSize(634, 220);
        }
        else if(n==2)
        {
            // Confronto [Prestazione - Estetica] e [Prestazione - Comfort]
            jPanel8.setVisible(false);
            jPanel9.setVisible(false);
            
            jRadioButton3.setText("Prestazione Tecnica");
            jRadioButton4.setText("Estetica");
            jRadioButton11.setText("Prestazione Tecnica");
            jRadioButton12.setText("Comfort");
            jButton1.setLocation(315,250);
            this.setSize(700, 300);
        }
        else if(n==3)
        {
            //Confronto [Prezzo - Prestazione] e [Prezzo - Estetica] e [Prezzo - Comfort]
            jPanel9.setVisible(false);
            
            jRadioButton3.setText("Prezzo");
            jRadioButton4.setText("Prestazione");
            jRadioButton11.setText("Prezzo");
            jRadioButton12.setText("Estetica");
            jRadioButton13.setText("Prezzo");
            jRadioButton14.setText("Comfort");
            jButton1.setLocation(315,250);
            this.setSize(700, 380);           
        }
        else
        {
            //Confronto [Peso - Prezzo], [Peso-Prest], [Peso - Estetica] , [Peso - Comfort]
            jRadioButton3.setText("Peso");
            jRadioButton4.setText("Prezzo");
            jRadioButton11.setText("Peso");
            jRadioButton12.setText("Prestazioni");
            jRadioButton13.setText("Peso");
            jRadioButton14.setText("Estetica");
            jRadioButton15.setText("Peso");
            jRadioButton16.setText("Comfort");
            jButton1.setLocation(315,250);
            this.setSize(720, 450); 
            
        }
            
    }
    
    //metodo per estrarre dall'hashmap delle valutazioni, il valore double corrispondente
    // quindi se cerco con chiave Nella Media, restuirò 1.0
    public double HashMap_getValue (String valutazione)
    {
        return hmap_valutazioni.get(valutazione);
    }
    
    public boolean checkAllBoxAreSelected(int n)
    {
      //  int[] indexRadioButton= new int[] {3,4,11,12,13,14,15,16};
        
        if(n==1 && (jRadioButton3.isSelected() || jRadioButton4.isSelected()))
            return true;
        else if(n==2 && (jRadioButton3.isSelected() || jRadioButton4.isSelected()) && 
                        (jRadioButton11.isSelected() || jRadioButton12.isSelected()))
            return true;
        else if(n==3 && (jRadioButton3.isSelected() || jRadioButton4.isSelected()) && 
                        (jRadioButton11.isSelected() || jRadioButton12.isSelected()) && 
                        (jRadioButton13.isSelected() || jRadioButton14.isSelected()))
            return true;
        else if (n==4 && (jRadioButton3.isSelected() || jRadioButton4.isSelected()) && 
                         (jRadioButton11.isSelected() || jRadioButton12.isSelected()) && 
                         (jRadioButton13.isSelected() || jRadioButton14.isSelected()) && 
                         (jRadioButton15.isSelected() || jRadioButton16.isSelected()))
            return true;
        
        return false;
    }
    
     
    public void setValue_Matrix(javax.swing.JRadioButton button, int i , int j , double value)
    {
         if(button.isSelected()) {// vuol dire che ho preferito una caratteristica a discapito di un'altra
            mainFrame2.mat.set(i,j,value);
            mainFrame2.mat.set(j,i,(1.0/value));
            }
            else{ // viceversa
            mainFrame2.mat.set(i,j,((1.0/value)));
            mainFrame2.mat.set(j,i,(value));
            }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jSlider2 = new javax.swing.JSlider();
        jLabel11 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jRadioButton11 = new javax.swing.JRadioButton();
        jRadioButton12 = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        jSlider6 = new javax.swing.JSlider();
        jLabel14 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jRadioButton13 = new javax.swing.JRadioButton();
        jRadioButton14 = new javax.swing.JRadioButton();
        jLabel16 = new javax.swing.JLabel();
        jSlider7 = new javax.swing.JSlider();
        jLabel17 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jRadioButton15 = new javax.swing.JRadioButton();
        jRadioButton16 = new javax.swing.JRadioButton();
        jLabel19 = new javax.swing.JLabel();
        jSlider8 = new javax.swing.JSlider();
        jLabel20 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(java.awt.Color.darkGray);
        setType(java.awt.Window.Type.POPUP);

        jPanel1.setPreferredSize(new java.awt.Dimension(714, 337));

        jPanel3.setPreferredSize(new java.awt.Dimension(702, 67));

        jLabel3.setText("o");

        jRadioButton3.setText("jRadioButton1");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        jRadioButton4.setText("jRadioButton1");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        jLabel4.setText("di quanto?");

        jSlider2.setMinorTickSpacing(9);
        jSlider2.setPaintTicks(true);
        jSlider2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider2StateChanged(evt);
            }
        });

        jLabel11.setBackground(new java.awt.Color(10, 37, 56));
        jLabel11.setForeground(new java.awt.Color(0, 51, 51));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Nella Media");

        jLabel5.setBackground(new java.awt.Color(10, 37, 56));
        jLabel5.setForeground(new java.awt.Color(0, 51, 51));
        jLabel5.setText("Grado di preferenza:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jRadioButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jRadioButton3)
                        .addComponent(jRadioButton4)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel5))
                .addContainerGap())
        );

        jLabel12.setText("o");

        jRadioButton11.setText("jRadioButton1");
        jRadioButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton11ActionPerformed(evt);
            }
        });

        jRadioButton12.setText("jRadioButton1");
        jRadioButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton12ActionPerformed(evt);
            }
        });

        jLabel13.setText("di quanto?");

        jSlider6.setMinorTickSpacing(9);
        jSlider6.setPaintTicks(true);
        jSlider6.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider6StateChanged(evt);
            }
        });

        jLabel14.setBackground(new java.awt.Color(10, 37, 56));
        jLabel14.setForeground(new java.awt.Color(0, 51, 51));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Nella Media");

        jLabel6.setBackground(new java.awt.Color(10, 37, 56));
        jLabel6.setForeground(new java.awt.Color(0, 51, 51));
        jLabel6.setText("Grado di preferenza:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jRadioButton11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jSlider6, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSlider6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(jRadioButton11)
                        .addComponent(jRadioButton12)
                        .addComponent(jLabel13)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel6))
                .addContainerGap())
        );

        jLabel15.setText("o");

        jRadioButton13.setText("jRadioButton1");
        jRadioButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton13ActionPerformed(evt);
            }
        });

        jRadioButton14.setText("jRadioButton1");
        jRadioButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton14ActionPerformed(evt);
            }
        });

        jLabel16.setText("di quanto?");

        jSlider7.setMinorTickSpacing(9);
        jSlider7.setPaintTicks(true);
        jSlider7.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider7StateChanged(evt);
            }
        });

        jLabel17.setBackground(new java.awt.Color(10, 37, 56));
        jLabel17.setForeground(new java.awt.Color(0, 51, 51));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Nella Media");

        jLabel7.setBackground(new java.awt.Color(10, 37, 56));
        jLabel7.setForeground(new java.awt.Color(0, 51, 51));
        jLabel7.setText("Grado di preferenza:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jRadioButton13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSlider7, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSlider7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(jRadioButton13)
                        .addComponent(jRadioButton14)
                        .addComponent(jLabel16)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel7))
                .addContainerGap())
        );

        jLabel18.setText("o");

        jRadioButton15.setText("jRadioButton1");
        jRadioButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton15ActionPerformed(evt);
            }
        });

        jRadioButton16.setText("jRadioButton1");
        jRadioButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton16ActionPerformed(evt);
            }
        });

        jLabel19.setText("di quanto?");

        jSlider8.setMinorTickSpacing(9);
        jSlider8.setPaintTicks(true);
        jSlider8.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider8StateChanged(evt);
            }
        });

        jLabel20.setBackground(new java.awt.Color(10, 37, 56));
        jLabel20.setForeground(new java.awt.Color(0, 51, 51));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Nella Media");

        jLabel8.setBackground(new java.awt.Color(10, 37, 56));
        jLabel8.setForeground(new java.awt.Color(0, 51, 51));
        jLabel8.setText("Grado di preferenza:");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jRadioButton15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSlider8, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSlider8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18)
                        .addComponent(jRadioButton15)
                        .addComponent(jRadioButton16)
                        .addComponent(jLabel19)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel8))
                .addContainerGap())
        );

        jLabel21.setFont(new java.awt.Font("Bell MT", 1, 18)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Preferisci il primo elemento o il secondo? E di quanto?");

        jButton1.setFont(new java.awt.Font("Bell MT", 1, 14)); // NOI18N
        jButton1.setText("Fatto");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(308, 308, 308))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jSlider2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider2StateChanged
          jSliderChanger(jSlider2,jLabel11);
    }//GEN-LAST:event_jSlider2StateChanged

    private void jRadioButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton11ActionPerformed

    private void jRadioButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton12ActionPerformed

    private void jSlider6StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider6StateChanged
        jSliderChanger(jSlider6,jLabel14);
    }//GEN-LAST:event_jSlider6StateChanged

    private void jRadioButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton13ActionPerformed

    private void jRadioButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton14ActionPerformed

    private void jSlider7StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider7StateChanged
         jSliderChanger(jSlider7,jLabel17);
    }//GEN-LAST:event_jSlider7StateChanged

    private void jRadioButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton15ActionPerformed

    private void jRadioButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton16ActionPerformed

    private void jSlider8StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider8StateChanged
        jSliderChanger(jSlider8,jLabel20); 
    }//GEN-LAST:event_jSlider8StateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
 /*
                            peso,prezzo,prestazioni,estetica,comfort 
                       peso  0,0  0,1       0,2        0,3     0,4
                     prezzo  1,0  1,1       1,2        1,3     1,4
                prestazioni  2,0  2,1       2,2        2,3     2,4
                   estetica  3,0  3,1       3,2        3,3     3,4
                    comfort  4,0  4,1       4,2        4,3     4,4     
                    
                    Demo Rapida :
                                    Prest (x) - Estetica = altissima
                                    Prest (x) - Comfort = bassa
                                    Prezzo    - Prestazione(x) = altissima
                                    Prezzo(x) - Estetica = bassissima
                                    Prezzo    - Comfort(x) = bassa
                                    Estetica  - Comfort(x) = media bassa
                                    Peso      - Prezzo(x) = bassissima
                                    Peso      - Prestazioni(x) = Alta
                                    Peso(x)   - Estetica = nella media
                                    Peso      - Comfort(x) =  media alta
*/
        
       // if(checkAllBoxAreSelected())
 
        double value1,value2,value3,value4;
        boolean uscita=true;
             
        switch (indicatore) {
            case 1:   // vuol dire che confrontFrame mi è servito per confrontare Estetica - Comfort  
               if(checkAllBoxAreSelected(indicatore))
               {
                value1 = HashMap_getValue(jLabel11.getText());  //estraggo il valore dall'hashmap 
                setValue_Matrix(jRadioButton3,3,4,value1);    // lo inserisco nella matrice
                mainFrame2.jLabel6.setVisible(true);
                mainFrame2.jLabel8.setVisible(true);
                mainFrame2.done_estetica=true;
                mainFrame2.done_comfort=true;
               } 
               else 
               {
                 JOptionPane.showMessageDialog(this,"Non tutti i box sono selezionati","Messaggio D'errore", JOptionPane.ERROR_MESSAGE);
                 uscita=false;
               }
                break;
            case 2://confrontFrame mi è servito per confrontare [Prestazione - Estetica] e [Prestazione - Comfort]
                if(checkAllBoxAreSelected(indicatore))
               {
                value1 = HashMap_getValue(jLabel11.getText());
                value2 = HashMap_getValue(jLabel14.getText());
                setValue_Matrix(jRadioButton3,2,3,value1);
                setValue_Matrix(jRadioButton11,2,4,value2);
                mainFrame2.jLabel2.setVisible(true);
                mainFrame2.done_prestazioni=true;
               }
                else
                {
                 JOptionPane.showMessageDialog(this,"Non tutti i box sono selezionati","Messaggio D'errore", JOptionPane.ERROR_MESSAGE);
                 uscita=false; 
                }
                break;
            case 3://confrontFrame mi è servito per confrontare [Prezzo - Prestazione] e [Prezzo - Estetica] e [Prezzo - Comfort]
                if(checkAllBoxAreSelected(indicatore)) 
                {
                value1 = HashMap_getValue(jLabel11.getText());
                value2 = HashMap_getValue(jLabel14.getText());
                value3 = HashMap_getValue(jLabel17.getText());
                setValue_Matrix(jRadioButton3,1,2,value1);
                setValue_Matrix(jRadioButton11,1,3,value2);
                setValue_Matrix(jRadioButton13,1,4,value3); 
                mainFrame2.jLabel4.setVisible(true);
                mainFrame2.done_prezzo=true;
                }
                else
                {
                 JOptionPane.showMessageDialog(this,"Non tutti i box sono selezionati","Messaggio D'errore", JOptionPane.ERROR_MESSAGE);
                 uscita=false;
                }
                break;
            case 4: // confrontFrame : [Peso - Prezzo], [Peso-Prest], [Peso - Estetica] , [Peso - Comfort]
                 if(checkAllBoxAreSelected(indicatore)) 
                {
                value1 = HashMap_getValue(jLabel11.getText());
                value2 = HashMap_getValue(jLabel14.getText());
                value3 = HashMap_getValue(jLabel17.getText());
                value4 = HashMap_getValue(jLabel20.getText()); 
                setValue_Matrix(jRadioButton3,0,1,value1);
                setValue_Matrix(jRadioButton11,0,2,value2);
                setValue_Matrix(jRadioButton13,0,3,value3);
                setValue_Matrix(jRadioButton15,0,4,value4);
                mainFrame2.jLabel10.setVisible(true);
                mainFrame2.done_peso=true;
                }
                 else
                 {
                 JOptionPane.showMessageDialog(this,"Non tutti i box sono selezionati","Messaggio D'errore", JOptionPane.ERROR_MESSAGE);
                 uscita=false;
                 }
                break;
            default:
                break;
        }
          
     
    mainFrame2.mat.print(5,5);
    
    if(uscita)
        this.dispose();
   

    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(confrontFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(confrontFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(confrontFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(confrontFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new confrontFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton11;
    private javax.swing.JRadioButton jRadioButton12;
    private javax.swing.JRadioButton jRadioButton13;
    private javax.swing.JRadioButton jRadioButton14;
    private javax.swing.JRadioButton jRadioButton15;
    private javax.swing.JRadioButton jRadioButton16;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JSlider jSlider2;
    private javax.swing.JSlider jSlider6;
    private javax.swing.JSlider jSlider7;
    private javax.swing.JSlider jSlider8;
    // End of variables declaration//GEN-END:variables
}
