import javax.swing.*;
import java.awt.*;

public class ConfigDialog extends JDialog {
    private static final long serialVersionUID = 1L;

    private JTextField tfMailAuthHost;
    private JTextField tfMailSendHost;
    private JTextField tfMailServerHost;

    private JButton okButton;
    private JButton cancelButton;
    private OnSubmitListener mOnSubmitListener;

    private ConfigDialog() {
        super();
        init();
        setModal(true);
        setSize(400, 300);
        setTitle("配置");
    }

    private void init() {
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        JLabel lMailSendHost=new JLabel("发送主机：");
        lMailSendHost.setBounds(5, 10, 80, 30);
        tfMailSendHost = new JTextField();
        tfMailSendHost.setBounds(80, 10, 200, 30);

        JLabel lMainServerHost=new JLabel("邮件服务：");
        lMainServerHost.setBounds(5, 50, 80, 30);
        tfMailServerHost = new JTextField();
        tfMailServerHost.setBounds(80, 50, 200, 30);

        JLabel lMailAuthHost=new JLabel("邮件授权：");
        lMailAuthHost.setBounds(5, 90, 80, 30);
        tfMailAuthHost = new JTextField();
        tfMailAuthHost.setBounds(80, 90, 200, 30);

        c.add(jPanel);
        jPanel.add(lMailSendHost);
        jPanel.add(tfMailSendHost);
        jPanel.add(lMainServerHost);
        jPanel.add(tfMailServerHost);
        jPanel.add(lMailAuthHost);
        jPanel.add(tfMailAuthHost);
        JPanel p = new JPanel();
        FlowLayout fl = new FlowLayout();
        fl.setHgap(15);
        p.setLayout(fl);
        okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            ConfigInfo configInfo = new ConfigInfo();
            configInfo.auth = tfMailAuthHost.getText();
            configInfo.mailSendHost = tfMailSendHost.getText();
            configInfo.server = tfMailServerHost.getText();
            if(mOnSubmitListener!=null)
            {
                mOnSubmitListener.onSubmit(configInfo);
            }
            dispose();
        });

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());
        p.add(okButton);
        p.add(cancelButton);
        c.add(p, BorderLayout.SOUTH);
    }

//    public void setOnSubmitListener(OnSubmitListener onSubmitListener) {
//        this.mOnSubmitListener = onSubmitListener;
//    }

    public static JDialog showDialog(Component relativeTo, ConfigInfo configInfo,OnSubmitListener onSubmitListener) {
        ConfigDialog d = new ConfigDialog();
        d.tfMailAuthHost.setText(configInfo.auth);
        d.tfMailSendHost.setText(configInfo.mailSendHost);
        d.tfMailServerHost.setText(configInfo.server);
        d.mOnSubmitListener=onSubmitListener;
        d.setLocationRelativeTo(relativeTo);
        d.setVisible(true);
        return d;
    }

    public interface OnSubmitListener {
        void onSubmit(ConfigInfo configInfo);
    }

}