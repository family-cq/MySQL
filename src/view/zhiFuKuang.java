package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static config.Config.*;
public class zhiFuKuang extends Frame {

    /**
     *  捐助界面
     *  调用二维码图片
     * */

    public zhiFuKuang(int index,Frame main){
        setIconImage(logo.getImage());
        setLayout(null);
        setBounds(1,1,600,800);
        setResizable(false);
        setLocationRelativeTo(null);
        JLabel jl_bg = new JLabel();
        jl_bg.setBounds(0,30,600,790);
        add(jl_bg);

        switch (index){
            case 0:     //微信
                setTitle("微信支付");
                wxf.setImage(wxf.getImage().getScaledInstance(600,790,Image.SCALE_DEFAULT));
                jl_bg.setIcon(wxf);
                break;
            case 1:     //支付宝
                setTitle("支付宝支付");
                zfbf.setImage(zfbf.getImage().getScaledInstance(600,790,Image.SCALE_DEFAULT));
                jl_bg.setIcon(zfbf);
                break;
            default:
                break;
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                main.setEnabled(true);
                zhiFuKuang.this.dispose();
            }
        });
    }
}
