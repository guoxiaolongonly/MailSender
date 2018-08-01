import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.*;


public class MailUI  {

    private final static String HINT_ACCOUNT_INPUT = "请输入邮箱帐号";
    private final static String HINT_PASSWORD_INPUT = "请输入邮箱密码或者SMTP授权码";
    private final static String HINT_RECEIVE_INPUT = "请输入收件人邮件地址，如果不止一个，用分号;隔开";
    private final static String HINT_MAIL_TITLE = "输入邮件主题";
    private final static String HINT_MAIL_DATE = "设置发送时间如：2018-08-01 14:08:57";
    private final static String HINT_MAIL_CONTENT = "邮件具体内容，请复制QQ邮箱上的html格式内容，详情咨询柯柯";

    private JFrame mFrameLayout;
    //发件人输入框
    private JTextField tfUserAccount;
    //发件人密码
    private JTextField tfUserPassword;


    //收件人输入框
    private JTextField tfToAddress;
    //邮件标题
    private JTextField tfMailTitle;
    //内容文本
    private JTextArea taMailContent;

    //发送时间
    private JTextField tfMailTime;


    //文本提示
    //帐号
    private JLabel lAccount;
    //密码
    private JLabel lPassword;
    //收件人
    private JLabel lToAddress;
    //主题
    private JLabel lSubject;
    //邮件内容
    private JLabel lContent;
    //发送时间
    private JLabel lSendTime;

    //发送按钮
    private JButton btnSend;
    //配置按钮
    private JButton btnSetting;

    //	//用来承载单选框的panel为了选择发送方式
//	private JPanel panelRecipientType;
    private ConfigInfo mConfigInfo;
    private ConfigDialog.OnSubmitListener mOnSubmitListener;


    public MailUI() {

        initView();
        initData();
        setListener();
    }

    private void initData() {
        mConfigInfo = FileUtil.getConfig();
        if (mConfigInfo == null) {
            mConfigInfo = new ConfigInfo();
            mConfigInfo.auth = "mail.smtp.auth";
            mConfigInfo.server = "mail.smtp.host";
            mConfigInfo.mailSendHost = "smtp.qq.com";
        }
        if (!"".equals(mConfigInfo.account)) {
            tfUserAccount.setText("");
        }
        if (!"".equals(mConfigInfo.password)) {
            tfUserPassword.setText("");
        }
    }

    private void setListener() {

        mOnSubmitListener = configInfo -> {
            FileUtil.writeFile(configInfo);
        };

        btnSend.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mConfigInfo.account = tfUserAccount.getText();
                mConfigInfo.password = tfUserPassword.getText();
                String b = MailTranslator.setMail(mConfigInfo, tfToAddress.getText(), tfMailTitle.getText(), tfMailTime.getText(), taMailContent.getText());
                if ("true".equals(b)) {
                    // 消息对话框无返回, 仅做通知作用
                    JOptionPane.showMessageDialog(
                            mFrameLayout,
                            "发送成功！",
                            "温馨提示：",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    FileUtil.writeFile(mConfigInfo);
                }else
                {
                    JOptionPane.showMessageDialog(
                            mFrameLayout,
                            b,
                            "错误提示：",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        btnSetting.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showConfigDialog(mFrameLayout, mConfigInfo, mOnSubmitListener);
            }
        });


        setHint(tfUserAccount, HINT_ACCOUNT_INPUT);
        setHint(tfUserPassword, HINT_PASSWORD_INPUT);
        setHint(tfToAddress, HINT_RECEIVE_INPUT);
        setHint(tfMailTime, HINT_MAIL_DATE);
        setHint(tfMailTitle, HINT_MAIL_TITLE);
        setHint(taMailContent, HINT_MAIL_CONTENT);
    }

    public void setHint(JTextComponent jTextComponent, String hintText) {
        jTextComponent.setText(hintText);
        jTextComponent.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (hintText.equals(jTextComponent.getText())) {
                    jTextComponent.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if ("".equals(jTextComponent.getText())) {
                    jTextComponent.setText(hintText);
                }
            }
        });
    }

    public void showConfigDialog(JFrame parentFrame, ConfigInfo configInfo, ConfigDialog.OnSubmitListener onSubmitListener) {
        ConfigDialog.showDialog(parentFrame, configInfo, onSubmitListener);
    }

    private void initView() {
        mFrameLayout = new JFrame();
        mFrameLayout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mFrameLayout.setTitle("keke Mail Sender");
        lAccount = new JLabel("帐号:");
        lAccount.setBounds(5, 10, 40, 30);
        tfUserAccount = new JTextField();
        tfUserAccount.setBounds(70, 10, 200, 30);
        mFrameLayout.add(lAccount);
        mFrameLayout.add(tfUserAccount);


        lPassword = new JLabel();
        lPassword.setText("密码：");
        lPassword.setBounds(285, 10, 40, 30);
        tfUserPassword = new JTextField();
        tfUserPassword.setBounds(330, 10, 200, 30);
        mFrameLayout.add(lPassword);
        mFrameLayout.add(tfUserPassword);


        lToAddress = new JLabel();
        lToAddress.setText("收件人：");
        lToAddress.setBounds(5, 50, 60, 30);
        tfToAddress = new JTextField();
        tfToAddress.setBounds(70, 50, 500, 30);
        mFrameLayout.add(lToAddress);
        mFrameLayout.add(tfToAddress);


        lSubject = new JLabel();
        lSubject.setText("主题：");
        lSubject.setBounds(5, 90, 40, 30);

        tfMailTitle = new JTextField();

        tfMailTitle.setBounds(70, 90, 500, 30);
        mFrameLayout.add(lSubject);
        mFrameLayout.add(tfMailTitle);


        lSendTime = new JLabel();
        lSendTime.setText("发送时间：");
        lSendTime.setBounds(5, 130, 80, 30);

        tfMailTime = new JTextField();
        tfMailTime.setBounds(70, 130, 500, 30);
        mFrameLayout.add(lSendTime);
        mFrameLayout.add(tfMailTime);


        lContent = new JLabel();
        lContent.setText("正文：");
        lContent.setBounds(5, 170, 40, 30);
        taMailContent = new JTextArea();
        JScrollPane jScrollPane = new JScrollPane(taMailContent);
        jScrollPane.setBounds(70, 170, 500, 380);
        mFrameLayout.add(lContent);
        mFrameLayout.add(jScrollPane);


        btnSend = new JButton("发送");
        btnSend.setBounds(200, 560, 140, 40);
        mFrameLayout.add(btnSend);

        btnSetting = new JButton("配置");
        btnSetting.setBounds(480, 580, 80, 40);
        mFrameLayout.add(btnSetting);
        mFrameLayout.setLayout(null);
        mFrameLayout.setSize(600, 680);
        mFrameLayout.setVisible(true);
    }


    public static void main(String a[]) {
        MailUI mail = new MailUI();
    }

}