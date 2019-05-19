/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.ChatClientDao;
import dao.ChatlogDao;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import pojo.ChatClient;
import pojo.Chatlog;

/**
 *
 * @author atulb
 */
public class Serverside extends javax.swing.JFrame {

    Socket s = null;
    ArrayList<String> userNames = new ArrayList<>();
    ArrayList<PrintWriter> printWriters = new ArrayList<>();
    ServerSocket server = null;
    Scanner reps;
    PrintWriter out;
    FileWriter fw = null;
    PrintWriter pwf = null;
    SimpleDateFormat sdf;
    String pass;

    /**
     * Creates new form Serverside
     */
    public Serverside() {
        initComponents();
        btnStop.setEnabled(false);
        try {
            fw = new FileWriter("E:\\chatlogs.txt", true);
            pwf = new PrintWriter(fw);
            sdf = new SimpleDateFormat("HH:mm:ss,dd-MMM-yyyy");
        } catch (IOException ex) {
            Logger.getLogger(Serverside.class.getName()).log(Level.SEVERE, null, ex);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        chatarea = new javax.swing.JTextArea();
        btnstart = new javax.swing.JButton();
        btnStop = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        chatarea.setEditable(false);
        chatarea.setBackground(new java.awt.Color(0, 0, 0));
        chatarea.setColumns(20);
        chatarea.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chatarea.setForeground(new java.awt.Color(204, 204, 204));
        chatarea.setRows(5);
        jScrollPane1.setViewportView(chatarea);

        btnstart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Toggle_On_25px.png"))); // NOI18N
        btnstart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnstartActionPerformed(evt);
            }
        });

        btnStop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Toggle_Off_25px.png"))); // NOI18N
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnstart, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStop, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnstart, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnStop, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnstartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnstartActionPerformed
        // TODO add your handling code here:
        chatarea.setText("Server started at port - 1999 \n");
        AcceptMultipleClient oobj = new AcceptMultipleClient();
        oobj.start();
        btnstart.setEnabled(false);
        btnStop.setEnabled(true);


    }//GEN-LAST:event_btnstartActionPerformed

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed
        try {
            // TODO add your handling code here:
            if (s == null) {
                server.close();
            } else {

            }

        } catch (IOException ex) {
            Logger.getLogger(Serverside.class.getName()).log(Level.SEVERE, null, ex);
        }
        btnstart.setEnabled(true);
        btnStop.setEnabled(false);
        dispose();
        JOptionPane.showMessageDialog(null, "Sucessfully disconnected from server");
    }//GEN-LAST:event_btnStopActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            sendLogsToDb();
            FileWriter fw = null;
            fw = new FileWriter("E:\\chatlogs.txt");
            fw.write("");
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(Serverside.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(Serverside.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Serverside.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Serverside.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Serverside.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Serverside().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStop;
    private javax.swing.JButton btnstart;
    private javax.swing.JTextArea chatarea;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    class ChatHandlerThread extends Thread {

        Socket sock;
        Scanner cl;
        PrintWriter pw;
        String name;

        ChatHandlerThread(Socket sock) {
            this.sock = sock;
        }

        public void run() {

            try {
                pw = new PrintWriter(sock.getOutputStream(), true);
                cl = new Scanner(sock.getInputStream());
                while (true) {
                    pw.println("NAMEREQUIRED");
                    name = cl.nextLine();
                    if (!ChatClientDao.findClient(name)) {
                        pw.println("NAMEALREADYEXISTS");
                        pass = cl.nextLine();
                        System.out.println(pass);
                        pw.println("NAMEACCEPTED" + name);
                        ChatClient clo = new ChatClient(name, pass);
                        ChatClientDao.addClient(clo);
                        break;
                    } else {
                        while (true) {
                            pw.println("NAMEALREADYEXISTS");
                            pass = cl.nextLine();
                            if (ChatClientDao.checkPassword(name, pass)) {
                                pw.println("NAMEACCEPTED" + name);
                                break;
                            }
                        }
                    }
                    break;
                }
                chatarea.append("CONNECTED CLIENTS....\n");
                chatarea.append(name + "from " + sock.getInetAddress() + "\n");
                printWriters.add(pw);
            } catch (IOException ex) {
                ex.printStackTrace();
                return;
            } catch (SQLException ex) {
                Logger.getLogger(Serverside.class.getName()).log(Level.SEVERE, null, ex);
            }
            Random r = new Random();

            while (true) {
                String message = cl.nextLine();
                if (message.equalsIgnoreCase("quit")) {
                    pw.println("quit");
                    for (PrintWriter p : printWriters) {
                        if (!p.equals(pw)) {
                            p.println(name + ":" + "is quitting");
                        }
                    }
                    try {
                        sock.close();
                        printWriters.remove(pw);
                        pwf.close();
                        return;
                    } catch (IOException ex) {
                        Logger.getLogger(Serverside.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                synchronized (pwf) {
                    String timestamp = sdf.format(new java.util.Date());
                    pwf.println(name + "@@" + message + "@@" + timestamp);
                }
                for (PrintWriter p : printWriters) {
                    if (!p.equals(pw)) {
                        p.println(name + ":" + message);
                    }
                }

            }
        }
    }

    class AcceptMultipleClient extends Thread {

        public void run() {
            try {
                server = new ServerSocket(1999);//backlog ----no. of requests a server can accept (default-50);
                while (true) {
                    s = server.accept();
                    ChatHandlerThread obj = new ChatHandlerThread(s);
                    obj.start();
                }
            } catch (IOException ex) {
                Logger.getLogger(Serverside.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void sendLogsToDb() {
        FileReader fr = null;
        try {
            fr = new FileReader("E:\\chatlogs.txt");
            BufferedReader bf = new BufferedReader(fr);
            StringTokenizer stz = null;
            String txt;
            while ((txt = bf.readLine()) != null) {
                stz = new StringTokenizer(txt,"@@");
                String uname = stz.nextToken();
                String msg = stz.nextToken();
                String time = stz.nextToken();
                System.out.println(msg + uname + time);
                Chatlog objj = new Chatlog(uname, msg, time);
                ChatlogDao.addChatLog(objj);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Serverside.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Serverside.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Serverside.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(Serverside.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}