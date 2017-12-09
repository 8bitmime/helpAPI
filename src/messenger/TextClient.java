//package messenger;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//import java.net.InetAddress;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JScrollPane;
//import javax.swing.JTextArea;
//import javax.swing.JTextField;
//
//public class TextClient implements MessageProcessor {
//
//    private SipLayer sipLayer;
//
//    private JTextField fromAddress;
//    private JLabel fromLbl;
//    private JLabel receivedLbl;
//    private JTextArea receivedMessages;
//    private JScrollPane receivedScrollPane;
//    private JButton sendBtn;
//    private JLabel sendLbl;
//    private JTextField sendMessages;
//    private JTextField toAddress;
//    private JLabel toLbl;
//
//    String addr;
//
//    //should be main, change it to run <or literally anything else>
//    public static void startIM(String uname, int portNum)
//    {
//        /*if(args.length != 2) {
//            printUsage();
//            System.exit(-1);
//        }*/
//
//		try
//        {
//		    String username = uname;
//		    int port = portNum;
//		    String ip = InetAddress.getLocalHost().getHostAddress();
//
//			SipLayer sipLayer = new SipLayer(username, ip, port);
//		    TextClient tc = new TextClient(sipLayer);
//
//		    sipLayer.setMessageProcessor(tc);
//
//			tc.show();
//        } catch (Throwable e)
//        {
//            System.out.println("Problem initializing the SIP stack.");
//            e.printStackTrace();
//            System.exit(-1);
//        }
//    }
//
//    public static void boot(String uname, int portNum){
//        try
//        {
//            String username = uname;
//            int port = portNum;
//            String ip = InetAddress.getLocalHost().getHostAddress();
//
//            SipLayer sipLayer = new SipLayer(username, ip, port);
//            TextClient tc = new TextClient(sipLayer);
//
//
//            //System.out.println("boot from " + addr);
//
//            sipLayer.setMessageProcessor(tc);
//
//        } catch (Throwable e)
//        {
//            System.out.println("Problem initializing the SIP stack.");
//            e.printStackTrace();
//            System.exit(-1);
//        }
//    }
//
//    private static void printUsage()
//    {
//        System.out.println("Syntax:");
//        System.out.println("  java -jar textclient.jar <username> <port>");
//        System.out.println("where <username> is the nickname of this user");
//        System.out.println("and <port> is the port number to use. Usually 5060 if not used by another process.");
//        System.out.println("Example:");
//        System.out.println("  java -jar textclient.jar snoopy71 5061");
//    }
//
//    public TextClient(SipLayer sip)
//    {
//        super();
//        sipLayer = sip;
//        initWindow();
//        String from = "sip:" + sip.getUsername() + "@" + sip.getHost() + ":" + sip.getPort();
//        System.out.println("textClient From = " + from);
//        //addr = from;
//        this.fromAddress.setText(from);
//    }
//
//    public TextClient(){
//        System.out.println("we out here bois");
//    }
//
//    public String getAddr(){
//        return this.addr;
//    }
//
//    private void initWindow() {
//        receivedLbl = new JLabel();
//        sendLbl = new JLabel();
//        sendMessages = new JTextField();
//        receivedScrollPane = new JScrollPane();
//        receivedMessages = new JTextArea();
//        fromLbl = new JLabel();
//        fromAddress = new JTextField();
//        toLbl = new JLabel();
//        toAddress = new JTextField();
//        sendBtn = new JButton();
//
//        getContentPane().setLayout(null);
//
//        setTitle("TextClient");
//        addWindowListener(new WindowAdapter() {
//            public void windowClosing(WindowEvent evt) {
//                //System.exit(0);
//            }
//        });
//
//        receivedLbl.setText("Received Messages:");
//        receivedLbl.setAlignmentY(0.0F);
//        receivedLbl.setPreferredSize(new java.awt.Dimension(25, 100));
//        getContentPane().add(receivedLbl);
//        receivedLbl.setBounds(5, 0, 136, 20);
//
//        sendLbl.setText("Send Message:");
//        getContentPane().add(sendLbl);
//        sendLbl.setBounds(5, 150, 90, 20);
//
//        getContentPane().add(sendMessages);
//        sendMessages.setBounds(5, 170, 270, 20);
//
//        receivedMessages.setAlignmentX(0.0F);
//        receivedMessages.setEditable(false);
//        receivedMessages.setLineWrap(true);
//        receivedMessages.setWrapStyleWord(true);
//        receivedScrollPane.setViewportView(receivedMessages);
//        receivedScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//
//        getContentPane().add(receivedScrollPane);
//        receivedScrollPane.setBounds(5, 20, 270, 130);
//
//        fromLbl.setText("From:");
//        getContentPane().add(fromLbl);
//        fromLbl.setBounds(5, 200, 35, 15);
//
//        getContentPane().add(fromAddress);
//        fromAddress.setBounds(40, 200, 235, 20);
//        fromAddress.setEditable(false);
//
//        toLbl.setText("To:");
//        getContentPane().add(toLbl);
//        toLbl.setBounds(5, 225, 35, 15);
//
//        getContentPane().add(toAddress);
//        toAddress.setBounds(40, 225, 235, 21);
//
//        sendBtn.setText("Send");
//        sendBtn.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent evt) {
//                sendBtnActionPerformed(evt);
//            }
//        });
//
//        getContentPane().add(sendBtn);
//        sendBtn.setBounds(200, 255, 75, 25);
//
//        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
//        setBounds((screenSize.width-288)/2, (screenSize.height-310)/2, 288, 320);
//    }
//
//    private void sendBtnActionPerformed(ActionEvent evt) {
//
//        try
//        {
//            String to = this.toAddress.getText();
//            String message = this.sendMessages.getText();
//            sipLayer.sendMessage(to, message);
//        } catch (Throwable e)
//        {
//            e.printStackTrace();
//            this.receivedMessages.append("ERROR sending message: " + e.getMessage() + "\n");
//        }
//
//    }
//
//    public void processMessage(String sender, String message)
//    {
//        this.receivedMessages.append("From " +
//                sender + ": " + message + "\n");
//    }
//
//    public void processError(String errorMessage)
//    {
//        this.receivedMessages.append("ERROR: " +
//                errorMessage + "\n");
//    }
//
//    public void processInfo(String infoMessage)
//    {
//        this.receivedMessages.append(
//                infoMessage + "\n");
//    }
//
//    public SipLayer getSipLayer() {
//        return sipLayer;
//    }
//
//    public void sendMsg(String toStr, String msgStr){
//        try
//        {
//            String to = toStr;
//            String message = msgStr;
//            sipLayer.sendMessage(to, message);
//        } catch (Throwable e)
//        {
//            e.printStackTrace();
//            this.receivedMessages.append("ERROR sending message: " + e.getMessage() + "\n");
//        }
//    }
//}
